package com.lyg.eatgo.domain;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RestaurantTest {

    @Test
    public void creation() {
        //객체 확인
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getId(),is(1004L));
        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }


    @Test
    public void information() {
        Restaurant restaurant =
                Restaurant.builder()
                        .id(1004L)
                        .name("Bob zip")
                        .address("Seoul")
                        .build();
        //set test
        //restaurant.setName("Test Zip");

        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }

}