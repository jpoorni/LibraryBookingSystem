package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Location;

public class LocationDataAccess implements LocationDAO {
	
	private static Connection connection = null;
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	//declaring Query
  	String selectAllSQL = "SELECT * FROM " + MYSQLConstants.LOCATION_TABLE_NAME ;
  	String insertSQL = "INSERT INTO " + MYSQLConstants.LOCATION_TABLE_NAME + "(" + MYSQLConstants.LOCATION_COL_LOCID + "," + MYSQLConstants.LOCATION_COL_RACKNUMBER + "," +MYSQLConstants.LOCATION_COL_SHELFID + ") VALUES (?, ?, ?)";
  	String deleteSQL = "DELETE FROM " + MYSQLConstants.LOCATION_TABLE_NAME + " WHERE " + MYSQLConstants.LOCATION_COL_LOCID  + " = ?";
  	String updateSQL = "UPDATE " + MYSQLConstants.LOCATION_TABLE_NAME + " SET " + MYSQLConstants.LOCATION_COL_RACKNUMBER +  " = ? ," + MYSQLConstants.LOCATION_COL_SHELFID + " = ? WHERE " + MYSQLConstants.LOCATION_COL_LOCID + " = ?";
  	String SelectOneSQL = "SELECT * FROM " + MYSQLConstants.LOCATION_TABLE_NAME   + " WHERE " + MYSQLConstants.LOCATION_COL_LOCID + " = ?";
  	
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
  	
  //get all location
  	public ArrayList<Location> getAllLocation() throws SQLException {
		openConnection();
		ArrayList<Location> result = new ArrayList<Location>();
		ps = connection.prepareStatement(selectAllSQL);
		rs = ps.executeQuery();
		while (rs.next()) {
			Location loc = new Location((rs.getInt(MYSQLConstants.LOCATION_COL_LOCID)),(rs.getString(MYSQLConstants.LOCATION_COL_RACKNUMBER)),(rs.getString(MYSQLConstants.LOCATION_COL_SHELFID)));
			result.add(loc);
		}
		closeConnection();
		return result;
	}
  	
	//get one itemtype
	public Location getOneLocation(int id) throws SQLException {
		openConnection();
		Location loc = null;
		ps = connection.prepareStatement(SelectOneSQL);
		ps.setInt(1,id);
		rs = ps.executeQuery();
		while (rs.next()) {
			loc = new Location((rs.getInt(MYSQLConstants.LOCATION_COL_LOCID)),(rs.getString(MYSQLConstants.LOCATION_COL_RACKNUMBER)),(rs.getString(MYSQLConstants.LOCATION_COL_SHELFID)));
		}
		closeConnection();
		return loc;
	}
  	
  	//add new location
	public int createLocation(Location t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(insertSQL);
		ps.setInt(1,t.getLocId());
		ps.setString(2,t.getRackNumber());
		ps.setString(3, t.getShelfId());
		int result = ps.executeUpdate();
		return result;
	}
	
	 //update location
	public int updateLocation(Location t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(updateSQL);
		ps.setString(1, t.getRackNumber());
		ps.setString(2, t.getShelfId());
		ps.setInt(3,t.getLocId());
		int result = ps.executeUpdate();
		//System.out.println(result);
		closeConnection();
		return result;
	}	
	
	//delete one location
	public int deleteLocation(Location t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(deleteSQL);
		ps.setInt(1, t.getLocId());
		int result = ps.executeUpdate();
		closeConnection();
		return result;
	}


	
	
	
}
