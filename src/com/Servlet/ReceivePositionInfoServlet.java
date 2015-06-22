package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;
import com.getPosition.Position;

public class ReceivePositionInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ReceivePositionInfoServlet() {
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
		List<Position> positionList = new ArrayList<Position>();
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		String uploadResult = null;
		PrintWriter out = response.getWriter();

		String username = request.getParameter("UserName");
		String MaxRecord = request.getParameter("MaxDriveRecord");
		String Position = request.getParameter("Position");
		
		String myinfo[] = Position.split("&");
		for (int i = 0; i < myinfo.length; i++) {
			String mydetail[] = myinfo[i].split(",");
			Position newPosition = new Position();
			newPosition.setLongitude(mydetail[0]);
			newPosition.setLatitude(mydetail[1]);
			positionList.add(newPosition);
		}
		
		uploadResult = jdbc.uploadPosition(username, MaxRecord, positionList);
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
