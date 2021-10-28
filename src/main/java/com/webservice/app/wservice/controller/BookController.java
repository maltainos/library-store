package com.webservice.app.wservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.app.wservice.service.impl.BookServiceResource;
import com.webservice.app.wservice.shared.dto.BookDTO;
import com.webservice.app.wservice.ui.request.BookRequestModel;
import com.webservice.app.wservice.ui.response.BookRest;

@RestController
@RequestMapping(path = "books")
public class BookController {
	
	@Autowired
	BookServiceResource bookService;
	
	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public List<BookRest> getBooks(){
		//BookCategoryDTO bookCategoryDTO = bookCategoryService.getBookCategory(bookCategoryId);
		//ModelMapper mapper = new ModelMapper();
		//BookCategoryRest returnValue = mapper.map(bookCategoryDTO, BookCategoryRest.class);
		return new ArrayList<BookRest>();
	}
	
	@GetMapping(path = "/{bookId}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public BookRest getBookCategory(@PathVariable String bookId){
		BookDTO bookDTO = bookService.getBookById(bookId);
		ModelMapper mapper = new ModelMapper();
		BookRest returnValue = mapper.map(bookDTO, BookRest.class);
		return returnValue;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public BookRest createBook(@RequestBody BookRequestModel bookRequest) {
		
		ModelMapper mapper = new ModelMapper();
		BookDTO createValue = mapper.map(bookRequest, BookDTO.class);
		
		createValue = bookService.createBook(createValue);
		BookRest returnValue = mapper.map(createValue, BookRest.class);
		return returnValue;
	}


}




















