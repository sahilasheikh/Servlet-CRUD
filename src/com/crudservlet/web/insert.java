package com.crudservlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		
		String button = request.getParameter("button");
		
		
		
//		condition to save/insert the user data into database
		if (button.equals("SAVE")) {
			
//			getting parameters form html
			int sr_no = 0;
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			long number = Long.parseLong(request.getParameter("number"));
			
			
			try {
				
//				connection for getting max sr_no
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
				PreparedStatement preparedStatement = connection.prepareStatement("select max(sr_no) from crud_user");
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					sr_no = resultSet.getInt(1);
					sr_no++;
//					setting the values
					User user = new User();
					user.setSr_no(sr_no);
					user.setName(name);
					user.setEmail(email);
					user.setPassword(password);
					user.setNumber(number);
					int i = User_DAO.insert(user);
					if (i == 1) {
						out.println("<p align=\"center\">User Added</p>");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
						requestDispatcher.include(request, response);
					} else {
						out.println("<p align=\"center\">Failed</p>");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
						requestDispatcher.include(request, response);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				out.println(e);
			}
			
		}

//		condition to retrieve complete table from database
		else if (button.equals("VIEW TABLE")) {
			
			List<User> users = User_DAO.retrieveTable();
			
			out.println("<table align=\"center\" border=\"1\">");
			for (User user:users) {
				out.println("<tr><th>" + user.getSr_no() + "</th><th>" + user.getName() + "</th><th>" + user.getEmail() + "</th><th>" + user.getPassword() + "</th><th>" + user.getNumber() + "</tr>");
			}
			out.println("</table>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.include(request, response);
			
		}
//		condition to retrieve a column from user data from database
		else if (button.equals("RETRIEVE")) {
			int sr_no = Integer.parseInt(request.getParameter("sr_no"));
			
//			setting the values
			User user = new User();
			user.setSr_no(sr_no);
			 
//			getting the user
			user = User_DAO.retrieve(sr_no);
			out.println("<table align=\"center\" border=\"1\"><tr><th>" + user.getSr_no() + "</th><th>" + user.getName() + "</th><th>" + user.getEmail() + "</th><th>" + user.getPassword() + "</th><th>" + user.getNumber() + "</tr></table>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.include(request, response);
		}
		
		else {
			out.println("Invalid Input");
		}
		
	}

}
