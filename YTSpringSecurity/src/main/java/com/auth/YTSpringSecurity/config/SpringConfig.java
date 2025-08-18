package com.auth.YTSpringSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.auth.YTSpringSecurity.SuccessHandler;
import com.auth.YTSpringSecurity.model.MyUserDetailService;

@Configuration
public class SpringConfig {
	
	@Autowired
	private MyUserDetailService myUserDetailService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//it return the database authentication 
	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
		
	}   
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity security) throws Exception {
	return security
			
			 .csrf(csrf->csrf.disable())			
			.authorizeHttpRequests(auth->auth
			    .requestMatchers("/home","/register/**").permitAll()
			    .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER").anyRequest()  
			    .authenticated())  //these is code for user authentication
			     .formLogin(form -> form     //form redirection
			    	.loginPage("/login") //login 
			    	.successHandler(new SuccessHandler())// SuccessHandler is class created for 
			    		 .permitAll())
			.build();		
	}
}
