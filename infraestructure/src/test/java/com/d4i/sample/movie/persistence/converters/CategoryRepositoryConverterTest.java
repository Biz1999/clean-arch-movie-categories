package com.d4i.sample.movie.persistence.converters;

import com.d4i.sample.movie.category.Category;
import com.d4i.sample.movie.persistence.entities.CategoryEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryConverterTest {

    private final CategoryRepositoryConverter categoryRepositoryConverter = new CategoryRepositoryConverter();

    @Test
    void should_convert_rest_object_to_table_entity() {
        Category dramaCategory = new Category(null, "Drama", false);

        CategoryEntity categoryEntity = categoryRepositoryConverter.mapToTable(dramaCategory);

        assertEquals(dramaCategory.getName(), categoryEntity.getName());
        assertEquals(dramaCategory.getAvailable(), categoryEntity.getAvailable());
        assertNull(categoryEntity.getId());
    }

    @Test
    void should_convert_domain_object_to_domain() {
        CategoryEntity categoryEntity = new CategoryEntity(Long.parseLong("1"), "Horror", false);

        Category category = categoryRepositoryConverter.mapToEntity(categoryEntity);

        assertEquals(categoryEntity.getName(), category.getName());
        assertEquals(categoryEntity.getAvailable(), category.getAvailable());
        assertNotNull(category.getId());
    }
}