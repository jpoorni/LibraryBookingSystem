
package Controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import biz.ItemManager;
import biz.TransacReturnManager;
import biz.UserManager;
import model.LoanTxnDetails;
import model.Users;
import model.itemDTO;

/**
 * Servlet implementation class TransacSearchController
 */
@WebServlet("/search/*")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/********************************************* <1>   MAMTA's WORK : START  ***************************************************/
		HttpSession session = request.getSession(false);
		if(session==null){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
		}
		
		/************************************************ <1> MAMTA's  WORKS END ********************************************************/
		
		
		String path = request.getPathInfo();
		 System.out.println("out PATH is" + path);
		RequestDispatcher rd = null;
		List<LoanTxnDetails> list1 = null;
		ArrayList<Users> userList = new  ArrayList<Users>();
		TransacReturnManager trnRetMgr = new TransacReturnManager();

		switch (path) {
		case "/main":
			rd = request.getRequestDispatcher("/SearchTransaction.jsp");
			rd.forward(request, response);
			break;
		case "/mainitems":
			rd = request.getRequestDispatcher("/SearchItems1.jsp");
			rd.forward(request, response);
			break;
		case "/list":
			list1 = trnRetMgr.AllTxnHistory1();
			if(list1.size() == 0){
				request.setAttribute("size", 1);
				System.out.println("size 0");
			}
			else{
				request.setAttribute("size", 6);
			System.out.println("size 1");	
			}
			request.setAttribute("slist", list1);
			rd = request.getRequestDispatcher("/SearchTransaction.jsp");
			rd.forward(request, response);
			break;
			
		case "/date":
			// System.out.println("size 0");
			path = request.getPathInfo(); // newly added
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// java.util.Date frmDate;
			try {
				java.util.Date frmDate = sdf.parse(request.getParameter("fromDate"));
				java.util.Date toDate = sdf.parse(request.getParameter("toDate"));

				// calling the search function

				List<LoanTxnDetails> list2 = trnRetMgr.AllTxnHistoryByDate(frmDate, toDate);
				// System.out.println(list2);
				if (list2.size() == 0) {
					request.setAttribute("size", 1);
					// System.out.println("size 0");
				} else {
					request.setAttribute("size", 6);
					// System.out.println("size 1");
				}
				request.setAttribute("slist", list2);
				rd = request.getRequestDispatcher("/SearchTransaction.jsp");
				rd.forward(request, response);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			rd = request.getRequestDispatcher("/SearchTransaction.jsp");
//			rd.forward(request, response);
			break;
		case "/cat":
			//System.out.println(request.getParameter("cat"));
			list1 = trnRetMgr.findTxbByCategory(request.getParameter("cat"));
			if(list1.size() == 0){
				request.setAttribute("size", 1);
				System.out.println("size 0");
			}
			else{
				request.setAttribute("size", 6);
			System.out.println("size 1");	
			}
			request.setAttribute("slist", list1);
			rd = request.getRequestDispatcher("/SearchTransaction.jsp");
			rd.forward(request, response);
			break;
		case "/usersmain":
			rd = request.getRequestDispatcher("/SearchUsers.jsp");
			rd.forward(request, response);
			break;
		case "/users" :
			Users user1 = new Users();
			UserManager usrMgr = new UserManager();
			ArrayList<Users> userList1 = new  ArrayList<Users>();
			
			//userList = null;
			String str = request.getParameter("searchText");
			if ((request.getParameter("useroptions") !=null ) && (!str.isEmpty()))
			{
				System.out.println("userid" + request.getParameter("searchText"));
				if (request.getParameter("useroptions").equals("userId"))
					{
						user1 = usrMgr.getOneUser(request.getParameter("searchText"));
						
						System.out.println("user record - >" + user1);
						userList1.add(user1);
						System.out.println("userlist count -> "  + userList.size());
						if(userList1.size() == 0)
							request.setAttribute("size", 1);
						else
							request.setAttribute("size", 6);
						System.out.println(userList1);
						request.setAttribute("userlist", userList1);
						rd = request.getRequestDispatcher("/SearchUsers.jsp");
						rd.forward(request, response);
					}
				  else if (request.getParameter("useroptions").equals("userName"))
					{
						String name = request.getParameter("searchText");
						System.out.println("name = " + name);
						userList = usrMgr.getUserUsingName(name);
						if(userList.size() == 0)
							request.setAttribute("size", 1);
						else
							request.setAttribute("size", 6);
	//					usrMgr.getUserUsingName(name);
						System.out.println(userList);
						request.setAttribute("userlist", userList);
						rd = request.getRequestDispatcher("/SearchUsers.jsp");
						rd.forward(request, response);
	
					}
			}
			else if (request.getParameter("status").equals("active"))
			{
				
				//String name = request.getParameter("searchText");
				System.out.println("status1  = " + request.getParameter("status"));
				userList = usrMgr.getUserActive("Active");
				if(userList.size() == 0)
					request.setAttribute("size", 1);
				else
					request.setAttribute("size", 6);
//				System.out.println(userList);
				request.setAttribute("userlist", userList);
				rd = request.getRequestDispatcher("/SearchUsers.jsp");
				rd.forward(request, response);
			}
			else if (request.getParameter("status").equals("inactive"))
			{
				
				//String name = request.getParameter("searchText");
				System.out.println("status1  = " + request.getParameter("status"));
				userList = usrMgr.getUserActive("Inactive");
				if(userList.size() == 0)
					request.setAttribute("size", 1);
				else
					request.setAttribute("size", 6);
				System.out.println(userList);
				request.setAttribute("userlist", userList);
				rd = request.getRequestDispatcher("/SearchUsers.jsp");
				rd.forward(request, response);
			}
			break;
		case "/listAllUsers":
			UserManager usrMgr1 = new UserManager();
			userList = usrMgr1.getAllUsers();
			request.setAttribute("userlist", userList);
			rd = request.getRequestDispatcher("/SearchUsers.jsp");
			rd.forward(request, response);
			break;
		case "/listAllItems":
			ItemManager img2=new ItemManager();
			ArrayList<itemDTO> itemlist2=img2.listALL();
			request.setAttribute("itemlist", itemlist2);
			rd=request.getRequestDispatcher("/SearchItems1.jsp");
			rd.forward(request, response);
			break;
		
		case "/stulist": 
			/******************************************************** <2>  MAMTA WORKS   : START  ***************************************/
			/*//String StuLoginId = (String) session.getAttribute("loginid");
			String StuLoginId = "S008" ; //change to session variable
			System.out.println("Student Search");*/
			Users loggedInUsr =(Users) session.getAttribute("loggedInUsr");
			String StuLoginId=	loggedInUsr.getLoginid();
			/****************************************************  <2>  MAMTA WORKS  : END  ***********************************************/			
			list1 = trnRetMgr.findTxnByStudent(StuLoginId);
			System.out.println(list1);
			if(list1.size() == 0){
				request.setAttribute("size", 1);
				System.out.println("size 0");
			}
			else{
				request.setAttribute("size", 6);
			System.out.println("size 1");	
			}
			request.setAttribute("slist", list1);
			rd = request.getRequestDispatcher("/StudentSearchTransaction.jsp");
			rd.forward(request, response);
			break;
			
			
		case "/stucat":
			//StuLoginId = "Student3" ; //change to session variable
			loggedInUsr =(Users) session.getAttribute("loggedInUsr");
			StuLoginId=	loggedInUsr.getLoginid();
			
			list1 = trnRetMgr.findTxbByCategoryForStud(request.getParameter("cat"), StuLoginId);
			if(list1.size() == 0){
				request.setAttribute("size", 1);
				System.out.println("size 0");
			}
			else{
				request.setAttribute("size", 6);
			System.out.println("size 1");	
			}
			request.setAttribute("slist", list1);
			rd = request.getRequestDispatcher("/StudentSearchTransaction.jsp");
			rd.forward(request, response);
			break;
			
		case "/studate" :
			path = request.getPathInfo(); // newly added
			//StuLoginId = "Student3" ; //change to session variable
			loggedInUsr =(Users) session.getAttribute("loggedInUsr");
			StuLoginId=	loggedInUsr.getLoginid();
			
			
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			// java.util.Date frmDate;
			try {
				java.util.Date frmDate = sdf.parse(request.getParameter("fromDate"));
				java.util.Date toDate = sdf.parse(request.getParameter("toDate"));

				// calling the search function

				List<LoanTxnDetails> list2 = trnRetMgr.AllTxnHistoryByDateForStud(frmDate, toDate, StuLoginId);
				// System.out.println(list2);
				if (list2.size() == 0) 
					request.setAttribute("size", 1);
				else 
					request.setAttribute("size", 6);
				request.setAttribute("slist", list2);
				rd = request.getRequestDispatcher("/StudentSearchTransaction.jsp");
				rd.forward(request, response);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		
			case "/items" :
				System.out.println("1321");
				itemDTO item1 = new itemDTO();
				ItemManager img=new ItemManager();
				ArrayList<itemDTO> itemList1 = new  ArrayList<itemDTO>();
				
				//userList = null;
				String string = request.getParameter("searchText");
				if ((request.getParameter("useroptions") !=null ) && (!string.isEmpty()))
				{
					System.out.println("itemID" + request.getParameter("searchText"));
					if (request.getParameter("useroptions").equals("itemID"))
						{
						    item1=img.getOneItem(request.getParameter("searchText"));
							System.out.println("item record - >" + item1);
							itemList1.add(item1);
							System.out.println("itemlist count -> "  + itemList1.size());
							if(itemList1.size() == 0)
								request.setAttribute("size", 1);
							else
								request.setAttribute("size", 6);
							System.out.println(itemList1);
							request.setAttribute("itemlist", itemList1);
							rd = request.getRequestDispatcher("/SearchItems1.jsp");
							rd.forward(request, response);
						}
					  else if (request.getParameter("useroptions").equals("itemName"))
						{
							String name = request.getParameter("searchText");
							System.out.println("name = " + name);
							itemList1=img.getOneItemAccordingToName(name);
							if(itemList1.size() == 0)
								request.setAttribute("size", 1);
							else
								request.setAttribute("size", 6);
		//					usrMgr.getUserUsingName(name);
							System.out.println(itemList1);
							request.setAttribute("itemlist", itemList1);
							rd = request.getRequestDispatcher("/SearchItems1.jsp");
							rd.forward(request, response);
		
						}
				}
				else if (request.getParameter("type").equals("500"))
				{
					
					//String name = request.getParameter("searchText");
					System.out.println("type  = " + request.getParameter("type"));
					itemList1=img.getOneItemAccordingToType(500);
					if(itemList1.size() == 0)
						request.setAttribute("size", 1);
					else
						request.setAttribute("size", 6);
//					System.out.println(userList);
					request.setAttribute("itemlist", itemList1);
					rd = request.getRequestDispatcher("/SearchItems1.jsp");
					rd.forward(request, response);
				}
				else if (request.getParameter("type").equals("501"))
				{
					//String name = request.getParameter("searchText");
					System.out.println("type  = " + request.getParameter("type"));
					itemList1=img.getOneItemAccordingToType(501);
					if(itemList1.size() == 0)
						request.setAttribute("size", 1);
					else
						request.setAttribute("size", 6);
//					System.out.println(userList);
					request.setAttribute("itemlist", itemList1);
					rd = request.getRequestDispatcher("/SearchItems1.jsp");
					rd.forward(request, response);
				}
				else if (request.getParameter("type").equals("502"))
				{
					//String name = request.getParameter("searchText");
					System.out.println("type  = " + request.getParameter("type"));
					itemList1=img.getOneItemAccordingToType(502);
					if(itemList1.size() == 0)
						request.setAttribute("size", 1);
					else
						request.setAttribute("size", 6);
//					System.out.println(userList);
					request.setAttribute("itemlist", itemList1);
					rd = request.getRequestDispatcher("/SearchItems1.jsp");
					rd.forward(request, response);
				}
				else if (request.getParameter("type").equals("503"))
				{
					//String name = request.getParameter("searchText");
					System.out.println("type = " + request.getParameter("type"));
					itemList1=img.getOneItemAccordingToType(503);
					if(itemList1.size() == 0)
						request.setAttribute("size", 1);
					else
						request.setAttribute("size", 6);
//					System.out.println(userList);
					request.setAttribute("itemlist", itemList1);
					rd = request.getRequestDispatcher("/SearchItems1.jsp");
					rd.forward(request, response);
				}
				else if (request.getParameter("type").equals("504"))
				{
					//String name = request.getParameter("searchText");
					System.out.println("type  = " + request.getParameter("type"));
					itemList1=img.getOneItemAccordingToType(504);
					if(itemList1.size() == 0)
						request.setAttribute("size", 1);
					else
						request.setAttribute("size", 6);
//					System.out.println(userList);
					request.setAttribute("itemlist", itemList1);
					rd = request.getRequestDispatcher("/SearchItems1.jsp");
					rd.forward(request, response);
				}
				else if (request.getParameter("type").equals("505"))
				{
					//String name = request.getParameter("searchText");
					System.out.println("type  = " + request.getParameter("type"));
					itemList1=img.getOneItemAccordingToType(505);
					if(itemList1.size() == 0)
						request.setAttribute("size", 1);
					else
						request.setAttribute("size", 6);
//					System.out.println(userList);
					request.setAttribute("itemlist", itemList1);
					rd = request.getRequestDispatcher("/SearchItems1.jsp");
					rd.forward(request, response);
				}
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
