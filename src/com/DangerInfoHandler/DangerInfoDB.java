package com.DangerInfoHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DangerInfoDB {

	private List<String> familylist;
	private Connection connection;

	public DangerInfoDB(List<String> familylist, Connection connection) {
		// TODO Auto-generated constructor stub
		this.familylist = familylist;
		this.connection = connection;
	}

	public List<DangerInfo> getDangerInfo() {
		List<DangerInfo> dangerInfoList = new ArrayList<DangerInfo>();
		try {
			for (int i = 0; i < familylist.size(); i++) {
				PreparedStatement pstmt = null;
				ResultSet resultSet = null;
				connection.createStatement();
				String sql = "select * from userdata where username=?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, familylist.get(i));
				resultSet = pstmt.executeQuery();
				String Danger = null;
				while (resultSet.next()) {
					Danger = resultSet.getString("InDanger");
					if (Danger.equals("ture")) {
						DangerInfo newInfo = new DangerInfo();
						newInfo.setName(resultSet.getString("username"));
						newInfo.setPhone(resultSet.getString("phone"));
						newInfo.setDangerLongitude(String.valueOf(resultSet
								.getDouble("current_Longitude")));
						newInfo.setDangerLatitude(String.valueOf(resultSet
								.getDouble("current_Latitude")));
						newInfo.setDangerSpeed(resultSet
								.getString("current_speed"));
						dangerInfoList.add(newInfo);

						// 读取到值以后将危险设置为false
						pstmt = null;
						connection.createStatement();
						sql = "update userdata set InDanger='false' where username=?";
						pstmt = connection.prepareStatement(sql);
						pstmt.setString(1, familylist.get(i));
						pstmt.executeUpdate();
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dangerInfoList;
	}
}
