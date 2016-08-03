package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Items;
import biz.itemsHandler;

/**
 * Servlet implementation class IssueServlet
 */
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemController(){
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Items> itemList=null;
		String nextJSP=null;
		
		String searchBy = request.getParameter("SearchBy");
		String searchValue = request.getParameter("SearchByValue");
		
		if(searchBy==null || searchValue==null){
			//some error -- get back to page and display error message
		}
		
		System.out.println("searchType  >>"+searchBy);
		System.out.println("searchValue >>"+searchValue);
		
		
		itemList=itemsHandler.findItems(searchBy, searchValue);
		
		request.setAttribute("items", itemList);
		nextJSP = "/searchItems.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
