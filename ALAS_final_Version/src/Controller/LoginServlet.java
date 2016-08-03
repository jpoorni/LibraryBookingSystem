package Controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.LoginHandler;
import model.Users;


@WebServlet(
		urlPatterns = { "/LoginServlet" }
		)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Users user;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	doGet( request,  response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action=request.getParameter("action");
		
		//login
		if(action!=null && action.equalsIgnoreCase("login")){
			String loginName = request.getParameter("login");
			String pwd  = request.getParameter("pwd");	
			this.user=LoginHandler.getLoginDetails(loginName, pwd);
			if(user!=null){
				session.setAttribute("loggedInUsr", this.user);
				session.setAttribute("loggedInUsrRole", this.user.getRoleName());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(request,response);
			}
			else{
				request.setAttribute("msg", "Either login id or password is incorrect, please retry with correct credentials !!!");
				session.invalidate();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				dispatcher.forward(request,response);
			}
		}
		
		//logout
		else if(action!=null && action.equalsIgnoreCase("Logout")){
			request.setAttribute("msg", "Dear "+( (Users) session.getAttribute("loggedInUsr")).getName() +", you are successfully logged out !!!");
			session.invalidate();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
		}
		
	}

}
