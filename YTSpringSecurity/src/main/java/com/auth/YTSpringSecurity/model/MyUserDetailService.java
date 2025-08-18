package com.auth.YTSpringSecurity.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	//these is code of database authentication
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.auth.YTSpringSecurity.model.User> user =userRepository.findByUsername(username);
		  
		 if(user.isPresent()) {
  	       var  userObj=user.get();
  	         return User.builder().username(userObj.getUsername())
  	        		 .password(userObj.getPassword()).roles(userObj.getRole()).build();
		 }
		 else {
			 throw new UsernameNotFoundException(username);
		 }
	}

	
}
