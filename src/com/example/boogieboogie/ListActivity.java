package com.example.boogieboogie;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
/**
 * This fragment is for the first tab (List all books that the user has)
 * All books will be shown as Gridview using GridImgeAdapter class which extends BaseAdapter
 * 
 *
 */
public class ListActivity extends Fragment implements OnClickListener {
	private DBOpenHelper db_open;
	private SQLiteDatabase db;
	
	
	private Cursor c_title, c_image, c_author, c_memo;
	private int numOfItems;
	private ArrayList<BookData> arrayList; //arrayList
	private GridImageAdapter adapter;
	private ArrayList<Bitmap> images;
	final String table_name = "book_list";
	
	//private ArrayList<String> images = new ArrayList<E>(); //image array for gridview 
	
	public ListActivity() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.list_activity, null);
		
		
		//DB Create and Open
		db_open = new DBOpenHelper(getActivity());
		db = db_open.getReadableDatabase();
		
		c_title = selectColumn(db, "book_title", table_name);
		c_title.moveToFirst();
		
		c_image = selectColumn(db, "book_image", table_name);
		c_image.moveToFirst();
		
		c_author= selectColumn(db, "book_author", table_name);
		c_author.moveToFirst();
		
		c_memo = selectColumn(db, "book_memo", table_name);
		c_memo.moveToFirst();
		
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
		}
					
		return view;
	}

	//select query from a db table
	public Cursor selectColumn(SQLiteDatabase db, String col_name, String table_name) {
		return db.rawQuery("SELECT "+col_name+" FROM "+table_name, null);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(db_open != null) {
			db_open.close();
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
