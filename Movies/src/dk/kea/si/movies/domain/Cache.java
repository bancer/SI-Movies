package dk.kea.si.movies.domain;

public class Cache extends DomainObject {

	private int hash;
	
	private String request;
	
	private String response;
	
	private long timestamp;
	
	public Cache() {
	}

	public Cache(String request, String response) {
		this.hash = request.hashCode();
		this.request = request;
		this.response = response;
	}

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public void refreshTimestamp() {
		timestamp = System.currentTimeMillis()/1000L;
	}

	public boolean isOlderThan24Hours() {
		long now = System.currentTimeMillis()/1000L;
		return now - timestamp > 86400;
	}

	public boolean isOlderThan1Week() {
		long now = System.currentTimeMillis()/1000L;
		return now - timestamp > 604800;
	}
}
