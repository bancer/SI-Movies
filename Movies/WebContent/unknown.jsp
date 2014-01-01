<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Unknown Command</title>

	<base href="<%= request.getContextPath() %>/" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="shell">
		<%@ include file="inc/header.jspf"%>
		<div class="main">
			<div class="content">
				<h1>Page Not Found</h1>
			</div>
		</div>
		<%@ include file="inc/footer.jspf"%>
	</div>
</body>
</html>
