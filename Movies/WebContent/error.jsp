<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Error</title>
	<base href="<%=request.getContextPath()%>/" />
	<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css" rel="stylesheet">
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="shell">
		<%@ include file="inc/header.jsp"%>
		<div class="main">
			<div class="content">
				<h1>Error</h1>
				<div><%=exception.toString()%></div>
				<div>
					<h2>StackTrace</h2>
					<pre><%
							StringWriter stringWriter = new StringWriter();
							PrintWriter printWriter = new PrintWriter(stringWriter);
							exception.printStackTrace(printWriter);
							out.println(stringWriter);
							printWriter.close();
							stringWriter.close();
						%>
					</pre>
				</div>
			</div>
		</div>
		<%@ include file="inc/footer.jsp"%>
	</div>
</body>

</html>
