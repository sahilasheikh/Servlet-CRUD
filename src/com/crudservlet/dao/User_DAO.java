package com.crudservlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
//	method to retrieve table
	public static List<User> retrieveTable() {
		List<User> users = new ArrayList<User>();
		try {
			Connection connection = User_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from crud_user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setSr_no(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setNumber(rs.getLong(5));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
//	condition to retrieve a column from user data from database
	public static User retrieve(int sr_no) {
		User user = new User();
		try {
			Connection connection = User_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from crud_user where sr_no = ?");
			ps.setInt(1, sr_no);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setSr_no(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setNumber(rs.getLong(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
//	method to delete user
	public static int delete(User user) {
		int i = 0;
		try {
			Connection connection = User_DAO.getConnection();
					PreparedStatement ps = connection.prepareStatement("delete crud_user where sr_no = ?");
					ps.setInt(1, user.getSr_no());
					i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
//	method to delete user
	public static int update(User user) {
		int i = 0;
		try {
			Connection connection = User_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("update crud_user set name = ?, email = ?, password = ?, mobile_number = ? where sr_no = ?");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setDouble(4, user.getNumber());
			ps.setInt(5, user.getSr_no());
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return i;
	}
	
}
