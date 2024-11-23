package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.Software;
import com.project.entity.User;
import com.project.helper.ConnectionProvider;

public class SoftwareDao {
	
    
	public List<Software> giveList(){
		List<Software> softwareList = new ArrayList<>();

		Connection conn = ConnectionProvider.getConnection();
        try {
        	
            String sql = "SELECT * FROM software"; 
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet  rs = stmt.executeQuery()) {
                while (rs.next()) {
                    
                    Software software = new Software(
                            rs.getInt("id"),          
                            rs.getString("name"),     
                            rs.getString("description"), 
                            rs.getString("access_levels") 
                    );
                    softwareList.add(software); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
        
        return softwareList;
	}
	public boolean addSoftware(Software software) {
		Connection conn = ConnectionProvider.getConnection();
		try {
			
            String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, software.getName());
                stmt.setString(2, software.getDescription());
                stmt.setString(3, software.getAccessLevel());
                stmt.executeUpdate();
                
            }
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
	}
	
	public Software getSoftwareById(Integer softwareId) {
		Software software = null;
		Connection con = ConnectionProvider.getConnection();
		try{

			
			String query = "SELECT * FROM software WHERE id = '"+softwareId+"';";
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(query);
			int id = 0;
			String name= "",description="",accessLevels="";
			if(rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				description = rs.getString("description");
				accessLevels = rs.getString("access_levels");
			}
			if(id != 0)
				software = new Software(id,name,description,accessLevels);
			st.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace()	;
		}
		return software;
	}
}
