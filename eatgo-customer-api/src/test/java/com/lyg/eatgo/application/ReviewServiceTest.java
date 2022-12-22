package com.lyg.eatgo.application;

import com.lyg.eatgo.application.ReviewService;
import com.lyg.eatgo.domain.Review;
import com.lyg.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
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
    public void addReview() {
        Review review = Review.builder()
                .name("spiderman")
                .score(3)
                .description("good")
                .build();

        reviewService.addReview(1004L, review);

        verify(reViewRepository).save(any());
    }
}