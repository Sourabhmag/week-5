package com.bridgelabz.controller.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.controller.model.Login;
import com.bridgelabz.controller.model.Registration;
import com.bridgelabz.controller.repository.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	public List<Registration> getUserList() {

		return userRepository.findAll();
	}
	
	public Registration getUser(String email)
	{
		return userRepository.findByEmail(email);
	}

	public Registration addUser(Registration user)
	{
		return userRepository.save(user);
	}

	public Registration updateUser(String email, Registration user) {
		
		Registration userUpdate = userRepository.findByEmail(email);
		userUpdate =user;
		return userRepository.save(userUpdate);
		
	}

	public void deleteUser(String email) {
		Registration user = userRepository.findByEmail(email);
		userRepository.delete(user);
		
	}

	public List<Registration> login(Login loginData) {
		Registration getUserData = userRepository.findByEmail(loginData.getUsername());
		if(getUserData!=null && getUserData.getPassword()==loginData.getPassword())
		{
			return getUserList();
		}
		return null;
	}
}