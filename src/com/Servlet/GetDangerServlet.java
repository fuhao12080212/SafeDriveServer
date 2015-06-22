package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DangerInfoHandler.DangerInfo;
import com.JDBC.JDBC;

public class GetDangerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GetDangerServlet() {
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
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		List<String> familyList = new ArrayList<String>();
		List<DangerInfo> dangerInfoList = new ArrayList<DangerInfo>();
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		PrintWriter out = response.getWriter();

		String familyNum = request.getParameter("SumNumber");
		System.out.println("-SumNumber-->>" + familyNum);
		for (int i = 0; i < Integer.valueOf(familyNum); i++) {
			String family = request.getParameter("Person" + i);
			familyList.add(family);
		}
		dangerInfoList = jdbc.DangerInfo(familyList);

		// List×ª³ÉString
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < dangerInfoList.size(); i++) {
			buffer.append(dangerInfoList.get(i).getName()).append(",")
					.append(dangerInfoList.get(i).getPhone()).append(",")
					.append(dangerInfoList.get(i).getDangerLongitude())
					.append(",")
					.append(dangerInfoList.get(i).getDangerLatitude())
					.append(",").append(dangerInfoList.get(i).getDangerSpeed())
					.append("&");
		}
		System.out.println("buffer--->>" + buffer);
		if (!buffer.toString().equals("")) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		System.out.println(buffer.toString());
		out.print(buffer.toString());
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
