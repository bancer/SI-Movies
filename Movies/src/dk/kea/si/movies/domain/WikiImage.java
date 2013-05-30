package dk.kea.si.movies.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


public class WikiImage {
	
	@XmlAttribute
	private String source;
	
	@XmlAttribute
	private String width;
	
	@XmlAttribute
	private String height;
	
	public WikiImage(){}
	
	public String getSource() {
		return source;
	}
	
//	public void setSource(String source) {
//		this.source = source;
//	}
	public String getWidth() {
		return width;
	}
	
//	public void setWidth(String width) {
//		this.width = width;
//	}
	public String getHeight() {
		return height;
	}
	
//	public void setHeight(String height) {
//		this.height = height;
//	}
	
	
}
