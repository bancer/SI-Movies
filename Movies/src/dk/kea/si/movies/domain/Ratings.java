package dk.kea.si.movies.domain;

public class Ratings extends DomainObject {

	private String critics_rating;
	
	private int critics_score;
	
	private String audience_rating;
	
	private int audience_score;
	
	private long movieId;

	public String getCritics_rating() {
		return critics_rating;
	}

	public void setCritics_rating(String critics_rating) {
		this.critics_rating = critics_rating;
	}

	public int getCritics_score() {
		return critics_score;
	}

	public void setCritics_score(int criticts_score) {
		this.critics_score = criticts_score;
	}

	public String getAudience_rating() {
		return audience_rating;
	}

	public void setAudience_rating(String audience_rating) {
		this.audience_rating = audience_rating;
	}

	public int getAudience_score() {
		return audience_score;
	}

	public void setAudience_score(int audience_score) {
		this.audience_score = audience_score;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	
}
