package com.UserManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManageDB {

	public UserManageDB() {
		// TODO Auto-generated constructor stub
	}

	public String Login(String username, String password, Connection connection) {
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from userdata where username=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String SQLpw = resultSet.getString("password");
				if (SQLpw.equals(password)) {
					return "pass";
				} else {
					return "not pass";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "not find";
	}

	public String IsExist(String username, Connection connection) {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection.createStatement();
			String sql = "select * from userdata where username=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				return "exist";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "not exist";
	}

	public String Register(String username, String password, String phone,
			String name, Connection connection) {
		PreparedStatement pstmt = null;
		String result = null;
		try {
			// 添加到用户表中
			connection.createStatement();
			// insert into user
			// values(NULL,"user1","user1","12345678910","user1","0.0","0.0","0","false","false")
			String sql = "insert into userdata values(NULL,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, phone);
			pstmt.setString(4, name);
			pstmt.setDouble(5, 0.0);
			pstmt.setDouble(6, 0.0);
			pstmt.setString(7, "0");
			pstmt.setString(8, "false");
			pstmt.executeUpdate();
			result = "ok";

			// 创建用户行驶记录表
			connection.createStatement();
			sql = "CREATE TABLE ? " +
					"(ID int(4) NOT NULL AUTO_INCREMENT," +
					"RecordID int(4) NOT NULL," +
					"Time varchar(16) NOT NULL," +
					"Danger_Longitude double(16,8) DEFAULT NULL," +
					"Danger_Latitude double(16,8) DEFAULT NULL," +
					"Danger_Speed varchar(8) DEFAULT NULL," +
					"RecordType varchar(16) NOT NULL,PRIMARY KEY (`ID`))";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username+"driverecord");
			pstmt.executeUpdate();
			result = "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String IsMatch(String username, String phone, Connection connection) {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String result = null;
		try {
			connection.createStatement();
			String sql = "select * from userdata where username=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				String SQLphone = resultSet.getString("phone");
				if (SQLphone.equals(phone)) {
					result = "match";
				} else {
					result = "not match";
				}
			} else {
				result = "not match";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String ChangePW(String username, String password,
			Connection connection) {
		PreparedStatement pstmt = null;
		try {
			connection.createStatement();
			String sql = "update userdata set password=? where username=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ok";
	}
}
