package com.webservice.app.wservice.service.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webservice.app.wservice.io.models.BookCategory;
import com.webservice.app.wservice.io.repository.BookCategoryRepository;
import com.webservice.app.wservice.service.BookCategoryService;
import com.webservice.app.wservice.shared.dto.BookCategoryDTO;
import com.webservice.app.wservice.shared.utils.Utils;

@Service
public class BookCategoryServiceResource implements BookCategoryService{

	@Autowired
	BookCategoryRepository bookCategoryRepository;
	
	public BookCategoryDTO createBookCategory(BookCategoryDTO createValue) {
		
		if(bookCategoryRepository.findByCategoryName(createValue.getCategoryName()) != null) 
			throw new RuntimeException("Record already exists");
		
		Utils generateBookCategoryId = new Utils();
		createValue.setBookCategoryId(generateBookCategoryId.generationBookCategoryId(30));
		
		ModelMapper mapper = new ModelMapper();
		BookCategory bookCategory = mapper.map(createValue, BookCategory.class);
		bookCategory = bookCategoryRepository.save(bookCategory);
		BookCategoryDTO returnValue = mapper.map(bookCategory, BookCategoryDTO.class);
		return returnValue;
	}

	public BookCategoryDTO getBookCategoryById(String bookCategoryId) {
		
		BookCategory bookCategory = bookCategoryRepository.findByBookCategoryId(bookCategoryId);
		if(bookCategory == null)throw new RuntimeException("Record not found!");
		
		ModelMapper mapper = new ModelMapper();
		BookCategoryDTO returnValue = mapper.map(bookCategory, BookCategoryDTO.class);
		return returnValue;
	}

	public BookCategoryDTO getBookCategoryByName(String bookCategoryName) {
		BookCategory bookCategory = bookCategoryRepository.findByCategoryName(bookCategoryName);
		if(bookCategory == null)
			return null;
		
		ModelMapper mapper = new ModelMapper();
		BookCategoryDTO returnValue = mapper.map(bookCategory, BookCategoryDTO.class);
		return returnValue;
	}

	public BookCategoryDTO updateBookCategory(BookCategoryDTO updateValue, String bookCategoryId) {
		BookCategory bookCategory = bookCategoryRepository.findByBookCategoryId(bookCategoryId);
		if(bookCategory == null)throw new RuntimeException("Record not found!");
		
		bookCategory.setCategoryName(updateValue.getCategoryName());
		bookCategory = bookCategoryRepository.save(bookCategory);
		
		ModelMapper mapper = new ModelMapper();
		BookCategoryDTO returnValue = mapper.map(bookCategory, BookCategoryDTO.class);
		return returnValue;
	}

	public List<BookCategoryDTO> getBookCategory(int page, int limit) {
		
		if(page > 0) page = page - 1;
		
		Pageable pageable = PageRequest.of(page, limit);
		Page<BookCategory> bookCategoryPage = bookCategoryRepository.findAll(pageable);
		List<BookCategory> bookCategories = bookCategoryPage.getContent();
		 
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<BookCategoryDTO>>() {}.getType();
		List<BookCategoryDTO> returnValue = mapper.map(bookCategories,listType);
		
		return returnValue;
	}

	public void deleteBookCategory(String bookCategoryId) {
		BookCategory deleteValue = bookCategoryRepository.findByBookCategoryId(bookCategoryId);
		
		if(deleteValue == null)throw new RuntimeException("Record not found!");
		
		bookCategoryRepository.delete(deleteValue);
	}
}