package com.example.boogieboogie;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Object> {
	private ArrayList<BookData> data;
	View vView;
	ImageView vImage;
	
	public CustomAdapter(Context context, int textViewResourceId,
			ArrayList items) {
		super(context, textViewResourceId, items);
		this.data = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		vView = convertView;
		if (vView == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			vView = vi.inflate(R.layout.find_book_listview_item, null);
		}
		
		BookData item = data.get(position);
		if (item != null) {
			vImage = (ImageView) vView.findViewById(R.id.image);
			
			TextView v_title = (TextView) vView.findViewById(R.id.title);
			// TextView v_isbn = (TextView) vView.findViewById(R.id.isbn);
			TextView v_author = (TextView) vView.findViewById(R.id.author);
			// TextView v_publisher = (TextView) vView
			// .findViewById(R.id.publisher);
			// TextView v_pubdate = (TextView) vView.findViewById(R.id.pubdate);
			// TextView v_description = (TextView) vView
			// .findViewById(R.id.description);
			
			v_title.setText(item.getTitle());
			// v_isbn.setText("ISBN : " + item.getIsbn());
			v_author.setText("저자 : " + item.getAuthor());
			// v_publisher.setText("Publisher : " + item.getPublisher());
			// v_pubdate.setText("Pubdate : " + item.getPubdate());
			// v_description.setText("Description : " + item.getDescription());
			
			URL imageUrl;
			try {
				imageUrl = new URL(item.getImage());
				HttpURLConnection con = (HttpURLConnection) imageUrl
						.openConnection();
				BufferedInputStream bis = new BufferedInputStream(
						con.getInputStream(), 10240);
				
				Bitmap bm = BitmapFactory.decodeStream(bis);
				bis.close();
				vImage.setImageBitmap(bm);
			} catch (MalformedURLException malformedURLException) {
				malformedURLException.printStackTrace();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
		return vView;
	}
}
