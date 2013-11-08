package dk.kea.si.movies.domain;

public class AlternateIds extends DomainObject {

	private String imdb;
	
	private long movieId;

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	
}
