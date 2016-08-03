package biz;

import java.util.ArrayList;
import dao.DAOException;
import dao.DAOFactory;
import dao.itemDAO;

import model.itemDTO;

public class ItemManager {

	itemDAO dao = DAOFactory.getitemDAOInstance();

	// listall
	public ArrayList<itemDTO> listALL() {
		try {
			return dao.findAllitem();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// create a new item
	public void CreateItems(itemDTO item) {
		try {
			dao.insertitem(item);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	// update user
	public void UpdateItem(itemDTO item) {
		try {
			dao.updateitem(item);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// delete user
	public void DeleteItem(itemDTO item) {
		try {
			dao.deleteitem(item.getItemID());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get one record
	public itemDTO getOneItem(String itemID) {
		try {
			return dao.finditembyitemID(itemID);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//get one record according to item name
	public ArrayList<itemDTO> getOneItemAccordingToName(String itemName){
		try {
			return dao.finditembyitemName(itemName);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//get one record according to item type
	public ArrayList<itemDTO> getOneItemAccordingToType(int typeID){
		try {
			return dao.finditembyitemtype(typeID);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
