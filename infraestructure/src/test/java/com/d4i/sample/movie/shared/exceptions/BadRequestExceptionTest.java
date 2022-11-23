package com.d4i.sample.movie.shared.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {

    @Test
    void should_set_code_and_message_for_bad_request_successfully(){
        String message = "Invalid field type";

        BadRequestException exception = assertThrows(BadRequestException.class, () -> { throw new BadRequestException(message); });

        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getCode());
        assertEquals(message, exception.getMessage());
    }
}