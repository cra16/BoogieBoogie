//ETC PAGE
package com.example.boogieboogie;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class EtcActivity extends Fragment {
	public EtcActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// SET VIEW
		View view = inflater.inflate(R.layout.etc_activity, null);
		// dialog event
		//final Dialog dialog = new Dialog(getActivity());
		//dialog.setContentView(R.layout.dialog_calendar);

		CalendarView calendar = (CalendarView) view
				.findViewById(R.id.calendarview);

		// If click the date then Dialog
		calendar.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				// TODO Auto-generated method stub

				// If print date, need add 1 to Month
				String str = year + "" + (month + 1) + "" + dayOfMonth;
				
				//Toast.makeText(getActivity().getBaseContext(), str,
				//		Toast.LENGTH_SHORT).show();
				//TextView tv = (TextView) dialog.findViewById(R.id.text_dialog);
				// tv.setText();
				//GridView gv = (GridView)dialog.findViewById(R.id.gridview_calendar);
				
				
				
				Intent intent = new Intent(getActivity(), ImageGridActivityOnCalendar.class);
				Bundle date = new Bundle();
				date.putString("date", str);
				intent.putExtras(date);
				getActivity().startActivity(intent);
						
				//dialog.setTitle("Book of " + str);
				
				//dialog.show();

			}
		});

		// calendar.set
		// findViewById(R.id.calendar_etc);

		// Intent intent = new Intent(getActivity(), CalenderActivity.class);
		// this.startActivity(intent);

		return view;
	}
}