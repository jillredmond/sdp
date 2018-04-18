package com.paddysAssignment.four.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paddysAssignment.four.model.Rating;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
