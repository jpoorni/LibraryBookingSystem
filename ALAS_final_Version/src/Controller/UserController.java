package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.UserManager;
import model.Users;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UserController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String path = request.getPathInfo();
		System.out.println("The Path is: " + path);
		UserManager usermgr = new UserManager();
		RequestDispatcher rd;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");	
		

		switch (path) {
		// list all
		case "/list":
			ArrayList<Users> userlist = usermgr.listAll();
			System.out.println("users list" + userlist);
			request.setAttribute("userlist", userlist);
			rd = request.getRequestDispatcher("/CURDUsers.jsp");
			rd.forward(request, response);
			break;

		case "/create":
			Users user1 = new Users();
			System.out.println(request.getParameter("loginid"));
			
			user1.setLoginid(request.getParameter("loginid"));
			user1.setPassword(request.getParameter("password"));
			user1.setRoleid(Integer.parseInt(request.getParameter("roleid")));
			user1.setName(request.getParameter("name"));
			user1.setEmail(request.getParameter("email"));
			user1.setPhoneno(Integer.parseInt(request.getParameter("phoneno")));
			//date conversion				
			java.util.Date date1 = f.parse(request.getParameter("createdon"));
			java.sql.Date d2 = new java.sql.Date(date1.getTime());
			user1.setCreatedon(d2);
			user1.setStatus(request.getParameter("status"));

			int result = usermgr.CreateUsers(user1);
			System.out.println(result);

			request.setAttribute("newuser", user1);
			rd = request.getRequestDispatcher("/user/list");
			rd.forward(request, response);
			break;

		case "/add":
			request.setAttribute("Option", "add");
			
			rd = request.getRequestDispatcher("/create.jsp");
			rd.forward(request, response);
			
			break;
			
		case "/show":
			request.setAttribute("Option", "edit");	
			
			String id=request.getParameter("LoginID");
			Users user3=usermgr.getOneUser(id);
			request.setAttribute("User", user3);
			System.out.println(user3);
		    rd = request.getRequestDispatcher("/create.jsp");
			rd.include(request, response);
			break;
			
		case "/update":
		    String Loginid=request.getParameter("loginid");
		    System.out.println(Loginid);
		    //System.out.println("what happened");
		    Users user4=usermgr.getOneUser(Loginid);
		    System.out.println(user4);
			user4.setPassword(request.getParameter("password"));
			user4.setRoleid(Integer.parseInt(request.getParameter("roleid")));
			user4.setName(request.getParameter("name"));
			user4.setEmail(request.getParameter("email"));
			user4.setPhoneno(Integer.parseInt(request.getParameter("phoneno")));
			//date conversion			
			java.util.Date date2 = f.parse(request.getParameter("createdon"));
			java.sql.Date d3 = new java.sql.Date(date2.getTime());
			user4.setCreatedon(d3);
			user4.setStatus(request.getParameter("status"));

			int r = usermgr.UpdateUser(user4);
			System.out.println(r);

			request.setAttribute("newuser", user4);
			rd = request.getRequestDispatcher("/user/list");
			rd.forward(request, response);			
			break;
		
		case "/delete":
			String LoginId=request.getParameter("LoginID");
			System.out.println(LoginId);
			Users user5=usermgr.getOneUser(LoginId);
			System.out.println(user5);
			usermgr.DeleteUser(user5);
			
			rd = request.getRequestDispatcher("/user/list");
			rd.forward(request, response);
			break;	

		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
