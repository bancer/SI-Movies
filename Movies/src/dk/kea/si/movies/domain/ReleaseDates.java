package dk.kea.si.movies.domain;

public class ReleaseDates extends DomainObject {

	private String dvd;
	
	private String theater;
	
	private long movieId;
	
	public ReleaseDates() {
		dvd = "1001-01-01";
		theater = "1001-01-01";
	}

	public String getDvd() {
		return dvd;
	}

	public void setDvd(String dvd) {
		this.dvd = dvd;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
}
