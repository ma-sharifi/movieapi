package com.example.movieapi.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mahdi Sharifi
 * @since 10/10/22
 */
class RequestDtoTest {

    @Test
    void shouldReturnRequestDto_WhenConstructorisCalled(){
        RequestDto requestDto=new RequestDto(10,"Inception");

        assertEquals(10,requestDto.getRate());
        assertEquals("Inception",requestDto.getTitle());
    }

}