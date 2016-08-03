package biz;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.UsersDAOInterface;
import model.Users;

public class UserManager {
	
	UsersDAOInterface uDAO = DAOFactory.getUsersDAOInterface();
	
	
//	public ArrayList<Users> getUserUsingName(String Name) {
//		return getUserUsingName(Name);
//	}

	public ArrayList<Users> getUserUsingName(String Name) {
		try {
			return uDAO.getUserUsingName(Name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Users> getUserActive(String sts)  {
		try {
			return uDAO.getUserActive(sts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Users> getAllUsers()  {
		try {
			return uDAO.getAllUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//list all
		public ArrayList<Users> listAll() {
			
			try {
				return uDAO.getAllUsers();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//create a new user
		public int CreateUsers(Users user) {
			try {
				return uDAO.CreateUsers(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		
		//update user
		public int UpdateUser(Users user){
			try {
				return uDAO.updateUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		
		//delete user
		public int DeleteUser(Users users){
			try {
				return uDAO.deleteUse(users);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;		
		}
		
		//get one record
		public Users getOneUser(String LoginId){
			try {
				return uDAO.getOneUser(LoginId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
}
