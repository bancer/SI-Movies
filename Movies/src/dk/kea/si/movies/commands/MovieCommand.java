package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import dk.kea.si.movies.domain.Movie;
import dk.kea.si.movies.domain.MovieSearchResults;
import dk.kea.si.movies.gateways.GoogleGateway;
import dk.kea.si.movies.gateways.RottenTomatoesGateway;

public class MovieCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		String id = request.getParameter("id");
		Movie movie = RottenTomatoesGateway.findMovie(
				rottenTomatoesApiKey, id);
		request.setAttribute("movie", movie);
		Object googleVideos = GoogleGateway.findVideos(googleApiKey, movie.getTitle() + " trailer");
		forward("/movie.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
