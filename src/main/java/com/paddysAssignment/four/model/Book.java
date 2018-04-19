package com.paddysAssignment.four.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class Book implements Serializable {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	 	
	 	private String author;
	 	private String title;
	 	private String category;
	 	private int stock;
	 	private double price;
	
	 	
	    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	    public List<Purchase> purchase;
	    
	    @OneToMany(fetch=FetchType.LAZY)
	    public List<Rating> rating;

	 	public Book() {
	 		super();		
	 	}
	
		public Book(Long id, String author, String title, String category, int stock, double price, 
				List<Purchase> purchase, List<Rating> rating) {
			super();
			this.id = id;
			this.author = author;
			this.title = title;
			this.category = category;
			this.stock = stock;
			this.price = price;

			this.purchase = purchase;
			this.rating = rating;
		}


		public Book(Long id, String author, String title, String category, double price) {
			super();
			this.id = id;
			this.author = author;
			this.title = title;
			this.category = category;
			this.price = price;	
		}
		
		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public List<Purchase> getPurchase() {
			return purchase;
		}

		public void setPurchase(List<Purchase> purchase) {
			this.purchase = purchase;
		}

		public List<Rating> getRating() {
			return rating;
		}

		public void setRating(List<Rating> rating) {
			this.rating = rating;
		}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		

		public void addRating(Rating rating){

			getRating().add(rating);
		}
		public void addPurchase(Purchase purchase){

			getPurchase().add(purchase);
		}

}
