<%@page import="com.bridgeLabz.model.Registration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	h1 {text-align:center;}
</style>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
<%@page import= "java.util.ArrayList"%>
<%@page import ="java.util.List"%> 
<%@page import ="com.bridgeLabz.model.Registration"%>
<h1 >Welcome</h1>
<%
	List<Registration> list = (ArrayList<Registration>)request.getAttribute("list");%>
<table border="1" align="center">
	<tr>
		<th><%out.println("Name"); %></th>
		<th><%out.println("Phone Number"); %></th>
		<th><%out.println("Email ID"); %></th>
		<th><%out.println("Password"); %></th>
	</tr>
<%
	for(int i=0;i<list.size();i++)
	{%>
		<tr>
			<th><% out.println(list.get(i).getName()+"\n"); %></th>
			<th><% out.println(list.get(i).getPhoneNo()+"\n"); %></th>
			<th><% out.println(list.get(i).getEmail()+"\n"); %></th>
			<th><% out.println(list.get(i).getPassword()+"\n"); %></th>
		</tr>
		
	<%} %>
</table>
</body>
</html>