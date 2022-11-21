package com.d4i.sample.movie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

    @Test
    public void contextLoads() {
        String[] arguments = {"-Dspring.profile.active=test"};
        Application.main(arguments);
    }

}