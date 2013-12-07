package com.example.boogieboogie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
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
	
	private String fileName;
	private View fragmentView;
	private ArrayList<Bitmap> images ;
	final String table_name = "book_list";
	private GridView gv;
	private GridListViewAdapter gvImgAdapter;
	
	public ListActivity() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 
		
		View view = inflater.inflate(R.layout.list_activity, null);
		
		gv = (GridView)view.findViewById(R.id.gridViewImages);
		images = new ArrayList<Bitmap>();
		/*
		gv.setOnClickListener(new OnItemClickListener() {
			
		});
		*/
		
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
					
		for(int i=0; i<numOfItems; i++)
		{	
			fileName= arrayList.get(i).getTitle()+".jpg";
			images.add(loadImageFromStorage(fileName));
		}
		
		view.invalidate();
		
		gvImgAdapter = new GridListViewAdapter(view.getContext(), images);
		gv.setAdapter(gvImgAdapter);
		
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(view.getContext(), "OK", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getActivity(), AddActivity.class);
				//Bundle date = new Bundle();
				//date.putString("", str);
				intent.putExtra("TITLE",arrayList.get(position).getTitle());
				intent.putExtra("AUTHOR",arrayList.get(position).getAuthor());
				intent.putExtra("MEMO", arrayList.get(position).getMemo());
				getActivity().startActivity(intent);
				
				
				
			}
		});
		
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
	//File file = new File (context.getFilesDir(), "yourFolder");
	private Bitmap loadImageFromStorage (String filename) {
		String path = "/data/data/com.example.boogieboogie/Test";
		//File file = new File(getActivity().getFilesDir(), "Test");
		ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
		File directory = cw.getDir("Test", Context.MODE_PRIVATE);
		try {
			File f = new File(directory, filename);
			Log.i("load", "2");

			//File f = getActivity()().getFilesDir()+"/"+"filename";
			Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
			return b;
//			ImageView img = (ImageView)getView().findViewById(R.id.);
//			img.setImageBitmap(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
