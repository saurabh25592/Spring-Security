package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.dto.AuthRequest;
import com.example.security.dto.AuthResponse;
import com.example.security.dto.RegisterRequest;
import com.example.security.repository.UserRepository;

@Service
public class UserService {
    
	@Autowired 
    private UserRepository userRepository;
    
	@Autowired 
	private PasswordEncoder encoder;
    
	@Autowired
	private	JwtService jwtService;

    public String register(RegisterRequest request) {
        UserDetails user = User.builder()
            .username(request.getUsername())
            .password(encoder.encode(request.getPassword()))
            .build();
        userRepository.save(user);
        return "User registered successfully!";
    }

    public AuthResponse authenticate(AuthRequest request) throws UsernameNotFoundException  {
        com.example.security.model.User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
