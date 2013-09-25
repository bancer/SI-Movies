package dk.kea.si.movies.domain;

public class OpenID {

	private String identifier;
	
	private String provider;
	
	private long userId;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String toString() {
		return "[" + identifier + "," + provider + "]";
	}
}
