package dk.kea.si.movies.domain;

import java.util.List;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

public class WikiSection {
	
	@XmlElements({@XmlElement(name="Item",type=WikiItem.class)})
	private List<WikiItem> wikiItems;
	
	public WikiSection(){}
	
	
	public List<WikiItem> getWikiItems() {
		return wikiItems;
	}
	
//	public void setWikiItems(List<WikiItem> wikiItems) {
//		this.wikiItems = wikiItems;
//	}
	
	

}
