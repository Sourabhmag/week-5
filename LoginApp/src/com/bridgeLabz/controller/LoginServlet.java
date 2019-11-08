package com.bridgeLabz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.bridgeLabz.dao.RetriveDataFromDB;
import com.bridgeLabz.model.Login;
import com.bridgeLabz.model.Registration;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Login login = new Login();
		login.setUsername(request.getParameter("username"));
		login.setPassword(request.getParameter("password"));

		//HttpSession session = request.getSession();
		List<Registration> list = new ArrayList<Registration>();
		list = RetriveDataFromDB.getData(login);
		PrintWriter out = response.getWriter();
			for (Registration i : list) {
				if (i.getEmail().equals(login.getUsername()) && i.getPassword().equals(login.getPassword())) {
					request.setAttribute("list", list);
					RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					 response.setContentType("text/html");
					 out.println("<script type=\"text/javascript\">");
					 out.println("alert('Invalid access cridentials');");
					 out.println("location='login.jsp';");
					 out.println("</script>");

			}

		}

	}
}
