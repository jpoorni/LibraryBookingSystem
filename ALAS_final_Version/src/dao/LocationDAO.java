package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Location;

public interface LocationDAO {
	
	public int createLocation(Location t) throws SQLException;
	
	public Location getOneLocation(int id) throws SQLException;
	
	public  ArrayList<Location> getAllLocation() throws SQLException;
	
	public int updateLocation(Location t) throws SQLException;
	
	public int deleteLocation(Location t) throws SQLException;
}
