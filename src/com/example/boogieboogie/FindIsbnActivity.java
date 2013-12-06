package com.example.boogieboogie;

import com.google.zxing.integration.android.IntentIntegrator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FindIsbnActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_isbn);
		
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_isbn, menu);
		return true;
	}
	
}
