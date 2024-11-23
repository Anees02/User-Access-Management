package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.Request;

public class RequestDao {
	private static final String url = "jdbc:postgresql://leucinedb-anees03.e.aivencloud.com:23273/defaultdb";
    private static final String userDb = "avnadmin";
    private static final String pass = "AVNS_xECBut5Np1GAgcHrg6q";
    
    public boolean addRequest(Request request) {
    	try{
    		Class.forName("org.postgresql.Driver");
    		Connection conn = DriverManager.getConnection(url, userDb, pass);
    		
            String sql = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, request.getUserId());
            stmt.setInt(2, request.getSoftwareId());
            stmt.setString(3, request.getAccessType());
            stmt.setString(4, request.getReason());
            stmt.executeUpdate();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    	return false;
    }
    
    public List<Request> fetchPendingRequests(){
    	List<Request> requestList = new ArrayList<>();

        try {
        	Class.forName("org.postgresql.Driver");
    		Connection conn = DriverManager.getConnection(url, userDb, pass);
            
            String sql = "SELECT * FROM requests WHERE status='Pending'";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create a Request object with basic data
                    Request requestObj = new Request(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("software_id"),
                            rs.getString("access_type"),
                            rs.getString("reason"),
                            rs.getString("status")
                    );
                    
                    
                    
                    requestList.add(requestObj);
                }
               
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return requestList;
    }
    
    public boolean updatePending(Integer id,String approval) {
    	try{
    		Class.forName("org.postgresql.Driver");
    		Connection conn = DriverManager.getConnection(url, userDb, pass);
    		
    		String sql = "UPDATE requests SET status = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, approval);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    	return false;
    }

    
}
