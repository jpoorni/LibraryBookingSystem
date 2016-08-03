package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.LoanTxnDetails;
import model.LoanTxnDto;

public class LoanTxnDaoImpl implements LoanTxnDao {

	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static String sql;

	public static void OpenConnection() {
		try {
			Class.forName(MYSQLConstants.DRIVER_CLASS);
			conn = DriverManager.getConnection(MYSQLConstants.URL, MYSQLConstants.USER, MYSQLConstants.PASSWORD);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void CloseConnection() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public void CreateLoanTxn(LoanTxnDto ltd) {
//
//		try {
//			OpenConnection();
//			// sql="insert into
//			// loantransaction(ItemId,StuLoginId,IssueDate,ActualReturnDate,IssuedBy,itemStatus)values(?,?,?,?,?,?);";
//			sql = "insert into loantransaction(ItemId,StuLoginId,IssueDate,IssuedBy,itemStatus)values(?,?,?,?,?);";
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, ltd.getItemId());
//			stmt.setString(2, ltd.getStuLoginId());
//			stmt.setDate(3, new java.sql.Date(ltd.getIssueDate().getTime()));
//			stmt.setInt(4, ltd.getIssuedBy());
//			stmt.setString(5, "on Loan");
//			stmt.execute();
//			UpdateItemstatusOnIssue(ltd);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			CloseConnection();
//		}
//
//		System.out.println("Loan transactin record created.");
//	}

	/*
	 * public void UpdateTxnOnReturn(LoanTxnDto ltd){
	 * conn=DbManager.getConnection(); //sql=
	 * "update loantransaction set itemStatus='returned', ActualReturnDate='"
	 * +ltd.getActualReturnDate()+"'"+" where LoanId="+ltd.getLoanId()+";"; sql=
	 * "update loantransaction set itemStatus='returned', ActualReturnDate=? where LoanId=?;"
	 * ; try { stmt=conn.prepareStatement(sql); stmt.setDate(1, new
	 * java.sql.Date(ltd.getActualReturnDate().getTime())); stmt.setInt(2,
	 * ltd.getLoanId()); stmt.executeUpdate(); UpdateItemstatusOnReturn(ltd); }
	 * catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public void UpdateTxnOnReturn(Date ActualReturnDate, String itemId) {
		OpenConnection();
		sql = "update loantransaction set itemStatus='returned', ActualReturnDate=? where ItemId=? and ActualReturnDate is null ;";
		try {
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(ActualReturnDate.getTime()));
			stmt.setString(2, itemId);
			stmt.executeUpdate();
			UpdateItemstatusOnReturn(itemId);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null)
			{
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			CloseConnection();
		}
		System.out.println("Loan transaction record updated.");
	}

	public void UpdateItemstatusOnIssue(LoanTxnDto ltd) {
		OpenConnection();
		sql = "update items set Status='Unavailable' where ItemId=?;";
		try {
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ltd.getItemId());
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			CloseConnection();
		}
	}

	public void UpdateItemstatusOnReturn(String itemId) {
		OpenConnection();
		sql = "update items set Status='Available' where ItemId=?;";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, itemId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		System.out.println("Item's status  updated");
	}

	public List<LoanTxnDetails> findTxnByName(String ItemName) { // transaction
																	// history
																	// by item
																	// name
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();

		OpenConnection();
		sql = "select i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId and i.ItemName=? and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";

		System.out.println("Sql query : " + sql);
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ItemName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	public List<LoanTxnDetails> findTxnByStudent(String StuLoginId) { // student
																		// transaction
																		// history
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();

		sql = "select i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName , ltd.itemStatus from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId and ltd.StuLoginId=? and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, StuLoginId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setItemStatus(rs.getString("ItemStatus"));
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	public List<LoanTxnDetails> findTxbByCategory(String CategoryName) { // transaction
																			// history
																			// by
																			// category
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();
		sql = "select i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName , ltd.itemStatus from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId and it.TypeName=? and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, CategoryName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setItemStatus(rs.getString("itemStatus")); // new
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	public List<LoanTxnDetails> AllTxnHistory() { // all history
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();
		sql = "select  i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId  and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			stmt.execute();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setLoanId(rs.getInt("LoanId")); // new
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	// new method
	public List<LoanTxnDetails> AllTxnOnLoan() { // items not returned as of
													// current date
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();
		sql = "select ltd.LoanId, ltd.ItemId, i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId  and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId and ltd.itemStatus='on Loan';";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			stmt.execute();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setLoanId(rs.getInt("LoanId"));
				loanTxnDetails.setItemId(rs.getString("ItemId"));
				// System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	// new for changing the tran status using loan id, as we dont have itemid
	public void UpdateTxnOnReturn1(Date ActualReturnDate, int loanId, String itemId) {
		OpenConnection();
		sql = "update loantransaction set itemStatus='returned', ActualReturnDate=? where LoanId=? and ActualReturnDate is null ;";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(ActualReturnDate.getTime()));
			stmt.setInt(2, loanId);
			stmt.executeUpdate();
			UpdateItemstatusOnReturn(itemId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		System.out.println("Loan transaction record updated.");
	}

	// new
	// return transaction according to the from date and to date
	public List<LoanTxnDetails> AllTxnHistoryByDate(Date frmDate, Date toDate) { // all
																					// history
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();
		sql = "select ltd.LoanId, ltd.itemId, i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName ,ltd.itemStatus from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.IssueDate >= ? and ltd.IssueDate <= ? and ltd.ItemId=i.ItemId and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";
		try {
			stmt = conn.prepareStatement(sql);
			// System.out.println(sql);
			// stmt.setDate(1, (java.sql.Date) frmDate);
			// stmt.setDate(2, (java.sql.Date) toDate);
			stmt.setDate(1, new java.sql.Date(frmDate.getTime()));
			stmt.setDate(2, new java.sql.Date(toDate.getTime()));
			// stmt.setString(3, "returned");

			ResultSet rs = stmt.executeQuery();
			// stmt.execute();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setLoanId(rs.getInt("LoanId")); // new
				loanTxnDetails.setItemStatus(rs.getString("itemStatus")); // new
				loanTxnDetails.setItemId(rs.getString("ItemId")); // new
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	// new all history with loanid
	public List<LoanTxnDetails> AllTxnHistory1() { // all history
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();
		sql = "select ltd.loanID, ltd.itemId, i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName,ltd.itemStatus from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId  and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			stmt.execute();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setLoanId(rs.getInt("LoanId")); // new
				loanTxnDetails.setItemStatus(rs.getString("itemStatus")); // new
				loanTxnDetails.setItemId(rs.getString("ItemId")); // new
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	// new
	public List<LoanTxnDetails> findTxnByStudentOnLoan(String StuLoginId) {
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();

		sql = "select ltd.itemStatus,ltd.loanID,ltd.itemid, i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId and ltd.StuLoginId=? and ltd.itemStatus = ? and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId ;";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, StuLoginId);
			stmt.setString(2, "on Loan");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setItemId(rs.getString("ItemId")); // new
				loanTxnDetails.setLoanId(rs.getInt("LoanId")); // new
				loanTxnDetails.setItemStatus(rs.getString("itemStatus")); // new
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	// new
	public List<LoanTxnDetails> findTxbByCategoryForStud(String CategoryName, String StuLoginId) { // transaction
		// history
		// by
		// category and student id
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();
		sql = "select ltd.loanId,ltd.itemId, i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName , ltd.itemStatus from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.ItemId=i.ItemId and it.TypeName=? and ltd.StuLoginId=? and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, CategoryName);
			stmt.setString(2, StuLoginId);
			System.out.println(StuLoginId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setItemStatus(rs.getString("itemStatus")); // new
				loanTxnDetails.setLoanId(rs.getInt("LoanId")); // new
				loanTxnDetails.setItemId(rs.getString("ItemId")); // new
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	// new
	// return transaction according to the from date and to date for a student
	public List<LoanTxnDetails> AllTxnHistoryByDateForStud(Date frmDate, Date toDate, String StuLoginId) {
		OpenConnection();
		LoanTxnDetails loanTxnDetails = null;
		List<LoanTxnDetails> loanTxnDetailslist = new ArrayList<LoanTxnDetails>();
		sql = "select ltd.LoanId,ltd.itemId, i.ItemName,it.TypeName,ltd.StuLoginId,ltd.IssueDate,r.RoleName ,ltd.itemStatus from loantransaction ltd,items i,"
				+ "ItemType it,role r where ltd.IssueDate >= ? and ltd.IssueDate <= ? and ltd.StuLoginId=? and ltd.ItemId=i.ItemId and i.TypeId=it.TypeId and ltd.IssuedBy=r.RoleId;";
		try {
			stmt = conn.prepareStatement(sql);
			// System.out.println(sql);
			// stmt.setDate(1, (java.sql.Date) frmDate);
			// stmt.setDate(2, (java.sql.Date) toDate);
			stmt.setDate(1, new java.sql.Date(frmDate.getTime()));
			stmt.setDate(2, new java.sql.Date(toDate.getTime()));
			stmt.setString(3, StuLoginId);
			// stmt.setString(3, "returned");

			ResultSet rs = stmt.executeQuery();
			// stmt.execute();
			while (rs.next()) {
				loanTxnDetails = new LoanTxnDetails();
				loanTxnDetails.setItemName(rs.getString("ItemName"));
				loanTxnDetails.setCategory(rs.getString("TypeName"));
				loanTxnDetails.setBorrower(rs.getString("StuLoginId"));
				loanTxnDetails.setIssueOn(rs.getDate("IssueDate"));
				loanTxnDetails.setIssuedBy(rs.getString("RoleName"));
				loanTxnDetails.setLoanId(rs.getInt("LoanId")); // new
				loanTxnDetails.setItemStatus(rs.getString("itemStatus")); // new
				loanTxnDetails.setItemId(rs.getString("ItemId")); // new
				System.out.println("loanTxnDetails-->" + loanTxnDetails);
				loanTxnDetailslist.add(loanTxnDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		System.out.println("total no rows returned : " + loanTxnDetailslist.size());
		return loanTxnDetailslist;
	}

	// mamta add new

	public boolean CreateLoanTxn(LoanTxnDto ltd){

		try {
			OpenConnection();
			// sql="insert into
			// loantransaction(ItemId,StuLoginId,IssueDate,ActualReturnDate,IssuedBy,itemStatus)values(?,?,?,?,?,?);";
			sql = "insert into loantransaction(ItemId,StuLoginId,IssueDate,IssuedBy,itemStatus)values(?,?,?,?,?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ltd.getItemId());
			stmt.setString(2, ltd.getStuLoginId());
			stmt.setDate(3, new java.sql.Date(ltd.getIssueDate().getTime()));
			stmt.setInt(4, ltd.getIssuedBy());
			stmt.setString(5, "on Loan");
			stmt.execute();
			UpdateItemstatusOnIssue(ltd);

		} catch (SQLException e) {
			e.printStackTrace();
						return false;

		} finally {
			CloseConnection();
		}

		System.out.println("Loan transactin record created.");
		return true;
	}
	public int noOfLoanItemsWithStudent(String borrowerStudentId) {
		int count = 0;
		try {
			conn = DbManager.getConnection();
			sql = " select count(*) AS COUNT from loantransaction where upper(itemStatus)='ON LOAN' AND StuLoginId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, borrowerStudentId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Total Items on Loan by " + borrowerStudentId + " are " + count);
		return count;
	}


	public boolean createLoanTxnForMultipleItems(String borrowerStudentId, List<String> requestItemsIdList, long loggedInUsrRoleId){
		
		try {	
			java.sql.Date today=new java.sql.Date(new Date().getTime());
			LoanTxnDto ltd= null;//new LoanTxnDto();
			for(String itemId: requestItemsIdList){
				ltd= new LoanTxnDto();
				ltd.setItemId(itemId);
				ltd.setStuLoginId(borrowerStudentId);
				ltd.setIssueDate(today);
				ltd.setIssuedBy((int) loggedInUsrRoleId);
				ltd.setitemStatus("on Loan");
				CreateLoanTxn(ltd);
			}
				/*conn=DbManager.getConnection();
				
				String sql_Loan=" INSERT INTO loantransaction ( ItemId,StuLoginId,IssueDate,IssuedBy,itemStatus)  VALUES  ( ?, ?, ?,? ,?) ";
				PreparedStatement  ps_loan=conn.prepareStatement(sql_Loan);
				
				
				String sql_Items=" update items set status='UNAVAILABLE' Where ItemID = ?";
				PreparedStatement  ps_Items=conn.prepareStatement(sql_Items);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 30);
				java.sql.Date today=new java.sql.Date(new Date().getTime());
				java.sql.Date returnDate=new java.sql.Date((cal.getTimeInMillis()));
				
				
				// insert into  load
				for(String itemId: requestItemsIdList){
					ps_loan.setString(1, itemId);
					ps_loan.setString(2, borrowerStudentId);
					ps_loan.setDate(3, today);
					//ps_loan.setDate(4, returnDate);
					ps_loan.setLong(4, loggedInUsrRoleId);
					ps_loan.setString(5, "OnLoan");
					ps_loan.execute();
					//ps_loan.addBatch();
					
					ps_Items.setString(1, itemId);
					ps_Items.executeUpdate();
					//ps_Items.addBatch();
					//UpdateItemstatusOnIssue();
				}
				//int[] rs_Items = ps_Items.executeBatch();
				//int[] rs_loan = ps_loan.executeBatch();
				
				
				//System.out.println("No of rows inserted into loantransaction "+ rs_loan!=null ? rs_loan.length : 0);
				//System.out.println("No of rows updated into items "+ rs_Items!=null ? rs_Items.length : 0);*/
				
				
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
		return true;
	}

}
