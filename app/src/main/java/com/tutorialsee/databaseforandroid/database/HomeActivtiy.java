package com.tutorialsee.databaseforandroid.database;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tutorialsee.databaseforandroid.R;


public class HomeActivtiy extends Fragment {
	Context context;
	ImageView click;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 1;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.home_activtiy, container, false);
		
		click = (ImageView) v.findViewById(R.id.click);
		click.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment newContent = new Kilol();
				if (newContent != null) {
					switchFragment(newContent);
				}
				
			}
		});
		
		MainActivity.actionBar.show();
		context = container.getContext();
	
		return v;

	}

	
	public void onBackPressed() {
		MainActivity.lp = 1;
	}

	@Override
	public void onResume() {
		MainActivity.lp = 1;
		super.onResume();
	}
	
	private void switchFragment(Fragment fragment) {

		// mDrawerLayout.closeDrawer(mDrawerList);
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment)
				.addToBackStack("my_fragment").commit();
	}
}