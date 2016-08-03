package dao;

import java.util.List;

import model.Users;

public interface LoginDao {

	public Users getLoginDetails(String loginId, String pwd);
	public List<Users> getUserByName(String name);
	public Users getUserByLoginId(String loginId);
	
}
