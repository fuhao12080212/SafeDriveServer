package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;

public class ReceiveAdviceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ReceiveAdviceServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		
		String uploadResult = null;
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("UserName");
		String time = request.getParameter("Time");
		String UserBackText = request.getParameter("UserBackText");
		
		uploadResult = jdbc.uploadAdvice(username, time, UserBackText);
		out.print(uploadResult);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
