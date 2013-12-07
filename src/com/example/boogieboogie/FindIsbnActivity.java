package com.example.boogieboogie;

import java.util.ArrayList;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class FindIsbnActivity extends Activity {
	
	private ProgressDialog dialog;
	private IsbnSearchParser isbnSearchParser;
	protected ListView myList;
	protected CustomAdapter adapter;
	
	ArrayList<BookData> data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_book_listview);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		myList = (ListView) findViewById(R.id.find_listview);
		isbnSearchParser = new IsbnSearchParser();
		// if(!DEBUGMODE){
		try {
			IntentIntegrator
					.initiateScan(
							this,
							"Install Barcode Scanner?",
							"This application requires you to install 'Barcode Scanner'. Would you like to install it?",
							"Yes", "No");
		} catch (Exception e) {
			// This error is usually handled in IntentIntegrator
			// AlertBox.show("Error", "Could not access Market",
			// FindActivity.this);
			e.printStackTrace();
		}
		// }
		// else{
		// //These are used for testing. It brings up a menu which
		// allows for several applications to be chosen
		// Intent myIntent = new Intent(v.getContext(),
		// ExtraMenu.class);
		// startActivityForResult(myIntent, 0);
		// }
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case IntentIntegrator.REQUEST_CODE: {
				if (resultCode != RESULT_CANCELED) {
					IntentResult scanResult = IntentIntegrator
							.parseActivityResult(requestCode, resultCode, data);
					String result = "";
					if (scanResult != null) {
						result = scanResult.getContents();
					} else
						return;
					
					result = result.trim();
					Log.i("DEBUG", result);
					// Check to see if it is a ISBN (checks if it is numeric)
					if (result.matches("[0-9]*")) {
						Log.i("DEBUG", "before getbookfromisbn");
						getBookFromIsbn(result);
						Log.i("DEBUG", "after getbookfromisbn");
						// Intent intent = new Intent(FindIsbnActivity.this,
						// BarcodeResult.class);
						//
						// // Pass the ISBN to the BarcodeResult form
						// Bundle bundle = new Bundle();
						// bundle.putString("ISBN", scanResult.getContents());
						// intent.putExtras(bundle);
						//
						// startActivity(intent);
					}
					// Assume that it must be a string
					// else {
					// AlertBox.show("Found Message", result, this);
					// }
				}
			}
				break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_isbn, menu);
		return true;
	}
	
	public void getBookFromIsbn(final String queryIsbn) {
		dialog = ProgressDialog.show(this, "Loading...", "Loading Book...",
				true, false);
		new Thread() {
			public void run() {
				data = isbnSearchParser.getBookData(queryIsbn);
				handler.sendEmptyMessage(0);
			}
		}.start();
	}
	
	private final Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			dialog.dismiss();
			adapter = new CustomAdapter(FindIsbnActivity.this,
					R.layout.find_book_listview_item, data);
			myList.setAdapter(adapter);
			myList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	};
}
