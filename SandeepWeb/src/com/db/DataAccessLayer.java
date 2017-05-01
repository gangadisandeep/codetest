package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import com.utils.StringUtils;

public class DataAccessLayer {
	public static String authenticate(String username, String password)
			throws RuntimeException {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new RuntimeException("Invalid Inputs!!");
		}
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sandeep", "root", "");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			String query = "select * from auth where email = '" + username+"';";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()){
				//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  "
				//		+ rs.getString(3));
				String dbEmail = rs.getString(1);
				String dbPassword = rs.getString(2);
				if(!password.equals(dbPassword)){
					System.err.println("Authentication failed!!");
					throw new RuntimeException("Authentication failed!!");
				}
				String dbSessionId = UUID.randomUUID().toString();
				stmt.executeUpdate("UPDATE auth set session_id = '" + dbSessionId + "' where email = '" + dbEmail+"';");
				return dbSessionId;
			}
			throw new RuntimeException("User - " + username + " doesn't exist!!");
		}catch(Exception e){
			System.err.println(e);
			throw new RuntimeException(e);
		}
		finally{
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					System.err.println(e);
				}
			}
		}
	}
}
