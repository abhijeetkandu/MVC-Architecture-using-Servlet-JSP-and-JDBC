<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

<%
    String success = (String) session.getAttribute("success_msg");
    if(success != null) {
%>
    <h3 style="color:green"><%= success %></h3>
<%
        session.removeAttribute("success_msg");
    }

    String error = (String) session.getAttribute("error_msg");
    if(error != null) {
%>
    <h3 style="color:red"><%= error %></h3>
<%
        session.removeAttribute("error_msg");
    }
%>

<form action="logForm" method="post">
    Email: <input type="text" name="email1" placeholder="Enter Your Email Here.." /> <br/> <br/>
    Password: <input type="password" name="pass1" placeholder="Enter Your Password Here.." /> <br/> <br/>
    <input type="submit" value="Login" />
</form>

<a href="register.jsp">Register Here</a>

</body>
</html>
