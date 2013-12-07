package com.example.boogieboogie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FindInternetActivity extends Activity implements OnClickListener {
	private EditText et;
	private Button bt;
	private String info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.find_internet_input);
		
		et = (EditText) findViewById(R.id.editText_find_input);
		bt = (Button) findViewById(R.id.btn_find_input);
		
		et.setHint("Input the Keyword");
		bt.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		info = et.getText().toString();
		
		Intent intent = new Intent(FindInternetActivity.this, NaverBooks.class);
		Bundle data = new Bundle();
		data.putString("key", info);
		intent.putExtras(data);
		this.startActivity(intent);
	}
	
}
