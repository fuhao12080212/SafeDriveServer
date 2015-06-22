package com.ReceiveInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddUserBackToDB {

	private String username;
	private String advice;
	private String Time;
	private Connection connection;

	public AddUserBackToDB(String username, String Time, String advice,
			Connection connection) {
		this.username = username;
		this.advice = advice;
		this.Time = Time;
		this.connection = connection;
	}

	public String ToDB() {
		String Result = null;
		try {
			PreparedStatement pstmt = null;
			String sql = null;
			connection.createStatement();
			
			sql = "insert into userfeedback values(NULL,?,?,?)";
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, advice);
			pstmt.setString(3, Time);
			Result = String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Result;
	}
}
