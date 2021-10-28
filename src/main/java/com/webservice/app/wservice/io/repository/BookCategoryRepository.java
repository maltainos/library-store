package com.webservice.app.wservice.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webservice.app.wservice.io.models.BookCategory;

@Repository
public interface BookCategoryRepository extends PagingAndSortingRepository<BookCategory, Long> {
	
	BookCategory findByCategoryName(String categoryName);
	BookCategory findByBookCategoryId(String bookCategoryId);
}
