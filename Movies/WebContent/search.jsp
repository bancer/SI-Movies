<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="results" scope="request"
	class="dk.kea.si.movies.domain.MovieSearchResults"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Movies - Search</title>
	
	<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>		
	<div id="shell">
		<%@ include file="inc/header.jsp"%>
	
		<div id="main">
			<div id="content">

					<% if (results.getTotal() > 0) { %>
					<div class="box">
						<div class="head">
							<h2>SEARCH RESULTS</h2>
						</div>
						</div>
						<% for(int i = 0; i < results.getMovies().length; i++) { %>
							<div class="box">
								<h3>
									<a href="?command=Movie&id=<%=results.getMovies()[i].getId()%>">
										<%= results.getMovies()[i].getTitle() %> 
										(<%= results.getMovies()[i].getYear() %>)
									</a>
								</h3>
								<div class="poster">
									<img src="<%= results.getMovies()[i].getPosters().getProfile() %>" />
								</div>
								<div class="synopsis">
									<h4>Synopsis:</h4>
									<%= results.getMovies()[i].getSynopsis() %>
								</div>
								<div>
									<h4>Actors:</h4>
									<ul>
									<% for(int j = 0; j < results.getMovies()[i].getAbridged_cast().length; j++) { %>
										<li><%= results.getMovies()[i].getAbridged_cast()[j].getName() %></li>
									<% } %>
									</ul>
								</div>
								<div>
									<h4>Critics Consensus:</h4>
									<%= results.getMovies()[i].getCritics_consensus() %>
								</div>
								<div>
									<h4>Ratings:</h4>
									Audience - <%= results.getMovies()[i].getRatings().getAudience_rating() %> 
									(<%= results.getMovies()[i].getRatings().getAudience_score() %>)
									<br />
									Critics - <%= results.getMovies()[i].getRatings().getCritics_rating() %> 
									(<%=results.getMovies()[i].getRatings().getCritics_score()%>)
								</div>
								<div>
									<h4>Runtime:</h4>
									<%= results.getMovies()[i].getRuntime() %> min.
								</div>
							</div>
						<% } %>
					<% } %>		

			</div>
		</div>
	
		<%@ include file="inc/footer.jsp"%>
	</div>
</body>
</html>