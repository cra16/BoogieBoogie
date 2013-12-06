//ETC PAGE
package com.example.boogieboogie;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

public class EtcActivity extends Fragment {
	public EtcActivity() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//TODO Auto-generated method stub
		
		
		//SET VIEW
		View view = inflater.inflate(R.layout.etc_activity, null);
		
		//dialog event
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.dialog_calendar);
		
		CalendarView calendar = (CalendarView)view.findViewById(R.id.calendarview);
		
		//If click the date then Dialog 
		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				dialog.show();
				//Log.d("ok","year "+year+"   month -1 "+month+"    dayOfMonth"+dayOfMonth);
				
			}
		});
		
		
		//calendar.set
				//findViewById(R.id.calendar_etc);
		
		//Intent intent = new Intent(getActivity(), CalenderActivity.class);
		//this.startActivity(intent);
		
		
		return view;
	}

}
