package com.webservice.app.wservice.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservice.app.wservice.io.models.Book;
import com.webservice.app.wservice.io.repository.BookRepository;
import com.webservice.app.wservice.service.BookCategoryService;
import com.webservice.app.wservice.shared.dto.BookCategoryDTO;
import com.webservice.app.wservice.shared.dto.BookDTO;
import com.webservice.app.wservice.shared.utils.Utils;

@Service
public class BookServiceResource {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookCategoryService bookCategoryService;
	
	public BookDTO createBook(BookDTO createValue) {
		
		BookCategoryDTO bookCategoryDTO = bookCategoryService.getBookCategoryByName(createValue.getCategory().getCategoryName());
		
		if(bookCategoryDTO == null)
			bookCategoryDTO = bookCategoryService.createBookCategory(createValue.getCategory());

		createValue.setCategory(bookCategoryDTO);
		
		Utils generateBookId = new Utils();
		createValue.setBookId(generateBookId.generationBookId(30));
		createValue.setCreateOn(LocalDateTime.now());
		ModelMapper mapper = new ModelMapper();
		Book book = mapper.map(createValue, Book.class);
		book = bookRepository.save(book);
		BookDTO returnValue = mapper.map(book, BookDTO.class);
		return returnValue;
	}

	public BookDTO getBookById(String bookId) {
		
		Book book = bookRepository.findByBookId(bookId);
		if(book == null)throw new RuntimeException("Record not found!");
		
		ModelMapper mapper = new ModelMapper();
		BookDTO returnValue = mapper.map(book, BookDTO.class);
		return returnValue;
	}//840661905

}
