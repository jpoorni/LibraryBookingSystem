            <%@ include file="./resource/jsp/head.jsp"%>
            
            
            
<center>

		<br><br><br>
			<form action="${pageContext.request.contextPath}/search/studate" method="post">
	<fieldset style=width:30%>
	 <legend style="font-size:17px;color:BlueViolet ">Search by Date</legend>
				From : <input type="date" name="fromDate" /> 
				To: <input type="date" name="toDate" /> <br> <br>
				<input type="submit" value="SUBMIT" /> 
				<input type="reset" value="RESET" />	
			</fieldset>
			</form>
<lable style="font-size:20px;color:Black ">OR</lable>
<br>
<form action="${pageContext.request.contextPath}/search/stucat" method="post">
	<fieldset style=width:30% >
	 <legend style="font-size:17px;color:BlueViolet">Search by Category</legend>
		<lable>Category : </lable>
			  <select  name="cat" id="catname" >
			  <option value="magazine">Magazine</option>
			  <option value="manuscript">Manuscript</option>
			  <option value="algorithm">Algorithm</option>
			  <option value="kits">Kits</option>
			  <option value="technology">Technology</option>
			  <option value="business">Business</option>
		      </select>
					<input type="submit" value="SUBMIT" /> 
					
			</fieldset>
		</form>
		<br> <br><br> <br>
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
	<br>
	<br>
	<c:url var="listallstu" value="/search/stulist">
	</c:url>
	<a href="${listallstu}">List All</a>
</center>
<%@ include file="./resource/jsp/foot.jsp"%>