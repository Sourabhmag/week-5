package com.bridgelabz.fundoouser.dto;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin ForgotPassworddto.java
 *  Execution:    
 *      
 *  Purpose:  Used as a dto class of forgot password
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Data
public class ForgotPassworddto {
	private String password;
	private String passwordCheck;
}
