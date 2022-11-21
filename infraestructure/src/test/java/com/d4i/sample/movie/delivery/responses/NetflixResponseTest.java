package com.d4i.sample.movie.delivery.responses;

import com.d4i.sample.movie.delivery.rest.CategoryRest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.d4i.sample.movie.shared.constants.CommonConstants.OK;
import static com.d4i.sample.movie.shared.constants.CommonConstants.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

class NetflixResponseTest {

    private final NetflixResponse<CategoryRest> categoryRestNetflixResponse = new NetflixResponse<>();

    @Test
    void should_set_new_values_to_response() {
        assertNull(categoryRestNetflixResponse.getStatus());
        assertNull(categoryRestNetflixResponse.getCode());
        assertNull(categoryRestNetflixResponse.getMessage());
        assertNull(categoryRestNetflixResponse.getData());


        String status = SUCCESS;
        String code = "200 Ok";
        String message = OK;
        CategoryRest dramaCategoryRest = new CategoryRest("Drama", true);

        categoryRestNetflixResponse.setStatus(status);
        categoryRestNetflixResponse.setCode(code);
        categoryRestNetflixResponse.setMessage(message);
        categoryRestNetflixResponse.setData(dramaCategoryRest);

        assertEquals(status, categoryRestNetflixResponse.getStatus());
        assertEquals(code, categoryRestNetflixResponse.getCode());
        assertEquals(message, categoryRestNetflixResponse.getMessage());
        assertEquals(dramaCategoryRest, categoryRestNetflixResponse.getData());
    }
}