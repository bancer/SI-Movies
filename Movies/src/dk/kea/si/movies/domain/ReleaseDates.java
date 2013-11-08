package dk.kea.si.movies.domain;

public class ReleaseDates extends DomainObject {

	private String dvd;
	
	private String theater;
	
	private long movieId;
	
	public ReleaseDates() {
		dvd = "0000-00-00";
		theater = "0000-00-00";
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
