<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
	boolean isStudent=false;
	boolean isLibrarian=false;
	String loggedInUsrRole =(String) session.getAttribute("loggedInUsrRole");
	if(loggedInUsrRole!=null && loggedInUsrRole.equalsIgnoreCase("STUDENT")){
		isStudent=true;
	} else if(loggedInUsrRole!=null && loggedInUsrRole.equalsIgnoreCase("LIBRARIAN")){
		isLibrarian=true;
	} %>

<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

eli a:hover:not(.active) {
    background-color: #111;
}

.active {
    background-color: #4CAF50;
}
</style>
<ul>
  <li><a href="<%=request.getContextPath()%>/searchItems.jsp">Issue Items</a></li>
  <%  if(isStudent){  %>
  <%-- <li><a href="<%=request.getContextPath()%>/StudentReturn.jsp"> Return Items </a></li>--%>
  <li><c:url var="studentReturn" value="/return/stulist" /><a href="${studentReturn}">Return Items</a></li>
  <li><c:url var="studentTransHistory" value="/search/stulist" /><a href="${studentTransHistory}">Student Transaction History</a></li>
  <% } %>
<%--  DONE BY REST :  START --%>
  <%if(isLibrarian){  %>
  	 <li><c:url var="returnitem" value="/return/list" /><a href="${returnitem}">Return Items</a></li>
 	 <li><c:url var="maintainusers" value="/user/list" /><a href="${maintainusers}"> Maintain Users </a></li>
 	 <li><c:url var="maintainitems" value="/item/list" /><a href="${maintainitems}"> Maintain Items </a></li> 
  <li><c:url var="maintainlocation" value="/location/list" /><a href="${maintainlocation}"> Maintain location </a></li>
  <li><c:url var="searchitems" value="/search/mainitems" /><a href="${searchitems}"> Search Items</a></li>
  <li><c:url var="searchusers" value="/search/usersmain" /><a href="${searchusers}"> Search User</a></li>
 	 <li><c:url var="search" value="/search/main" /><a href="${search}">History</a></li>  
  <% } %>
  	
<%--  DONE BY REST :  END --%>     
  	<li><a href="<%=request.getContextPath()%>/LoginServlet?action=Logout"><font color="red">Logout !</font></a></li>
</ul>
               
  