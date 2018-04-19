package com.paddysAssignment.four.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paddysAssignment.four.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	
	
	Book findById(long id);
	Book findByTitle(String title);
//	Book findOne(Long bookId);
//	Book findOne(long id);
//	Book findByBookTitle(String bookTitle);

}