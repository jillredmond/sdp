package com.paddysAssignment.four.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class Rating {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
		private String comment;
		private double rating;
		
		public Rating() {
			super();
		}
		
		public Rating(Long id, double rating, String comment) {
			super();
			this.id = id;
			this.rating = rating;
			this.comment = comment;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public double getRating() {
			return rating;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
		
		

}
