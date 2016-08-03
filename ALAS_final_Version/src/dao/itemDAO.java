package dao;

import java.util.ArrayList;

import model.itemDTO;

public interface itemDAO {

	public ArrayList<itemDTO> findAllitem() throws DAOException;

	public void insertitem(itemDTO item) throws DAOException;

	public void updateitem(itemDTO item) throws DAOException;

	public void deleteitem(String itemID) throws DAOException;

	public itemDTO finditembyitemID(String itemID) throws DAOException;

	public ArrayList<itemDTO> finditembyitemName(String itemName) throws DAOException;

	public ArrayList<itemDTO> finditembyitemtype(int typeID) throws DAOException;

}
