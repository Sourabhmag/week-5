package com.bridgeLabz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgeLabz.dao.RetriveDataFromDB;
import com.bridgeLabz.model.Login;
import com.bridgeLabz.model.Registration;

@WebServlet("/forgot")
public class ForgotServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	static Login login = new Login();
	List<Registration> list = new ArrayList<Registration>();
	public void service(HttpServletRequest request,HttpServletResponse response)
	{
		
		login.setUsername(request.getParameter("email"));
		
		
		list = RetriveDataFromDB.getData(login);
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(list==null)
			out.println("Invalid Access Cridentials");
		else
		{
			for (Registration i : list) 
			{
				if (i.getEmail().equals(login.getUsername()))
				{
					try {
						response.sendRedirect("password.jsp");
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					 response.setContentType("text/html");
					 out.println("<script type=\"text/javascript\">");
					 out.println("alert('Email id is not matching');");
					 out.println("location='forgotPassword.jsp';");
					 out.println("</script>");
				}
			}
		}
	}	
}
