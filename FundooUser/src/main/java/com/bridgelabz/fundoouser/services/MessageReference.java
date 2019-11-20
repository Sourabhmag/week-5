package com.bridgelabz.fundoouser.services;
/******************************************************************************
 *  Compilation:  javac -d bin MessageReference.java
 *  Execution:    
 *      
 *  Purpose:  Used to generate link to send email
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
public class MessageReference {
	public static final String Validate_account = "http://localhost:8081/validate?token=";
	public static final String password_Reset = "http://localhost:8081/verify?token=";
	public static final String user_Already_exits = "user is already registered";
}
