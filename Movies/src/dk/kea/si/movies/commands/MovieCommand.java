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
		String cacheRequest = "movie/" + id;
		Cache cache = getStorage().findCache(cacheRequest);
		if(cache == null || cache.isOlderThan1Week()) {
			movie = RottenTomatoesGateway.findMovie(rottenTomatoesApiKey, id);
			String json = new Gson().toJson(movie, Movie.class);
			if(cache == null) {
				cache = new Cache(cacheRequest, json);
				getStorage().insert(cache);
			} else {
				cache.setResponse(json);
				cache.refreshTimestamp();
				getStorage().update(cache);
			}	
		} else {
			movie = new Gson().fromJson(cache.getResponse(), Movie.class);
		}

		Movie dbMovie = (Movie) getStorage().find(Long.parseLong(id), Movie.class);
		if(dbMovie == null) {
			getStorage().insert(movie);
		} else {
			//getStorage().update(dbMovie);
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
