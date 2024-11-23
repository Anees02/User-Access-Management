package com.project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.project.dao.UserDao;
import com.project.entity.User;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User usr = new User(username,password,role);
		
		UserDao udao = new UserDao();
		if(udao.addUser(usr)) {
			
			response.sendRedirect("login.jsp?success=success");
		}
		else {
			response.sendRedirect("signup?error=serverError");
		}
		
		
	}

}
