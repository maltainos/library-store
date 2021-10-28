package com.webservice.app.wservice.service;

import com.webservice.app.wservice.shared.dto.BookDTO;

public interface BookService {
	BookDTO getBook(String bookId);
	BookDTO createBook(BookDTO createValue);
}
