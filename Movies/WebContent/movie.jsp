<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="dk.kea.si.movies.domain.User.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="movie" scope="request"
	class="dk.kea.si.movies.domain.Movie" />
<jsp:useBean id="googleVideos" scope="request" 
	type="java.util.ArrayList<dk.kea.si.movies.domain.GoogleVideo>" />
<jsp:useBean id="commentsHelper" scope="request"
	class="dk.kea.si.movies.helpers.CommentsListHelper" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Movie </title>
	
	<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
	<script src="js/movie.js"></script>
	
</head>
<body>		
	<div id="shell">
		<%@ include file="inc/header.jspf"%>

		<div id="main">
			<div id="content">

				<h1><%=movie.getTitle()%>
					(<%=movie.getYear()%>)
				</h1>
				<div class="poster">
					<img src="<%=movie.getPosters().getDetailed()%>" />
				</div>
				<div class="synopsis">
					<h4>Synopsis:</h4>
					<%=movie.getSynopsis()%>
				</div>
				<div>
					<h4>Genres:</h4>
					<ul>
						<% for (int i = 0; i < movie.getGenres().length; i++) { %>
							<li><%=movie.getGenres()[i]%></li>
						<% } %>
					</ul>
				</div>
				<div>
					<h4>Actors:</h4>
					<ul>
						<% for (int j = 0; j < movie.getAbridged_cast().length; j++) { %>
							<li><%=movie.getAbridged_cast()[j].getName()%></li>
						<% } %>
					</ul>
				</div>
				<div>
					<h4>Critics Consensus:</h4>
					<%=movie.getCritics_consensus()%>
				</div>
				<div>
					<h4>Ratings:</h4>
					Audience -
					<%=movie.getRatings().getAudience_rating()%>
					(<%=movie.getRatings().getAudience_score()%>) <br />
					Critics -
					<%=movie.getRatings().getCritics_rating()%>
					(<%=movie.getRatings().getCritics_score()%>)
				</div>
				<div>
					<h4>Runtime:</h4>
					<%=movie.getRuntime()%> min.
				</div>
			</div>
			<div>
				<h4 class="toggleable">Trailers</h4>
				<div class="hidden_on_load">
					<% for (int i = 0; i < googleVideos.size(); i++) { %>
					<div>
						<h3><%=googleVideos.get(i).getTitle()%></h3>
						<iframe width="640" height="390"
							src="http://www.youtube.com/embed/<%= googleVideos.get(i).getId() %>?autoplay=0"
							frameborder="1" allowfullscreen></iframe>
					</div>
					<% } %>
				</div>
			</div>
			
			<div>
				<h4 class="toggleable">Wikipedia</h4>
				<div class="hidden_on_load">
					<%=request.getAttribute("wikiPage")%>
				</div>
			</div>
			
			<div>
				<h4 class="toggleable">Comments</h4>
				<div class="hidden_on_load">
					<% User user = (User) session.getAttribute(Constants.SESSION_USER_KEY); %>	
					<ul id="comments_list">
						<% for(int i = 0; i < commentsHelper.size(); i++) { %>
							<li>
								<h3><%= commentsHelper.getAuthorDisplayName(i) %></h3>
								<%= commentsHelper.getCommentText(i) %>
								<a href="#">Reply</a>
								<% if(user != null && (user.getRole() == Role.ADMIN || user.getRole() == Role.EDITOR)) { %>
									<p>
										<form name="delete_comment">
											<input type="hidden" name="command" value="DeleteComment" />
											<input type="hidden" 
												name="<%=Constants.SESSION_CSRF_KEY %>" 
												value="<%=session.getAttribute(Constants.SESSION_CSRF_KEY) %>" />
											<input type="hidden" name="comment_id" value="<%=commentsHelper.getCommentId(i) %>" />
											<input type="submit" name="delete_comment" value="Delete" />
										</form>
									</p>
								<% } %>
							</li>
						<% } %>
					</ul>
					<% if(user != null) { %>
					<form id="comment_form">
						<input type="hidden" name="command" value="Comment" />
						<input type="hidden" 
							name="<%=Constants.SESSION_CSRF_KEY %>" 
							value="<%=session.getAttribute(Constants.SESSION_CSRF_KEY) %>" />
						<input type="hidden" name="movie_id" value="<%=movie.getId() %>" />
						<input type="hidden" name="parent_id" value="" />
						<textarea id="comment_text" name="comment_text"></textarea>
						<input type="submit" value="Submit" />
					</form>
					<% } %>
				</div>
			</div>
		</div>

		<%@ include file="inc/footer.jspf"%>
	</div>
</body>
</html>
