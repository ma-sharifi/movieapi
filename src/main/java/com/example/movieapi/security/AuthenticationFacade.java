package com.example.movieapi.security;

import org.springframework.security.core.Authentication;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22

 * be able to retrieve the authentication everywhere,not just in @Controller beans.
 * This facade encapsulates a complex subsystem behind a simple interface.
 * It defines a higher-level interface that makes the subsystem easier to use.
*/

public interface  AuthenticationFacade {
    /**
     * For spring security
     */
    Authentication getAuthentication();

//    Authentication getKeycloakAuthentication();// (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication(); For Keycloak
}
