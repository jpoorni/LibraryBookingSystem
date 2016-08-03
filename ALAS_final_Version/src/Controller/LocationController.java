package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Location;
import biz.LocationManager;

/**
 * Servlet implementation class LocationController
 */
@WebServlet("/location/*")
public class LocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LocationController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		//System.out.println("out PATH is" + path);
		
		RequestDispatcher rd = null;
		LocationManager locMgr = new LocationManager();
		
		//String list = "poorni";
		//ArrayList<Student> list = mgr.listAll();
		//ArrayList<Location> list1 = locMgr.listAll();
		//request.setAttribute("slist", list);
		//request.setAttribute("slist1", list1);
		
		//rd = request.getRequestDispatcher("/listlocation.jsp");
		//rd.forward(request, response);		
		
		
		switch (path) {
		case "/list":
			path = request.getPathInfo(); //newly added	
			System.out.println("list PATH is" + path);
			
			ArrayList<Location> list1 = locMgr.listAll();
			request.setAttribute("slist", list1);
			rd = request.getRequestDispatcher("/listlocation.jsp");
			rd.forward(request, response);	
			break;
		case "/add":
			System.out.println("add PATH is" + path);
			Location loc = new Location();	
//			System.out.println(request.getParameter("locId"));
//			System.out.println(request.getParameter("rackNumber"));
//			System.out.println(request.getParameter("shelfId"));
			
			loc.setLocId(Integer.parseInt(request.getParameter("locationid")));
			loc.setRackNumber(request.getParameter("racknumber"));
			loc.setShelfId(request.getParameter("shelfid"));
			locMgr.createLocation(loc);
//			l.setRackNumber("abc");
//			l.setShelfId("def");
			
			System.out.println(loc);
			request.setAttribute("loca1", loc);
			rd = request.getRequestDispatcher("/location/list");
			rd.forward(request, response);	
			break;
		case "/showempty":
			System.out.println("PATH is" + path);
			rd = request.getRequestDispatcher("/EmptylistLocation.jsp");
			rd.forward(request, response);	
			break;
			
		case "/delete":
			int Locid=Integer.parseInt(request.getParameter("LOCid"));
			//System.out.println(Locid);
			Location locobj = locMgr.getOneLocation(Locid);
			//System.out.println(locobj);
			locMgr.deleteLocation(locobj);		
			//rd = request.getRequestDispatcher("/location/listlocation.jsp");
			//rd.forward(request, response);
			rd = request.getRequestDispatcher("/location/list");
			rd.forward(request, response);	
			
			//rd.include(request, response);
			break;
		case "/showvalue":
			int locid=Integer.parseInt(request.getParameter("LOCid"));
			System.out.println(locid);
			Location locationobj = locMgr.getOneLocation(locid);
			System.out.println(locationobj);
			request.setAttribute("locationobj", locationobj);			
			rd = request.getRequestDispatcher("/ValueListLocation.jsp");
			rd.forward(request, response);
			break;	
		case "/update":
			int locid1=Integer.parseInt(request.getParameter("locationid"));
			System.out.println(locid1);
			Location locationobj1 = locMgr.getOneLocation(locid1);		
			locationobj1.setRackNumber(request.getParameter("racknumber"));
			locationobj1.setShelfId(request.getParameter("shelfid"));
			
			int result = locMgr.updateLocation(locationobj1);
			System.out.println(result);
			
			request.setAttribute("locationobj", locationobj1);
			rd = request.getRequestDispatcher("/location/list");
			rd.forward(request, response);	
		}

		
	}



}
