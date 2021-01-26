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
 * Servlet implementation class retrieve
 */
public class retrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retrieve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		try {
			
			int sr_no = Integer.parseInt(request.getParameter("sr_no"));
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			
			PreparedStatement preparedStatement_retrieve = connection.prepareStatement("select * from crud_user where sr_no = ?");
			
			preparedStatement_retrieve.setInt(1, sr_no);
			
			ResultSet resultSet = preparedStatement_retrieve.executeQuery();
			
			while (resultSet.next()) {
				
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);
				Long number = resultSet.getLong(5);
				
				printWriter.println("sr_no: " + sr_no);
				printWriter.println("<br>name: " + name);
				printWriter.println("<br>email: " + email);
				printWriter.println("<br>password: " + password);
				printWriter.println("<br>number: " + number + "<br>");
				
				connection.close();
				
			}
			
		} catch (Exception e) {
			printWriter.println(e);
		}
		
		
		
	}

}
