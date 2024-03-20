package com.example.demo.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity

public class SecurityFilterConfig {
	private JwtAuthenticationEntryPoint authenticationEntrypoint;
	private JwtAuthenticationFilter jwtAuthenticationfilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		return security.csrf(csrf -> csrf.disable())   //cross site request frogary
				.cors(cors -> cors.disable())    
				.authorizeHttpRequests(
						                auth -> auth.requestMatchers("/authenticate", "/sys/new").permitAll()
				                       .anyRequest().authenticated()
				                      )
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntrypoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class)
				.build();

		/*
		 * @Override protected void configure(HttpSecurity http) throws Exception { http
		 * .csrf().disable() .authorizeRequests()
		 * .antMatchers("/api/private/**").authenticated() // Requires authentication
		 * for this pattern .anyRequest().permitAll(); // Permits access to any other
		 * request without authentication }
		 */

	}
}