package com.w1zer.configuration;

import com.w1zer.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private static final String AUTH_ALL = "/api/auth/**";
    private static final String TOADS_ALL = "/api/toads/**";
    private static final String TOADS = "/api/toads";
    private static final String PROFILES_ALL = "/api/profiles/**";
    private static final String PROFILES = "/api/profiles";
    private static final String USER = "USER";

    private final JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests()
                .antMatchers(AUTH_ALL).permitAll()
                .antMatchers(HttpMethod.POST, PROFILES).permitAll()
                .antMatchers(TOADS_ALL, PROFILES_ALL).hasRole(USER)
                .antMatchers(HttpMethod.GET, PROFILES, TOADS).access("hasIpAddress('127.0.0.1')")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
