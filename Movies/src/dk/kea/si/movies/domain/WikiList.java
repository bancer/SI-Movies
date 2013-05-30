package dk.kea.si.movies.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SearchSuggestion")
@XmlAccessorType(XmlAccessType.FIELD)
public class WikiList {
	
	@XmlElement(name="Query")
	private String query;
	
	@XmlElement(name="Section")
	private WikiSection wikiSection;
	
	@XmlAttribute(name="xml:space")
	private String space;
	
	public WikiList(){}
	
	public String getSpace() {
		return space;
	}
	

//	public void setSpace(String space) {
//		this.space = space;
//	}
	
	public String getQuery() {
		return query;
	}
	
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public WikiSection getWikiSection() {
		return wikiSection;
	}
	
	
//	public void setWikiSection(WikiSection wikiSection) {
//		this.wikiSection = wikiSection;
//	}
	
	
}
