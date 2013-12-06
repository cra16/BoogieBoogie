package com.example.boogieboogie;

import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;

public class LoadingActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_activity);
		
		Handler hd = new Handler();
		
		hd.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				finish(); // 3 sec.
			}
		}, 3000);
		
	}
	
}
