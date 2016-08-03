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
<table border="1">
    <!-- Summer add new -->   
		<tr>
		    <td>SNo </td>
			<td>Location ID</td>
			<td>Rack Number</td>
			<td>Shelf Number</td>
			<td>Edit</td>
			<td>Delete </td>
		</tr>
		
		<c:forEach items="${slist}" var="location" varStatus="i">
			<tr> 
			    <td>${i.index+1}</td>
				<td>${location.locId}</td>
				<td>${location.rackNumber}</td>
				<td>${location.shelfId}</td>
				<td><c:url var="edit" value="/location/showvalue">
						<c:param name="LOCid" value="${location.locId}" />
						</c:url> <a href="${edit}">Edit</a></td>
						
				<td><c:url var="delete" value="/location/delete">
						<c:param name="LOCid" value="${location.locId}" />
					</c:url> <a href="${delete}">Delete</a></td>
			
			</tr>
		</c:forEach>	
		
	</table>
	
 	<c:url var="add" value="/location/showempty" /> 
     <a href="${add}"> Add Location</a> <br>
	</center>
<%-- </body>
<%@ include file="./resource/jsp/foot.jsp"%>
</html> --%>
<%@ include file="./resource/jsp/foot.jsp"%>
