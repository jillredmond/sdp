package com.paddysAssignment.four.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paddysAssignment.four.model.Book;
import com.paddysAssignment.four.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
