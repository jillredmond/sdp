package com.paddysAssignment.four.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private Long bookId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Item() {
		super();
	}
	
	
	public Item(Long id, Long bookId) {
		super();
		this.id = id;
		this.bookId = bookId;
	}
	
	

}
