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
 * Servlet implementation class deleteUser
 */
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int sr_no = Integer.parseInt(request.getParameter("id"));
		User user = new User();
		user.setSr_no(sr_no);
		int i = User_DAO.delete(user);
		if (i == 1) {
			request.getRequestDispatcher("viewTable").include(request, response);
			out.println("User Deleted");
		} else {
			request.getRequestDispatcher("viewTable").include(request, response);
			out.println("Failed");
		}
		
		
	}

}
