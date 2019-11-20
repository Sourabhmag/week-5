package com.bridgelabz.fundoouser.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoouser.Response.Response;
import com.bridgelabz.fundoouser.dto.ForgotPassworddto;
import com.bridgelabz.fundoouser.dto.Logindto;
import com.bridgelabz.fundoouser.dto.Registerdto;
import com.bridgelabz.fundoouser.model.*;
import com.bridgelabz.fundoouser.services.UserServices;
import com.bridgelabz.fundoouser.utility.TokenUtil;
/******************************************************************************
 *  Compilation:  javac -d bin UserController.java
 *  Execution:    
 *      
 *  Purpose:  Used as a controller class of user actions
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@RestController
@RequestMapping
public class UserController {
	@Autowired
	private UserServices userServices;
	@Autowired
	TokenUtil tokenUtil;
	@Autowired
	ModelMapper mapper=null;
	
	// Method to print all data
	@GetMapping("/user")
	public List<Registration> getUsersData() {
		System.out.println("in get");
		return userServices.getUserList();
	}

	// Method to print information of specific user
	@PostMapping("/user/{email}")
	public Registration filterUser(@PathVariable String email) {
		return userServices.getUser(email);
	}
	
	// Method to add new User
	@PostMapping("/user")
	public ResponseEntity<Response> addUser(@RequestBody Registerdto user) {
		Registration userData = mapper.map(user,Registration.class);
		return new ResponseEntity<Response>(userServices.addUser(userData),HttpStatus.OK);
		
	}

	// Method to update data of existing user
	@PutMapping("/user/{email}")
	public ResponseEntity<Response> updateUser(@RequestBody Registration user, @PathVariable String email) {
		Response update = userServices.updateUser(email, user);
		return new ResponseEntity<Response>(update,HttpStatus.OK);
	}

	// Method to delete existing user
	@DeleteMapping("/user/{email}")
	public ResponseEntity<Response> deleteUser(@PathVariable String email) {
		return new ResponseEntity<Response>(userServices.deleteUser(email),HttpStatus.OK);
	}

	// Method to login existing user
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody Logindto loginData) {
		Login userData = mapper.map(loginData, Login.class);
		return new ResponseEntity<Response>(userServices.login(userData),HttpStatus.OK);
	}

	// Method to forgot password
	@PostMapping("/forgot/{email}")
	public String forgotPassword(@PathVariable String email)
	{
		return userServices.forgotPassword(email);
	}
	
	// Method to verify and reset the password
	@PostMapping("/verify")
	public ResponseEntity<Response> verify(@RequestParam String token,@RequestBody ForgotPassworddto password)
	{
		System.out.println(password.getPassword()+"\n"+password.getPasswordCheck());
		ForgotPassword pass = mapper.map(password,ForgotPassword.class);
		return new ResponseEntity<Response>(userServices.verify(pass,token),HttpStatus.OK);
		
	}
	
	// Method to validate user account
	@GetMapping("/validate")
	public String validate(@RequestParam String token)
	{
		String username = (tokenUtil.getUsernameFromToken(token));
		if(userServices.getUser(username)!=null) {
			userServices.validate(username);
			return "Validation Successfull\nUser registered successfully";
		}
		return "validation failed";
	}
	
}
