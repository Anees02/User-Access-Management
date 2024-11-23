<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%=application.getContextPath() %>/css/login.css"/>
</head>
<body>
    <div class="login-container">
        <h2>Login to Your Account</h2>
        <!-- Display error messages if any -->
        <%
            String error = request.getParameter("error");
        	String success = request.getParameter("success");
            if (error != null) {
        %>
            <div class="error">
                <%
                    if (error.equals("InvalidCredentials")) {
                        out.print("Invalid username or password. Please try again.");
                    } else if (error.equals("InvalidRole")) {
                        out.print("Invalid role detected. Please contact the administrator.");
                    } else if (error.equals("ServerError")) {
                        out.print("A server error occurred. Please try again later.");
                    } else {
                        out.print("An unexpected error occurred.");
                    }
                %>
            </div>
        <%
            }
            
            
        %>
        <!-- Login Form -->
        <form action="LoginServlet" method="post">
        	<div class = "success">
       		
	        	<%
	        		if(success != null){
	        			out.print("Registeration Successfull! Please Login");
	        		}
	        	%>
       		</div>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
