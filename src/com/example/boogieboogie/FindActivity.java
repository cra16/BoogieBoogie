package com.example.boogieboogie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FindActivity extends Fragment implements OnClickListener {
	public FindActivity(){
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.find_activity, null);
		
		Button search_btn = (Button)view.findViewById(R.id.btn_internet);
		search_btn.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i("onclick","1");
//		FragmentManager fragmentManager = getFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Intent intent = new Intent (getActivity(), FindInternetActivity.class);
		this.startActivity(intent);
	}
}
