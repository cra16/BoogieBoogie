package com.example.boogieboogie;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

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
