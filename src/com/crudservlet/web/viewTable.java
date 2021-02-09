package com.crudservlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crudservlet.dao.User_DAO;
import com.crudservlet.model.User;

/**
 * Servlet implementation class viewTable
 */
public class viewTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		List<User> users = User_DAO.retrieveTable();
		
		out.println("<table align=\"center\" border=\"1\">");
		out.println("<tr><th>Sr_No</th><th>Name</th></th><th>Email</th><th>Password</th><th>Number</th><th>Update</th><th>Delete</th></tr>");
		for (User user:users) {
			out.println("<tr><th>" + user.getSr_no() + "</th><th>" + user.getName() + "</th><th>" + user.getEmail() + "</th><th>" + user.getPassword() + "</th><th>" + user.getNumber() + "</th><th><a href=\'updateUser?id="+ user.getSr_no() + "\'>Update</a></th><th><a href=\'deleteUser?id=" + user.getSr_no() + "\'>Delete</a>"
					+ "</th></tr>");
		}
		out.println("</table>");
		
	}

}
