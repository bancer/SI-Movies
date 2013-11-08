package dk.kea.si.movies.domain;

public class Reviews {

	private int total;

	private Review[] reviews;

	private PaginatorLinks links;

	private String link_template;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Review[] getReviews() {
		return reviews;
	}

	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}

	public PaginatorLinks getLinks() {
		return links;
	}

	public void setLinks(PaginatorLinks links) {
		this.links = links;
	}

	public String getLink_template() {
		return link_template;
	}

	public void setLink_template(String link_template) {
		this.link_template = link_template;
	}

	public Reviews() {
		total = 0;
	}
}
