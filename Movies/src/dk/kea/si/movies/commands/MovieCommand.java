package dk.kea.si.movies.commands;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;

import com.google.gson.Gson;

import dk.kea.si.movies.domain.Cache;
import dk.kea.si.movies.domain.GoogleVideo;
import dk.kea.si.movies.domain.Movie;
import dk.kea.si.movies.domain.Reviews;
import dk.kea.si.movies.gateways.GoogleGateway;
import dk.kea.si.movies.gateways.RottenTomatoesGateway;
import dk.kea.si.movies.gateways.WikipediaGateway;

public class MovieCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		String id = request.getParameter("id");
		Movie movie = retrieveCachedMovie(id);
		
		Reviews reviews = retrieveCachedReviews(id);
		movie.setReviews(Arrays.asList(reviews.getReviews()));

		Movie dbMovie = (Movie) getStorage().find(Long.parseLong(id), Movie.class);
		if(dbMovie == null) {
			getStorage().insert(movie);
		} else {
			//TODO: implement movie update
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

	private Reviews retrieveCachedReviews(String id)
			throws MalformedURLException, IOException {
		Reviews result = null;
		Class<Reviews> cachedClass = Reviews.class;
		String cacheRequest = "movie/reviews/" + id;
		Cache cache = getStorage().findCache(cacheRequest);
		if(cache == null || cache.isOlderThan24Hours()) {
			result = RottenTomatoesGateway.findReviews(rottenTomatoesApiKey, id);
			String json = new Gson().toJson(result, cachedClass);
			refreshCache(cacheRequest, cache, json);
		} else {
			result = new Gson().fromJson(cache.getResponse(), cachedClass);
		}
		return result;
	}

	private Movie retrieveCachedMovie(String id) throws MalformedURLException,
			IOException {
		Movie result = null;
		Class<Movie> cachedClass = Movie.class;
		String cacheRequest = "movie/" + id;
		Cache cache = getStorage().findCache(cacheRequest);
		if(cache == null || cache.isOlderThan1Week()) {
			result = RottenTomatoesGateway.findMovie(rottenTomatoesApiKey, id);
			String json = new Gson().toJson(result, cachedClass);
			refreshCache(cacheRequest, cache, json);	
		} else {
			result = new Gson().fromJson(cache.getResponse(), cachedClass);
		}
		return result;
	}

	private void refreshCache(String cacheRequest, Cache cache,
			String freshResponse) {
		if(cache == null) {
			cache = new Cache(cacheRequest, freshResponse);
			getStorage().insert(cache);
		} else {
			cache.setResponse(freshResponse);
			cache.refreshTimestamp();
			getStorage().update(cache);
		}
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
