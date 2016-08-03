<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="./resource/jsp/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body> --%>

<%@ include file="./resource/jsp/head.jsp"%>

	<form action="/AllLibraryAccessSystem/location/add" method="post">
		<Label> Location ID</Label> <input type="text" name="locId" /> <br>
		<br> <Label>Rack Number </Label> <input type="text"
			name="rackNumber" /> <br>
		<br> <Label>Shelf Number </Label> <input type="text"
			name="shelfId" /> <br>
		<br> <input type="submit" value="SUBMIT" /> <input type="reset"
			value="RESET" />
	</form>
<%-- </body>
<%@ include file="./resource/jsp/foot.jsp"%>
</html>
 --%>
 
 <%@ include file="./resource/jsp/foot.jsp"%>

