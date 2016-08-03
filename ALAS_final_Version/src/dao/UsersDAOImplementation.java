package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Users;

public class UsersDAOImplementation implements UsersDAOInterface {
	public static final String readsql = "Select * from alas2.users;";
	public static final String insertsql = "Insert into alas2.users (loginid,password,roleid,name,email,phoneno,createdon,status) values(?,?,?,?,?,?,?,?) ";
	public static final String updatesql = "update alas2.users set password=? ,roleid=? ,name=? ,email=? ,phoneno=? ,createdon=? ,status=? where (loginid=?) ";
	public static final String deletesql = "update alas2.users set status=? where (loginid=?)";
	public static final String selectonesql = "Select * from alas2.users where (loginid=?)";
	public static final String selectNamesql = "Select * from alas2.users where (name Like ?)";
	public static final String selectStatusSql = "Select * from alas2.users where status = ?";

	private static Connection connection = null;
	private static PreparedStatement prepstatement = null;
	private static ResultSet rs;

	public static void OpenConnection() {
		try {
			Class.forName(MYSQLConstants.DRIVER_CLASS);
			connection = DriverManager.getConnection(MYSQLConstants.URL, MYSQLConstants.USER, MYSQLConstants.PASSWORD);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void CloseConnection() {
		try {
			prepstatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Users> getAllUsers() throws SQLException {
		OpenConnection();
		ArrayList<Users> alluserlist = new ArrayList<Users>();
		prepstatement = connection.prepareStatement(readsql);
		rs = prepstatement.executeQuery();

		while (rs.next()) {
			String loginid = rs.getString(MYSQLConstants.USERS_COL_LOGINID);
			String password = rs.getString(MYSQLConstants.USERS_COL_PASSWORD);
			int roleid = rs.getInt(MYSQLConstants.USERS_COL_ROLEID);
			String name = rs.getString(MYSQLConstants.USERS_COL_NAME);
			String email = rs.getString(MYSQLConstants.USERS_COL_EMAIL);
			int phoneno = rs.getInt(MYSQLConstants.USERS_COL_PHONENO);
			java.util.Date createdon = rs.getDate(MYSQLConstants.USERS_COL_CREATEDON);
			String status = rs.getString(MYSQLConstants.USERS_COL_STATUS);
			Users user = new Users(loginid, password, roleid, name, email, phoneno, createdon, status);
			alluserlist.add(user);
		}
		CloseConnection();
		return alluserlist;
	}

	public int CreateUsers(Users users) throws SQLException {
		OpenConnection();
		prepstatement = connection.prepareStatement(insertsql);

		prepstatement.setString(1, users.getLoginid());
		prepstatement.setString(2, users.getPassword());
		prepstatement.setInt(3, users.getRoleid());
		prepstatement.setString(4, users.getName());
		prepstatement.setString(5, users.getEmail());
		prepstatement.setInt(6, users.getPhoneno());
		prepstatement.setDate(7, (java.sql.Date) users.getCreatedon());
		prepstatement.setString(8, users.getStatus());

		int number = prepstatement.executeUpdate();
		CloseConnection();
		return number;
	}

	// String updatesql = "update alas2.users set password=? ,roleid=? ,name=?
	// ,email=? ,phoneno=? ,createdon=? ,status=? where (loginid=?) ";
	public int updateUser(Users users) throws SQLException {
		OpenConnection();
		prepstatement = connection.prepareStatement(updatesql);
		prepstatement.setString(1, users.getPassword());
		prepstatement.setInt(2, users.getRoleid());
		prepstatement.setString(3, users.getName());
		prepstatement.setString(4, users.getEmail());
		prepstatement.setInt(5, users.getPhoneno());
		prepstatement.setDate(6, (java.sql.Date) users.getCreatedon());
		prepstatement.setString(7, users.getStatus());
		prepstatement.setString(8, users.getLoginid());

		int number = prepstatement.executeUpdate();
		CloseConnection();
		return number;
	}

	// when you delete one user, just update the status of the user to inactive
	public int deleteUse(Users users) throws SQLException {
		OpenConnection();
		prepstatement = connection.prepareStatement(deletesql);
		prepstatement.setString(1, "Inactive");
		prepstatement.setString(2, users.getLoginid());

		int number = prepstatement.executeUpdate();
		CloseConnection();
		System.out.println(number);
		return number;
	}

	// search one record using the LoginId
	public Users getOneUser(String LoginId) throws SQLException {
		OpenConnection();
		Users user = null;
		prepstatement = connection.prepareStatement(selectonesql);
		prepstatement.setString(1, LoginId);
		rs = prepstatement.executeQuery();
		while (rs.next()) {
			String loginid = rs.getString(MYSQLConstants.USERS_COL_LOGINID);
			String password = rs.getString(MYSQLConstants.USERS_COL_PASSWORD);
			int roleid = rs.getInt(MYSQLConstants.USERS_COL_ROLEID);
			String name = rs.getString(MYSQLConstants.USERS_COL_NAME);
			String email = rs.getString(MYSQLConstants.USERS_COL_EMAIL);
			int phoneno = rs.getInt(MYSQLConstants.USERS_COL_PHONENO);
			java.util.Date createdon = rs.getDate(MYSQLConstants.USERS_COL_CREATEDON);
			String status = rs.getString(MYSQLConstants.USERS_COL_STATUS);
			user = new Users(loginid, password, roleid, name, email, phoneno, createdon, status);
		}

		CloseConnection();
		return user;
	}

	// new
	public ArrayList<Users> getUserUsingName(String Name) {
		// public void getUserUsingName(String Name) {
		OpenConnection();
		Users user = null;
		ArrayList<Users> list = new ArrayList<Users>();
		// String selectonesql = "Select * from MYSQLConstants.USERS_TABLE_NAME
		// where (loginid=?)";
		try {
			prepstatement = connection.prepareStatement(selectNamesql);
			System.out.println(selectNamesql);
			prepstatement.setString(1, Name);
			rs = prepstatement.executeQuery();
			while (rs.next()) {
				String loginid = rs.getString(MYSQLConstants.USERS_COL_LOGINID);
				String password = rs.getString(MYSQLConstants.USERS_COL_PASSWORD);
				int roleid = rs.getInt(MYSQLConstants.USERS_COL_ROLEID);
				String name = rs.getString(MYSQLConstants.USERS_COL_NAME);
				String email = rs.getString(MYSQLConstants.USERS_COL_EMAIL);
				int phoneno = rs.getInt(MYSQLConstants.USERS_COL_PHONENO);
				java.util.Date createdon = rs.getDate(MYSQLConstants.USERS_COL_CREATEDON);
				String status = rs.getString(MYSQLConstants.USERS_COL_STATUS);
				user = new Users(loginid, password, roleid, name, email, phoneno, createdon, status);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CloseConnection();
		return null;
	}

	// new
	public ArrayList<Users> getUserActive(String sts) {
		// public void getUserUsingName(String Name) {
		OpenConnection();
		Users user = null;
		ArrayList<Users> list = new ArrayList<Users>();
		// String selectonesql = "Select * from MYSQLConstants.USERS_TABLE_NAME
		// where (loginid=?)";
		try {
			prepstatement = connection.prepareStatement(selectStatusSql);
			System.out.println(selectStatusSql);
			prepstatement.setString(1, sts);
			rs = prepstatement.executeQuery();
			while (rs.next()) {
				String loginid = rs.getString(MYSQLConstants.USERS_COL_LOGINID);
				String password = rs.getString(MYSQLConstants.USERS_COL_PASSWORD);
				int roleid = rs.getInt(MYSQLConstants.USERS_COL_ROLEID);
				String name = rs.getString(MYSQLConstants.USERS_COL_NAME);
				String email = rs.getString(MYSQLConstants.USERS_COL_EMAIL);
				int phoneno = rs.getInt(MYSQLConstants.USERS_COL_PHONENO);
				java.util.Date createdon = rs.getDate(MYSQLConstants.USERS_COL_CREATEDON);
				String status = rs.getString(MYSQLConstants.USERS_COL_STATUS);
				user = new Users(loginid, password, roleid, name, email, phoneno, createdon, status);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CloseConnection();
		return null;
	}

}
