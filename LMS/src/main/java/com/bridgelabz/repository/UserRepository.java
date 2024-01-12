package com.bridgelabz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.bridgelabz.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long>{
	
	Mono<User> findByEmailId(String email);

}
