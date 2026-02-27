package org.birnbickl.fitness.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(csrf-> csrf.disable())
               .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .exceptionHandling(ex ->
                       ex.authenticationEntryPoint((request, response, authException)
                               -> response.sendError(401, "Unauthorized"))
                               .accessDeniedHandler((request, response, accessDeniedException)
               -> response.sendError(403, "Forbidden")))
               .authorizeHttpRequests((auth-> auth
                       .requestMatchers("/api/auth/**").permitAll()
                       .requestMatchers("/api/user/**").authenticated()
                       .anyRequest().authenticated()))
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }
}
