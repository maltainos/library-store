package com.webservice.app.wservice.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webservice.app.wservice.io.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByBookId(String bookId);

}
