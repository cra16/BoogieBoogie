package com.example.boogieboogie;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ImageGridActivityOnCalendar extends Activity {
	// public String data;
//	private int[] imageIDs = new int[] { R.drawable.ic_launcher,
//			R.drawable.ic_launcher };

	private DBOpenHelper db_open;
	private SQLiteDatabase db;
	
	
	private Cursor c_title, c_image, c_author, c_memo;
	private int numOfItems;
	private ArrayList<BookData> arrayList; //arrayList
	
	

	final String table_name = "book_list";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.etc_dialog);

		Intent intent = getIntent();
		String date = intent.getExtras().getString("date");

		setTitle("Book of " + date);
		// Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
		//GridView gvImg = (GridView) findViewById(R.id.gridview_calendar);
		//ImageGridAdapterOnCalendar gvImgAdapter = new ImageGridAdapterOnCalendar(
		//		this, imageIDs);
		//gvImg.setAdapter(gvImgAdapter);
		TextView tv = (TextView)findViewById(R.id.text_etc_dialog);
		
		//DB Create and Open
				db_open = new DBOpenHelper(this);
				db = db_open.getReadableDatabase();
				
				c_title = selectColumn(db, "book_title", table_name);
				c_title.moveToFirst();
				
				c_image = selectColumn(db, "book_image", table_name);
				c_image.moveToFirst();
				
				c_author= selectColumn(db, "book_author", table_name);
				c_author.moveToFirst();
				
				c_memo = selectColumn(db, "book_memo", table_name);
				c_memo.moveToFirst();
				
				//c_date = selectColumn(db, "book_date", table_name);
				//c_date.moveToFirst();
				
				arrayList = new ArrayList<BookData>();
				numOfItems = c_title.getCount();
				
				for(int i=0; i<numOfItems; i++) {
					arrayList.add(new BookData(c_title.getString(0), c_image.getString(0), c_author.getString(0), c_memo.getString(0)));
					
					//images
					Log.i("query!",arrayList.get(i).getTitle());
					c_title.moveToNext();
					c_image.moveToNext();
					c_author.moveToNext();
					c_memo.moveToNext();
					//c_date.moveToNext();
				}
						
				for(int i=0; i<numOfItems; i++) {
					if(arrayList.get(i).getMemo().equals(date)){
						tv.setText(tv.getText()+"\n"+arrayList.get(i).getTitle());
					}
				}
	
	
	}
	//select query from a db table
	public Cursor selectColumn(SQLiteDatabase db, String col_name, String table_name) {
		return db.rawQuery("SELECT "+col_name+" FROM "+table_name, null);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(db_open != null) {
			db_open.close();
		}
	}
}
