<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"        %>
 <%@ page import="java.util.*" %>
  <%@ page import="model.Items" %>
 <%@ page import="model.LoanTxnDto" %>   --%>
 
 
  <%@ page import="java.util.*" %>
<%@ include file="./resource/jsp/head.jsp"%>

<%  List<Items> itemList=(ArrayList)request.getAttribute("items");  
 	List<Users> studentList=(List<Users> ) session.getAttribute("studentList"); 
 	String txnResultMsg = (String) request.getAttribute("txnResultMsg");
 	Users loggedInUsrLoginId = (Users) session.getAttribute("loggedInUsr");
 %>
<script>
		$(document).ready(function() {
				$('#searchType').change(function(event) {
				    var ctgry = $("select#searchType").val();
			        $.get('ItemSelectionServlet', {
			        	 categoryName : ctgry
			        }, function(response) {			
				        var select = $('#searchValue');
				        select.find('option').remove();
				        
				          $.each(response, function(index, value) {
				          $('<option>').val(value).text(value).appendTo(select);
				      	});
				         $('<option>').val('All').text('All').prependTo("#searchValue");
				      
			        });
		        });
		});

</script>

<script>


function findBorrowerCheck(){
	var usrSearchKey=document.getElementById("findUsertxtBx").value;
	if(!usrSearchKey.length>0){
		alert("Please key in student name ");
		return false;	
	}else{
		document.forms["findBorrowerForm"].submit();
	}
	
}

function searchItem(){
	document.getElementById("SearchBy").value=document.getElementById("searchType").value;
	document.getElementById("SearchByValue").value=document.getElementById("searchValue").value;
	
	if (document.getElementById("searchValue").value == "NoName") {
		alert("Please select a Search by");
		return false;		
	} else {
		
	    document.forms["searchItemForm"].submit();
	}
}


function checkIt(){
	// check for borrower selection
	var radios = document.getElementsByName("usrRadioBtnId");
	var isStudentSelected="no";
	for (var i = 0; i < radios.length; i++) {
	    if (radios[i].type == 'radio' && radios[i].checked) {
	    	isStudentSelected = radios[i].value; 
	    	//alert(isStudentSelected);
	    	document.getElementById("selectedBorrower").value=isStudentSelected;
	    }
	}
	
	// check for book selection
	var isAnyItemChecked="no";
	var itemSelectioCount=0;
	<% if(itemList!=null &&  itemList.size()>0){
		for(int i=0; i< itemList.size(); i++){
	%>
			var checkBx_id="itemChkBx_"+<%=i%>;
			var checkBx_ctrl=document.getElementById(checkBx_id);
			if(checkBx_ctrl.checked){
	 			isAnyItemChecked="yes";
	 			itemSelectioCount++;
	 		 }
	 	
	<% } 
	}
	%>
	
	// Do not submit form if no user or item is selected
	if(isStudentSelected == "no"){
		alert("Please select a student");
		return false;
	} 
	if( isAnyItemChecked =="no"){
		alert("Please select an item for loan ");
		return false;
	}
	
	if(itemSelectioCount>10){
		alert("More than 10 items are not allowed to take on loan!, Please select max 10 items. ");
		return false;
	}
	
	//alert(document.getElementById("selectedBorrower").value);
	document.forms["loanitemForm"].submit();

	 
}


//for student submission

function checkItforStudent(){
	// check for borrower selection
	//var radios = document.getElementsByName("usrRadioBtnId");
	//var isStudentSelected="no";
	/* for (var i = 0; i < radios.length; i++) {
	    if (radios[i].type == 'radio' && radios[i].checked) {
	    	isStudentSelected = radios[i].value; 
	    	//alert(isStudentSelected);
	    	document.getElementById("selectedBorrower").value=isStudentSelected;
	    }
	}
	 */
	//set borrower studentId
	
	//alert("Start");
	document.getElementById("selectedBorrower").value=<%= "'"+loggedInUsrLoginId.getLoginid()+"';" %>;
	//alert(document.getElementById("selectedBorrower").value);
	// check for book selection
	var isAnyItemChecked="no";
	var itemSelectioCount=0;
	<% if(itemList!=null &&  itemList.size()>0){
		for(int i=0; i< itemList.size(); i++){
	%>
			var checkBx_id="itemChkBx_"+<%=i%>;
			var checkBx_ctrl=document.getElementById(checkBx_id);
			if(checkBx_ctrl.checked){
	 			isAnyItemChecked="yes";
	 			itemSelectioCount++;
	 		 }
	 	
	<% } 
	}
	%>
	
	// Do not submit form if no user or item is selected
	/* if(isStudentSelected == "no"){
		alert("Please select a student");
		return false;
	}  */
	if( isAnyItemChecked =="no"){
		alert("Please select an item for loan ");
		return false;
	}
	
	if(itemSelectioCount>10){
		alert("More than 10 items are not allowed to take on loan!, Please select max 10 items. ");
		return false;
	}
	
	//alert(document.getElementById("selectedBorrower").value);
	document.forms["loanitemForm"].submit();

	 
}
</script>

