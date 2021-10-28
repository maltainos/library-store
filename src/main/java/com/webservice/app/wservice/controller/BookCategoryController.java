package com.webservice.app.wservice.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.app.wservice.service.impl.BookCategoryServiceResource;
import com.webservice.app.wservice.shared.dto.BookCategoryDTO;
import com.webservice.app.wservice.ui.request.BookCategoryRequestModel;
import com.webservice.app.wservice.ui.response.BookCategoryRest;
import com.webservice.app.wservice.ui.response.RequestOperationName;
import com.webservice.app.wservice.ui.response.RequestOperationStatus;
import com.webservice.app.wservice.ui.response.RequestStatusModel;

@RestController
@RequestMapping(path = "categories")
@CrossOrigin("http://localhost:4200")
public class BookCategoryController {
	
	@Autowired
	BookCategoryServiceResource bookCategoryService;
	
	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public List<BookCategoryRest> getBooksCategories(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25")int limit){
		
		List<BookCategoryDTO> bookCategoriesDTO = bookCategoryService.getBookCategory(page, limit);
		
		ModelMapper mapper = new ModelMapper();
		Type listTyped = new TypeToken<List<BookCategoryRest>>() {}.getType();
		List<BookCategoryRest> returnValue = mapper.map(bookCategoriesDTO, listTyped);
		
		return returnValue;
	}
	
	@GetMapping(path = "/{bookCategoryId}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public BookCategoryRest getBookCategory(@PathVariable String bookCategoryId){
		BookCategoryDTO bookCategoryDTO = bookCategoryService.getBookCategoryById(bookCategoryId);
		ModelMapper mapper = new ModelMapper();
		BookCategoryRest returnValue = mapper.map(bookCategoryDTO, BookCategoryRest.class);
		return returnValue;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public BookCategoryRest createBookCategory(@RequestBody BookCategoryRequestModel bookCategoryRequest) {
		
		ModelMapper mapper = new ModelMapper();
		BookCategoryDTO createValue = mapper.map(bookCategoryRequest, BookCategoryDTO.class);
		
		createValue = bookCategoryService.createBookCategory(createValue);
		BookCategoryRest returnValue = mapper.map(createValue, BookCategoryRest.class);
		return returnValue;
	}
	
	@PutMapping(path = "/{bookCategoryId}", 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public BookCategoryRest updateBookCategory(@PathVariable String bookCategoryId,
			@RequestBody BookCategoryRequestModel bookCategoryRequest) {
		ModelMapper mapper = new ModelMapper();
		BookCategoryDTO updateValue = mapper.map(bookCategoryRequest, BookCategoryDTO.class);
		
		updateValue = bookCategoryService.updateBookCategory(updateValue, bookCategoryId);
		BookCategoryRest returnValue = mapper.map(updateValue, BookCategoryRest.class);
		return returnValue;	
	}
	
	@DeleteMapping(path = "/{bookCategoryId}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public RequestStatusModel deleteBookCategory(@PathVariable String bookCategoryId) {
		
		RequestStatusModel requestStatusModel = new RequestStatusModel();
		requestStatusModel.setRequestOperationName(RequestOperationName.DELETE.getOperationName());
		
		bookCategoryService.deleteBookCategory(bookCategoryId);
		
		requestStatusModel.setRequestOperationStatus(RequestOperationStatus.SUCCESS.getStatusModel());
		
		return requestStatusModel;
	}
}