/**
 * Class that search by ISBN and return a book item.
 */
package com.example.boogieboogie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/**
 * @author Joshua Jung
 * 
 */
public class IsbnSearchParser {
	public final static String APIKEY = "b3f224bbd5ddd47762917f1a41403ac4";
	private BookData item = null;
	
	public BookData getBookData(final String queryIsbn) {
		try {
			URL queryString = new URL("http://openapi.naver.com/search?key="
					+ APIKEY + "&query=" + queryIsbn
					+ "&display=10&start=1&target=book_adv&d_isbn=" + queryIsbn);
			
			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(queryString.openStream(), null);
			
			int parseEvent = parser.getEventType();
			while (parseEvent != XmlPullParser.END_DOCUMENT) {
				switch (parseEvent) {
					case XmlPullParser.START_TAG:
						String tag = parser.getName();
						if (tag.compareTo("title") == 0) {
							item = new BookData();
							String titlesrc = parser.nextText();
							item.setTitle(titlesrc);
						}
						if (tag.compareTo("image") == 0) {
							String imagesrc = parser.nextText();
							item.setImage(imagesrc);
						}
						if (tag.compareTo("author") == 0) {
							String authorsrc = parser.nextText();
							item.setAuthor(authorsrc);
						}
						break;
				}
				parseEvent = parser.next();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
