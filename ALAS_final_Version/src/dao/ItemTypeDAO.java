package dao;

import model.ItemType;
import java.sql.SQLException;
import java.util.ArrayList;


public interface ItemTypeDAO {

	public int createItemType(ItemType t) throws SQLException;
	
	public ItemType getOneItemType(int id) throws SQLException;
	
	public  ArrayList<ItemType> getAllItemType() throws SQLException;
	
	public int updateItemType(ItemType t) throws SQLException;
	
	public int deleteItemType(ItemType t) throws SQLException;
}
