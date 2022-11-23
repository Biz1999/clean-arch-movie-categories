package com.d4i.sample.movie.delivery.converters;

import com.d4i.sample.movie.category.Category;
import com.d4i.sample.movie.delivery.rest.CategoryRest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRestConverterTest {

    private final CategoryRestConverter categoryRestConverter = new CategoryRestConverter();

    @Test
    void should_convert_rest_object_to_domain() {
        CategoryRest categoryRest = new CategoryRest("Horror", true);

        Category category = categoryRestConverter.mapToEntity(categoryRest);

        assertEquals(categoryRest.getName(), category.getName());
        assertEquals(categoryRest.getAvailable(), category.getAvailable());
        assertNull(category.getId());
    }

    @Test
    void should_convert_domain_object_to_rest() {
        Category dramaCategory = new Category(null, "Drama", false);

        CategoryRest categoryRest = categoryRestConverter.mapToRest(dramaCategory);

        assertEquals(categoryRest.getName(), dramaCategory.getName());
        assertEquals(categoryRest.getAvailable(), dramaCategory.getAvailable());
    }

}