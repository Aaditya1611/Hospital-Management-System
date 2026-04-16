package com.example.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.auth.api.oauth.OAuthSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    //     return http
    //             // .cors(cors -> cors.configurationSource(corsConfigurationSource()))
    //             .cors(Customizer.withDefaults())
    //             .csrf(csrf -> csrf.disable())
    //             .authorizeHttpRequests((requests) -> requests
    //                     .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
    //                     .requestMatchers("/api/**", "/login", "/signup").permitAll()
    //                     .requestMatchers("/admin/**").hasRole(RoleType.ADMIN.name())
    //                     .requestMatchers("/doctor/**").hasAnyRole(RoleType.DOCTOR.name())
    //                     .anyRequest().authenticated())
    //                     .httpBasic(httpBasic-> httpBasic.disable())
    //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    //             .oauth2Login(oAuth2 -> oAuth2
    //                     .failureHandler(
    //                             (request, response, exception) -> {
    //                                 log.error("oAuth failure", exception.getMessage());
    //                             })
    //                     .successHandler(oAuthSuccessHandler)
    //                     )
    //             .exceptionHandling(exceptionHandlingConfigurer ->
    //                     exceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) -> {
    //                         handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
    //                     })
    //             )
    //             .build();
    // }

    @Autowired
    private OAuthSuccessHandler oAuthSuccessHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults()) 
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/**", "/login", "/signup").permitAll()
                        .anyRequest().authenticated() 
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // REMOVED: .addFilterBefore(jwtFilter, ...) 
                .oauth2Login(oAuth2 -> oAuth2
                        .successHandler(oAuthSuccessHandler)
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
