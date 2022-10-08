package com.example.movieapi.security;

import org.springframework.security.core.Authentication;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22
 * be able to retrieve the authentication everywhere,
 * not just in @Controller beans, we need to hide the static access behind a facade
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
}