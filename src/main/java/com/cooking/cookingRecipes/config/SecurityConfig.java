package com.cooking.cookingRecipes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        // not logged-in users can look at other users' profiles and register site (add endpoints for reviews and recipes later)
                        request.requestMatchers(new AntPathRequestMatcher("/users/list", "GET")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/save", "POST")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/register", "GET")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/*", "GET")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/**")).authenticated())
                // since we use jwt, we don't have to store user data in any sessions
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                // add the filter to the filter chain
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
