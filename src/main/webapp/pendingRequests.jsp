<%@page import="com.project.entity.Request"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .card {
            background: #fff;
            padding: 20px;
            margin: 10px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        .card h3 {
            font-size: 18px;
            margin: 0 0 10px;
        }

        .card p {
            font-size: 14px;
            color: #555;
            margin-bottom: 10px;
        }

        .action-button {
            padding: 8px 12px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }

        .action-button.reject {
            background-color: #dc3545;
        }

        .action-button:hover {
            opacity: 0.9;
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
        	margin-left: 20px;
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
   	<div class="header-container">
        
        <div>
            <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
            
        </div>
        
        <a href="LogoutServlet" class="logout-button">Logout</a>
    </div>
    <h2>Pending Access Requests</h2>
    <div class="card-container">
        <%
            // Retrieve the list of pending requests from the request attribute
            List<Request> requestList = (List<Request>) session.getAttribute("pendingRequests");

            if (requestList != null && !requestList.isEmpty()) {
                for (Request req : requestList) {
                	String username = (String) session.getAttribute("username_" + req.getId());
                    String softwareName = (String) session.getAttribute("softwareName_" + req.getId());
        %>
        <div class="card">
            <h3>Request by: <%= username %></h3>
            <p>Software: <%= softwareName %></p>
            <p>Access Type: <%= req.getAccessType() %></p>
            <p>Reason: <%= req.getReason() %></p>
            <p>Status: <strong><%= req.getStatus() %></strong></p>
            <a href="ApprovalServlet?action=approve&requestId=<%= req.getId() %>" class="action-button">Approve</a>
            <a href="ApprovalServlet?action=reject&requestId=<%= req.getId() %>" class="action-button reject">Reject</a>
        </div>
        <%
                }
            } else {
        %>
        <p>No pending requests found.</p>
        <%
            }
        %>
    </div>
</body>
</html>
