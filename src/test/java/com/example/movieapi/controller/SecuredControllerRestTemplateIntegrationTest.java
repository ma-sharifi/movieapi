package com.example.movieapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mahdi Sharifi
 * @since 10/9/22
 */
//https://www.baeldung.com/spring-security-integration-tests
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredControllerRestTemplateIntegrationTest {

    @Autowired
    private TestRestTemplate template;

    // ... other methods

    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("mahdi", "mahdi")
                .getForEntity("/private/hello", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}

