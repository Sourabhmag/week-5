package com.bridgelabz.controller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "User")
@Data
public class Registration {
	
	private String name;
	private String phoneNo;
	@Id
	private String email;
	private String password;
	private String checkPassword;
	public Registration() {
		
	}
	public Registration(String name, String phoneNo, String email, String password, String checkPassword) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.checkPassword = checkPassword;
	}
}