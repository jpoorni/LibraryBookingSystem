package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {
	
	private static Connection mysqlConn=null;
	
	public DbManager(){
		
	}
	
	public static Connection getConnection(){
		if(mysqlConn==null){
			mysqlConn= getDbConnection();
		}
		
		return mysqlConn;
	}
	
	private static Connection getDbConnection(){
			// JDBC driver name and database URL
		   String JDBC_DRIVER = MYSQLConstants.DRIVER_CLASS ;// "com.mysql.jdbc.Driver";  
		   String DB_URL =  MYSQLConstants.URL;//"jdbc:mysql://localhost/ALAS2";

		   //  Database credentials
		   String USER = MYSQLConstants.USER;//"root";
		   String PASS = MYSQLConstants.PASSWORD;//"password";
		   
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		  
		      System.out.println("Connected  to database.");
		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		   
		   return conn;
	}
	

}
