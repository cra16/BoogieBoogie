package com.example.boogieboogie;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class BookParser {
	public final static String APIKEY = "b3f224bbd5ddd47762917f1a41403ac4";
	ArrayList<BookData> data;
	
	public ArrayList<BookData> getBookData(final String query, final int count,
			final int start) {
		data = new ArrayList<BookData>();
		BookData item = null;
		String m_searchinfo = "";
		
		try {
			m_searchinfo = URLEncoder.encode(query, "UTF8");
		} catch (UnsupportedEncodingException unsupportedEncodingException) {
			unsupportedEncodingException.printStackTrace();
		}
		
		try {
			URL text = new URL("http://openapi.naver.com/search?key=" + APIKEY
					+ "&query=" + m_searchinfo + "&display=" + count
					+ "&start=" + start + "&target=book");
			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(text.openStream(), null);
			Log.i("NET", "Parsing...");
			
			int parseEvent = parser.getEventType();
			while (parseEvent != XmlPullParser.END_DOCUMENT) {
				switch (parseEvent) {
					case XmlPullParser.START_TAG:
						String tag = parser.getName();
//						if (tag.compareTo("channel") == 0) {
//							break;
//						}
						if (tag.compareTo("title") == 0) {
							item = new BookData();
							String titlesrc = parser.nextText();
							item.setTitle(titlesrc);
							Log.i("NET", "START...");
						}
//						if (tag.compareTo("isbn") == 0) {
//							String isbnsrc = parser.nextText();
//							item.setIsbn(isbnsrc);
//						}
						if (tag.compareTo("image") == 0) {
							String imagesrc = parser.nextText();
							item.setImage(imagesrc);
						}
						if (tag.compareTo("author") == 0) {
							String authorsrc = parser.nextText();
							item.setAuthor(authorsrc);
							data.add(item);
						}
//						if (tag.compareTo("publisher") == 0) {
//							String publishersrc = parser.nextText();
//							item.setPublisher(publishersrc);
//						}
//						if (tag.compareTo("pubdate") == 0) {
//							String pubdatesrc = parser.nextText();
//							item.setPubdate(pubdatesrc);
//						}
//						if (tag.compareTo("description") == 0) {
//							String descriptionsrc = parser.nextText();
//							item.setDescription(descriptionsrc);
//							data.add(item);
//						}
						break;
				}
				parseEvent = parser.next();
			}
			Log.i("NET", "End...");
		} catch (Exception exception) {
			Log.i("NET", "Parsing fail");
		}
		return data;
	}
}
