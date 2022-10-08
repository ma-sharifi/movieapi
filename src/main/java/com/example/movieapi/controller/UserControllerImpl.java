package com.example.movieapi.controller;

import com.example.movieapi.security.JwtTokenUtil;
import com.example.movieapi.service.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

/**
 * @author Mahdi Sharifi
 * @since 10/7/22
 */

@RestController
@RequestMapping("v1/user")
@Tag(name = "User-controller for issuing JWT token", description = "Issue token based on username")
@Slf4j
public class UserControllerImpl implements UserController{

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> login(
            @RequestParam(value = "username") String username, @RequestParam(required = false,value = "password") String password) {
//            @RequestParam(value = "Authorization") String username, @RequestParam(required = false,value = "password") String password) {
        log.debug("#UserController login method called.");
//        String encoding = Base64.getEncoder().encodeToString((user + ":" + password).getBytes());
//        String authHeader = "Basic " + encoding;
        // Assumed we checked the user and password and found they are a match. then we issued a token.
        String token = JwtTokenUtil.issueJwtToken(username);
        return ResponseEntity.ok().header("Authorization",token).body(new UserDto(username,token));
    }

}
