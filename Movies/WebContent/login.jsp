<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dk.kea.si.movies.util.Constants"%>
<jsp:useBean id="helper" 
	class="dk.kea.si.movies.helpers.UserHelper" 
	scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Login</title>
	
	<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>		
	<div id="shell">
		<%@ include file="inc/header.jsp"%>

		<div id="main">
			<div id="content">
				<form id="login_form" method="post" action="" name="login_form">
					<input type="hidden" name="command" value="Login" />
					<input type="hidden" 
						name="<%=Constants.SESSION_CSRF_KEY %>" 
						value="<%=session.getAttribute(Constants.SESSION_CSRF_KEY) %>" />
					<ul>
						<li>
							<h1>Login</h1>
						</li>
						<% if(request.getAttribute(Constants.ERROR_MESSAGE_KEY) != null) { %>
							<li class='validation_error'>
								<%=request.getAttribute(Constants.ERROR_MESSAGE_KEY)%>
							</li>
						<% } %>
						<li>
							<label for="username">Username:</label>
							<input type="text" name="username" id="username" value="<%=helper.getUsername()%>" />
							<span class='validation_error'>
								<%=helper.getError("username")%>
							</span>
						</li>
						<li>
							<label for="password">Password:</label>
							<input type="password" name="password" id="password" />
							<span class='validation_error'>
								<%=helper.getError("password")%>
							</span>
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
