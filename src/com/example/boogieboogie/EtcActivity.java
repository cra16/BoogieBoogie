package com.example.boogieboogie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class EtcActivity extends Fragment {
	public EtcActivity() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.etc_activity, null);
		
		CalendarView calendar = (CalendarView)getView();
		//calendar.set
				//findViewById(R.id.calendar_etc);
		
		Log.d("tag","111111111");
		Intent intent = new Intent(getActivity(), CalenderActivity.class);
		Log.d("tag","22222222");
		this.startActivity(intent);
		
		
		return view;
	}
	
}
