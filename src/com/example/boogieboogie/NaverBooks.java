package com.example.boogieboogie;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
//HI
public class NaverBooks extends Activity {
	final int MENU_back = Menu.FIRST;
	final int MENU_forward = Menu.FIRST + 1;
	
	private ProgressDialog dialog;
	
	private ListView myList;
	private BookParser bookParser;
	private CustomAdapter adapter;
	
	ArrayList<BookData> data;
	private String info;
	
	final int count = 20;
	int start = 1;
	
	private final Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			dialog.dismiss();
			
			adapter = new CustomAdapter(NaverBooks.this,
					R.layout.find_book_listview_item, data);
			myList.setAdapter(adapter);
			myList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);// ///
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_book_listview);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Intent intent = getIntent();
		Bundle myBundle = intent.getExtras();
		info = myBundle.getString("key");
		
		myList = (ListView) findViewById(R.id.find_listview);
		bookParser = new BookParser();
		
		getNewList(info, count, start);
		
		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Object o = myList.getSelectedItem();
				// SelectDialogActivity myDialog = new SelectDialogActivity();
				// myDialog.show();
				dialog();
			}
			
		});
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_back, 0, "Prev page");
		menu.add(0, MENU_forward, 1, "Next page");
		return true;
		//22222
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case MENU_back:
				if (start <= 21) {
					Toast.makeText(this, "No prev page", Toast.LENGTH_SHORT)
							.show();
				} else {
					start -= 21;
					getNewList(info, count, start);
				}
				break;
			
			case MENU_forward:
				start += 21;
				getNewList(info, count, start);
				break;
		
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	public void getNewList(final String inform, final int count,
			final int starts) {
		dialog = ProgressDialog.show(this, "Loading..", "Loading book list...",
				true, false);
		new Thread() {
			public void run() {
				data = bookParser.getBookData(inform, count, starts);
				handler.sendEmptyMessage(0);
			}
		}.start();
		//hihihi
	}
	
}
