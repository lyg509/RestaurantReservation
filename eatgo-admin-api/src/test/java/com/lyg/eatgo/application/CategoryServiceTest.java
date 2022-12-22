package com.lyg.eatgo.application;

import com.lyg.eatgo.domain.Category;
import com.lyg.eatgo.domain.CategoryRepository;
import com.lyg.eatgo.domain.Region;
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

class CategoryServiceTest {
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategory() {
        List<Category> mockCategory = new ArrayList<>();
        mockCategory.add(Category.builder().name("rice").build());

        given(categoryRepository.findAll()).willReturn(mockCategory);

        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);
        assertThat(category.getName(), is("rice"));
    }

    @Test
    public void addCategory() {
        Category category = categoryService.addCategory("rice");

        assertThat(category.getName(), is("rice"));

        verify(categoryRepository).save(any());

        assertThat(category.getName(), is("rice"));
    }

}