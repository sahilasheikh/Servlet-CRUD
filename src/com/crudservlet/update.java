package com.crudservlet;

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

/**
 * Servlet implementation class update
 */
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		

		int sr_no = Integer.parseInt(request.getParameter("sr_no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int number = Integer.parseInt(request.getParameter("number"));
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			
			PreparedStatement preparedStatement_select = connection.prepareStatement("select max(sr_no) from crud_user");
			
			ResultSet resultSet = preparedStatement_select.executeQuery();
			
			if (resultSet.next()) {
				
				PreparedStatement preparedStatement_insert = connection.prepareStatement("update crud_user set name=?, email=?, password=?, mobile_number=? where sr_no=?");
				
				preparedStatement_insert.setString(1, name);
				preparedStatement_insert.setString(2, email);
				preparedStatement_insert.setString(3, password);
				preparedStatement_insert.setInt(4, number);
				preparedStatement_insert.setInt(5, sr_no);
				
				int i = preparedStatement_insert.executeUpdate();
				
				connection.close();
				
				if (i==1) {
					printWriter.println("Data Updated Successfully");
				} else {
					printWriter.println("Failed");
				}
			}
				
		} catch (Exception e) {
			printWriter.println(e);
		}
		
	}

}
