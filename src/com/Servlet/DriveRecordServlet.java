package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DriveRecord.DriveRecordInfo;
import com.JDBC.JDBC;

public class DriveRecordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DriveRecordServlet() {
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

		PrintWriter out = response.getWriter();

		List<DriveRecordInfo> recordList = new ArrayList<DriveRecordInfo>();
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();

		String username = request.getParameter("UserName");
		System.out.println("-Username----->>>" + username);
		recordList = jdbc.RecordList(username);
		
		// ½«list×ª³ÉString
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < recordList.size(); i++) {
			buffer.append(String.valueOf(recordList.get(i).getRecordId()))
					.append(",").append(recordList.get(i).getTime())
					.append(",").append(recordList.get(i).getDangerLongitude())
					.append(",").append(recordList.get(i).getDangerLatitude())
					.append(",").append(recordList.get(i).getDangerSpeed())
					.append(",").append(recordList.get(i).getRecordType())
					.append("&");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		StringBuffer transportBuffer = new StringBuffer();
		transportBuffer.append(username).append("$").append(buffer.toString());
		System.out.println(transportBuffer.toString());
		out.print(transportBuffer.toString());
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
