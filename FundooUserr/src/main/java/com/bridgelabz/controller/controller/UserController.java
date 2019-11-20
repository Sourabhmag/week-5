package com.bridgelabz.controller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.controller.model.Login;
import com.bridgelabz.controller.model.Registration;
import com.bridgelabz.controller.services.*;
@RestController
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	//Method to print all data
//	@RequestMapping(method=RequestMethod.GET,value="/user")
	@GetMapping("/user")
	public List<Registration> getUsersData()
	{
		System.out.println("in get");
		return userServices.getUserList();
	}
	
	@GetMapping("/")
	public String hellow() {
		System.out.println("hey hello");
		return "hello user";
	}
	
	@PostMapping("/user/{email}")
	public Registration filterUser(@PathVariable String email)
	{
		return userServices.getUser(email);
	}
	
	@PostMapping("/user")
	public String addUser(@RequestBody Registration user)
	{
		Registration val = userServices.addUser(user);
		System.out.println(val);
		if(val!=null)
			return "User registered successfully";
		return "Problem to add user";
	}
	
	@PutMapping("/user/{email}")
	public String updateUser(@RequestBody Registration user,@PathVariable String email)
	{
		if(userServices.updateUser(email,user)!=null)
			return "Data Updated Sussfully";
		return "Problem to update data";
	}
	
	@DeleteMapping("/user/{email}")
	public String deleteUser(@PathVariable String email)
	{
		userServices.deleteUser(email);
			return "User deleted successfully";
	}
	
	@PostMapping("/login")
	public List<Registration> login(@PathVariable Login loginData)
	{
		return userServices.login(loginData);
	}
	@GetMapping("/vishnu")
	public boolean vishnu()
	{
		return true;
	}
	
}
