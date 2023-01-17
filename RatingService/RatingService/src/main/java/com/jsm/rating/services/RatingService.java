package com.jsm.rating.services;

import com.jsm.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    // create rating
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getAllRatings();

    // get all ratings by userId
    List<Rating> getRatingByUserId(String userId);

    // get all ratings by hotelId
    List<Rating> getRatingByHotelId(String hotelId);
}
