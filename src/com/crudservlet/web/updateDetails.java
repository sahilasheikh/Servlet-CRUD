package com.crudservlet.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crudservlet.dao.User_DAO;
import com.crudservlet.model.User;

/**
 * Servlet implementation class updateDetails
 */
public class updateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		User user = new User();
		
//		getting parameters form html
		int sr_no = Integer.parseInt(request.getParameter("sr_no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		long number = Long.parseLong(request.getParameter("number"));
		
		user.setSr_no(sr_no);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setNumber(number);
		
		int i = User_DAO.update(user);
		
		if (i == 1) {
			request.getRequestDispatcher("viewTable").include(request, response);
			out.println("User\'s Details Updated");
		} else {
			request.getRequestDispatcher("updateUser").include(request, response);
			out.println("Failed");
		}
		
		
		
	}

}
