package biz;

import java.util.Date;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import dao.DAOFactory;
import dao.LoanTxnDao;
//import dao.LoanTxnDaoImpl;
import model.LoanTxnDetails;

public class TransacReturnManager {

	LoanTxnDao tDAO = DAOFactory.getTxnImplInstance();

	public List<LoanTxnDetails> listAllOnLoan() {
		return tDAO.AllTxnOnLoan();
	}

	public void UpdateTxnOnReturn(Date ActualReturnDate, int loanId, String itemId) {
		tDAO.UpdateTxnOnReturn1(ActualReturnDate, loanId, itemId);
	}

	public List<LoanTxnDetails> findTxnByStudent(String StuLoginId) {
		return tDAO.findTxnByStudent(StuLoginId);
	}
	
	public List<LoanTxnDetails> AllTxnHistoryByDate(Date frmDate, Date toDate) {
		return tDAO.AllTxnHistoryByDate(frmDate, toDate);
	}
	
	public List<LoanTxnDetails> AllTxnHistory1() {
		return tDAO.AllTxnHistory1();
	}
	
	public List<LoanTxnDetails> findTxbByCategory(String CategoryName){
		return tDAO.findTxbByCategory(CategoryName);
	}
	
	public List<LoanTxnDetails> findTxnByStudentOnLoan(String StuLoginId) {
		return tDAO.findTxnByStudentOnLoan(StuLoginId);
	}
	
	public List<LoanTxnDetails> findTxbByCategoryForStud(String CategoryName,String StuLoginId) {
		return tDAO.findTxbByCategoryForStud(CategoryName, StuLoginId);
	}
	
	public List<LoanTxnDetails> AllTxnHistoryByDateForStud(Date frmDate, Date toDate,String StuLoginId) {
		return tDAO.AllTxnHistoryByDateForStud(frmDate, toDate, StuLoginId);
	}
}