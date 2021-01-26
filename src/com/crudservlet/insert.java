package com.crudservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

/**
 * Servlet implementation class insert
 */
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		int sr_no = 0;
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
				
				sr_no = resultSet.getInt(1);
				sr_no++;
				
				PreparedStatement preparedStatement_insert = connection.prepareStatement("insert into crud_user values(?,?,?,?,?)");
				preparedStatement_insert.setInt(1, sr_no);
				preparedStatement_insert.setString(2, name);
				preparedStatement_insert.setString(3, email);
				preparedStatement_insert.setString(4, password);
				preparedStatement_insert.setInt(5, number);
				
				int i = preparedStatement_insert.executeUpdate();
				
				connection.close();
				
				if (i==1) {
					printWriter.println("Data Inserted Successfully");
				} else {
					printWriter.println("Failed");
				}
				
			}
			
		} catch (Exception e) {
			printWriter.println(e);
		}
		
	}

}
