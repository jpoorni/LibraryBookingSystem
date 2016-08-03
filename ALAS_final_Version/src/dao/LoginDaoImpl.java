package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Users;

public class LoginDaoImpl implements LoginDao {
	
	public Users getLoginDetails(String loginId, String pwd){
		
		System.out.println("loginId["+loginId +"] pwd["+pwd+"]");
		
		Users user=null;
		try{
				Connection conn=DbManager.getConnection();
				String loginSql= "select * from users  where upper(status)='ACTIVE' AND LoginId =? AND Password=?";
			    
			    PreparedStatement  ps = conn.prepareStatement(loginSql);
			    ps.setString(1, loginId);
			    ps.setString(2, pwd);
			     
			    System.out.println("Sql query >>"+loginSql );
			     
			    ResultSet rs = ps.executeQuery();	
			    
				if(rs.next()){
					user=new Users();
				     //Retrieve by column name
				     user.setLoginid(rs.getString("LoginId"));
				     //user.setUserId(rs.getLong("UserId"));
				     user.setName(rs.getString("Name"));
				     user.setRoleid((int) rs.getLong("RoleId"));
				     user.setEmail(rs.getString("Email"));
				     user.setPhoneno((int)rs.getLong("PhoneNo"));
				     user.setCreatedon(rs.getDate("CreatedOn"));
				}else{
					System.out.println(loginId + " : NO such active user found");
				}
				// role name retrieval
				if(user!=null){
					ps = conn.prepareStatement("select * from role where roleid=?");
					ps.setLong(1, user.getRoleid());
					rs = ps.executeQuery();
					if(rs.next())
						user.setRoleName(rs.getString("RoleName"));					
				}
			}catch(Exception e){
				e.printStackTrace();
			}

			System.out.println("Active user found >> "+ (user != null ? user.toString():"USER not found") );
		
		return user;
	}
	
public List<Users> getUserByName(String usrNameSearchkey){
		
		System.out.println("usrNameSearchkey["+usrNameSearchkey +"]");
		
		List<Users> userList=new ArrayList<Users>();
		Users user=null;
		
		try{
				Connection conn=DbManager.getConnection();
				usrNameSearchkey=usrNameSearchkey+"%";
				String loginSql= "select * from users where upper(name) like '"+usrNameSearchkey+"' AND ROLEID = (select ROLEID from role where upper(RoleName)='STUDENT')";
			    PreparedStatement  ps = conn.prepareStatement(loginSql);
			    System.out.println("Query >> "+ loginSql);
			    //ps.setString(1, usrNameSearchkey.toUpperCase()+"%");
			    ResultSet rs = ps.executeQuery();	
			    while(rs.next()){
					user=new Users();
				     //Retrieve by column name
				     user.setLoginid(rs.getString("LoginId"));
				     //user.setUserId(rs.getLong("UserId"));
				     user.setName(rs.getString("Name"));
				     user.setRoleid((int)rs.getLong("RoleId"));
				     user.setEmail(rs.getString("Email"));
				     user.setPhoneno((int) rs.getLong("PhoneNo"));
				     user.setCreatedon(rs.getDate("CreatedOn"));
				     user.setStatus(rs.getString("Status"));
				     userList.add(user);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}

			System.out.println(" No of Active users found >> "+ userList.size());
		
		return userList;
	}

	public Users getUserByLoginId(String loginId){
		System.out.println("loginId["+loginId +"]");
		Users user=null;
		try{
				Connection conn=DbManager.getConnection();
				String loginSql= "select * from users where upper(loginId) = ?";
			    PreparedStatement  ps = conn.prepareStatement(loginSql);
			    System.out.println("Query >> "+ loginSql);
			    ps.setString(1, loginId.toUpperCase());
			    ResultSet rs = ps.executeQuery();	
			    while(rs.next()){
					user=new Users();
				     user.setLoginid(rs.getString("LoginId"));
				     user.setName(rs.getString("Name"));
				     user.setRoleid((int) rs.getLong("RoleId"));
				     user.setEmail(rs.getString("Email"));
				     user.setPhoneno((int) rs.getLong("PhoneNo"));
				     user.setCreatedon(rs.getDate("CreatedOn"));
				     user.setStatus(rs.getString("Status"));
				 }				
			}catch(Exception e){
				e.printStackTrace();
			}
		System.out.println(" user found >> "+ (user != null ? user.toString():"USER not found") );		
	return user;
}
}
	

