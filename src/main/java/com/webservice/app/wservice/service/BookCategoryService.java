package com.webservice.app.wservice.service;

import java.util.List;

import com.webservice.app.wservice.shared.dto.BookCategoryDTO;

public interface BookCategoryService {

	void deleteBookCategory(String bookCategoryId);
	List<BookCategoryDTO> getBookCategory(int page, int limit);
	BookCategoryDTO getBookCategoryById(String bookCategoryId);
	BookCategoryDTO getBookCategoryByName(String bookCategoryName);
	BookCategoryDTO createBookCategory(BookCategoryDTO createValue);
	BookCategoryDTO updateBookCategory(BookCategoryDTO updateValue, String bookCategoryId);

}
