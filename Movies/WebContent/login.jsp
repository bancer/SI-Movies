<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					<ul>
						<li>
							<h1>Login</h1>
						</li>
						<li>
							<label for="username">Username:</label>
							<input type="text" name="username" id="username" />
						</li>
						<li>
							<label for="password">Password:</label>
							<input type="password" name="password" id="password" />
						</li>
						<li><input type="submit" value="Submit" /></li>
					</ul>
				</form>

			</div>

			<%@ include file="inc/footer.jsp"%>
		</div></body>
</html>
