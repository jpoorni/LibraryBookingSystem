package Controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.itemsHandler;

import com.google.gson.Gson;

/**
 * Servlet implementation class ItemSelectionServlet
 */
@WebServlet("/ItemSelectionServlet")
public class ItemSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemSelectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchValue = request.getParameter("categoryName");
		//System.out.println("searchValue----> "+searchValue);
		
		List<String> itemList = new ArrayList<String>();
        
        if (searchValue!=null && searchValue.length()>0) {
        	itemList=itemsHandler.searchItemsForSelection( searchValue);
        } else{
        	// no selection received, something wrong, go back to page        	
        }
       
        //send response back to jsp page
        String json = new Gson().toJson(itemList);
        response.setContentType("application/json");
        response.getWriter().write(json);
        
	}

}
