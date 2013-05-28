package dk.kea.si.movies.domain;

public class Ratings {

	private String critics_rating;
	
	private String criticts_score;
	
	private String audience_rating;
	
	private String audience_score;

	public String getCritics_rating() {
		return critics_rating;
	}

	public void setCritics_rating(String critics_rating) {
		this.critics_rating = critics_rating;
	}

	public String getCriticts_score() {
		return criticts_score;
	}

	public void setCriticts_score(String criticts_score) {
		this.criticts_score = criticts_score;
	}

	public String getAudience_rating() {
		return audience_rating;
	}

	public void setAudience_rating(String audience_rating) {
		this.audience_rating = audience_rating;
	}

	public String getAudience_score() {
		return audience_score;
	}

	public void setAudience_score(String audience_score) {
		this.audience_score = audience_score;
	}
	
}
