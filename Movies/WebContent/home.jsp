<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="inTheaters" scope="request"
	class="dk.kea.si.movies.domain.MovieSearchResults"></jsp:useBean>
<jsp:useBean id="comingSoon" scope="request"
	class="dk.kea.si.movies.domain.MovieSearchResults"></jsp:useBean>
<jsp:useBean id="opening" scope="request"
	class="dk.kea.si.movies.domain.MovieSearchResults"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Movies</title>
	
	<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>		
	<div id="shell">
		<%@ include file="inc/header.jsp"%>
		<div id="main">
			<div id="content">
				<% if (inTheaters.getTotal() > 0) { %>
					<div class="box">
						<div class="head">
							<h2>IN THEATERS</h2>
							<p class="text-right">
								<a href="#">See all</a>
							</p>
						</div>
						<% for(int i = 0; i < inTheaters.getMovies().length; i++) { %>
							<div class="movie">
								<div class="movie-image">
									<a href="?command=Movie&id=<%=inTheaters.getMovies()[i].getId()%>">
										<img alt="movie" src="<%= inTheaters.getMovies()[i].getPosters().getProfile() %>" />
									</a>
								</div>
								<div class="rating">
									<p>RATING</p>
									<div class="stars">
										<div class="stars-in"></div>
									</div>
									<span class="comments"></span>
								</div>
							</div>
						<% } %>
					</div>
				<% } %>
				
				<% if (opening.getMovies().length > 0) { %>
					<div class="box">
						<div class="head">
							<h2>OPENING</h2>
							<p class="text-right">
								<a href="#">See all</a>
							</p>
						</div>
						<% for(int i = 0; i < opening.getMovies().length; i++) { %>
							<div class="movie">
								<div class="movie-image">
									<a href="?command=Movie&id=<%=opening.getMovies()[i].getId()%>">
										<img alt="movie" src="<%= opening.getMovies()[i].getPosters().getProfile() %>" />
									</a>
								</div>
								<div class="rating">
									<p>RATING</p>
									<div class="stars">
										<div class="stars-in"></div>
									</div>
									<span class="comments"></span>
								</div>
							</div>
						<% } %>
					</div>
				<% } %>
				
				<% if (comingSoon.getTotal() > 0) { %>
					<div class="box">
						<div class="head">
							<h2>COMING SOON</h2>
							<p class="text-right">
								<a href="#">See all</a>
							</p>
						</div>
						<% for(int i = 0; i < comingSoon.getMovies().length; i++) { %>
							<div class="movie">
								<div class="movie-image">
									<a href="?command=Movie&id=<%=comingSoon.getMovies()[i].getId()%>">
										<img alt="movie" src="<%= comingSoon.getMovies()[i].getPosters().getProfile() %>" />
									</a>
								</div>
								<div class="rating">
									<p>RATING</p>
									<div class="stars">
										<div class="stars-in"></div>
									</div>
									<span class="comments"></span>
								</div>
							</div>
						<% } %>
					</div>
				<% } %>
			</div>
		</div>
		<%@ include file="inc/footer.jsp"%>
	</div>
</body>
</html>