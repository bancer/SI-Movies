package dk.kea.si.movies.domain;

public abstract class DomainObject {

	protected static final long PLACEHOLDER_ID = -1;
	
	private long id = PLACEHOLDER_ID;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if(id < -1L || id == 0) {
			throw new IllegalArgumentException("ID cannot be less than -1 or 0.");
		}
		this.id = id;
	}
	
	public boolean isNew() {
		return id == PLACEHOLDER_ID;
	}
}
