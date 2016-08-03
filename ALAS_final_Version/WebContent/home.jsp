<%@ include file="./resource/jsp/head.jsp"%>

	<h1 style="color:red;">Welcome To ALAS, <%= ((Users) session.getAttribute("loggedInUsr")).getName() %> !</h1> 
	
	
 
<%@ include file="./resource/jsp/foot.jsp"%>