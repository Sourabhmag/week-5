package com.bridgelabz.fundoouser.dto;
import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin Logindto.java
 *  Execution:    
 *      
 *  Purpose:  Used as a dto class of login user
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Data
public class Logindto {
	private String username;
	private String password;
}
