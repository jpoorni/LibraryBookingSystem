package dao;


public class MYSQLConstants {
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";  
	public static final String URL = "jdbc:mysql://localhost:3306/alas2";
    public static final String USER = "root";
    public static final String PASSWORD = "19930110wengjin";//"poorni";
    
    //summer
    public static final String USERS_TABLE_NAME = "alas2.users";
    
    public static final String USERS_COL_LOGINID = "loginid";
    public static final String USERS_COL_PASSWORD = "password";
    public static final String USERS_COL_ROLEID = "roleid";
    public static final String USERS_COL_NAME= "name";
    public static final String USERS_COL_EMAIL = "email";
    public static final String USERS_COL_PHONENO = "phoneno";
    public static final String USERS_COL_CREATEDON = "createdon";
    public static final String USERS_COL_STATUS = "status";
    
    //poorni
  //Notes
  //#MySQL Database exception numbers
  //1062=DUPLICATE_KEY_FOUND
  //1216=CHILD_RECORD_FOUND
  //1217=PARENT_RECORD_NOT_FOUND
  //1048=NULL_VALUE_FOUND
  //1205=RECORD_HAS_BEEN_LOCKED
    
    //ItemType Table
    public static final String ITEMTYPE_TABLE_NAME = "alas2.itemtype";
    public static final String ITEMTYPE_COL_TYPEID = "Typeid";
    public static final String ITEMTYPE_COL_TYPENAME = "TypeName"; 
    
    //Location Table
    public static final String LOCATION_TABLE_NAME = "alas2.location";
    public static final String LOCATION_COL_LOCID = "LocId";
    public static final String LOCATION_COL_RACKNUMBER = "RackNumber";
    public static final String LOCATION_COL_SHELFID = "ShelfId";	
    
  //Role Table
    public static final String ROLE_TABLE_NAME = "alas2.role";
    public static final String ROLE_COL_ROLEID = "RoleId";
    public static final String ROLE_COL_ROLENAME = "RoleName";
    
    public static final int MYSQL_DUPLICATE_PK = 1062;
    

}
