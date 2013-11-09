package dk.kea.si.movies.domain;

public class CompleteCast {

	private Cast[] cast;
	
	public Cast[] getCast() {
		return cast;
	}

	public void setCast(Cast[] cast) {
		this.cast = cast;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	private Links links;
}
