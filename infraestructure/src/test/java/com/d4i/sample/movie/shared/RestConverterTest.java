package com.d4i.sample.movie.shared;

import com.d4i.sample.movie.category.Category;
import com.d4i.sample.movie.delivery.rest.CategoryRest;
import com.d4i.sample.movie.persistence.entities.CategoryEntity;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

class RestConverterTest {
    RestConverter restConverter = new RestConverter() {

        @Override
        public Serializable mapToEntity(Serializable rest) {
            return RestConverter.super.mapToEntity(rest);
        }

        @Override
        public Serializable mapToRest(Serializable entity) {
            return RestConverter.super.mapToRest(entity);
        }
    };

    @Test
    void should_throw_unsupported_operation_exception_when_trying_to_map_to_entity() {
        CategoryRest dramaCategoryRest = new CategoryRest("Drama", true);

        RuntimeException exception = assertThrows(UnsupportedOperationException.class, () -> restConverter.mapToEntity(dramaCategoryRest));

        assertNull(exception.getMessage());
    }

    @Test
    void should_throw_unsupported_operation_exception_when_trying_to_map_to_rest() {
        Category category = new Category(Long.parseLong("0"), "Drama", false);

        RuntimeException exception = assertThrows(UnsupportedOperationException.class, () -> restConverter.mapToRest(category));

        assertNull(exception.getMessage());
    }

}