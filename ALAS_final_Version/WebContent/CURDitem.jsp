<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<%@ include file="./resource/jsp/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List All Items</title>
</head>
<body> --%>

    <%@ include file="./resource/jsp/head.jsp"%>
    
    
<table border="1" style="width: 100%">

		<tr>
		    <td>SNo</td>
			<td>itemID</td>
			<td>typeID</td>
			<td>itemName</td>
			<td>ISBN</td>
			<td>publisher</td>
			<td>publishedYear</td>
			<td>author</td>
			<td>status</td>
			<td>locID</td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>

		<c:forEach items="${itemlist}" var="itemDTO" varStatus="i" >
			<tr>
					<td>${i.index+1}</td>
					<td>${itemDTO.itemID}</td>
					<td>${itemDTO.typeID}</td>
					<td>${itemDTO.itemName}</td>
					<td>${itemDTO.ISBN}</td>
					<td>${itemDTO.publisher}</td>
					<td>${itemDTO.publishedYear}</td>
					<td>${itemDTO.author}</td>
					<td>${itemDTO.status}</td>
					<td>${itemDTO.locID}</td>
					
					<td><c:url var="edit" value="/item/show">
						<c:param name="itemID" value="${itemDTO.itemID}"/>
						</c:url>
						 <a href="${edit}">Edit</a></td>
					
					<td><c:url var="delete" value="/item/delete">
							<c:param name="itemID" value="${itemDTO.itemID}"/>
						</c:url>
						 <a href="${delete}">Delete</a>
						 
						 </td>

			</tr>
		</c:forEach>
	</table>
	<c:url var="createitem" value="/item/add" />
	<a href="${createitem}">Create New Item</a>
<%@ include file="./resource/jsp/foot.jsp"%>
<!-- </body>
</html> -->