<div  style="text-align: center " >
		<% if(txnResultMsg!=null && txnResultMsg.length()>0){	%>		
			<h2 style="color:red;"><%=txnResultMsg %></h2>   
			<hr>
		<% } %>
	</div>
	
	

<%  if(isLibrarian){  %>
<div>
<h3>Search student</h3>
<form action="loanController" method="post" name="findBorrowerForm" >
		<input type="hidden" name="actionName" id="actionName" value="findBorrower" />
		Student Name: <input type="text" name="findUsertxtBx" id="findUsertxtBx" value=""/>		
		<input type="button" value="Find Student" onclick="findBorrowerCheck()" />   		
</form>
</div>
<br/><br/>
<div>
<center>
	<% if(studentList!=null &&  studentList.size()>0){	%>		
		<table border="1"  >
			<tr>
				<th></th>
				<th>Name</th>
				<th>Login Id</th>
				<th>Email</th>
				<th>phone No</th>
				<th>Status</th>
			</tr><tr/><tr/>
			<% for(Users usr:studentList){ %>			
			<tr> 
				<td><input type="radio" name="usrRadioBtnId" id="usrRadioBtnId" value="<%= usr.getLoginid() %>"> </td>
				<td><%=usr.getName() %></td>
				<td><%=usr.getLoginid() %></td>
				<td><%=usr.getEmail()%></td>
				<td><%=usr.getPhoneno() %></td>
				<td><%=usr.getStatus() %></td>
			</tr>
			<%} %>
		</table>
	<% } else if (studentList!=null) {%>
		<%= "No student found matching search criteria, Search other items! " %>

	<% } %>	
	
	</center>
	
</div>
<% } %>

<br/><br/>


<div>
<hr> 
	<form action="ItemController" method="get" name="searchItemForm" >
	
		<input type="hidden" name="SearchBy" id="SearchBy" />
		<input type="hidden" name="SearchByValue" id="SearchByValue" />
		<h3>Search An Item</h3>
		Search by 
			<select  id="searchType">
			  <option value="NoName">Select..</option>
			  <option value="Category">Category</option>
			  <option value="Author">Author</option>
			  <option value="Publisher">Publisher</option>
			</select>		 
		Name:
			<select  id="searchValue" >
				<option value="NoName">Select..</option>
			</select>
		<input type="button" value="search" onclick=searchItem()  />   
		
	</form>
</div>

<br/>

<div class="headerTable" align="center" style="text-align: center;">

<center>
	<% if(itemList!=null &&  itemList.size()>0){	%>
		<form action="loanController" name="loanitemForm" method="post">
			<table border="1"  >
			<tr>
				<th></th>
				<th>isbn</th>
				<th>Name</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Published Year</th>
				</tr>
			<tr/> <tr/>
			<% for(int i=0; i<itemList.size();i++){ %>
			
			<tr> <input type="hidden" name="itemId_<%=i%>" id="itemId_<%=i%>" value=<%=itemList.get(i).getItemId()%>/>
				<td><input type="checkbox" name="itemChkBx_<%=i%>"id="itemChkBx_<%=i%>"  ></td>
				<td><%=itemList.get(i).getIsbn() %></td>
				<td><%=itemList.get(i).getItemName()%>
					<%--<a href="javascript:showBookingDiv('<%=itemList.get(i).getItemId()%>','<%=itemList.get(i).getItemName()%>',<%=itemList.get(i).getIsbn()%>,'<%=itemList.get(i).getAuthor()%>',<%=itemList.get(i).getLocId()%>,<%=itemList.get(i).getTypeId()%>,'<%=itemList.get(i).getPublishedYear()%>','<%=itemList.get(i).getPublisher()%>')">
						<%=itemList.get(i).getItemName()%>
					</a> --%>
				</td>
				<td><%=itemList.get(i).getAuthor() %></td>
				<td><%=itemList.get(i).getPublisher() %></td>
				<td><%=itemList.get(i).getPublishedYear() %></td>
				
			</tr>
			<%} %>
			</table>
			
			<br/><br/><br/>
			<input type="hidden" name="methodName" id="methodName" value="loanRequest" />
			<input type="hidden" name="selectedBorrower" id="selectedBorrower" value="" />
			<%-- <input type="submit" value="Loan this Book" /> --%> 
			<% if(isStudent){ %>
				<input type="button" value="Loan this Book" onclick="checkItforStudent()"/> 
			<%} %>
			<% if(isLibrarian){ %>
				<input type="button" value="Loan this Book" onclick="checkIt()"/> 
			<%} %> 
		</form>	
	</center>
	</div>

		<hr>
	<% } else if(request.getAttribute("txnResultMsg")==null && itemList!=null) {%>
	<%= "No item is found matching search criteria, Search other items! " %>
	<hr>
	<% } %>	

<br/>


<%@ include file="./resource/jsp/foot.jsp"%>