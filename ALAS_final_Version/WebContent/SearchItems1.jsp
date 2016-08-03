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
<br><br><br>
	<form action="${pageContext.request.contextPath}/search/items" method="post">
	<fieldset style=width:30%>
	 <legend style="font-size:17px;color:BlueViolet ">Search by Id/Name</legend>
	  <input type="radio" name="useroptions" value="itemID">itemID <br><br><br>
	  <input type="radio" name="useroptions" value="itemName">itemName <br><br><br>
	  <input type="text" name="searchText" />
	</fieldset>
	 <br>											
	 
	<lable style="font-size:20px;color:Black ">OR</lable>
	<br><br>				
	 <fieldset style=width:30%>
	 <legend style="font-size:17px;color:BlueViolet ">Search by Type</legend>
	  <select  name="type" id="itemType" >
	  <option value="500">Magazine</option>
	  <option value="501">Manuscript</option>
	  <option value="502">Algorithm</option>
	  <option value="503">Kits</option>
	  <option value="504">Technology</option>
	  <option value="505">Business</option>
	  </select>
	</fieldset>
		<br><br>
 	 <input type="submit" value="Search" /> 
	 <input type="reset" value="Reset" /> <br> <br> <br>
	</form>
	<c:choose>
		<c:when test="${size < 2}">
		There is no list to show
		</c:when>
		<c:otherwise>
			<label>item's List</label>
			<table border="1">
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
				</tr>
				<c:forEach items="${itemlist}" var="itemDTO" varStatus="i">
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
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	
	<c:url var="listallitems" value="/search/listAllItems">
	</c:url>
	<a href="${listallitems}">List All</a>
	<br>
	<br>
	<br>
	<br>
	<br>
	<label>${message}</label>
</center>
<%-- 
<%@ include file="./resource/jsp/foot.jsp"%>

</body>
</html> --%>

<%@ include file="./resource/jsp/foot.jsp"%>