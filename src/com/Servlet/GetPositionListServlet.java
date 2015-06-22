package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;

public class GetPositionListServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	public GetPositionListServlet() {
		super();
	}

	
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
		
		String Result = null;
		String username = request.getParameter("Username");
		String number = request.getParameter("Number");
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		PrintWriter out = response.getWriter();
		Result = jdbc.getPositionString(username, number);
		out.print(Result);
		out.flush();
		out.close();
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
