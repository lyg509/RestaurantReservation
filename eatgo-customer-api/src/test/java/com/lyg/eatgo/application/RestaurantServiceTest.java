package com.lyg.eatgo.application;

import com.lyg.eatgo.application.RestaurantService;
import com.lyg.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(
                restaurantRepository, menuItemRepository, reviewRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
    }

    private void mockMenuItemRepository() {

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().
                name("Kimchi")
                .build());

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                        .name("BeRyong")
                        .score(1)
                        .description("bad")
                        .build());

        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(reviews);
    }



    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }

  /*  @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));

        Review review = restaurant.getReviews().get(0);

        assertThat(review.getDescription(), is("bad"));
    }*/

    @Test
    public void getRestaurantWithNotExisted() {
        assertThatThrownBy(()-> {
            restaurantService.getRestaurant(404L);
        }).isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return  restaurant;
        });

        Restaurant  restaurant= Restaurant.builder()
                .name("Beryong")
                .address("Busan")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(),is(1234L));
    }

    //update
    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L, "TestName","Test Address");

        assertThat(restaurant.getName(), is("TestName"));
        assertThat(restaurant.getAddress(), is("Test Address"));
    }
}