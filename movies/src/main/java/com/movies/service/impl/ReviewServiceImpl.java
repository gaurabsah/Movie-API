package com.movies.service.impl;

import com.movies.dto.ReviewDTO;
import com.movies.entity.Movie;
import com.movies.entity.Review;
import com.movies.repository.ReviewRepository;
import com.movies.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ReviewDTO createReview(String reviewBody, String imdbId) {
        Review review = Review.builder()
                .body(reviewBody)
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();
        Review newReview = reviewRepository.save(review);

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviews").value(review))
                .first();

        ReviewDTO reviewDTO = mapper.map(newReview, ReviewDTO.class);
        logger.info("Review created: {}", reviewDTO.getId());
        return reviewDTO;
    }
}
