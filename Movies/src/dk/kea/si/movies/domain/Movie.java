package dk.kea.si.movies.domain;

import java.util.List;

public class Movie extends DomainObject {
	
	public enum Timeline {
		IN_THEATERS, COMING_SOON, OPENING, OTHER
	}
	
	private String title;
	
	private String year;
	
	private String[] genres;
	
	private String mpaa_rating;
	
	// TODO: convert runtime to integer type. It will cause Gson
	// NumberFormatException. See how to handle that in the link below.
	// http://stackoverflow.com/questions/8863429/how-to-handle-a-numberformatexception-with-gson-in-deserialization-a-json-respon
	private String runtime;
	
	private String critics_consensus;
	
	private ReleaseDates release_dates;
	
	private Ratings ratings;
	
	private String synopsis;
	
	private Posters posters;
	
	private Cast[] abridged_cast;
	
	private Cast[] fullCast;
	
	private Directors[] abridged_directors;
	
	private String studio;
	
	private AlternateIds alternate_ids;
	
	private Links links;
	
	private Timeline timeline;
	
	private float usersRatingScore;
	
	private List<Review> reviews;
	
	private List<Clip> clips;
	
	public Movie() {
		timeline = Timeline.OTHER;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMpaa_rating() {
		return mpaa_rating;
	}

	public void setMpaa_rating(String mpaa_rating) {
		this.mpaa_rating = mpaa_rating;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getCritics_consensus() {
		return critics_consensus;
	}

	public void setCritics_consensus(String critics_consensus) {
		this.critics_consensus = critics_consensus;
	}

	public Ratings getRatings() {
		return ratings;
	}

	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Posters getPosters() {
		return posters;
	}

	public void setPosters(Posters posters) {
		this.posters = posters;
	}

	public Cast[] getAbridged_cast() {
		return abridged_cast;
	}

	public void setAbridged_cast(Cast[] abridged_cast) {
		this.abridged_cast = abridged_cast;
	}

	public AlternateIds getAlternate_ids() {
		return alternate_ids;
	}

	public void setAlternate_ids(AlternateIds alternate_ids) {
		this.alternate_ids = alternate_ids;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public ReleaseDates getRelease_dates() {
		return release_dates;
	}

	public void setRelease_dates(ReleaseDates release_dates) {
		this.release_dates = release_dates;
	}

	public Directors[] getAbridged_directors() {
		return abridged_directors;
	}

	public void setAbridged_directors(Directors[] abridged_directors) {
		this.abridged_directors = abridged_directors;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public float getUsersRatingScore() {
		return usersRatingScore;
	}

	public void setUsersRatingScore(float usersRatingScore) {
		this.usersRatingScore = usersRatingScore;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Clip> getClips() {
		return clips;
	}

	public void setClips(List<Clip> clips) {
		this.clips = clips;
	}

	public Cast[] getFullCast() {
		return fullCast;
	}

	public void setFullCast(Cast[] fullCast) {
		this.fullCast = fullCast;
	}

}
