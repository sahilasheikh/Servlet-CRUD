package com.crudservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class index
 */
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String button = request.getParameter("submit");
		String retrieve = request.getParameter("retrieve");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		
		
//		insert button
		if (button.equals("  Insert   ")) {
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("insert.html");
			requestDispatcher.forward(request, response);
			
		}
		
//		retrieve button
		else if (button.equals("Retrieve")) {
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("retrieve.html");
			requestDispatcher.forward(request, response);
			
		}

//		update button
		else if (button.equals(" Update ")) {
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.html");
			requestDispatcher.forward(request, response);
			
		}

//		delete button
		else if (button.equals("  Delete  ")) {
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("delete.html");
			requestDispatcher.forward(request, response);
			
		}
		
//		else block
		else {
			
			PrintWriter printWriter = response.getWriter();
			printWriter.println("Input Error");
			
		}
		
		
	}

}
