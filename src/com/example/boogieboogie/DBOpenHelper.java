package com.example.boogieboogie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "book_list", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//create a new table book_list
		db.execSQL("CREATE TABLE book_list"+"(_id integer primary key autoincrement,"
		+"book_title TEXT, book_isbn TEXT, book_image INTEGER, book_author TEXT, book_publisher TEXT," +
		"book_pubdate TEXT, book_memo TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
			
		//if there exists book_list table, drop it
		db.execSQL("DROP TABLE IF EXISTS book_list");
		
		//recreate a db table
		onCreate(db);
	}
	
}