package dao;

public class DAOFactory {
	//summer
	//accessing users interface
	public static UsersDAOInterface getUsersDAOInterface(){
		UsersDAOInterface sdaointerface= new UsersDAOImplementation();
		return sdaointerface;
	}
	
	//poorni
	//accessing itemtype interface
		public ItemTypeDAO getItemTypeDAO(){
			ItemTypeDAO tDAO = new ItemTypeDataAccess();
			return tDAO;
		}
		
		//accessing location interface
		public static LocationDAO getLocationDAO(){
			LocationDAO lDAO = new LocationDataAccess();
			return lDAO;
		}
		
		//accessing location interface
		public RoleDAO getRoleDAO(){
			RoleDAO rDAO = new RoleDataAccess();
			return rDAO;
		}
		
		//candice
		//accessing item interface
		public static itemDAO getitemDAOInstance(){
			
			return new ItemDAOImpl();
		}
		
		//mamta : start
		public static LoanTxnDao getTxnImplInstance(){		
			return new LoanTxnDaoImpl();
		}
		public static ItemsDao CreateItemDao(){
			return new ItemsDaoImpl();
		}
		public static LoginDao getLoginImplInstance(){		
			return new LoginDaoImpl();
		}
		//mamta : end
}
