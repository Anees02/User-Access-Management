package com.project.helper;

import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class ConnectionProvider {
	
	private static String url = "jdbc:postgresql://leucinedb-anees03.e.aivencloud.com:23273/defaultdb";
	private static final String userDb = "avnadmin";
    private static final String pass = "AVNS_xECBut5Np1GAgcHrg6q";
    
	private static Connection con;
	
	public static Connection getConnection() {
		try {
			if(con == null || con.isClosed()) {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(url,userDb,pass);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
}
