package com.bridgelabz.controller.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.controller.model.Registration;

@Repository
public interface UserRepository extends MongoRepository<Registration,Integer> {
	public Registration findByEmail(String email);
}
