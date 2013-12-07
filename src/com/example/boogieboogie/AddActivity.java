package com.example.boogieboogie;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener {

	Button addBtn;
	String memo;
	EditText editText_memo;
	TextView addMemo;
	TextView tv_title;
	private DBOpenHelper db_open; // db open helper class
	private SQLiteDatabase db; // db
	String title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_add);
		
		// DB Create and open
		
		setTitle("Book Info");
		Intent intent = getIntent();
		
		title = intent.getExtras().get("TITLE").toString();
		String author = intent.getExtras().get("AUTHOR").toString();
		String memo = intent.getExtras().get("MEMO").toString();
		
		tv_title = (TextView)findViewById(R.id.addTitle);
		TextView tv_author = (TextView)findViewById(R.id.addAuthor);
		
		tv_title.setText(title);
		tv_author.setText(author);
		
		editText_memo = (EditText)findViewById(R.id.editMemo);
		if(memo.equals(""))
			editText_memo.setHint("Note for your book");
		else
			editText_memo.setText(memo);
		addBtn = (Button)findViewById(R.id.addButton);
		addBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		db_open = new DBOpenHelper(this);
		db = db_open.getWritableDatabase();
		
		// TODO Auto-generated method stub
		if(v.getId()==R.id.addButton){
			memo = editText_memo.getText().toString();
			
			//Update Memo column in database
			db.execSQL("UPDATE book_list SET book_memo ='"+memo+"' where book_title='"+title+"';");
			Toast.makeText(AddActivity.this, "Your book note has saved ", Toast.LENGTH_LONG ).show();
		}
		db.close();
	
		
	}
}
