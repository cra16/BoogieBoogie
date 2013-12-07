/**
 * Class that search by ISBN and return a book item.
 */
package com.example.boogieboogie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
	ArrayList<BookData> data;
	
	public ArrayList<BookData> getBookData(final String queryIsbn) {
		data = new ArrayList<BookData>();
		BookData item = new BookData();
		
		try {
			URL queryString = new URL("http://openapi.naver.com/search?key="
					+ APIKEY + "&query=" + queryIsbn
					+ "&display=1&start=1&target=book_adv&d_isbn=" + queryIsbn);
			Log.i("DEBUG", queryString.toString());
			
			// Ready XML Pull Parser
			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(queryString.openStream(), null);
			
			int parseEvent = parser.getEventType();
			boolean isItemText = false;
			while (parseEvent != XmlPullParser.END_DOCUMENT) {
				switch (parseEvent) {
					case XmlPullParser.START_DOCUMENT:
					case XmlPullParser.END_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:
						if (parser.getName().equals("item")) {
							isItemText = true;
							Log.i("START TAG", "item");
						}
						if (isItemText) {
							if (parser.getName().equals("title")) {
								Log.i("DEBUG", parser.getName());
								String titlesrc = parser.nextText();
								Log.i("DEBUG", titlesrc);
								item.setTitle(titlesrc);
								Log.i("title", item.getTitle());
							}
							if (parser.getName().equals("image")) {
								item.setImage(parser.nextText());
								Log.i("image", item.getImage());
							}
							if (parser.getName().equals("author")) {
								item.setAuthor(parser.nextText());
								data.add(item);
							}
						}
						break;
					case XmlPullParser.END_TAG:
						if (parser.getName().equals("item")) {
							isItemText = false;
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
		
		return data;
	}
}
