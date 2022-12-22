package com.lyg.eatgo.interfaces;

import com.lyg.eatgo.application.CategoryService;
import com.lyg.eatgo.domain.Category;
import com.lyg.eatgo.domain.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private CategoryService categoryService;

        @Test
        public void list() throws Exception {
            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder().name("rice").build());

            given(categoryService.getCategories()).willReturn(categories);

            mvc.perform(get("/categories"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("rice")));
        }

        @Test
        public void create() throws Exception {
            Category category = Category.builder().name("rice").build();

            given(categoryService.addCategory("rice")).willReturn(category);

            mvc.perform(post("/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"rice\"}"))
                    .andExpect(status().isCreated())
                    .andExpect(content().string("{}"));

            verify(categoryService).addCategory("rice");
        }
    }