package dk.kea.si.movies.domain;

public class Links extends DomainObject {

	private String self;
	
	private String alternate;
	
	private String cast;
	
	private String clips;
	
	private String reviews;
	
	private String rel;
	
	/**
	 * This field holds a link to a single movie review. It is used by Review
	 * class.
	 */
	private String review;
	
	private String similar;
	
	private long movieId;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getAlternate() {
		return alternate;
	}

	public void setAlternate(String alternate) {
		this.alternate = alternate;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getClips() {
		return clips;
	}

	public void setClips(String clips) {
		this.clips = clips;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getSimilar() {
		return similar;
	}

	public void setSimilar(String similar) {
		this.similar = similar;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}
	
}
