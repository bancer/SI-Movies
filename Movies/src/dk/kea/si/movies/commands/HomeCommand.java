package dk.kea.si.movies.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import com.google.gson.Gson;

import dk.kea.si.movies.domain.Cache;
import dk.kea.si.movies.domain.MovieSearchResults;
import dk.kea.si.movies.gateways.RottenTomatoesGateway;

public class HomeCommand extends FrontCommand {

	private static final String CACHE_REQUEST_IN_THEATERS = "movies/in_theaters/1";

	private static final String CACHE_REQUEST_COMING_SOON = "movies/coming_soon/1";

	private static final String CACHE_REQUEST_OPENING = "movies/opening/1";

	@Override
	public void process() throws ServletException, IOException {
		MovieSearchResults inTheaters = retrieveCachedMoviesList(CACHE_REQUEST_IN_THEATERS);
		request.setAttribute("inTheaters", inTheaters);

		MovieSearchResults comingSoon = retrieveCachedMoviesList(CACHE_REQUEST_COMING_SOON);
		request.setAttribute("comingSoon", comingSoon);

		MovieSearchResults opening = retrieveCachedMoviesList(CACHE_REQUEST_OPENING);
		request.setAttribute("opening", opening);

		forward("/home.jsp");
	}

	private MovieSearchResults retrieveCachedMoviesList(String cacheRequest)
			throws MalformedURLException, IOException {
		MovieSearchResults results = null;
		Cache cache = getStorage().findCache(cacheRequest);
		if (cache == null || cache.isOlderThan24Hours()) {
			//TODO: update Movie.timeline from here, because the request of 
			//individual movie from RottenTomatoes does not contain info
			//whether the movie is in theatre, opening or so on.
			if (cacheRequest.equals(CACHE_REQUEST_IN_THEATERS)) {
				results = RottenTomatoesGateway
						.findCurrentlyInTheaters(rottenTomatoesApiKey);
			} else if (cacheRequest.equals(CACHE_REQUEST_COMING_SOON)) {
				results = RottenTomatoesGateway
						.findComingSoon(rottenTomatoesApiKey);
			} else if (cacheRequest.equals(CACHE_REQUEST_OPENING)) {
				results = RottenTomatoesGateway
						.findOpening(rottenTomatoesApiKey);
			}
			String json = new Gson().toJson(results, MovieSearchResults.class);
			if (cache == null) {
				cache = new Cache(cacheRequest, json);
				getStorage().insert(cache);
			} else {
				cache.setResponse(json);
				cache.refreshTimestamp();
				getStorage().update(cache);
			}
		} else {
			results = new Gson().fromJson(cache.getResponse(),
					MovieSearchResults.class);
		}
		return results;
	}

	@Override
	public void processPost() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
