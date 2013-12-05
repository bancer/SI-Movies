<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Register</title>
	
	<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>		
	<div id="shell">
		<%@ include file="inc/header.jsp"%>

		<div id="main">
			<div id="content">
			
				<form id="register_form" method="post" action="" name="register_form">
					<input type="hidden" name="command" value="Register" />
					<ul>
						<li>
							<h1>Register</h1>
						</li>
						<% if(request.getAttribute("error.message") != null) { %>
							<li class='validation_error'>
								<%=request.getAttribute("error.message")%>
							</li>
						<% } %>
						<li>
							<label for="username">Username:</label>
							<input type="text" name="username" id="username" />
							<% if(request.getAttribute("error.username") != null) { %>
								<span class='validation_error'>
									<%=request.getAttribute("error.username")%>
								</span>
							<% } %>
						</li>
						<li>
							<label for="email">Email:</label>
							<input type="text" name="email" id="email" />
							<% if(request.getAttribute("error.email") != null) { %>
								<span class='validation_error'>
									<%=request.getAttribute("error.email")%>
								</span>
							<% } %>
						</li>
						<li>
							<label for="password">Password:</label>
							<input type="password" name="password" id="password" />
							<% if(request.getAttribute("error.password") != null) { %>
								<span class='validation_error'>
									<%=request.getAttribute("error.password")%>
								</span>
							<% } %>
						</li>
						<li>
							<label for="repeat_password">Repeat Password:</label>
							<input type="password" name="repeat_password" id="repeat_password" />
							<% if(request.getAttribute("error.repeat_password") != null) { %>
								<span class='validation_error'>
									<%=request.getAttribute("error.repeat_password")%>
								</span>
							<% } %>
						</li>
						<li><input type="submit" value="Submit" /></li>
					</ul>
				</form>

			</div>
		</div>

		<%@ include file="inc/footer.jsp"%>
	</div>
</body>
</html>
