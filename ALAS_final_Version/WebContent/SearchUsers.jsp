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
	<form action="${pageContext.request.contextPath}/search/users" method="post">
	<fieldset style="width:30%;">
	 <legend style="font-size:17px;color:BlueViolet ">Search by Id/Name</legend>
	  <input type="radio" name="useroptions" value="userId">User Id <br><br><br>
	  <input type="radio" name="useroptions" value="userName">Name <br><br><br>
	  <input type="text" name="searchText" />
	</fieldset>
	 <br>
	 <lable style="font-size:20px;color:Black ">OR</lable>
	  <br>
	 <fieldset style="width:30%;">
	 <legend style="font-size:17px;color:BlueViolet ">Search by Status</legend>
	  <select  name="status" id="statusname" >
	  <option value="active">Active</option>
	  <option value="inactive">In Active</option>
	  </select>
	</fieldset>
		<br><br>
 	 <input type="submit" value="Search" /> 
	 <input type="reset" value="Reset" /> 
	</form>
	<c:choose>
		<c:when test="${size < 2}">
		There is no list to show
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<td>SNo</td>
					<td>Login Id</td>
					<td>Name</td>
					<td>RoleId</td>
					<td>Email</td>
					<td>Status</td>
				</tr>
				<c:forEach items="${userlist}" var="user" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td>${user.loginid}</td>
						<td>${user.name}</td>
						<td>${user.roleid}</td>
						<td>${user.email}</td>
						<td>${user.status}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	
	<c:url var="listallUsers" value="/search/listAllUsers">
	</c:url>
	<a href="${listallUsers}">List All</a>
	<br>
	<br>
	<br>
	<br>
	<br>
	<label>${message}</label>
	</center>
<%-- 	
</body>
<%@ include file="./resource/jsp/foot.jsp"%>
</html>
 --%>
 
 <%@ include file="./resource/jsp/foot.jsp"%>
 