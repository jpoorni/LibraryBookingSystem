<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="./resource/jsp/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List All Users</title>
</head>
<body> --%>

<%@ include file="./resource/jsp/head.jsp"%>

	<table border="1" style="width: 100%">

		<tr>
			<td>SNo</td>
			<td>Login ID</td>
			<td>Password</td>
			<td>Role ID</td>
			<td>Name</td>
			<td>E-mail</td>
			<td>Phone Number</td>
			<td>CreatedOn</td>
			<td>Status</td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>
		<c:forEach var="User" items="${userlist}" varStatus="i">
			<tr>
				<c:if test="${User.status=='Active'}">
					<td>${i.index+1}</td>
					<td>${User.loginid}</td>
					<td>${User.password}</td>
					<td>${User.roleid}</td>
					<td>${User.name}</td>
					<td>${User.email}</td>
					<td>${User.phoneno}</td>
					<td>${User.createdon}</td>
					<td>${User.status}</td>
					<td><c:url var="edit" value="/user/show">
						<c:param name="LoginID" value="${User.loginid}" />
						</c:url> <a href="${edit}">Edit</a></td>
					
					<td><c:url var="delete" value="/user/delete">
							<c:param name="LoginID" value="${User.loginid}" />
						</c:url> <a href="${delete}">Delete</a></td>

				</c:if>
			</tr>
		</c:forEach>
	</table>
	<c:url var="createuser" value="/user/add" />
	<a href="${createuser}">Create New User</a>
	<br>

<%@ include file="./resource/jsp/foot.jsp"%>
<!-- 
</body>

</html> -->