package biz;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Location;
import dao.LocationDAO;
import dao.MYSQLConstants;
import dao.DAOFactory;

public class LocationManager {

	public ArrayList<Location> listAll() {
		
		LocationDAO lDAO = DAOFactory.getLocationDAO();
		 try {
			return lDAO.getAllLocation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	
	public int createLocation(Location l) {
		
		LocationDAO lDAO = DAOFactory.getLocationDAO();
		
		try {
			return lDAO.createLocation(l);
		} catch (SQLException e) {
				if(e.getErrorCode() == (MYSQLConstants.MYSQL_DUPLICATE_PK))
				{
					System.out.println("pk exception");
				}
				e.printStackTrace();
			}
		return 0;
		
		}
	//summer add new
	public Location getOneLocation(int id){
		LocationDAO lDAO = DAOFactory.getLocationDAO();
		try {
			return lDAO.getOneLocation(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int deleteLocation(Location t){
		LocationDAO lDAO = DAOFactory.getLocationDAO();
		
		try {
			return lDAO.deleteLocation(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateLocation(Location t){
		LocationDAO lDAO = DAOFactory.getLocationDAO();
		try {
			return lDAO.updateLocation(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
