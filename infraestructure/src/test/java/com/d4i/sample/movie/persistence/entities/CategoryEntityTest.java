package com.d4i.sample.movie.persistence.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    void should_use_getters_and_setters_successfully(String categoryName, Boolean isAvailable) {
        Long id = Long.parseLong("1");
        String name = "Comedy";
        Boolean availability = true;
        CategoryEntity categoryEntity = new CategoryEntity(id, name, availability);

        assertEquals(id, categoryEntity.getId());
        assertEquals(name, categoryEntity.getName());
        assertEquals(availability, categoryEntity.getAvailable());

        categoryEntity.setName(categoryName);
        categoryEntity.setAvailable(isAvailable);

        assertEquals(categoryName, categoryEntity.getName());
        assertEquals(isAvailable, categoryEntity.getAvailable());
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of("Drama", true),
                Arguments.of("Horror", false),
                Arguments.of("Action", false),
                Arguments.of("Adventure", true)
        );
    }
}