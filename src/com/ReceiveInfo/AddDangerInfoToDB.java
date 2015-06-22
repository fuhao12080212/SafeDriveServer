package com.ReceiveInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.DriveRecord.DriveRecordInfo;

public class AddDangerInfoToDB {
	private DriveRecordInfo uploadInfo;
	private Connection connection;
	private String username;

	public AddDangerInfoToDB(DriveRecordInfo uploadInfo, String username,
			Connection connection) {
		// TODO Auto-generated constructor stub
		this.uploadInfo = uploadInfo;
		this.connection = connection;
		this.username = username;
	}

	// 1�ж����͡�Danger or Safe
	//
	public String ChangeDB() {
		String Result = null;
		try {
			PreparedStatement pstmt = null;
			String sql = null;
			connection.createStatement();
			
			if (uploadInfo.getRecordType().equals("danger")) {
				// �޸��û����ֵ�����ڼ��˼�ع���
				sql = "update userdata set current_Longitude=?,current_Latitude=?,"
						+ "current_speed=?,InDanger='ture' where username=?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setDouble(1,
						Double.valueOf(uploadInfo.getDangerLongitude()));
				pstmt.setDouble(2,
						Double.valueOf(uploadInfo.getDangerLatitude()));
				pstmt.setString(3, uploadInfo.getDangerSpeed());
				pstmt.setString(4, username);
				pstmt.executeUpdate();
				Result = String.valueOf(pstmt.executeUpdate());
				
				// ���Ӽ�¼��ļ�¼���洢Σ����Ϣ��
				sql = "insert into " + username + "driverecord"
						+ " values(NULL,?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, uploadInfo.getRecordId());
				pstmt.setString(2, uploadInfo.getTime());
				pstmt.setDouble(3, Double.valueOf(uploadInfo.getDangerLongitude()));
				pstmt.setDouble(4, Double.valueOf(uploadInfo.getDangerLatitude()));
				pstmt.setString(5, uploadInfo.getDangerSpeed());
				pstmt.setString(6, uploadInfo.getRecordType());
				Result = String.valueOf(pstmt.executeUpdate());
			} else if (uploadInfo.getRecordType().equals("safe")) {
				// ���Ӽ�¼��ļ�¼���洢Σ����Ϣ��
				sql = "insert into " + username + "driverecord"
						+ " values(NULL,?,?,NULL,NULL,NULL,?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, uploadInfo.getRecordId());
				pstmt.setString(2, uploadInfo.getTime());
				pstmt.setString(3, uploadInfo.getRecordType());
				Result = String.valueOf(pstmt.executeUpdate());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Result;
	}
}
