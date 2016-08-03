package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.DAOException;
import dao.itemDAO;
import model.itemDTO;

public class ItemDAOImpl implements itemDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection con;
	String selectALLSQL = "SELECT * FROM items";
	String finditembyitemNameSQL = "SELECT *FROM items WHERE (ItemName LIKE ? )";
	String finditembyitemIDSQL = "SELECT * FROM items WHERE (ItemID= ? )";
	String finditembytypeIDSQL = "SELECT * FROM items WHERE (TypeID= ? )";
	String deleteSQL = "UPDATE items SET Status='Unvaliable' WHERE (ItemID = ? )";
	String updateSQL = "UPDATE items SET TypeID = ?, ItemName = ?, ISBN = ?, Publisher = ?, PublishedYear = ?, Author = ?,  Status = ?, LocID = ? WHERE (ItemID = ? ) ";
	// String insertSQL="INSERT INTO items (ItemId, TypeId, ItemName,
	// ,ISBN,Publisher, PublishedYear, Author, Status, LocId) VALUES (?, ?, ?,
	// ?, ?, ?, ?, ?, ?)";
	String insertSQL = "INSERT INTO items VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public void openconnection() {
		try {
			Class.forName(MYSQLConstants.DRIVER_CLASS);
			con = DriverManager.getConnection(MYSQLConstants.URL, MYSQLConstants.USER, MYSQLConstants.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<itemDTO> findAllitem() throws DAOException {
		openconnection();
		ArrayList<itemDTO> result = new ArrayList<itemDTO>();
		try {
			ps = con.prepareStatement(selectALLSQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				itemDTO item = new itemDTO(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				result.add(item);
			}
			con.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void insertitem(itemDTO item) throws DAOException {
		openconnection();
		try {
			ps = con.prepareStatement(insertSQL);
			System.out.println(insertSQL);
			System.out.println(item);

			ps.setString(1, item.getItemID());
			ps.setInt(2, item.getTypeID());
			ps.setString(3, item.getItemName());
			ps.setInt(4, item.getISBN());
			ps.setString(5, item.getPublisher());
			ps.setDate(6, (java.sql.Date) item.getPublishedYear());
			ps.setString(7, item.getAuthor());
			ps.setString(8, item.getStatus());
			ps.setInt(9, item.getLocID());
			int result = ps.executeUpdate();
			System.out.println(result);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateitem(itemDTO item) throws DAOException {
		openconnection();
		try {
			ps = con.prepareStatement(updateSQL);
			ps.setInt(1, item.getTypeID());
			ps.setString(2, item.getItemName());
			ps.setInt(3, item.getISBN());
			ps.setString(4, item.getPublisher());
			ps.setDate(5, (java.sql.Date) item.getPublishedYear());
			ps.setString(6, item.getAuthor());
			ps.setString(7, item.getStatus());
			ps.setInt(8, item.getLocID());
			ps.setString(9, item.getItemID());
			int result = ps.executeUpdate();
			System.out.println(result);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteitem(String itemID) throws DAOException {
		openconnection();
		try {
			ps = con.prepareStatement(deleteSQL);
			ps.setString(1, itemID);
			int result = ps.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// if the input of the itemID is not exist~need validate!
	public itemDTO finditembyitemID(String itemID) throws DAOException {
		openconnection();
		try {
			ps = con.prepareStatement(finditembyitemIDSQL);
			ps.setString(1, itemID);
			rs = ps.executeQuery();
			while (rs.next()) {
				itemDTO item = new itemDTO(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				System.out.println(item.toString());
				return item;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<itemDTO> finditembyitemName(String itemName) throws DAOException {
		openconnection();
		ArrayList<itemDTO> result = new ArrayList<itemDTO>();
		try {
			ps = con.prepareStatement(finditembyitemNameSQL);
			ps.setString(1, "%" + itemName + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				itemDTO item = new itemDTO(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				result.add(item);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<itemDTO> finditembyitemtype(int typeID) throws DAOException {
		ArrayList<itemDTO> result = new ArrayList<itemDTO>();
		openconnection();

		try {
			ps = con.prepareStatement(finditembytypeIDSQL);
			ps.setInt(1, typeID);
			rs = ps.executeQuery();
			while (rs.next()) {
				itemDTO item = new itemDTO(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				result.add(item);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
