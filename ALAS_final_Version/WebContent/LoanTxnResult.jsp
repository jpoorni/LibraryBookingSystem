<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"        %> --%>
 <%@ page import="java.util.*" %>
 <%@ page import="model.Items" %>
 <%@ page import="model.LoanTxnDto" %>  
     
<%@ include file="./resource/jsp/head.jsp"%>

			
<%  List<Items> itemList=(ArrayList)request.getAttribute("itemslist");  
 	Users borrowerStu =(Users) request.getAttribute("borrowerStu"); 
 	String txnResultMsg = (String) request.getAttribute("txnResultMsg");
 	
 %>

	<div  style="text-align: center " >
		<% if(txnResultMsg!=null && txnResultMsg.length()>0){	%>		
			<h2 style="color:red;"><%=txnResultMsg %></h2>   
			<hr>
		<% } %>
	</div>
	
<h3 style="text-align: center ">Student Details:</h3>

	<div  style="text-align: right " >
		<center>
		<% if(borrowerStu!=null ){	%>		
			<table border="1"  >
				<tr>
				
					<th>Name</th>
					<th>Login Id</th>
					<th>Email</th>
					<th>phone No</th>
					<th>Status</th>
				</tr><tr/><tr/>
						
				<tr> 
					
					<td><%=borrowerStu.getName() %></td>
					<td><%=borrowerStu.getLoginid() %></td>
					<td><%=borrowerStu.getEmail()%></td>
					<td><%=borrowerStu.getPhoneno() %></td>
					<td><%=borrowerStu.getStatus() %></td>
				</tr>
			</table>

		<% } else  {%>
			<%= "Although transaction was successful but system faces issue in display summary " %>
		<% } %>	
			</center>
	</div>

<br/><br/> 		<hr>
<h3 style="text-align: center ">Items Details:</h3>
<div  style="text-align: right " >
	<center>
	<% if(itemList!=null &&  itemList.size()>0){	%>		
			<table border="1"  >
			<tr>
				<th>Sr. No.</th>
				<th>isbn</th>
				<th>Name</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Published Year</th>
				</tr>
			<tr/> <tr/>
			<% for(int i=0; i<itemList.size();i++){ %>
			
			<tr> 
				<td><%=i+1%></td>
				<td><%=itemList.get(i).getIsbn() %></td>
				<td><%=itemList.get(i).getItemName()%></td>
				<td><%=itemList.get(i).getAuthor() %></td>
				<td><%=itemList.get(i).getPublisher() %></td>
				<td><%=itemList.get(i).getPublishedYear() %></td>
				
			</tr>
			<%} %>
			</table>
	<% } else if(request.getAttribute("txnResultMsg")==null && itemList!=null) {%>
		<%= "Although transaaction was successful but system faces issue in display summary " %>
	
	<% } %>	
		</center>
</div>
<br/><br/>
<hr>
<%@ include file="./resource/jsp/foot.jsp"%>