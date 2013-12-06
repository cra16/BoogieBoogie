package com.example.boogieboogie;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case IntentIntegrator.REQUEST_CODE: {
				if (resultCode != RESULT_CANCELED) {
					IntentResult scanResult = IntentIntegrator
							.parseActivityResult(requestCode, resultCode, data);
					String result = "";
					if (scanResult != null) {
						result = scanResult.getContents();
					} else
						return;
					
					result = result.trim();

					// Check to see if it is a ISBN (checks if it is numeric)
					if (result.trim().matches("[0-9]*")) {
						Intent intent = new Intent(FindIsbnActivity.this,
								BarcodeResult.class);
						
						// Pass the ISBN to the BarcodeResult form
						Bundle bundle = new Bundle();
						bundle.putString("ISBN", scanResult.getContents());
						intent.putExtras(bundle);
						
						startActivity(intent);
					}
					// Assume that it must be a string
//					else {
//						AlertBox.show("Found Message", result, this);
//					}
				}
			}
				break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_isbn, menu);
		return true;
	}
	
}
