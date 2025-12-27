package com.MediServe.apiMediServe.infra.security;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("JWT Filter executado!");

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/mediServe/auth/**").permitAll()
//
//                        .requestMatchers(HttpMethod.POST, "/api/mediServe/users").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/mediServe/users").hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/mediServe/specialtys").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/mediServe/specialtys").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/api/mediServe/specialtys").hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/mediServe/doctors").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/mediServe/doctors").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/api/mediServe/doctors").hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/mediServe/doctors-diary").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/mediServe/doctors-diary/*/schedule").hasAnyRole("ADMIN","RECEPCIONISTA")
//                        .requestMatchers(HttpMethod.GET, "/api/mediServe/doctors-diary/*").hasAnyRole("ADMIN","RECEPCIONISTA")
//                        .requestMatchers(HttpMethod.PUT, "/api/mediServe/doctors-diary/*").hasAnyRole("ADMIN","RECEPCIONISTA")
//
//                        .requestMatchers(HttpMethod.POST,"/api/mediServe/patients").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/api/mediServe/patients").permitAll()
//                        .requestMatchers(HttpMethod.PUT,"/api/mediServe/patients").hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST,"/api/mediServe/appointments").hasAnyRole("ADMIN","RECEPCIONISTA")
//                        .requestMatchers(HttpMethod.GET,"/api/mediServe/appointments").hasAnyRole("ADMIN","RECEPCIONISTA")
//                        .requestMatchers(HttpMethod.PUT,"/api/mediServe/appointments").hasAnyRole("ADMIN","RECEPCIONISTA")
//
//                        .requestMatchers(HttpMethod.GET,"/api/mediServe/dashboard").hasAnyRole("ADMIN")

                        .anyRequest().permitAll()  //authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
