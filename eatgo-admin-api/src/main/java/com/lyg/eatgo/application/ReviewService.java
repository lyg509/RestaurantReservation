package com.lyg.eatgo.application;

import com.lyg.eatgo.domain.ReviewRepository;
import com.lyg.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reViewRepository) {
        this.reviewRepository = reViewRepository;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
