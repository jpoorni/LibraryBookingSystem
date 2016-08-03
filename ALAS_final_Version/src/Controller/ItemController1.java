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

import biz.ItemManager;

import model.itemDTO;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/item/*")
public class ItemController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemController1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     try {
			doProcess(request,response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, ParseException{
		 String path = request.getPathInfo();
		 System.out.println(path+" here is the controller ");
		 ItemManager img=new ItemManager();
		 RequestDispatcher rd;
	     SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	     
	     switch (path) {
	  // list all
			case "/list":
				String path2 = request.getPathInfo();
				System.out.println("PATH is" + path2);
				ArrayList<itemDTO> list = img.listALL();
				request.setAttribute("itemlist", list);
				rd = request.getRequestDispatcher("/CURDitem.jsp");
				rd.forward(request, response);
				break;

			case "/create":
				itemDTO item1=new itemDTO();
				
				item1.setItemID(request.getParameter("itemID"));
				item1.setTypeID(Integer.parseInt(request.getParameter("typeID")));
				item1.setItemName(request.getParameter("itemName"));
				item1.setISBN(Integer.parseInt(request.getParameter("ISBN")));
				item1.setPublisher(request.getParameter("publisher"));
				item1.setAuthor(request.getParameter("author"));
				item1.setStatus(request.getParameter("status"));
				item1.setLocID(Integer.parseInt(request.getParameter("locID")));
				//date conversion				
				java.util.Date date1 = f.parse(request.getParameter("publishedYear"));
				java.sql.Date d2 = new java.sql.Date(date1.getTime());
				item1.setPublishedYear(d2);

                img.CreateItems(item1);

				request.setAttribute("newitem", item1);
				rd = request.getRequestDispatcher("/item/list");
				rd.forward(request, response);
				break;
				
			case "/add":
				request.setAttribute("Option", "add");
				
				rd = request.getRequestDispatcher("/createitem.jsp");
				rd.forward(request, response);
				
				break;
				
			case "/show":
				request.setAttribute("Option", "edit");	
				
				String id=request.getParameter("itemID");
				System.out.println(id);
				itemDTO item3=img.getOneItem(id);
				System.out.println(item3.getItemID());
				request.setAttribute("item", item3);
				System.out.println(item3.getAuthor());
				
			    rd = request.getRequestDispatcher("/updateitem.jsp");
				rd.forward(request, response);
				break;
				
			case "/update":
				System.out.println(2);
			    String id2=request.getParameter("itemID");
			    //System.out.println("what happened");
			    itemDTO item4=img.getOneItem(id2);
			    item4.setTypeID(Integer.parseInt(request.getParameter("typeID")));
			    item4.setItemName(request.getParameter("itemName"));
			    item4.setISBN(Integer.parseInt(request.getParameter("ISBN")));
				item4.setPublisher(request.getParameter("publisher"));
				item4.setStatus(request.getParameter("status"));
				item4.setAuthor(request.getParameter("author"));
				item4.setLocID(Integer.parseInt(request.getParameter("locID")));
				//date conversion			
				java.util.Date date2 = f.parse(request.getParameter("publishedYear"));
				java.sql.Date d3 = new java.sql.Date(date2.getTime());
				item4.setPublishedYear(d3);

				img.UpdateItem(item4);

				request.setAttribute("newitem", item4);
				rd = request.getRequestDispatcher("/item/list");
				rd.forward(request, response);			
				break;
			
			case "/delete":
				String path1 = request.getPathInfo();
				System.out.println("PATH is" + path1);
				
				String itemID1=request.getParameter("itemID");
				System.out.println(itemID1);
				itemDTO item5=img.getOneItem(itemID1);
				System.out.println(item5);
				img.DeleteItem(item5);
				rd = request.getRequestDispatcher("/item/list");
				rd.forward(request, response);
				break;	

			default:
				break;
			}


	     
	     }
	     
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			doProcess(request,response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
