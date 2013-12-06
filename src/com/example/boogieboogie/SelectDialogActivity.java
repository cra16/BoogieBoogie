package com.example.boogieboogie;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class SelectDialogActivity extends Dialog implements OnClickListener {
	
	public SelectDialogActivity(Context context) {
		super(context);
		
		setContentView(R.layout.activity_dialog);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dismiss();
	}
	
}
