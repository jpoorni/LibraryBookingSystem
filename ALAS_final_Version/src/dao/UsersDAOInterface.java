package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Users;

public interface UsersDAOInterface {

	public abstract ArrayList<Users> getAllUsers() throws SQLException;	
	public abstract int CreateUsers(Users users) throws SQLException;
	public abstract int updateUser(Users users) throws SQLException;
	public abstract int deleteUse (Users users) throws SQLException;
	
	//search function: using login id to realize the searching 
	public abstract Users getOneUser(String LoginId) throws SQLException;
	public ArrayList<Users> getUserUsingName(String Name) throws SQLException; //new
	public ArrayList<Users> getUserActive(String sts) throws SQLException;  // new
}
