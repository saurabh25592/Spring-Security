package com.demo.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.builder().username("aadi").password(passwordEncoder().encode("1234")).roles("ADMIN")
				.build();

		UserDetails user = User.builder().username("rahul").password(passwordEncoder().encode("rahul")).roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security.csrf(csrf -> csrf.disable()) //it is not good
				.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).build();
	}

}
