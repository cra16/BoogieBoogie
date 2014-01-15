package com.example.boogieboogie;

public class FindActivity extends Fragment implements OnClickListener {
	public FindActivity() {}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_internet) {
			Log.i("onclick", "1");
			// FragmentManager fragmentManager = getFragmentManager();
			// FragmentTransaction fragmentTransaction =
			// fragmentManager.beginTransaction();
			Intent intent = new Intent(getActivity(),
					FindInternetActivity.class);
			this.startActivity(intent);
		} else if (v.getId() == R.id.btn_isbn) {
			Intent intent = new Intent(getActivity(), FindIsbnActivity.class);
			this.startActivity(intent);
			
		}
	}
	
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
}
