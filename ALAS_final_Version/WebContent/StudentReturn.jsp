<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./resource/jsp/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
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
	<br><br><br><br><br><br><br>
	<label>${message}</label>
	<lable style="font-size:20px;color:Brown">${fineMSg}</lable>
<center>
</body>
<%@ include file="./resource/jsp/foot.jsp"%>
</html>