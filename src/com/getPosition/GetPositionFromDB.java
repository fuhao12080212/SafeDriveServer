package com.getPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetPositionFromDB {
	private String username;
	private String number;
	private Connection connection;

	public GetPositionFromDB(String username, String number,
			Connection connection) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.number = number;
		this.connection = connection;
	}

	public String GetPositionInfo() {
		String PositionString = null;
		List<Position> list = new ArrayList<Position>();
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			String sql = "select * from " + username + number + "positionlist";
			connection.createStatement();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Position position = new Position();
				position.setLongitude(resultSet.getString("longitude"));
				position.setLatitude(resultSet.getString("latitude"));
				list.add(position);
			}
			PositionString = positionListToString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return PositionString;
	}
	
	private String positionListToString(List<Position> positionList) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < positionList.size(); i++) {
			buffer.append(positionList.get(i).getLongitude()).append(",")
					.append(positionList.get(i).getLatitude()).append("&");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}
}
