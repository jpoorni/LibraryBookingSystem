package biz;

import java.util.ArrayList;
import java.util.List;

import model.LoanTxnDto;
import dao.DAOFactory;

public class LoanHandler {
	
	public static boolean loanItem(LoanTxnDto ltd){
		return DAOFactory.getTxnImplInstance().CreateLoanTxn(ltd);
	}
	public static boolean createLoanTxnForMultipleItems(String borrowerStudentId, List<String> requestItemsIdList, long loggedInUsrRoleId){
	
		System.out.println("itemId List  "+requestItemsIdList);
		List<String> requestItemsIdList_temp= new ArrayList<String>();
		String str=null;
		for(int i=0; i<requestItemsIdList.size();i++){
			  str=requestItemsIdList.get(i);
			  if(str.substring(str.length()-1).equalsIgnoreCase("/")){
				  str=str.replace(str.substring(str.length()-1), "");//remove unwanted / from end of itemid
			  }
		      requestItemsIdList_temp.add(str);
		}
		
		
		return DAOFactory.getTxnImplInstance().createLoanTxnForMultipleItems(borrowerStudentId, requestItemsIdList_temp,loggedInUsrRoleId);
	}
	
	public static int noOfLoanItemsWithStudent(String borrowerStudentId){
		
		return DAOFactory.getTxnImplInstance().noOfLoanItemsWithStudent(borrowerStudentId);
	}
}
