package com.example.boogieboogie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FindActivity extends Fragment implements OnClickListener {
	public FindActivity() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.find_activity, null);
		
		Button search_btn = (Button) view.findViewById(R.id.btn_internet);
		search_btn.setOnClickListener(this);
		
		Button isbn_btn = (Button) view.findViewById(R.id.btn_isbn);
		isbn_btn.setOnClickListener(this);
		
		return view;
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_internet) {
			//Log.i("onclick", "1");
			Intent intent = new Intent(getActivity(),FindInternetActivity.class);
			this.startActivity(intent);
		} else if (v.getId() == R.id.btn_isbn) {
			Intent intent = new Intent(getActivity(), FindIsbnActivity.class);
			this.startActivity(intent);
			
		}
	}
}
