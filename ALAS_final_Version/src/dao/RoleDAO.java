package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Role;

public interface RoleDAO {
	
	public int createRole(Role t) throws SQLException;
	
	public Role getOneRole(int id) throws SQLException;
	
	public  ArrayList<Role> getAllRole() throws SQLException;
	
	public int updateRole(Role t) throws SQLException;
	
	public int deleteRole(Role t) throws SQLException;
}
