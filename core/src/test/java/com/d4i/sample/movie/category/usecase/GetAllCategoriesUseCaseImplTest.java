package com.d4i.sample.movie.category.usecase;


import com.d4i.sample.movie.category.Category;
import com.d4i.sample.movie.category.ports.CategoryRepositoryService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetAllCategoriesUseCaseImplTest {

    private final CategoryRepositoryService categoryRepositoryService = mock(CategoryRepositoryService.class);
    private final GetAllCategoriesUseCaseImpl getCategoryUseCase = new GetAllCategoriesUseCaseImpl(categoryRepositoryService);
    @Test
    void get_all_database_categories_with_registry_test() {

        Category dramaCategory = new Category(Long.parseLong("0"), "Drama", true);
        Category horrorCategory = new Category(Long.parseLong("1"), "Horror", false);
        List<Category> CategoryList = List.of(dramaCategory, horrorCategory);

        when(categoryRepositoryService.getAllCategories()).thenReturn(CategoryList);
        Collection<Category> listResponse =  getCategoryUseCase.execute();

        Assertions.assertThat(listResponse)
                .hasOnlyElementsOfTypes(Category.class)
                .hasSize(2)
                .containsExactlyInAnyOrder(dramaCategory, horrorCategory);

    }

    @Test
    void get_all_database_categories_no_registry_test() {

        List<Category> CategoryList = List.of();

        when(categoryRepositoryService.getAllCategories()).thenReturn(CategoryList);
        Collection<Category> listResponse =  getCategoryUseCase.execute();

        Assertions.assertThat(listResponse)
                .hasOnlyElementsOfTypes(Category.class)
                .hasSize(0)
                .containsExactlyInAnyOrder();


    }

}