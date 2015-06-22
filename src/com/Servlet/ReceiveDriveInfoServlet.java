package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DriveRecord.DriveRecordInfo;
import com.JDBC.JDBC;

public class ReceiveDriveInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ReceiveDriveInfoServlet() {
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

		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		DriveRecordInfo newInfo = new DriveRecordInfo();
		String uploadResult = null;
		PrintWriter out = response.getWriter();

		String username = request.getParameter("UserName");

		String RecordId = request.getParameter("RecordId");
		newInfo.setRecordId(Integer.valueOf(RecordId));

		String Time = request.getParameter("Time");
		newInfo.setTime(Time);

		String DangerLongitude = request.getParameter("DangerLongitude");
		newInfo.setDangerLongitude(DangerLongitude);

		String DangerLatitude = request.getParameter("DangerLatitude");
		newInfo.setDangerLatitude(DangerLatitude);

		String DangerSpeed = request.getParameter("DangerSpeed");
		newInfo.setDangerSpeed(DangerSpeed);

		String RecordType = request.getParameter("RecordType");
		newInfo.setRecordType(RecordType);

		uploadResult = jdbc.uploadDanger(newInfo, username);
		out.print(uploadResult);
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
