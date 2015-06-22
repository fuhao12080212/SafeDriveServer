package com.ReceiveInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.getPosition.Position;

public class AddPositionInfoToDB {
	private String username;
	private String MaxRecord;
	private List<Position> positionList = new ArrayList<Position>();
	private Connection connection;

	public AddPositionInfoToDB(String username, String MaxRecord,
			List<Position> positionList, Connection connection) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.MaxRecord = MaxRecord;
		this.positionList = positionList;
		this.connection = connection;
	}

	// 1≈–∂œ¿‡–Õ°£Danger or Safe
	//
	public String WriteToDB() {
		int Result = 0;
		try {
			PreparedStatement pstmt = null;
			String sql = "create table "
					+ username
					+ MaxRecord
					+ "positionlist(id int(4) not null primary key auto_increment,"
					+ "longitude varchar(16) not null,latitude varchar(16) not null)";
			connection.createStatement();
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			
			for (int i = 0; i < positionList.size(); i++) {
				pstmt = null;
				sql = "insert into " + username + MaxRecord +
						"positionlist values(NULL,?,?)";
				connection.createStatement();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, positionList.get(i).getLongitude());
				pstmt.setString(2, positionList.get(i).getLatitude());
				Result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return String.valueOf(Result);
	}
}
