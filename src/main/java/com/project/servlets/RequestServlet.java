package com.project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.project.dao.RequestDao;
import com.project.dao.UserDao;
import com.project.entity.Request;
import com.project.entity.User;

/**
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String softwareId = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");
        
        HttpSession session = request.getSession();
        
        Integer userId =  (Integer) session.getAttribute("userId");
        
        System.out.println(userId);

        

        
        if (softwareId == null || accessType == null || reason == null || 
            softwareId.isEmpty() || accessType.isEmpty() || reason.isEmpty()) {
            response.sendRedirect("requestAccess.jsp?error=missingFields");
            return;
        }
        
        UserDao userDao = new UserDao();
        RequestDao requestDao = new RequestDao();
        User user = userDao.getUserById(userId);
        
        if(user != null) {
        	Request request1 = new Request(user.getId(), Integer.parseInt(softwareId), accessType, reason, "pending");
        	if(requestDao.addRequest(request1)) {
        		response.sendRedirect("requestAccess.jsp");
        	}
        	else {
        		response.sendRedirect("requestAccess.jsp?error=serverError");
        	}
        }
        else {
        	response.sendRedirect("login.jsp");
        }
        
        
	}

}
