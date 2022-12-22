package com.lyg.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RegionTest {

    @Test
    public void creation() {
       Region region = Region.builder().name("서울").build();

       assertThat(region.getName(), is("서울"));
    }
}