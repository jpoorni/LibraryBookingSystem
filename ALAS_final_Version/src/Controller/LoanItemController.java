package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import biz.LoanHandler;
import biz.LoginHandler;
import biz.itemsHandler;
import model.Items;
import model.LoanTxnDto;
import model.Users;

/**
 * Servlet implementation class LoanItemController
 */
@WebServlet("/loanController")
public class LoanItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextJSP ="";
		
		HttpSession session = request.getSession();
		Users loggedInusr= (Users) session.getAttribute("loggedInUsr");
		
		String methodName=request.getParameter("methodName");
		String actionName=request.getParameter("actionName");
		String borrowerloginId=request.getParameter("selectedBorrower");
		String borrowerStuName= borrowerloginId!=null?borrowerloginId: loggedInusr.getName();
		List<String> requestItemsIdList=null;
		String txnResultMsg="";
		boolean canStudentTakeLoan=true;
		
		if(actionName!=null && actionName.equalsIgnoreCase("findBorrower")){
			List<Users> studentList= findBorrower(request, response);
			session.setAttribute("studentList", studentList);
			nextJSP  = "/searchItems.jsp";
			
		} else if(methodName!=null && methodName.equalsIgnoreCase("loanRequest")){
			
			requestItemsIdList = loanItem(request, response);
			
			// Has borrower already got 10 items on loan
			int existingLoanCount= LoanHandler.noOfLoanItemsWithStudent(borrowerloginId);
			if (existingLoanCount>=10){
					//return to searchitems.jsp with error message
					txnResultMsg= borrowerStuName+" already has  "+existingLoanCount+" items on loan, Only 10 items are allowed to take on loan";
					nextJSP  = "/searchItems.jsp";
					canStudentTakeLoan=false;
			} else if(existingLoanCount<10){
				int loanBal=10-existingLoanCount;
				if (loanBal>0 && (requestItemsIdList.size()>loanBal)){
					txnResultMsg=borrowerStuName+" already has  "+existingLoanCount+" items on loan, Only "+loanBal+" more item(s) can be further taken on loan !  ";
					nextJSP  = "/searchItems.jsp";	
					canStudentTakeLoan=false;
				}
			}
			
			if(canStudentTakeLoan){
				if(!(LoanHandler.createLoanTxnForMultipleItems(borrowerloginId, requestItemsIdList, ((Users) request.getSession().getAttribute("loggedInUsr")).getRoleid()))){
					txnResultMsg="System is facing some problem, try your transaction after a while !  ";
					nextJSP  = "/searchItems.jsp";
					
				}else {
					
					Users user=LoginHandler.getUserByLoginId(borrowerloginId);
					List<Items> itemslist = itemsHandler.findMultipleItemById(requestItemsIdList);
					
					request.setAttribute("borrowerStu",user );
					request.setAttribute("itemslist",itemslist );
					
					txnResultMsg="Loan transaction is successfully completed";
					nextJSP  = "/LoanTxnResult.jsp";
					
					//remove session variables maintaining list of students
					session.setAttribute("studentList", null);
					
				}
			
			}
		}
			request.setAttribute("txnResultMsg",txnResultMsg );
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	
	
	private List<String>  loanItem(HttpServletRequest request, HttpServletResponse response){
		List<String> requestItemsId=new ArrayList<String>();
		String checkItemdId;
		
		for(int j=0; j<20;j++){
			if("ON".equalsIgnoreCase(request.getParameter("itemChkBx_"+j))) {
				checkItemdId=request.getParameter("itemId_"+j);
				requestItemsId.add(checkItemdId);
			}			
		}
		
		return requestItemsId;
	}
	
	private List<Users> findBorrower(HttpServletRequest request, HttpServletResponse response){
		List<Users> studentList=new ArrayList<Users>();
		String usrNameSearchkey=request.getParameter("findUsertxtBx");
		if(usrNameSearchkey!=null && usrNameSearchkey.length()>0){
			studentList=LoginHandler.getUserByName(usrNameSearchkey);
		}
		return studentList;
	}
	
}
