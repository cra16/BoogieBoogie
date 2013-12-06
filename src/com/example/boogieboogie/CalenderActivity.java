package com.example.boogieboogie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

public class CalenderActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.etc_activity);
		Log.d("tag","33333");
		CalendarView calendar = (CalendarView)findViewById(R.id.calendar_etc);
		Log.d("tag","4444444");
		
		/*
		calendar.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				Log.d("ttt","1111111111111");
				// TODO Auto-generated method stub
				dialog();
			}
		});
		
		*/
	}
	private void dialog() {
		AlertDialog.Builder al_builder = new AlertDialog.Builder(this);
		al_builder
				.setMessage("Do you want to add this book?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// TODO Auto-generated method stub
								Log.i("yes", "1");
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = al_builder.create();
		// title of the dialog
		alert.setTitle("Confirm");
		alert.show();
	}
	
}