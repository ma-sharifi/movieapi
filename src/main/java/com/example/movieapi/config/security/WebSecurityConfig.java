package com.example.movieapi.config.security;

import com.example.movieapi.security.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Mahdi Sharifi
 * @since 10/8/22
 */

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/user/login").permitAll()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest()
                .authenticated();
        ////Since the H2 database console runs inside a frame, we need to enable this in Spring Security.
        //for h2-console can be load. Frame options are needed to prevent a browser to load your HTML page in an <iframe> or a <frame> tag and for H2 Console page to load, you need to disable this option.
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javainuse")
                .password(passwordEncoder().encode("javainuse"))
                .authorities("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}