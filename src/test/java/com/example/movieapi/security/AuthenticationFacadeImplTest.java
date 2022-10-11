//package com.example.movieapi.security;
//
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.security.authentication.TestingAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * @author Mahdi Sharifi
// * @since 10/10/22
// */
//class AuthenticationFacadeImplTest {
//df
//    @Test
//    void getAuthentication() {
//
//        Authentication authentication = mock(Authentication.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication().getName()).thenReturn("mahdi");
////        SecurityContextHolder.setContext(securityContext);
//        System.out.println("#SecurityContextHolder.getContext().getAuthentication().getName(): "+SecurityContextHolder.getContext().getAuthentication().getName());;
//    }
//    @Test
//    void shoutTestSecurityContextHolder(){
//        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
//        SecurityContextHolder.getContext().setAuthentication(
//                new TestingAuthenticationToken("TEST_USER", null)
//        );
//    }
//
//}