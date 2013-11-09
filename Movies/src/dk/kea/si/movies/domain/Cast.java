package dk.kea.si.movies.domain;

public class Cast extends DomainObject {

	private String name;
	
	private String[] characters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getCharacters() {
		return characters;
	}

	public void setCharacters(String[] characters) {
		this.characters = characters;
	}
}
