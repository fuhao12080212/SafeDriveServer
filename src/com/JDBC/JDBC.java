package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DangerInfoHandler.DangerInfo;
import com.DangerInfoHandler.DangerInfoDB;
import com.DriveRecord.DriveRecordInfo;
import com.DriveRecord.GetDriveRecord;
import com.DriveRecord.GetMaxRecordIdDB;
import com.ReceiveInfo.AddDangerInfoToDB;
import com.ReceiveInfo.AddPositionInfoToDB;
import com.ReceiveInfo.AddUserBackToDB;
import com.UserManage.UserManageDB;
import com.getPosition.GetPositionFromDB;
import com.getPosition.Position;

public class JDBC {

	private Connection connection;
	private UserManageDB usermanage;

	public JDBC() {
		// TODO Auto-generated constructor stub
		usermanage = new UserManageDB();
	}

	public void ConnectToSQL() {
		String USERNAME = "root";
		String PASSWORD = "root";
		String URL = "jdbc:mysql://localhost:3306/safadrive";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String Login_Authentication(String username, String password) {
		String loginResult = null;
		loginResult = usermanage.Login(username, password, connection);
		return loginResult;
	}

	public String Register(String username, String password, String phone,
			String name) {
		String IsExist = null;
		String registerResult = null;
		IsExist = usermanage.IsExist(username, connection);
		if (IsExist.equals("not exist")) {
			registerResult = usermanage.Register(username, password, phone,
					name, connection);
		} else if (IsExist.equals("exist")) {
			registerResult = "exist";
		}
		return registerResult;
	}

	public String IsMatch(String username, String phone) {
		String MatchResult = null;
		MatchResult = usermanage.IsMatch(username, phone, connection);
		return MatchResult;
	}

	public String ChangePW(String username, String password) {
		String ChangePWResult = null;
		ChangePWResult = usermanage.ChangePW(username, password, connection);
		return ChangePWResult;
	}

	public List<DangerInfo> DangerInfo(List<String> familyList) {
		List<DangerInfo> dangerInfoList = new ArrayList<DangerInfo>();
		DangerInfoDB dangerInfoDB = new DangerInfoDB(familyList, connection);
		dangerInfoList = dangerInfoDB.getDangerInfo();
		return dangerInfoList;
	}

	public List<DriveRecordInfo> RecordList(String username) {
		List<DriveRecordInfo> RecordList = new ArrayList<DriveRecordInfo>();
		GetDriveRecord getDriveRecord = new GetDriveRecord(username, connection);
		RecordList = getDriveRecord.getRecordList();
		return RecordList;
	}

	public String getMaxRecordID(String username) {
		// TODO Auto-generated method stub
		String MaxId = null;
		GetMaxRecordIdDB getMaxRecordIdDB = new GetMaxRecordIdDB(username,
				connection);
		MaxId = getMaxRecordIdDB.GetID();
		return MaxId;
	}

	public String getPositionString(String username, String number) {
		String positionString = null;
		GetPositionFromDB getPositionFromDB = new GetPositionFromDB(username,
				number, connection);
		positionString = getPositionFromDB.GetPositionInfo();
		return positionString;
	}

	public String uploadDanger(DriveRecordInfo newInfo, String username) {
		// TODO Auto-generated method stub
		String uploadResult = null;
		AddDangerInfoToDB addInfoToDB = new AddDangerInfoToDB(newInfo,
				username, connection);
		uploadResult = addInfoToDB.ChangeDB();
		return uploadResult;
	}

	public String uploadPosition(String username, String MaxRecord,
			List<Position> positionList) {
		String uploadPositionResult = null;
		AddPositionInfoToDB addPositionInfoToDB = new AddPositionInfoToDB(
				username, MaxRecord, positionList, connection);
		uploadPositionResult = addPositionInfoToDB.WriteToDB();
		return uploadPositionResult;
	}

	public String uploadAdvice(String username, String Time, String userbackText) {
		String uploadResult = null;
		AddUserBackToDB addUserBackToDB = new AddUserBackToDB(username, Time, userbackText, connection);
		uploadResult = addUserBackToDB.ToDB();
		return uploadResult;
	}
}
