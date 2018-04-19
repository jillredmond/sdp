package com.paddysAssignment.four.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private double payment;
    private String date;
    private String bookTitle;
    
    public Purchase() {
    	super();
    }
    
    
    
    
	public Purchase(Long id, double payment, String date, String bookTitle) {
		super();
		this.id = id;
		this.payment = payment;
		this.date = date;
		this.bookTitle = bookTitle;
	}




	public String getBookTitle() {
		return bookTitle;
	}




	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}




	public Purchase(Long id, double payment, String date) {
		super();
		this.id = id;
		this.payment = payment;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
    
    
    
}
