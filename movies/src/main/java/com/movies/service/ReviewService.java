package com.movies.service;

import com.movies.dto.ReviewDTO;

public interface ReviewService {

    ReviewDTO createReview(String reviewBody, String imdbId);
}
