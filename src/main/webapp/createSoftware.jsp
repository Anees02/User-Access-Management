<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Software</title>
    <link rel="stylesheet" href="<%=application.getContextPath() %>/css/createSoftware.css"/>
</head>
<body>
    <div class="software-container">
 		<div class="header-container">
	        <div>
	            <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
	            
	        </div>
	        
	        <a href="LogoutServlet" class="logout-button">Logout</a>
	    </div>
        <h2>Create New Software</h2>
        <!-- Display error messages if any -->
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
            <div class="error">
                <%= error.equals("missingFields") ? "Please fill out all fields." : "An error occurred. Please try again." %>
            </div>
        <%
            }
        %>
        
        
        
        <form action="SoftwareServlet" method="post">
            <label for="softwareName">Software Name</label>
            <input type="text" id="softwareName" name="softwareName" placeholder="Enter software name" required>

            <label for="description">Description</label>
            <textarea id="description" name="description" placeholder="Enter a description for the software" required></textarea>

            <label>Access Levels</label>
            <div class="checkbox-group">
                <label><input type="checkbox" name="accessLevels" value="Read"> Read</label><br>
                <label><input type="checkbox" name="accessLevels" value="Write"> Write</label><br>
                <label><input type="checkbox" name="accessLevels" value="Admin"> Admin</label>
            </div>

            <button type="submit">Create Software</button>
        </form>
    </div>
</body>
</html>
