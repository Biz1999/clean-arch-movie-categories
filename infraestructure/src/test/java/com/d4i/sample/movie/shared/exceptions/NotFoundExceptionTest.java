package com.d4i.sample.movie.shared.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    void should_set_code_and_message_for_not_found_successfully(){
        String message = "Category [Drama] not found";

        NetflixException exception = assertThrows(NotFoundException.class, () -> { throw new NotFoundException(message); });

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getCode());
        assertEquals(message, exception.getMessage());
    }
}