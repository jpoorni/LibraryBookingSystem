package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Role;

public class RoleDataAccess implements RoleDAO {

	private static Connection connection = null;
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	//declaring Query
  	String selectAllSQL = "SELECT * FROM " + MYSQLConstants.ROLE_TABLE_NAME ;
  	String insertSQL = "INSERT INTO " + MYSQLConstants.ROLE_TABLE_NAME + "(" + MYSQLConstants.ROLE_COL_ROLEID + ","  + MYSQLConstants.ROLE_COL_ROLENAME + ") VALUES (?, ?)";
  	//auto increment use this query and change the method implmentation
  	//String insertSQL = "INSERT INTO " + MYSQLConstants.ROLE_TABLE_NAME + "(" + MYSQLConstants.ROLE_COL_ROLENAME + ") VALUES ( ?)";
  	String deleteSQL = "DELETE FROM " + MYSQLConstants.ROLE_TABLE_NAME + " WHERE " + MYSQLConstants.ROLE_COL_ROLEID  + " = ?";
  	String updateSQL = "UPDATE " + MYSQLConstants.ROLE_TABLE_NAME + " SET " + MYSQLConstants.ROLE_COL_ROLENAME +  " = ? WHERE " + MYSQLConstants.ROLE_COL_ROLEID + " = ?";
  	String SelectOneSQL = "SELECT * FROM " + MYSQLConstants.ROLE_TABLE_NAME   + " WHERE " + MYSQLConstants.ROLE_COL_ROLEID + " = ?";

  	
  	private static void openConnection() {
		try {
			Class.forName(MYSQLConstants.DRIVER_CLASS);
			connection = DriverManager.getConnection(MYSQLConstants.URL, MYSQLConstants.USER, MYSQLConstants.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  	
  	
  	private static void closeConnection() {
		try {
			ps.close();

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

  	
    //get all ROLES
  	public ArrayList<Role> getAllRole() throws SQLException {
		openConnection();
		ArrayList<Role> result = new ArrayList<Role>();
		ps = connection.prepareStatement(selectAllSQL);
		rs = ps.executeQuery();
		while (rs.next()) {
			Role role = new Role((rs.getInt(MYSQLConstants.ROLE_COL_ROLEID)),(rs.getString(MYSQLConstants.ROLE_COL_ROLENAME)));
			result.add(role);
		}
		closeConnection();
		return result;
	}

  	//get one Role
	public Role getOneRole(int id) throws SQLException {
		openConnection();
		Role role = null;
		ps = connection.prepareStatement(SelectOneSQL);
		ps.setInt(1,id);
		rs = ps.executeQuery();
		while (rs.next()) {
			role = new Role((rs.getInt(MYSQLConstants.ROLE_COL_ROLEID)),(rs.getString(MYSQLConstants.ROLE_COL_ROLENAME)));
		}
		closeConnection();
		return role;
	}

	
	//add new role
	public int createRole(Role t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(insertSQL);
		ps.setInt(1, t.getRoleId());
		ps.setString(2, t.getRoleName());
		int result = ps.executeUpdate();
		closeConnection();
		return result;
	}
	
	
	 //update role
	public int updateRole(Role t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(updateSQL);
		//ps.setInt(2, t.getRoleId());
		ps.setString(1, t.getRoleName());
		int result = ps.executeUpdate();
		System.out.println(updateSQL + "  " + t.getRoleId() + "  " + t.getRoleName() );
		System.out.println(result);
		closeConnection();
		return result;
	}
	
	//delete one location
	public int deleteRole(Role t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(deleteSQL);
		ps.setInt(1, t.getRoleId());
		int result = ps.executeUpdate();
		closeConnection();
		return result;
	}

}
