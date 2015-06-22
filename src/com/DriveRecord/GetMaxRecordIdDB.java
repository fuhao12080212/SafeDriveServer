package com.DriveRecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetMaxRecordIdDB {

	private Connection connection;
	private String username;

	public GetMaxRecordIdDB(String username, Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.username = username;
	}

	public String GetID() {
		String MaxRecordID = null;
		try {
			Statement stmt = null;
			ResultSet resultSet = null;
			String sql = "select max(RecordID) as RecordID from " + username
					+ "driverecord";
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				int result = resultSet.getInt("RecordID");
				MaxRecordID = String.valueOf(result);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return MaxRecordID;
	}
}
