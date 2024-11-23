package com.project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.project.dao.SoftwareDao;
import com.project.entity.Software;

/**
 * Servlet implementation class SoftwareServlet
 */
public class SoftwareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("softwareName");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("accessLevels");
        // Validate input
        if (name == null || name.isEmpty() || description == null || description.isEmpty() || accessLevels == null || accessLevels.isEmpty()) {
            response.sendRedirect("createSoftware.jsp?error=missingFields");
            return;
        }
        
        Software software = new Software(name,description,accessLevels);
        System.out.println(software);

        // Insert the new software into the database
        SoftwareDao softwareDao = new SoftwareDao();
        
        if(softwareDao.addSoftware(software)) {
        	System.out.println("I am busy");
        	response.sendRedirect("createSoftware.jsp");
        }
        else {
        	response.sendRedirect("createSoftware.jsp?error=serverError");
        }
        
        

        
	}

}
