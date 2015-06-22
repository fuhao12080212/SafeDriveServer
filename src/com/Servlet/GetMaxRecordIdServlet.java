package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;

public class GetMaxRecordIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public GetMaxRecordIdServlet() {
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
		String MaxRecordId;
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		PrintWriter out = response.getWriter();

		String username = request.getParameter("Username");

		MaxRecordId = jdbc.getMaxRecordID(username);
		out.print(MaxRecordId);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
