package com.d4i.sample.movie.persistence.impl;

import com.d4i.sample.movie.category.Category;
import com.d4i.sample.movie.delivery.rest.CategoryRest;
import com.d4i.sample.movie.persistence.converters.CategoryRepositoryConverter;
import com.d4i.sample.movie.persistence.entities.CategoryEntity;
import com.d4i.sample.movie.persistence.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private final CategoryRepositoryConverter categoryRepositoryConverter = mock(CategoryRepositoryConverter.class);

    private final CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(
            categoryRepository,
            categoryRepositoryConverter
    );

    @Test
    void should_get_all_database_created_categories_successfully() {
        Category dramaCategory = new Category(Long.parseLong("0"), "Drama", true);
        CategoryEntity dramaCategoryEntity = new CategoryEntity(Long.parseLong("0"), "Drama", true);

        Category horrorCategory = new Category(Long.parseLong("1"), "Horror", false);
        CategoryEntity horrorCategoryEntity = new CategoryEntity(Long.parseLong("1"), "Horror", false);

        List<CategoryEntity> categoryEntities = List.of(dramaCategoryEntity, horrorCategoryEntity);

        when(categoryRepository.findAll()).thenReturn(categoryEntities);
        when(categoryRepositoryConverter.mapToEntity(dramaCategoryEntity)).thenReturn(dramaCategory);
        when(categoryRepositoryConverter.mapToEntity(horrorCategoryEntity)).thenReturn(horrorCategory);

        Collection<Category> categories = categoryServiceImpl.getAllCategories();

        Assertions.assertThat(categories)
                .hasOnlyElementsOfTypes(Category.class)
                .hasSize(2)
                .containsExactlyInAnyOrder(horrorCategory, dramaCategory);
    }

    @Test
    void should_save_the_new_category_successfully() {
        Category dramaCategory = new Category(Long.parseLong("0"), "Drama", true);
        CategoryEntity dramaCategoryEntity = new CategoryEntity(Long.parseLong("0"), "Drama", true);

        when(categoryRepositoryConverter.mapToTable(dramaCategory)).thenReturn(dramaCategoryEntity);

        categoryServiceImpl.saveCategory(dramaCategory);

        verify(categoryRepositoryConverter, times(1)).mapToTable(any());
        verify(categoryRepository, times(1)).save(any());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Drama", "Horror", "Action", "Adventure"})
    void should_inform_that_category_does_not_exists(String categoryName) {
        when(categoryRepository.findByName(eq(categoryName))).thenReturn(List.of());

        Boolean doesCategoryExists = categoryServiceImpl.doesCategoryNameExists(categoryName);

        assertFalse(doesCategoryExists);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Drama", "Horror", "Action", "Adventure"})
    void should_inform_that_category_does_exists(String categoryName) {
        Category category = new Category(Long.parseLong("1"), categoryName, true);
        when(categoryRepository.findByName(eq(categoryName))).thenReturn(List.of(category));

        Boolean doesCategoryExists = categoryServiceImpl.doesCategoryNameExists(categoryName);

        assertTrue(doesCategoryExists);
    }

}