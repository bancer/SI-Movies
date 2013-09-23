<%@page import="dk.kea.si.movies.domain.User"%>
<!-- Header -->
<div id="header">
	<h1 id="logo">
		<a href="#">Movie Hunter</a>
	</h1>
	<div class="social">
		<span>FOLLOW US ON:</span>
		<ul>
			<li><a class="twitter" href="#">twitter</a></li>
			<li><a class="facebook" href="#">facebook</a></li>
			<li><a class="vimeo" href="#">vimeo</a></li>
			<li><a class="rss" href="#">rss</a></li>
		</ul>
	</div>
	<div class="loginbox">
		<!-- Links -->
		<% if(session.getAttribute("authenticated.user") != null) { %>
		<% User user = (User) session.getAttribute("authenticated.user"); %>
		<ul class="nav pull-right remove">
			<li class="dropdown pull-right"><a data-toggle="dropdown" id="username"
				class="dropdown-toggle" href="#"> <%= user.getUsername() %> <b
					class="caret"></b>
			</a>
			 <!-- Dropdown menu -->
				<ul class="dropdown-menu">
				<!--<li><a href="#"><i class="icon-user"></i> Profile</a></li>
					<li><a href="#"><i class="icon-cogs"></i> Settings</a></li> -->
					
				</ul></li>
				<li><a id="logout" href="?command=SignOut" ><i class="icon-off"></i>
							Logout</a></li>
		</ul>
		<% } else { %>
		<ul class="nav pull-right s">
			<li><a id="login" class="janrainEngage" href="#">Sign-In</a></li>
		</ul>
		<% } %>
	</div>

	<!-- Navigation -->
	<div id="navigation">
		<ul>
			<li><a class="active" href="#">HOME</a></li>
			<li><a href="#">NEWS</a></li>
			<li><a href="#">IN THEATERS</a></li>
			<li><a href="#">COMING SOON</a></li>
			<li><a href="#">CONTACT</a></li>
			<li><a href="#">ADVERTISE</a></li>
		</ul>
	</div>
	<!-- end Navigation -->

	<!-- Sub-menu -->
	<div id="sub-navigation">
		<ul>
			<li><a href="#">SHOW ALL</a></li>
			<li><a href="#">LATEST TRAILERS</a></li>
			<li><a href="#">TOP RATED</a></li>
			<li><a href="#">MOST COMMENTED</a></li>
		</ul>
		<div id="search">
			<form action="" method="get" accept-charset="utf-8">
				<input type="hidden" name="command" value="SearchMovies" /> <input
					type="hidden" name="page" value="1" /> <label for="search_field">SEARCH</label>
				<input type="text" name="search_field" value="Enter search here"
					id="search_field" title="Enter search here"
					class="blink search-field" /> <input type="submit" value="GO!"
					class="search-button" />
			</form>
		</div>
	</div>
	<!-- end Sub-Menu -->

</div>
<!-- end Header -->