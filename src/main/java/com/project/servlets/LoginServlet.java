package com.project.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.project.dao.RequestDao;
import com.project.dao.SoftwareDao;
import com.project.dao.UserDao;
import com.project.entity.Request;
import com.project.entity.Software;
import com.project.entity.User;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//System.out.print(username+" hello "+password);
		
		UserDao userDao = new UserDao();
		SoftwareDao softwareDao = new SoftwareDao();
		RequestDao requestDao = new RequestDao();
		
		User user = userDao.getUser(username, password);
		
		if(user != null) {
			 
             request.setAttribute("username", user.getUsername());
             request.setAttribute("role",user.getRole());
             
             HttpSession session = request.getSession();
             session.setAttribute("username", user.getUsername());
             session.setAttribute("userId", user.getId());
             System.out.println(user);
             switch (user.getRole().toLowerCase()) {
                 case "employee":
                	 
         			List<Software> softwareList = softwareDao.giveList();
             		
                     session.setAttribute("softwareList", softwareList);
                     
                     response.sendRedirect("requestAccess.jsp");
                     break;
                 case "manager":
                	 
                     response.sendRedirect("pendingRequests.jsp");
                     break;
                 case "admin":
                     response.sendRedirect("createSoftware.jsp");
                     break;
                 default:
                     response.sendRedirect("login.jsp?error=InvalidRole");
                     break;
             }
		}
		else {
			response.sendRedirect("login.jsp?error=InvalidCredentials");
		}
		
	}

}
