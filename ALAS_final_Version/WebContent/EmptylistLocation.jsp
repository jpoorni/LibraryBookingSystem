<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ include file="./resource/jsp/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body> --%>

	<%@ include file="./resource/jsp/head.jsp"%>
	
	
	<center>
		<table frame="box" style="width: 45%">
			<form action="/AllLibraryAccessSystem/location/add" method="post">
				<tr>
					<td>Location ID:</td>
					<td><input type="text" name="locationid" required /></td>
				</tr>

				<tr>
					<td>Rack Number:</td>
					<td><input type="text" name="racknumber" required /></td>
				</tr>

				
					<tr> <td>Shelf Number:</td>
					<td><input type="text" name="shelfid" required /></td></tr>
								
		</table>
		<br /> <input type="submit" value="Submit"> <input
			type="reset" value="Reset">
		</form>
		<c:url var="List" value="/location/list"/>
		<a href="${List}">Back</a>
	</center>

	<br>
	
	<%@ include file="./resource/jsp/foot.jsp"%>
	
<%-- </body>
<%@ include file="./resource/jsp/foot.jsp"%>
</html> --%>