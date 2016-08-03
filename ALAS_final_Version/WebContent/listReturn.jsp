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
	<!-- do -- type in student name , we will get student id -->
	<!-- do -- transaction commit and roll back-->
	<!-- do -- filter the list according to the student id -->
	<!-- do -- font change for not found -->

<%@ include file="./resource/jsp/head.jsp"%>
<center>
	<form
		action="${pageContext.request.contextPath}/return/listStudentTran"
		method="post">
		<br> <br> <Label>Student ID</Label> <input type="text"
			name="studId" Required /> <input type="submit" value="Search" /> <input
			type="reset" value="Reset" /> <br> <br> <br>
	</form>

	<c:choose>
		<c:when test="${size < 2}">
		There is no list to show
		</c:when>
		<c:otherwise>
			<label>Transactions List</label>
			<table border="1">
				<tr>
					<td>SNo</td>
					<td>Item Name</td>
					<td>Category</td>
					<td>IssuedOn</td>
					<td>Student ID</td>
					<td></td>
				</tr>
				<c:forEach items="${slist}" var="trans" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td>${trans.itemName}</td>
						<td>${trans.category}</td>
						<td>${trans.issueOn}</td>
						<td>${trans.borrower}</td>
						<td><c:url var="itemreturn" value="/return/itemreturn">
								<c:param name="loanId" value="${trans.loanId}" />
								<c:param name="itemId" value="${trans.itemId}" />
								<c:param name="issueOn" value="${trans.issueOn}" />
							</c:url> <a href="${itemreturn}">Return Book</a></td>

					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<c:url var="listall" value="/return/list">
	</c:url>
	<a href="${listall}">List All</a>
	<br>
	<br>
	<br>
	<br>
	<br>
	<lable style="font-size:20px;color:Red">${message}</lable>
	<lable style="font-size:20px;color:Brown">${fineMSg}</lable>
	</center>
	<%@ include file="./resource/jsp/foot.jsp"%>
	
<%-- 
<%@ include file="./resource/jsp/foot.jsp"%>
<!-- </body>
</html> --> --%>