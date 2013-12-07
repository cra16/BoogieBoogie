package com.example.boogieboogie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

public class ImageGridActivityOnCalendar extends Activity {
	// public String data;
	private int[] imageIDs = new int[] { R.drawable.ic_launcher,
			R.drawable.ic_launcher };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_calendar);

		Intent intent = getIntent();
		String date = intent.getExtras().getString("date");

		setTitle("Book of " + date);
		// Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
		GridView gvImg = (GridView) findViewById(R.id.gridview_calendar);
		ImageGridAdapterOnCalendar gvImgAdapter = new ImageGridAdapterOnCalendar(
				this, imageIDs);
		gvImg.setAdapter(gvImgAdapter);
	}

}
