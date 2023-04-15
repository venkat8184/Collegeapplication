package com.collegeapplication.controller;

import com.collegeapplication.entities.Role;
import com.collegeapplication.entities.User;
import com.collegeapplication.payload.JwtAuthResponse;
import com.collegeapplication.payload.LogInDto;
import com.collegeapplication.payload.SignUpDto;
import com.collegeapplication.repositories.RoleRepository;
import com.collegeapplication.repositories.UserRepository;
import com.collegeapplication.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LogInDto logInDto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDto.getUserNameOrEmail(), logInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token= tokenProvider.generateToken(authenticate);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("User Already exists With UserName"+signUpDto.getUsername(),HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("User Already exists With Email"+signUpDto.getEmail(),HttpStatus.BAD_REQUEST);
        }
        User user=new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User Registered successfully!",HttpStatus.OK);
    }
}
