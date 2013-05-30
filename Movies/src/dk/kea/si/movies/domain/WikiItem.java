package dk.kea.si.movies.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


public class WikiItem {
	
	@XmlElement(name="Image")
	private WikiImage wikiImage;
	
	@XmlElement(name="Text")
	private String text;
	
	@XmlElement(name="Description")
	private String description;
	
	@XmlElement(name="Url")
	private String url;
	
	public WikiItem(){}

	public WikiImage getWikiImage() {
		return wikiImage;
	}
	
	
//	public void setWikiImage(WikiImage wikiImage) {
//		this.wikiImage = wikiImage;
//	}

	public String getText() {
		return text;
	}
	
//	
//	public void setText(String text) {
//		this.text = text;
//	}

	public String getDescription() {
		return description;
	}
	
	
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public String getUrl() {
		return url;
	}
	
	
//	public void setUrl(String url) {
//		this.url = url;
//	}
	
	

}
