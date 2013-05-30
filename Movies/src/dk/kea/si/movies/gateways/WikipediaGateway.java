package dk.kea.si.movies.gateways;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dk.kea.si.movies.domain.WikiList;

public class WikipediaGateway {

	private static final int LIMIT = 20;

	private static final String listTemplate = "http://en.wikipedia.org/w/api.php?" +
					"action=opensearch&search=%s&limit=%s&namespace=0&format=xml";
	
	private static final String pageTemplate = "http://en.wikipedia.org/w/index.php?action=render&title=%s";
	
	public static String getWikiPage(String search) throws MalformedURLException, IOException
	{
		String url = String.format(listTemplate, search, LIMIT);
		String content = readUrlContents(url);
		
//		WikiList wikiList = JAXB.unmarshal(new URL(url), WikiList.class);
//		WikiList wl = new WikiList();
//		wl.setQuery("hello");
//		JAXB.marshal(wl, new File("C:\\temp.xml"));
//		System.out.println(content);
//		System.out.println(wikiList.getQuery());
		
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(WikiList.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url1 = new URL(url);
			WikiList wl = (WikiList)u.unmarshal(url1);
			System.out.println(wl.getQuery());
			System.out.println(wl.getWikiSection());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String readUrlContents(String url) throws IOException,
			MalformedURLException {
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(
				new URL(url).openStream())));
		String content = "";
		while (scanner.hasNextLine()) {
			content += scanner.nextLine();
		}
		return content;
	}
}
