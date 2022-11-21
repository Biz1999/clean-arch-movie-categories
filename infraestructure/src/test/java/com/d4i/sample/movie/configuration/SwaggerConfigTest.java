package com.d4i.sample.movie.configuration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

class SwaggerConfigTest {

    private final SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    void should_create_swagger_api_bean() {
        Assertions.assertThat(swaggerConfig.api())
                .isInstanceOf(Docket.class);
    }
}