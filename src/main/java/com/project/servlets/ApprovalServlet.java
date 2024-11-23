package com.project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.project.dao.RequestDao;


public class ApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); // Either "approve" or "reject"
        String requestId = request.getParameter("requestId");
        
        
        
        // Validate parameters
        if (action == null || requestId == null || requestId.isEmpty()) {
            response.sendRedirect("pendingRequests.jsp?error=missingParams");
            return;
        }
        
        RequestDao requestDao = new RequestDao();
        
        if(requestDao.updatePending(Integer.parseInt(requestId), action)) {
        	response.sendRedirect("pendingRequests.jsp");
        }
        else {
        	response.sendRedirect("pendingRequests.jsp?error=serverError");
        }
	}

}
