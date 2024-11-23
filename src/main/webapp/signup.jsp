<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="<%=application.getContextPath() %>/css/signup.css"/>
</head>
<body>
    <div class="signup-container">
        <h2>Create an Account</h2>
       
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
            <div class="error">
                <%= error.equals("UsernameExists") ? "Username already exists. Please try a different one." : "An error occurred. Please try again." %>
            </div>
        <%
            }
        %>
        
        <form action="SignUpServlet" method="POST">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            
           
            <input type="hidden" name="role" value="Employee">
            
            <button type="submit">Sign Up</button>
        </form>
    </div>
</body>
</html>
