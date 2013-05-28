package dk.kea.si.movies.gateways;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

import dk.kea.si.movies.domain.Movie;
import dk.kea.si.movies.domain.MovieSearchResults;

public class RottenTomatoesGateway {

	private static final String ROOT_URL = "http://api.rottentomatoes.com/api/public/v1.0/";

	private static final int LIMIT = 10;

	private static final String listTemplate = ROOT_URL
			+ "movies.json?apikey=%s&q=%s&page_limit=%s&page=%s";

	private static final String movieTemplate = ROOT_URL
			+ "movies/%s.json?apikey=%s";

	public static MovieSearchResults searchMovies(String apiKey, String search,
			String page) throws MalformedURLException, IOException {
		String url = String.format(listTemplate, apiKey, search, "" + LIMIT,
				page);
		String content = readUrlContents(url);
		Gson gson = new Gson();
		return gson.fromJson(content, MovieSearchResults.class);
	}
	
	public static Movie findMovie(String apiKey, String id)
			throws MalformedURLException, IOException {
		String url = String.format(movieTemplate, id, apiKey);
		String content = readUrlContents(url);
		Gson gson = new Gson();
		return gson.fromJson(content, Movie.class);
	}

	private static String readUrlContents(String url) throws IOException,
			MalformedURLException {
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(
				new URL(url).openStream())));
		String content = "";
		while (scanner.hasNextLine()) {
			content += scanner.nextLine();
		}
		return content;
	}
}
