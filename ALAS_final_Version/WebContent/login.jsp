<%@ page import="model.*" %>
<html>
  <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <meta http-equiv="expires" content=<%= new java.util.Date() %>>
        <meta http-equiv="no-cache"> 
		<title>Library Information System</title>
		<link rel="stylesheet" type="text/css" href="./resource/css/alas.css" >
	</head> 
	
	
<script>
		function txtBoxInputValidate(){
			var loginTxtBoxInputVal=document.getElementById("login").value;
			var pwdTxtBoxInputVal=document.getElementById("pwd").value;
			if(!loginTxtBoxInputVal.length>0){
				alert("Please key in login id");
				return false;	
			}else if(!pwdTxtBoxInputVal.length>0){
				alert("Please key in password");
				return false;	
			}else{
				document.forms["loginForm"].submit();
			}
		}
</script>
	
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
                </table> 
			</div>
  
            <br/><br/><br/><br/><br/><br/><br/>
            <%-- logout message  or invalid credential msg--%>
            <div style="text-align: center"> 
            	<% String msg = (String) request.getAttribute("msg"); 
			      	if(msg !=null && msg.length()>0) {%>
			      	<h2 style="color:red;"><%=msg %></h2>      	
		    	<% } %>
      		</div>
      	<br/><br/>
			
            <div id="bd">
				<div id="main">
                    <div>
						<table border="1" class="headerTable" width="100%" > 
						
						<tr>
							<td class="headerTable" align="center" style="text-align: center;">	
							
							  <div align="center"> 	  
					   			<form action="LoginServlet" method="post"  name="loginForm" > 
					   				<input type="hidden" name="action" id="action" value="login" /> 
									<table> 			
										<tr>
											<td>Login</td>
											<td><input type="text" name="login" id="login" /></td>
											<td/>
											<td/>
										</tr> 
										<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
										<tr>
											<td>Password</td>
											<td><input type="password" name="pwd" id="pwd" /></td>
											<td/>
											<td/>
										</tr>
										<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
										<tr>
											<td/>
											<td>&nbsp&nbsp&nbsp&nbsp<input type="button" value="login" onclick="txtBoxInputValidate()">
												&nbsp&nbsp&nbsp&nbsp
											<input type="reset" value="Reset"></td>
											<td/>
										</tr>
									</table>
								</form>  		
								</div>
								
							
							</td>
							</tr>
						</table>     			
                    </div>
                </div>
            </div>
  		<div  class="footerFix">
  		<p style="text-align: center" ><font color="white"> © 2015 ALAS | All rights reserved | NUS ISS SA41 Team10B   </font></p> 
  		</div>
        </body>
</html>
