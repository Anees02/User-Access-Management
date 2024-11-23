package com.project.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.entity.User;

public class UserDao  {
	private static final String url = "jdbc:postgresql://leucinedb-anees03.e.aivencloud.com:23273/defaultdb";
    private static final String userDb = "avnadmin";
    private static final String pass = "AVNS_xECBut5Np1GAgcHrg6q";
    
	public boolean addUser(User user) {
		
		try{

			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,userDb,pass);
			String query = "INSERT INTO users(username,password,role) VALUES(?,?,?);";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, user.getUsername());
			p.setString(2, user.getPassword());
			p.setString(3, user.getRole());
			p.executeUpdate();
			p.close();
			con.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace()	;
		}
		
		return false;
	}
	public User getUser(String username,String password) {
		User user = null;
		try{

			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,userDb,pass);
			String query = "SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"';";
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(query);
			int id = 0;
			String us= "",pa="",role="";
			if(rs.next()) {
				id = rs.getInt("id");
				us = rs.getString("username");
				pa = rs.getString("password");
				role = rs.getString("role");
			}
			if(id != 0)
				user = new User(id,us,pa,role);
			
			con.close();
			st.close();
		}catch(Exception e) {
			e.printStackTrace()	;
		}
		return user;
	}
	
	public User getUserById(int userId) {
		User user = null;
		try{

			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,userDb,pass);
			String query = "SELECT * FROM users WHERE id = '"+userId+"';";
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(query);
			int id = 0;
			String us= "",pa="",role="";
			if(rs.next()) {
				id = rs.getInt("id");
				us = rs.getString("username");
				pa = rs.getString("password");
				role = rs.getString("role");
			}
			if(id != 0)
				user = new User(id,us,pa,role);
			con.close();
			st.close();
			
		}catch(Exception e) {
			e.printStackTrace()	;
		}
		return user;
	}
}
