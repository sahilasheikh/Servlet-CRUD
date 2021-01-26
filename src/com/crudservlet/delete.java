package com.crudservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class delete
 */
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete() {
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
			
			PreparedStatement preparedStatement = connection.prepareStatement("delete crud_user where sr_no = ?");
			preparedStatement.setInt(1, sr_no);
			
			int i = preparedStatement.executeUpdate();
			
			if (i == 1) {
				printWriter.println("Data Deleted");
			} else {
				printWriter.println("Failed");
			}
			
			
		} catch (Exception e) {
			printWriter.println(e);
		}
		
	}

}
