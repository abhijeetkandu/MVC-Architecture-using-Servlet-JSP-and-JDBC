<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="in.ak.model.User" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Profile Page</title>
	</head>

	<body>
		<% User user=(User) session.getAttribute("user_key"); 
		if(user == null){
			response.sendRedirect("login.jsp");
			return;
		}
			
			
		%>
			<h2>Welcome </h2>

			<h3> Name : <%= user.getName() %>
			</h3>
			<h3> City : <%= user.getCity() %>
			</h3>
			<h3> Gender : <%= user.getGender() %>
			</h3>
	</body>

	</html>