package com.paddysAssignment.four.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paddysAssignment.four.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}