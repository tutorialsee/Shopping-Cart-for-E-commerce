package com.tutorialsee.databaseforandroid.database;

import java.util.ArrayList;
import java.util.HashMap;

import com.tutorialsee.databaseforandroid.R;
import com.tutorialsee.databaseforandroid.cart.dbhelpercart;
import com.tutorialsee.databaseforandroid.http.PrefernceSettings;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener,OnItemClickListener {

	private DrawerLayout mDrawerLayout;
	private RelativeLayout mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	public static Context context;
	public static int lp = 0;
	int flag = 1;
	static ActionBar actionBar;
	RelativeLayout lin2;
	public static String CUR_PAGE_TITLE = "Title";
	public static AutoCompleteTextView autoComplete;
	@SuppressWarnings("unused")
	private Handler mHandler = new Handler();
	ImageView btn_card, btn_search, btn_close;
	TextView btn_logo;
	public static TextView tx, _ordd;
	String usernid = "";
	static TextView txt_name;
	ArrayList<HashMap<String, String>> arraylist;
	public static dbhelpercart dbHelpercart;
	@Override
	public void onResume() {
		Log.v("DEBUG", "onResume of LoginFragment");

		super.onResume();
	}

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_main);
		context = this;		

		dbHelpercart = new dbhelpercart(context);


		PrefernceSettings.openDataBase(context);

		actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
		actionBar.setCustomView(R.layout.row);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.listicon);
		btn_card = ((ImageView) findViewById(R.id.btn_card));
		btn_search = ((ImageView) findViewById(R.id.btn_search));
		btn_close = ((ImageView) findViewById(R.id.btn_close));
		btn_logo = ((TextView) findViewById(R.id.btn_logo));
		autoComplete = ((AutoCompleteTextView) findViewById(R.id.edt_search));
		btn_card.setOnClickListener(this);
		btn_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btn_logo.setVisibility(View.VISIBLE);
				btn_card.setVisibility(View.VISIBLE);
				btn_search.setVisibility(View.GONE);
				btn_close.setVisibility(View.GONE);
				autoComplete.setVisibility(View.GONE);

			}
		});

		tx = ((TextView)findViewById(R.id.textView1));
		tx.setText(dbHelpercart.countRecord() + "");





		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btn_logo.setVisibility(View.GONE);
				btn_card.setVisibility(View.GONE);
				btn_search.setVisibility(View.GONE);
				btn_close.setVisibility(View.VISIBLE);
				autoComplete.setVisibility(View.VISIBLE);
			}
		});
		initMenu();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (RelativeLayout) findViewById(R.id.left_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);


		mDrawerToggle = new ActionBarDrawerToggle(this, // host Activity
				mDrawerLayout, // DrawerLayout object
				// nav drawer image to replace 'Up' image
				R.string.drawer_open, // "open drawer" description for
				// accessibility
				R.string.drawer_close // "close drawer" description for
				// accessibility
				) {
			public void onDrawerClosed(View view) {
				btn_logo.setVisibility(View.VISIBLE);
				btn_card.setVisibility(View.VISIBLE);
				btn_search.setVisibility(View.GONE);
				btn_close.setVisibility(View.GONE);
				autoComplete.setVisibility(View.GONE);
				autoComplete.setText("");
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				btn_logo.setVisibility(View.VISIBLE);
				btn_card.setVisibility(View.VISIBLE);
				btn_search.setVisibility(View.GONE);
				btn_close.setVisibility(View.GONE);
				autoComplete.setVisibility(View.GONE);
				autoComplete.setText("");
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
				//onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			switchFragment(new HomeActivtiy());
			setSelected(lin2);

		}
	}


	@SuppressLint("CutPasteId")
	private void initMenu() {
		lin2 = (RelativeLayout) findViewById(R.id.lin2);
		lin2.setOnClickListener(this);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}
		return true;

	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		Bundle bundle = new Bundle();

		if (v.getId() == R.id.lin2) {
			newContent = new HomeActivtiy();
		}else if (v.getId() == R.id.btn_card) {
			if (dbHelpercart.countRecord() < 0) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Your cart is empty", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 40);
				toast.show();
			} else {

				newContent = new AddCart();

			}

		}
		if (newContent != null) {
			newContent.setArguments(bundle);
			switchFragment(newContent);
		}

	}

	// switching fragment
	private void switchFragment(Fragment fragment) {

		mDrawerLayout.closeDrawer(mDrawerList);

		FragmentManager fragmentManager = getSupportFragmentManager();
		// fragmentManager.beginTransaction().replace(R.id.content_frame,
		// fragment).commit();
		fragmentManager.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.addToBackStack("my_fragment").commit();
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	// set the selected option as enabled
	private void setSelected(RelativeLayout rl) {

		lin2.setSelected(false);

		rl.setSelected(true); // set current selection

	}

	// When using the ActionBarDrawerToggle, you must call it during
	// onPostCreate() and onConfigurationChanged()
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}



	@SuppressLint("NewApi")
	@Override
	public void onBackPressed() {

		if (MainActivity.lp == 1) {

			Log.v("MainActivity", "" + MainActivity.lp);

		} else {
			Log.v("MainActivity", "" + MainActivity.lp);
			super.onBackPressed();
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (MainActivity.lp == 1) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
				System.exit(0);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}
}