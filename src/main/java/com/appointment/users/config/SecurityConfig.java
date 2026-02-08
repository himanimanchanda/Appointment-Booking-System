package com.appointment.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Swagger endpoints allow
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
//                         allow POST auth APIs
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        //Auth APIs open
                        .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/admin/onboard").permitAll()
                        // everything else secure
                        .anyRequest().authenticated()
                )

                // for JWT apps
                .formLogin(form -> form.disable());

        return http.build();
    }
}
