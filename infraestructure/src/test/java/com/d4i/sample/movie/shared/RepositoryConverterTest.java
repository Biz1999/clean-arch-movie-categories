package com.d4i.sample.movie.shared;

import com.d4i.sample.movie.category.Category;
import com.d4i.sample.movie.persistence.entities.CategoryEntity;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryConverterTest {
    RepositoryConverter repositoryConverter = new RepositoryConverter() {
        @Override
        public Serializable mapToTable(Serializable persistenceObject) {
            return RepositoryConverter.super.mapToTable(persistenceObject);
        }

        @Override
        public Serializable mapToEntity(Serializable tableObject) {
            return RepositoryConverter.super.mapToEntity(tableObject);
        }
    };

    @Test
    void should_throw_unsupported_operation_exception_when_trying_to_map_to_table() {
        Category dramaCategory = new Category(null, "Drama", false);

        RuntimeException exception = assertThrows(UnsupportedOperationException.class, () -> repositoryConverter.mapToTable(dramaCategory));

        assertNull(exception.getMessage());
    }

    @Test
    void should_throw_unsupported_operation_exception_when_trying_to_map_to_entity() {
        CategoryEntity categoryEntity = new CategoryEntity(Long.parseLong("0"), "Drama", false);

        RuntimeException exception = assertThrows(UnsupportedOperationException.class, () -> repositoryConverter.mapToEntity(categoryEntity));

        assertNull(exception.getMessage());
    }
}