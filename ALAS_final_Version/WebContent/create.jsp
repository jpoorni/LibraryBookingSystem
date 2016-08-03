<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
	

	
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 --%>



<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New User</title>
</head>
<body> --%>

    <%@ include file="./resource/jsp/head.jsp"%>
    
	<center>
		<%--<label>sddff-${User.loginid}-hhjhjh</label>just for checking--%>	
		<table frame="box" style="width: 45%">
	    
		<c:choose>
			<c:when test="${requestScope.Option=='add'}">
				<form action="/AllLibraryAccessSystem/user/create" method="post">				
					<tr>
						<td>Login ID:</td>
						<td><input type="text" name="loginid" required /></td>
					</tr>
			</c:when>
		
			<c:otherwise>
				<form action="/AllLibraryAccessSystem/user/update" method="post">					
					<tr>
						<td>Login ID:</td>
						<td><input type="text" name="loginid" readonly="readonly" value="${User.loginid} "/>
						</td>
					</tr>
			</c:otherwise>
		</c:choose>
		
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value="${User.password}" required/></td>
			</tr>

			<tr>
				<td>Role ID:</td>
				<c:choose>
				<c:when test="${requestScope.Option=='add'}">
				<td><input type="radio" name="roleid" value="101"  /> Student <br />
					<input type="radio" name="roleid" value="102" /> Librarian <br /></td>
				</c:when>
				
				<c:otherwise>	
							
				<c:if test="${User.roleid==101}">
				<td><input type="radio" name="roleid" value="101"  checked="checked"/> Student <br />
				<input type="radio" name="roleid" value="102" /> Librarian <br /></td>
				</c:if>
				
				<c:if test="${User.roleid==102}">
				<td><input type="radio" name="roleid" value="101"  /> Student <br />
					<input type="radio" name="roleid" value="102"  checked="checked" /> Librarian <br /></td>	
				</c:if>	
					
				</c:otherwise>
				</c:choose>								
			</tr>

			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" value="${User.name}" required /></td>

			</tr>

			<tr>
				<td>E-mail:</td>
				<td><input type="text" name="email"  value="${User.email}"/></td>

			</tr>

			<tr>
				<td>Phone No:</td>
				<td><input type="text" name="phoneno" value="${User.phoneno}" required/></td>

			</tr>

			<tr>
				<td>Created Date:</td>
				<td><input type="date" name="createdon" value="${User.createdon}"/></td>
			</tr>

			<tr>
				<td>Status:</td>
				<td><input type="radio" name="status" value="Active" checked="checked"/> Active<br />
					<input type="radio" name="status" value="Inactive" />Inactive</td>
			</tr>



		</table>
		<c:choose>
		<c:when test="${requestScope.Option=='add'}">
		<br /> <input type="submit" value="Submit"> <input
			type="reset" value="Reset">			
		</c:when>
		<c:otherwise>
		<br /> <input type="submit" value="Update">
       </c:otherwise>
        </c:choose>
		</form>
	</center>
	
	<%@ include file="./resource/jsp/foot.jsp"%>
	<!-- 
</body>

</html> -->