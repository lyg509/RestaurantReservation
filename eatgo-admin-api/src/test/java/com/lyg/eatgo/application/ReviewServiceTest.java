package com.lyg.eatgo.application;

import com.lyg.eatgo.domain.ReviewRepository;
import com.lyg.eatgo.domain.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reViewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reViewRepository);
    }

    @Test
    public void getReviews() {
        List<Review> MockReviews = new ArrayList<>();
        MockReviews.add(Review.builder().description("Cool!").build());

        given(reViewRepository.findAll()).willReturn(MockReviews);

        List<Review> reviews = reviewService.getReviews();

        Review review  = reviews.get(0);

        assertThat(review.getDescription(), is("Cool!"));
    }

}