<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<form action="${pageContext.request.contextPath}/search/date" method="post">
	<br>
	<fieldset style=width:30%>
	 <legend style="font-size:17px;color:BlueViolet ">Search by Date</legend>
		From : <input type="date" name="fromDate" /> 
		To: <input type="date" name="toDate" /> <br> <br>
		<input type="submit" value="SUBMIT" /> 
		<input type="reset" value="RESET" />
	</form>
	<br>
	</fieldset>
	<lable style="font-size:20px;color:Black">OR</lable>
	<br>
<form action="${pageContext.request.contextPath}/search/cat" method="post">
	<fieldset style="width:30%;">
	 <legend style="font-size:17px;color:BlueViolet ">Search by Category</legend>
		Category : 
	  <select  name="cat" id="catname" >
	  <option value="magazine">Magazine</option>
	  <option value="manuscript">Manuscript</option>
	  <option value="algorithm">Algorithm</option>
	  <option value="kits">Kits</option>
	  <option value="technology">Technology</option>
	  <option value="business">Business</option>
      </select>
			<input type="submit" value="SUBMIT" /> 
			<br><br>
	</fieldset>
	<br>
	</form>
	<c:choose>
		<c:when test="${size < 2}">
		There is no list to show
		</c:when>
		<c:otherwise>
			<table border="1",  border-collapse: collapse>
				<tr>
					<td>SNo</td>
					<td>Item Name</td>
					<td>Category</td>
					<td>IssuedOn</td>
					<td>Student ID</td>
					<td>Status</td>
				</tr>
				<c:forEach items="${slist}" var="trans" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td>${trans.itemName}</td>
						<td>${trans.category}</td>
						<td>${trans.issueOn}</td>
						<td>${trans.borrower}</td>
						<td>${trans.itemStatus}</td>
						</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>

	<c:url var="listall" value="/search/list">
	</c:url>
	<a href="${listall}">List All</a>
</center>
<%-- </body>
<%@ include file="./resource/jsp/foot.jsp"%>
</html> --%>

<%@ include file="./resource/jsp/foot.jsp"%>

