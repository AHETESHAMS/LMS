package com.bridgelabz.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.repository.UserRepository;

import reactor.core.publisher.Mono;

@RestController
public class Controller {
	
	private final UserRepository userRepository;

    @Autowired
    public Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody User user) {
        return userRepository.findByEmailId(user.getEmailId())
            .flatMap(existingUser -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists")))
            .switchIfEmpty(userRepository.save(user)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully"))
            );
    }
	
}
