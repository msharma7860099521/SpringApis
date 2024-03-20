package com.example.demo.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.UserInfoDetailsService;

import lombok.extern.slf4j.Slf4j;

@Configuration
//@EnableMethodSecurity

@Slf4j
public class SpringSecurityConfig {
	
    @Bean
 	 PasswordEncoder passwordencoder() {
    	
		return new BCryptPasswordEncoder();
	}
    
    @Bean
	 UserDetailsService userDetailsService( ) {

    	/*
		UserDetails adminUser=org.springframework.security.core.userdetails.User.builder().username("admin").password(encoder.encode("admin")).roles("ADMIN").build();
		UserDetails normalUser=org.springframework.security.core.userdetails.User.builder().username("user").password(encoder.encode("user")).roles("NORMAL").build();
		return new InMemoryUserDetailsManager(adminUser,normalUser);
		*/    
    	return new UserInfoDetailsService();
		
	}
    
    @Bean
     AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userDetailsService());
    	authenticationProvider.setPasswordEncoder(passwordencoder());
		return authenticationProvider;

    }
    
    @Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
}
