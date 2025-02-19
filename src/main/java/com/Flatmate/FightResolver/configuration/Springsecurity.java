package com.Flatmate.FightResolver.configuration;

import com.Flatmate.FightResolver.service.UserDetailserviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.Flatmate.FightResolver.filters.jwtfilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Springsecurity {
    @Autowired
    UserDetailserviceimpl userDetailserviceimpl;
    @Autowired
    jwtfilter jwtFilters;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(cd->cd.disable()).authorizeHttpRequests(request->request
                .requestMatchers("/auth/login", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/journal/**","/user/**").authenticated().
                requestMatchers("/public/**")//.hasRole("ADMIN").anyRequest()
                .permitAll()).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS).maximumSessions(1));
        http.addFilterBefore(jwtFilters, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)throws  Exception{
        return auth.getAuthenticationManager();
    }
}
