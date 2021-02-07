package com.crudservlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.crudservlet.model.User;

public class User_DAO {
	
//	method for connection
	protected static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
//	method to insert the data
	public static int insert(User user) {
		int i = 0;
		try {
			Connection connection = User_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into crud_user values (?,?,?,?,?)");
			ps.setInt(1, user.getSr_no());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setLong(5, user.getNumber());
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	
	
}
