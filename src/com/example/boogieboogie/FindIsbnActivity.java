package com.example.boogieboogie;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FindIsbnActivity extends Activity {
	
	private ProgressDialog dialog;
	private IsbnSearchParser isbnSearchParser;
	protected ListView myList;
	protected CustomAdapter adapter;
	private DBOpenHelper db_open; // db open helper class
	private SQLiteDatabase db; // db
	ArrayList<BookData> data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_book_listview);
		
		db_open = new DBOpenHelper(this);
		db = db_open.getWritableDatabase();
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		myList = (ListView) findViewById(R.id.find_listview);
		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView arg0, View view, int position,
					long arg3) {
				String title = data.get(position).getTitle();
				String isbn = data.get(position).getIsbn();
				String image = data.get(position).getImage();
				String author = data.get(position).getAuthor();
				String publisher = data.get(position).getPublisher();
				String pubdate = data.get(position).getPubdate();
				String memo = data.get(position).getMemo();
				
				Log.i("title", title);
				Object o = myList.getSelectedItem();
				dialog(title, isbn, image, author, publisher, pubdate, memo);
			}
			
		});
		
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
	
	private void dialog(final String title, final String isbn,
			final String image, final String author, final String publisher,
			final String pubdate, final String memo) {
		AlertDialog.Builder al_builder = new AlertDialog.Builder(this);
		al_builder
				.setMessage("Do you want to add this book?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int id) {
								Bitmap bm = null;
								// insert new row to db
								db.execSQL("INSERT INTO book_list"
										+ "(book_title, book_isbn, book_image, book_author, book_publisher, book_pubdate, book_memo)"
										+ " VALUES('" + title + "','" + isbn
										+ "','" + image + "','" + author
										+ "','" + publisher + "','" + pubdate
										+ "','" + memo + "');");
								
								URL imageUrl;
								try {
									imageUrl = new URL(image);
									HttpURLConnection con = (HttpURLConnection) imageUrl
											.openConnection();
									BufferedInputStream bis = new BufferedInputStream(
											con.getInputStream(), 10240);
									
									bm = BitmapFactory.decodeStream(bis);
									bis.close();
								} catch (MalformedURLException malformedURLException) {
									malformedURLException.printStackTrace();
								} catch (IOException ioException) {
									ioException.printStackTrace();
								}
								// 파일로 로컬하게 저장하는 것도 여기서 해야 함
								//saveAsFile(image, title, bm);
								saveToInternalStorage(title, bm);
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
	
	private String saveToInternalStorage (String name, Bitmap bitmapImage) {
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		File directory = cw.getDir("Test", Context.MODE_PRIVATE);
		File mypath = new File (directory, name+".jpg" );
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(mypath);
			Log.i("save", "1");
			bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i("save", directory.getAbsolutePath());
		return directory.getAbsolutePath();
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
