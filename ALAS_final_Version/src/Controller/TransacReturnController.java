package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoanTxnDetails;
import model.Users;
import biz.TransacReturnManager;

/**
 * Servlet implementation class TransacReturn
 */
@WebServlet("/return/*")
public class TransacReturnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransacReturnController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		/********************************************* <1>   MAMTA's WORK : START  ***************************************************/
		HttpSession session = request.getSession(false);
		if(session==null){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
		}
		
		/************************************************ <1> MAMTA's  WORKS END ********************************************************/
		
		
		String path = request.getPathInfo();
//		String loggedInUsrRole =(String) session.getAttribute("loggedInUsrRole");
		
//		HttpSession session1 = request.getSession();
		Boolean isStuLogin = Boolean.FALSE;
		//System.out.println("out PATH is" + path);
		//List<LoanTxnDetails> list2;
		
		RequestDispatcher rd = null;
		TransacReturnManager trnRetMgr = new TransacReturnManager();
		List<LoanTxnDetails> list1 = null;
		
		String message=null;
		String fineMsg=null;
//		int noOfRec = 0;  
		switch (path) {
		case "/index":
			
//	        session1.setAttribute("loginId", request.getParameter("loginId"));

			
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);	
		case "/list":
//			message=null;
//			fineMsg=null;
			path = request.getPathInfo(); //newly added	
			list1 = trnRetMgr.listAllOnLoan();
			//noOfRec = list1.size()	;
			//list2 = trnRetMgr.listAllOnLoan();
			request.setAttribute("slist", list1);
			if(list1.size() == 0)
				request.setAttribute("size", 1);
			else
				request.setAttribute("size", 6);
			rd = request.getRequestDispatcher("/listReturn.jsp");
			rd.forward(request, response);	
			isStuLogin = false;
			break;
		case "/listStudentTran":
			isStuLogin = false;
			String studId = request.getParameter("studId");
			//System.out.println(studId);
			list1 = trnRetMgr.findTxnByStudentOnLoan(studId);
			System.out.println(list1.size());
			request.setAttribute("slist", list1);
			//System.out.println(list1);
			if(list1.size() == 0){
				request.setAttribute("size", 1);
				System.out.println("size 0");
			}
			else{
				request.setAttribute("size", 6);
			System.out.println("size 1");	
			}
			rd = request.getRequestDispatcher("/listReturn.jsp");
			rd.forward(request, response);
			break;
		case "/show":
			break;	
		case "/itemreturn":
			
			int loanid = Integer.parseInt(request.getParameter("loanId"));
			String itemid = request.getParameter("itemId");
			System.out.println("itemid = " + itemid);
			System.out.println("laonid = " + loanid);
			//System.out.println(request.getParameter("issueOn"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
//				*********get the current date and send as the parameter
				//Calendar c = Calendar.getInstance();
				//System.out.println (sdf.format(sdf.parse(c.getTime().toString())));
				
				//Calculating fine if actualReturnDate > issueDate + 30
				//1. get the record from Database using loan id which is sent as parameter
				//or
				//1. pass the issue date as parameter
				//1. get the element from array list
		
				
				
				Calendar c = Calendar.getInstance();
				java.util.Date date = new java.util.Date();
				java.sql.Date actualReturnDate = new java.sql.Date(date.getTime());
				java.util.Date actualReturnDate1 = new java.util.Date(date.getTime());
				
				//System.out.println(sqlDate);
				
				java.util.Date issuedDate =  sdf.parse(request.getParameter("issueOn"));
   			    System.out.println("issuedDate = " + issuedDate);
				c.setTime(issuedDate);
				c.add(Calendar.DATE,29);
				java.util.Date returnDate = c.getTime();
				
				System.out.println("returnDate = " + returnDate);
				
				
				System.out.println("actualReturnDate =" + actualReturnDate);
				System.out.println("actualReturnDate1 =" + actualReturnDate1);
				
				
				if (actualReturnDate.after(returnDate))
				{
//					System.out.println("Fine");
					
					long diff =  actualReturnDate1.getTime() - returnDate.getTime() ;
					long diffDays = diff / (24 * 60 * 60 * 1000);
					fineMsg = "You have to pay $" + diffDays + " as fine!!!";
					System.out.println(fineMsg);
//				    System.out.println("Days = " + diffDays);
				    
				    
				}
  			    //trnRetMgr.UpdateTxnOnReturn(sqlDate,loanid, itemid);
				trnRetMgr.UpdateTxnOnReturn(actualReturnDate,loanid, itemid);
				
				message = "Returned Successfully";
				request.setAttribute("message",message);
				request.setAttribute("fineMSg",fineMsg);
//				message=null;
//				fineMsg=null;
				
			}
			 catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//System.out.println(isStuLogin); 
			//isStuLogin = false; 		// take from the session variable
			String loggedInUsrRole =(String) session.getAttribute("loggedInUsrRole");
			if(loggedInUsrRole!=null && loggedInUsrRole.equalsIgnoreCase("STUDENT")){
				isStuLogin=true;
			} 
			//if (isStudent)
			if (isStuLogin)
			{
				System.out.println("Login student");
				rd = request.getRequestDispatcher("/return/stulist");
			}
			else
			{
				System.out.println("Login library");
				rd = request.getRequestDispatcher("/return/list");
			}
			rd.forward(request, response);
			break;	
		
		case "/stulist" :
			 isStuLogin = true;
			 //studId = "S008" ; 											//change to session variable			 
			 Users loggedInUsr =(Users) session.getAttribute("loggedInUsr");
			 studId=	loggedInUsr.getLoginid();
			 
			 System.out.println("Student ID in return list" +  studId);
			 //studId = (String) session1.getAttribute("loginId");
			//System.out.println(studId);
			list1 = trnRetMgr.findTxnByStudentOnLoan(studId);
			System.out.println(list1.size());
			request.setAttribute("slist", list1);
			//System.out.println(list1);
			if(list1.size() == 0)
				request.setAttribute("size", 1);
			else
				request.setAttribute("size", 6);
			rd = request.getRequestDispatcher("/StudentReturn.jsp");
			rd.forward(request, response);
			break;
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


//Points to handle
//*********handling exceptions
//*********Transactions commit and roll back