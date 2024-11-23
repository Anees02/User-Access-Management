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

public class SoftwareDao {
	private static final String url = "jdbc:postgresql://leucinedb-anees03.e.aivencloud.com:23273/defaultdb";
    private static final String userDb = "avnadmin";
    private static final String pass = "";
    
	public List<Software> giveList(){
		List<Software> softwareList = new ArrayList<>();

        // Fetch the software list from the database
        try {
        	Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, userDb,pass);
            String sql = "SELECT * FROM software"; // Simple query to fetch all software
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet  rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create a Software object for each row in the result set
                    Software software = new Software(
                            rs.getInt("id"),          // Software ID
                            rs.getString("name"),     // Software name
                            rs.getString("description"), // Software description
                            rs.getString("access_levels") // Access levels
                    );
                    softwareList.add(software); // Add the software to the list
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
        //System.out.println("I am in the dao "+softwareList);
        return softwareList;
	}
	public boolean addSoftware(Software software) {
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, userDb,pass);
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
		try{

			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,userDb,pass);
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
