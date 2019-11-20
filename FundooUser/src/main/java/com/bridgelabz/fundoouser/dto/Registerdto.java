package com.bridgelabz.fundoouser.dto;
/******************************************************************************
 *  Compilation:  javac -d bin Registerdto.java
 *  Execution:    
 *      
 *  Purpose:  Used as a dto class of user registration
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
import lombok.Data;
@Data
public class Registerdto {
	private String name;
	private String phoneNo;
	private String email;
	private String password;
	private String checkPassword;
	private boolean validate;
	
	public boolean getV()
	{
		return validate;
	}

}
