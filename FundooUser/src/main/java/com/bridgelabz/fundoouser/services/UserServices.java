package com.bridgelabz.fundoouser.services;

import java.util.Base64;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoouser.Response.Response;
import com.bridgelabz.fundoouser.model.ForgotPassword;
import com.bridgelabz.fundoouser.model.Login;
import com.bridgelabz.fundoouser.model.Registration;
import com.bridgelabz.fundoouser.repository.UserRepository;
import com.bridgelabz.fundoouser.utility.TokenUtil;
/******************************************************************************
 *  Compilation:  javac -d bin UserServices.java
 *  Execution:    
 *      
 *  Purpose:   Used provide services to controller class
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	TokenUtil tokenUtil;
	
	public Registration reg;
	public String Token;
	/**
	 * @Purpose - Returns list of all users
	 * @return List of all users
	 */
	public List<Registration> getUserList() {

		return userRepository.findAll();
	}
	/**
	 * @Purpose - Returns details of specific user
	 * @return details of specific user
	 */
	public Registration getUser(String email)
	{
		//userRepository.findAll().stream().filter(i->i.getEmail().equals(email));
		Registration user = userRepository.findByEmail(email).orElse(null);
		if(user!=null)
			return user;
		return null;
		
	}
	/**
	 * @Purpose - Adds new user into database
	 * @return details of specific user
	 */
	public Response addUser(Registration user)
	{
		if(getUser(user.getEmail())!=null)
			return new Response(400,"User already registered",null);
		String password = user.getPassword();
		user.setPassword(encryptPassword(password));
		reg=user;
		Token = TokenUtil.generateToken(user.getEmail());
		userRepository.save(user);
		javaMailSender.send(EmailService.sendMail(user.getEmail(),Token,MessageReference.Validate_account));
		
		return new Response(200,"User Registered \n validation email sent on registered mail id",user);
	}
	/**
	 * @Purpose - updates user information into database
	 * @return details of specific user
	 */
	public Response updateUser(String email, Registration user) {
		
		Registration userUpdate = userRepository.findByEmail(email).orElse(null);
		userUpdate =user;
		return new Response(200,"User Data Updated Successfully",userUpdate);
		
	}
	/**
	 * @return 
	 * @Purpose - delete user from database
	 */
	public Response deleteUser(String email) {
		Registration user = userRepository.findByEmail(email).orElse(null);
		userRepository.delete(user);
		return new Response(200,"User Deleted Successfully",null);
		
	}
	/**
	 * @Purpose - used to login user
	 * @return details of specific user
	 */
	public Response login(Login loginData) {

		Registration getUserData = userRepository.findByEmail(loginData.getUsername()).orElse(null);
		//System.out.println(getUserData.getPassword());
		String password = (decryptPassword(getUserData.getPassword()));
		//System.out.println(password);
		if(getUserData!=null && password.equals(loginData.getPassword()))
		{
			return new Response(200,"Login Successfull",getUserData);
		}
		return new Response(400,"Login Failed",null);
	}
	/**
	 * @Purpose - Send password reset link to user who requested for password reset
	 * @return status of request
	 */
	public String forgotPassword(String email) {
		Registration user = getUser(email);
		if(user==null)
			return "User is not Registered";
		else if(user.isValidate()==false)
			return "User is not validated";
		reg=user;
		Token = TokenUtil.generateToken(user.getEmail());
		javaMailSender.send(EmailService.sendMail(user.getEmail(),Token,MessageReference.password_Reset));
		return "Password reset link is sent to your registered mail id";
	}
	
	/**
	 * @Purpose - Used to encrypt password
	 * @param accepts password in the form of string
	 * @return password in encrypted format as a string
	 */
	public String encryptPassword(String password)
	{
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
	
	/**
	 * @Purpose - Used to decrypt Password
	 * @param accepts decrypted password in the form of string
	 * @return password in decrypted format as a string
	 */
	public String decryptPassword(String encryptedPassword)
	{
		return new String(Base64.getMimeDecoder().decode(encryptedPassword));
	}
	
	/**
	 * @Purpose - Used to validate user account
	 * @param accepts user name
	 */
	public void validate(String username) {
		
		Registration user = getUser(username);
		user.setValidate(true);
		updateUser(username, user);
	}

	/**
	 * @Purpose - Used to reset password
	 * @param Accepts password
	 * @param Accepts token
	 * @return Status of request
	 */
	public Response verify(ForgotPassword password,String token) {
		
		if(this.Token.equals(token)!=true)
			return new Response(400,"Unauthentic user found", null);
	
		else if(password.getPassword().equals(password.getPasswordCheck())!=true)
			return new Response(400, "Enter password is not matching", null);
		
		String username = (tokenUtil.getUsernameFromToken(token));
		Registration user = getUser(username);
		
		String encryptedPassword = encryptPassword(password.getPassword());
		user.setPassword(encryptedPassword);
		
		updateUser(username, user);
		return new Response(200, "Password reset successfull", user);
	}
}
