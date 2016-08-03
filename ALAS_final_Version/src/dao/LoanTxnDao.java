package dao;

import java.util.Date;
import java.util.List;
import model.LoanTxnDetails;
import model.LoanTxnDto;

public interface LoanTxnDao {

	public boolean CreateLoanTxn(LoanTxnDto ltd);

	public void UpdateTxnOnReturn(Date ActualReturnDate, String itemId);

	public void UpdateItemstatusOnIssue(LoanTxnDto ltd);

	public void UpdateItemstatusOnReturn(String itemId);

	public List<LoanTxnDetails> findTxnByName(String ItemName);

	public List<LoanTxnDetails> findTxnByStudent(String StuLoginId);

	public List<LoanTxnDetails> findTxbByCategory(String CategoryName);

	public List<LoanTxnDetails> AllTxnHistory();
	public boolean createLoanTxnForMultipleItems(String borrowerStudentId, List<String> requestItemsIdList, long loggedInUsr);
	public int noOfLoanItemsWithStudent(String borrowerStudentId);
	
	public List<LoanTxnDetails> AllTxnOnLoan() ; //new
	
	public void UpdateTxnOnReturn1(Date ActualReturnDate, int loanId, String itemId); //new
	
	public List<LoanTxnDetails> AllTxnHistoryByDate(Date frmDate, Date toDate); //new
	
	public List<LoanTxnDetails> AllTxnHistory1(); //new
	
	public List<LoanTxnDetails> findTxnByStudentOnLoan(String StuLoginId); //new
	
	public List<LoanTxnDetails> findTxbByCategoryForStud(String CategoryName,String StuLoginId); //new
	
	public List<LoanTxnDetails> AllTxnHistoryByDateForStud(Date frmDate, Date toDate,String StuLoginId); //new

}
