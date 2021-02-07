package com.crudservlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crudservlet.dao.User_DAO;
import com.crudservlet.model.User;

/**
 * Servlet implementation class insert
 */
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public insert() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
//		getting parameters form html
		int sr_no = 0;
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		long number = Long.parseLong(request.getParameter("number"));
		
		try {
		
//			connection for getting max sr_no
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			PreparedStatement preparedStatement = connection.prepareStatement("select max(sr_no) from crud_user");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				sr_no = resultSet.getInt(1);
				sr_no++;
				
//				setting the values
				save(sr_no, name, email, password, number, out);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		}
		
	}

//	method to save/insert the values into oracle database
	private void save(int sr_no, String name, String email, String password, long number, PrintWriter out) {
		
		User user = new User();
		user.setSr_no(sr_no);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setNumber(number);
		
		int i = User_DAO.insert(user);
		if (i == 1) {
			out.println("User Added");
		} else {
			out.println("Failed");
		}
		
	}

}
