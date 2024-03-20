package com.example.demo.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class JwtAuthenticationController {

	private UserDetailsService userDetailsService;

	private AuthenticationManager authenticationManager;

	private JwtHelper helper;

	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userDetails);
		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
		org.springframework.security.core.Authentication authenticationResult = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (authenticationResult.isAuthenticated()) {

			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {

			throw new BadCredentialsException("Credentials Invalid !!");

		}

	}

}