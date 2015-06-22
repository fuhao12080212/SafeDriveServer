package com.DriveRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDriveRecord {
	private String username;
	private List<DriveRecordInfo> RecordList = new ArrayList<DriveRecordInfo>();
	private Connection connection;

	public GetDriveRecord(String username, Connection connection) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.connection = connection;
	}

	public List<DriveRecordInfo> getRecordList() {

		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from " + username + "driverecord";
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				DriveRecordInfo newInfo = new DriveRecordInfo();
				newInfo.setRecordId(resultSet.getInt("RecordID"));
				newInfo.setTime(resultSet.getString("Time"));
				newInfo.setDangerLongitude(String.valueOf(resultSet
						.getDouble("Danger_Longitude")));
				newInfo.setDangerLatitude(String.valueOf(resultSet
						.getDouble("Danger_Latitude")));
				newInfo.setDangerSpeed(resultSet.getString("Danger_Speed"));
				newInfo.setRecordType(resultSet.getString("RecordType"));
				RecordList.add(newInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RecordList;
	}
}
