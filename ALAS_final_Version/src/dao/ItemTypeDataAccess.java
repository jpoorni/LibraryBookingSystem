package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ItemType;

public class ItemTypeDataAccess implements ItemTypeDAO {

	private static Connection connection = null;
	//private static Statement statement;
	private static PreparedStatement ps = null;
	private ResultSet rs = null;

	//declaring Query
	String selectAllSQL = "SELECT * FROM " + MYSQLConstants.ITEMTYPE_TABLE_NAME ;
	String insertSQL = "INSERT INTO " + MYSQLConstants.ITEMTYPE_TABLE_NAME + "(" + MYSQLConstants.ITEMTYPE_COL_TYPEID + "," + MYSQLConstants.ITEMTYPE_COL_TYPENAME + ") VALUES (?, ?)";
	String deleteSQL = "DELETE FROM " + MYSQLConstants.ITEMTYPE_TABLE_NAME + " WHERE " + MYSQLConstants.ITEMTYPE_COL_TYPEID  + " = ?";
	String updateSQL = "UPDATE " + MYSQLConstants.ITEMTYPE_TABLE_NAME + " SET " + MYSQLConstants.ITEMTYPE_COL_TYPENAME + " = ? WHERE " + MYSQLConstants.ITEMTYPE_COL_TYPEID + " = ?";
	String SelectOneSQL = "SELECT * FROM " + MYSQLConstants.ITEMTYPE_TABLE_NAME   + " WHERE " + MYSQLConstants.ITEMTYPE_COL_TYPEID + " = ?";
	
	private static void openConnection() {
		try {
			Class.forName(MYSQLConstants.DRIVER_CLASS);
			connection = DriverManager.getConnection(MYSQLConstants.URL, MYSQLConstants.USER, MYSQLConstants.PASSWORD);
			//statement = connection.createStatement();
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
	
	//get all itemtypes
	public ArrayList<ItemType> getAllItemType() throws SQLException {
		openConnection();
		ArrayList<ItemType> result = new ArrayList<ItemType>();
		ps = connection.prepareStatement(selectAllSQL);
		rs = ps.executeQuery();
		while (rs.next()) {
			ItemType t = new ItemType((rs.getInt(MYSQLConstants.ITEMTYPE_COL_TYPEID)),(rs.getString(MYSQLConstants.ITEMTYPE_COL_TYPENAME)));
			result.add(t);
		}
		closeConnection();
		return result;
	}
	
	
	//get one itemtype
	public ItemType getOneItemType(int id) throws SQLException {
		openConnection();
		ItemType t = null;
		ps = connection.prepareStatement(SelectOneSQL);
//		System.out.println(SelectOneSQL);
//		System.out.println(id);
		ps.setInt(1,id);
		rs = ps.executeQuery();
		while (rs.next()) {
//			System.out.println(rs.getInt(MYSQLConstants.ITEMTYPE_COL_TYPEID)); 
//			System.out.println(rs.getString(MYSQLConstants.ITEMTYPE_COL_TYPENAME));
			
			t = new ItemType((rs.getInt(MYSQLConstants.ITEMTYPE_COL_TYPEID)),(rs.getString(MYSQLConstants.ITEMTYPE_COL_TYPENAME)));
//			System.out.println(t.getTypeID());
//			System.out.println(t.getTypeName());
			//return t;
		}
		closeConnection();
		return t;
	}
	
	//add new itemtype
	public int createItemType(ItemType t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(insertSQL);
//		System.out.println(insertSQL);
//		System.out.println(t.getTypeID());
//		System.out.println(t.getTypeName());
		ps.setInt(1,t.getTypeID());
		ps.setString(2,t.getTypeName());
		int result = ps.executeUpdate();
//		System.out.println(result);
		closeConnection();
		return result;
	}
	
	
	//delete itemtype
	public int deleteItemType(ItemType t) throws SQLException {
		openConnection();
		ps = connection.prepareStatement(deleteSQL);
		ps.setInt(1, t.getTypeID());
		int result = ps.executeUpdate();
//		System.out.println(result);
		closeConnection();
		return result;
	}
	
	 //update itemtype
      //Update Operations
	public int updateItemType(ItemType t) throws SQLException {
		openConnection();
//		System.out.println(updateSQL);
		ps = connection.prepareStatement(updateSQL);
		ps.setString(1, t.getTypeName());
		ps.setInt(2, t.getTypeID());
		int result = ps.executeUpdate();
		System.out.println(result);
		closeConnection();
		return result;
	}
}
