package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.entity.UserInfo;
import com.example.demo.jwt.JwtHelper;
import com.example.demo.jwt.JwtRequest;
import com.example.demo.jwt.JwtResponse;
import com.example.demo.service.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("sys")
@AllArgsConstructor
public class StudentController {

	private UserDetailsService userDetailsService;
	private AuthenticationManager manager;
	private JwtHelper helper;

	@Autowired
	StudentService studentService;

	@PostMapping("/addStudent")
//	@PreAuthorize("hasAuthority(ADMIN)")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {

		Student addedStdent = studentService.saveStudent(student);
		return new ResponseEntity<Student>(addedStdent, HttpStatus.ACCEPTED);

	}

	@GetMapping("/getStudent/{id}")
//	@PreAuthorize("hasAuthority(NORMAL)")
	public ResponseEntity<Student> findStudentById(@PathVariable int id) {
		Student student = studentService.getStudentById(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public String addUser(@RequestBody UserInfo userInfo) {
		return this.studentService.addUser(userInfo);
	}
	
	/*
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		this.doAuthenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userDetails);
		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage() + " !!");
		}

	}
	*/
	
	
	
}
