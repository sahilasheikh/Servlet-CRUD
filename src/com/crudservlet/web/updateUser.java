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
 * Servlet implementation class updateUser
 */
public class updateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateUser() {
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
		
		User user = User_DAO.retrieve(sr_no);
		
		out.println("<form action=\"updateDetails\" method=\"post\">");
		out.println("<table align=\"center\" border=\"1\">");
		out.println("<tr><td><input type=\"hidden\" name=\"sr_no\" value=\'" + user.getSr_no() + "\' ></td</tr>");
		out.println("<tr><td><input type=\"name\" name=\"name\" value=\'" + user.getName() + "\' ></td</tr>");
		out.println("<tr><td><input type=\"email\" name=\"email\" value=\'" + user.getEmail() + "\' ></td</tr>");
		out.println("<tr><td><input type=\"password\" name=\"password\" value=\'" + user.getPassword() + "\' ></td</tr>");
		out.println("<tr><td><input type=\"tel\" name=\"number\" value=\'" + user.getNumber() + "\' ></td</tr>");
		out.println("<tr><th><input type=\"submit\" value=\"UPDATE USER\" name=\"button\"></th></tr>");
		out.println("</table></form>");
		
	}

}
