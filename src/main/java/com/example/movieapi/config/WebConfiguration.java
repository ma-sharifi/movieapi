//package com.example.movieapi.config;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.h2.server.web.WebServlet;
//
///**
// * @author Mahdi Sharifi
// * @since 10/10/22
// */
//
//@Configuration
//public class WebConfiguration {
//    @Bean
//    ServletRegistrationBean h2servletRegistration() {
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//    }
//}