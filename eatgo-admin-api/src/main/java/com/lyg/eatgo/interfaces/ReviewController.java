package com.lyg.eatgo.interfaces;

import com.lyg.eatgo.application.ReviewService;
import com.lyg.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

   @GetMapping("/reviews")
    public List<Review> list() {
       List<Review> reviews = reviewService.getReviews();

       return reviews;
   }
}
