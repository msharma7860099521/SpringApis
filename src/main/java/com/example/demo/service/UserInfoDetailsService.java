package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInfo;
import com.example.demo.model.UserInfoRepository;
import com.example.demo.securityConfig.UserInfoUserDetails;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class UserInfoDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByName(username);
		log.info("+++++++++++++++loadUserByUsername===================="+userInfo);
		
//		return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found. " + username));
		
		if (userInfo.isPresent()) {
		    UserInfo userDetails = userInfo.get();
		    UserInfoUserDetails user = new UserInfoUserDetails(userDetails);
		    System.out.println("user======="+user);
		    
		    return user;
		} else {
		    throw new UsernameNotFoundException("user not found. " + username);
		}

	}

}
