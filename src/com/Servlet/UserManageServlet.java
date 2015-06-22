package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;

public class UserManageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserManageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		PrintWriter out = response.getWriter();

		String method = request.getParameter("Method");
		System.out.println("-Method--->>>" + method);

		if (method.equals("login")) {
			String loginResult = null;
			String username = request.getParameter("Username");
			String password = request.getParameter("Password");
			loginResult = jdbc.Login_Authentication(username, password);

			out.print(loginResult);
		} else if (method.equals("register")) {
			String registerResult = null;
			String username = request.getParameter("Username");
			String password = request.getParameter("Password");
			String phone = request.getParameter("Phone");
			String name = request.getParameter("Name");
			registerResult = jdbc.Register(username, password, phone, name);

			out.print(registerResult);
		} else if (method.equals("IsMatch")) {
			String MatchResult = null;
			String username = request.getParameter("Username");
			String phone = request.getParameter("Phone");
			MatchResult = jdbc.IsMatch(username, phone);

			out.print(MatchResult);
		} else if (method.equals("changePW")) {
			String ChangePWResult = null;
			String username = request.getParameter("Username");
			String repassword = request.getParameter("Password");
			ChangePWResult = jdbc.ChangePW(username, repassword);

			out.print(ChangePWResult);
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
