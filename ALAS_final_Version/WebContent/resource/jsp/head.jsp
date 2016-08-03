<%@ page import="model.*" %>

<%--
other's code
 --%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
 
<html>
  <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <meta http-equiv="expires" content=<%= new java.util.Date() %>>
        <meta http-equiv="no-cache"> 
		<title>ALAS</title>
		<% String contextPath=request.getContextPath(); %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-1.11.1.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resource/css/alas.css" >
	</head> 
	
	 <body >
      
            <div id="hd" class="headerFix">
                <!-- start: your content here -->
                <table border="0" class="headerFix"  width="100%" > 
                <tr>
                <td class="headerTable" align="center" style="text-align: center;">
                	<h1 class="title">
                		<p> <font color="white" face="Times New Roman">All Library Access System</font></p>
                	</h1>
                </td>
                </tr>
                <tr>
                </table>
                
			</div>
                <!-- end: your content here -->
            <br /><br />
            <br/><hr />
			
			
			<%--  MENU BAR --%>
				 <%@ include file="./menu.jsp"%>
				<%-- <jsp:include page="<%=request.getContextPath()%>/menu.jsp" /> --%>
				
            <div id="bd">
				<div id="main">
                    <div>
						<table border="0" class="headerTable" width="100%" > 
						<tr>
							<td class="headerTable" align="center" style="text-align: center;">	
							<td>
						</tr>
						<tr>
							<td class="headerTable" align="center" style="text-align: center;">	
							
					