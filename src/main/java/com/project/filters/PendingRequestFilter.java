package com.project.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
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

@WebFilter("/pending")
public class PendingRequestFilter extends HttpFilter implements Filter {
       
    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session =  request.getSession();

        // Check if pendingRequests is already in the session
        List<Request> pendingRequests = (List<Request>) session.getAttribute("pendingRequests");

        // If the list is not in the session, fetch it from the database
        
        pendingRequests = fetchPendingRequestsFromDatabase(session);
        // Store the fetched list in the session
        session.setAttribute("pendingRequests", pendingRequests);
        
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	public List<Request> fetchPendingRequestsFromDatabase(HttpSession session) {
		RequestDao requestDao = new RequestDao();
		UserDao userDao = new UserDao();
		SoftwareDao softwareDao = new SoftwareDao();
		List<Request> requestList = requestDao.fetchPendingRequests();
   	 
	   	 for(Request req: requestList) {
	   		 System.out.println(req);
	   		 User u = userDao.getUserById(req.getUserId());
	   		 Software s = softwareDao.getSoftwareById(req.getSoftwareId());
	   		 session.setAttribute("username_"+req.getId(), u.getUsername());
	   		 session.setAttribute("softwareName_"+req.getId(), s.getName());
	   	 }
	   	 
		return requestList;
	}

}
