package dk.kea.si.movies.commands;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;

import com.google.gson.Gson;

import dk.kea.si.movies.domain.Cache;
import dk.kea.si.movies.domain.GoogleVideo;
import dk.kea.si.movies.domain.Movie;
import dk.kea.si.movies.gateways.GoogleGateway;
import dk.kea.si.movies.gateways.RottenTomatoesGateway;
import dk.kea.si.movies.gateways.WikipediaGateway;

public class MovieCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		Movie movie = null;
		String id = request.getParameter("id");
		Cache cache = getStorage().findCache("movie/" + id);
		if(cache == null) {
			//TODO: check if the movie with this id is present in the database
			// if it is not then send the request to RottenTomatoes	
			movie = RottenTomatoesGateway.findMovie(rottenTomatoesApiKey, id);
		} else {
			movie = new Gson().fromJson(cache.getResponse(), Movie.class);
		}
		
		request.setAttribute("movie", movie);
		
		String query = movie.getTitle() + " " + movie.getYear() + " trailer";
		ArrayList<GoogleVideo> googleVideos = GoogleGateway.findVideos(
				googleApiKey, query);
		request.setAttribute("googleVideos", googleVideos);
		
		String wikiPage = WikipediaGateway.getWikiPage(movie.getTitle(),
				movie.getYear());
		//fix relative urls
		wikiPage = wikiPage.replaceAll("a href=\"#", "a href=\""
				+ getFullRequestURL() + "#");
		request.setAttribute("wikiPage", wikiPage);
		
		forward("/movie.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
	
	private String getFullRequestURL() {
		StringBuffer requestURL = request.getRequestURL();
		if (request.getQueryString() != null) {
		    requestURL.append("?").append(request.getQueryString());
		}
		return requestURL.toString();
	}

}
