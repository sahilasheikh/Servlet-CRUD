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
		
		try {
			
			int sr_no = Integer.parseInt(request.getParameter("sr_no"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			int number = Integer.parseInt(request.getParameter("number"));
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			
			PreparedStatement preparedStatement_update = connection.prepareStatement("");
			preparedStatement_update.setInt(1, sr_no);
			preparedStatement_update.setString(2, name);
			preparedStatement_update.setString(3, email);
			preparedStatement_update.setString(4, password);
			preparedStatement_update.setInt(5, number);
			
			
			
		} catch (Exception e) {
			printWriter.println(e);
		}
		
	}

}
