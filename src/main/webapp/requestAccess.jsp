<%@page import="com.project.entity.Software"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Request Access</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .request-container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .request-container h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .request-container form {
            display: flex;
            flex-direction: column;
        }

        .request-container label {
            text-align: left;
            margin-bottom: 5px;
            font-size: 14px;
            color: #555;
        }

        .request-container select,
        .request-container textarea {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 100%;
        }

        .request-container button {
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }

        .request-container button:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            font-size: 14px;
            margin-bottom: 15px;
        }
        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #007bff;
            color: white;
            padding: 15px 20px;
            border-radius: 8px;
        }

        .header-container h1 {
            margin: 0;
            font-size: 20px;
        }

        .header-container p {
            margin: 0;
            font-size: 14px;
        }

        .logout-button {
            padding: 10px 20px;
            background-color:  #F96E2A;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
            text-decoration: none;
        }

        .logout-button:hover {
            background-color: red;
        }
    </style>
</head>
<body>
    <div class="request-container">
    	<div class="header-container">
	        
	        <div>
	            <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
	            
	        </div>
	        
	        <a href="LogoutServlet" class="logout-button">Logout</a>
	    </div>
        <h2>Request Access to Software</h2>

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

        <!-- Access Request Form -->
        <form action="RequestServlet" method="post">
            <label for="software">Software</label>
            <select id="software" name="softwareId" required>
                <%
                    // Get the softwareList from the request attribute
                    
                    List<Software> softwareList = (List<Software>) session.getAttribute("softwareList");
                    for (Software software : softwareList) {
                %>
                    <option value="<%= software.getId() %>"><%= software.getName() %></option>
                <%
                    }
                %>
            </select>

            <label for="accessType">Access Type</label>
            <select id="accessType" name="accessType" required>
                <option value="Read">Read</option>
                <option value="Write">Write</option>
                <option value="Admin">Admin</option>
            </select>

            <label for="reason">Reason</label>
            <textarea id="reason" name="reason" placeholder="Provide a reason for your request" required></textarea>

            <button type="submit">Request Access</button>
        </form>
    </div>
</body>
</html>
