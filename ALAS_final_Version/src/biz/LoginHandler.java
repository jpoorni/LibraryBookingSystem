package biz;

import java.util.List;

import model.Users;
import dao.DAOFactory;

public class LoginHandler {
	
	
	public static Users  getLoginDetails(String loginId, String pwd){
		   return DAOFactory.getLoginImplInstance().getLoginDetails(loginId, pwd);		
	}
	
	public static List<Users> getUserByName(String usrNameSearchkey){
		   return DAOFactory.getLoginImplInstance().getUserByName(usrNameSearchkey);		
	}
	
	public static Users getUserByLoginId(String loginId){
		   return DAOFactory.getLoginImplInstance().getUserByLoginId(loginId);		
	}
}
