<%-- <%@page import="model.itemDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new item</title>
</head>
<body> --%>

<%@ include file="./resource/jsp/head.jsp"%>

<center>
	<%--<label>sddff-${User.loginid}-hhjhjh</label>just for checking--%>


	<c:if test="${requestScope.Option == 'add'}">
		<c:set var="ss" value="/AllLibraryAccessSystem/item/create" />

	</c:if>
	<c:if test="${requestScope.Option == 'update'}">
		<c:set var="ss" value="/AllLibraryAccessSystem/item/update" />

	</c:if>
	<form action="/AllLibraryAccessSystem/item/create" method="post">
		<table frame="box" style="width: 45%">
			<c:choose>
				<c:when test="${requestScope.Option == 'add'}">

					<tr>
						<td>Item ID:</td>
						<td><input type="text" name="itemID" required /></td>
					</tr>

				</c:when>

				<c:otherwise>
					<tr>
						<label> ${requestScope.item.itemID}</label>
						<td>Item ID:</td>
						<td><input type="text" name="itemID" readonly="readonly"
							value="${item.itemID}" /></td>
					</tr>
				</c:otherwise>
			</c:choose>

			<tr>
				<td>Type ID</td>
				<td><input type="text" name="typeID" value="${item.typeID}"
					required /></td>
			</tr>

			<tr>
				<td>Item Name</td>
				<td><input type="text" name="itemName" value="${item.itemName}"
					required /></td>
			</tr>

			<tr>
				<td>ISBN</td>
				<td><input type="text" name="ISBN" value="${item.ISBN}"
					required /></td>
			</tr>

			<tr>
				<td>Publisher</td>
				<td><input type="text" name="publisher"
					value="${item.publisher}" required /></td>
			</tr>

			<tr>
				<td>Published Year</td>
				<td><input type="date" name="publishedYear"
					value="${item.publishedYear}" required /></td>
			</tr>

			<tr>
				<td>Author</td>
				<td><input type="text" name="author" value="${item.author}"
					required /></td>
			</tr>

			<tr>
				<td>Status:</td>
				<td><input type="radio" name="status" value="Available"
					checked="checked" /> Available<br /> <input type="radio"
					name="status" value="Unvailable" />Unavailable</td>
			</tr>

			<tr>
				<td>LocationID</td>
				<td><input type="text" name="locID" value="${item.locID}"
					required /></td>
			</tr>

		</table>
		<c:choose>
			<c:when test="${requestScope.Option=='add'}">
				<br />
				<input type="submit" value="Submit">
				<input type="reset" value="Reset">
			</c:when>
			<c:otherwise>
				<br />
				<input type="submit" value="Update">
			</c:otherwise>
		</c:choose>

	</form>
</center>

<%@ include file="./resource/jsp/foot.jsp"%>

<!-- 
</body>
</html> -->