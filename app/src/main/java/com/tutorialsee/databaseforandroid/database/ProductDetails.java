package com.tutorialsee.databaseforandroid.database;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.squareup.picasso.Picasso;
import com.tutorialsee.databaseforandroid.R;
import com.tutorialsee.databaseforandroid.http.BaseActivity;
import com.tutorialsee.databaseforandroid.http.Constant;
import com.tutorialsee.databaseforandroid.http.Network;
import com.tutorialsee.databaseforandroid.http.PrefernceSettings;
import com.tutorialsee.databaseforandroid.http.TouchImageView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "SetJavaScriptEnabled", "SimpleDateFormat" })
public class ProductDetails extends Fragment implements OnClickListener,OnItemSelectedListener {
	protected static final String ARG_DETIALS_ID = null;
	ArrayList<HashMap<String, String>> array1;
	List<ItemDetails> itemList = new ArrayList<ItemDetails>();
	LinearLayout addtocart, confim;
	ImageView _btn,  _img_product, imageviewnew;
	ItemDetails item;
	Dialog dialog;
	ViewPager mViewPager;
	private ImageView[] dots;
	private int currentIndex;
	TextView _txt_name, _txt_rs, txt_webviewtext, txt_costrs;
	Boolean back = false;
	static String msg = "";
	ItemDetails mItems;
	int flag = 1;
	ImageView btn_search, btn_logo;
	EditText edt_search;
	String flg = "L";
	Context context;
	HashMap<String, String> sizee = new HashMap<String, String>();
	private static String[] pics;
	DateFormat df;
	String date;
	Spinner spinner;
	private List<View> views;
	private ViewPagerAdapter vpAdapter;
	String[] spinnersArray = null;
	String value;
	ArrayAdapter<String> dataAdapters;
	int pidtest;
	String quatest;
	TextView addtocarsst;
	String size_name;
	int showsixe = 0;
	int showsixes = 0;

	@SuppressLint({ "CutPasteId", "SetJavaScriptEnabled" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;

		if (getArguments().containsKey(ARG_DETIALS_ID)) {
			mItems = (ItemDetails) getArguments().getSerializable(ARG_DETIALS_ID);

		}
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.product_details, container, false);

		MainActivity.actionBar.show();
		context = container.getContext();
		PrefernceSettings.openDataBase(context);

		String tt = mItems.getImagePath1();
		Log.v("tag", mItems.getImagePath1());
		if ((mItems.getFullImages() != null)
				&& !(mItems.getFullImages().contains("null"))) {
			tt = tt + "," + mItems.getFullImages();
		}
		if ((mItems.getImagePath() != null)
				&& !(mItems.getImagePath().contains("null"))) {
			tt = tt + "," + mItems.getImagePath();
		}
		Log.v("tt", tt);
		pics = tt.split(",");
		Log.v("tt1", "" + pics.length);

		
		addtocart = (LinearLayout) v.findViewById(R.id.addtocart);
		addtocarsst = (TextView) v.findViewById(R.id.addtocarsst);
		confim = (LinearLayout) v.findViewById(R.id.confim);
		imageviewnew = (ImageView) v.findViewById(R.id.imageviewnew);
		spinner = (Spinner) v.findViewById(R.id.spinner);
		final Spinner spinners = (Spinner) v.findViewById(R.id.spinners);
		spinner.setOnItemSelectedListener(this);
		spinners.setOnItemSelectedListener(this);
		
		
		confim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (MainActivity._ordd.getText().toString().contains("Login")) {

					String abc = PrefernceSettings.getUserId();
					mItems.setmax(PrefernceSettings.getMax());
					showsixe = 0;
					try {
						showsixe = MainActivity.dbHelpercart
								.countRecordbynid(mItems.getNid());
					} catch (Exception ex) {
					}
					MainActivity.dbHelpercart.inserRecord(mItems.getNid(),mItems.getTitle(), 
							                              mItems.getThumb_Images(),Integer.valueOf(PrefernceSettings.getQuantity()),
							                              mItems.getPrice().toString(),mItems.getSizeselected(), mItems.getmax());
					
					MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord() + "");

					if (!abc.isEmpty()) {
						pidtest = (int) mItems.getNid();
						quatest = PrefernceSettings.getQuantity();
						size_name = (String) mItems.getSizeselected();
						Cartcheck ck = new Cartcheck();
						ck.execute();
						
					} else {
						if (Integer.parseInt(mItems.getmax()) > showsixe) {
							Toast toast = Toast.makeText(context,"Added to Cart", Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
							
						} else {
							Toast toast = Toast.makeText(context,"There is only " + mItems.getmax()+ " items in stock",Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
						}
					}
					
				} else {

					String abc = PrefernceSettings.getUserId();
					mItems.setmax(PrefernceSettings.getMax());
					try {
						showsixe = MainActivity.dbHelpercart.countRecordbynid(mItems.getNid());
					} catch (Exception ex) {
					}
					MainActivity.dbHelpercart.inserRecord(mItems.getNid(),mItems.getTitle(), 
							                              mItems.getThumb_Images(),Integer.valueOf(PrefernceSettings.getQuantity()),
							                              mItems.getPrice().toString(),mItems.getSizeselected(), mItems.getmax());
					
					MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord() + "");

					if (!abc.isEmpty()) {
						pidtest = (int) mItems.getNid();
						quatest = PrefernceSettings.getQuantity();
						size_name = (String) mItems.getSizeselected();
						Cartchecks ck = new Cartchecks();
						ck.execute();
					} else {
						if (Integer.parseInt(mItems.getmax()) > showsixe) {
							Toast toast = Toast.makeText(context,"Added to Cart", Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
							
						} else {
							Toast toast = Toast.makeText(context,"There is only " + mItems.getmax()+ " items in stock",Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
						}
					}
				}	
			}
		});
		imageviewnew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				ViewDialog alert = new ViewDialog();
				alert.showDialog(getActivity(), "Error de conexi�n al servidor");

			}
		});

		
		List<String> categories = new ArrayList<String>();
		String sp = mItems.getSize();
		String[] spinnerArray = null;

		if (!sp.contains("null")) {

			JSONObject jsObject;
			try {
				jsObject = new JSONObject(sp);
				spinnerArray = new String[jsObject.length()];
				Iterator<String> iter = jsObject.keys();
				int t = 0;
				while (iter.hasNext()) {
					String key = iter.next();
					try {
						String value = (String) jsObject.get(key);
						sizee.put(value, key);
						spinnerArray[t] = value;
						Log.v(value, key);
						Log.v("AAAAAAAAAAAAAAAAAAAAAAA", value);
						Log.v("BBBBBBBBBBBBBBBBBBBBBBB", key);
						t++;

					} catch (JSONException e) {
					}

				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}

		// Spinner1 Drop down elements
		if (spinnerArray != null) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,spinnerArray);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
					String selected = parent.getItemAtPosition(position).toString();
					String id1 = sizee.get(selected);
					mItems.setSizeselected(id1);
					String spp = mItems.getSize_Stock();
					Log.v("spp", spp);
					
					JSONObject jsObject;
					try {
						jsObject = new JSONObject(spp);
						value = (String) jsObject.get(selected);
						int t = 0;

						spinnersArray = new String[Integer.parseInt(value)];
						while (t < Integer.parseInt(value)) {
							spinnersArray[t] = String.valueOf(t + 1);
							t++;
							dataAdapters = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,spinnersArray);
							dataAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							spinners.setAdapter(dataAdapters);
							dataAdapters.notifyDataSetChanged();
							PrefernceSettings.setMax(value);
							Log.v("Max Max ", PrefernceSettings.getMax());
						}

						Log.v("spinner value", value);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
				}
			});
		}

		// Spinner2 Drop down elements
		List<String> categoriess = new ArrayList<String>();
		String spp = mItems.getSize_Stock();

		spinners.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				String selecteds = parent.getItemAtPosition(position).toString();
				PrefernceSettings.setQuantity(selecteds);
				Log.v("Quantity count", selecteds);
				}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	
	
		mItems.getNid();
		mItems.getThumb_Images();
		mItems.getPrice();
		mItems.getBody();
		mItems.getCost_Price();
		_txt_name = (TextView) v.findViewById(R.id.txt_name);
		_txt_rs = (TextView) v.findViewById(R.id.txt_rs);
		txt_costrs = (TextView) v.findViewById(R.id.txt_costrs);
		txt_costrs.setPaintFlags(txt_costrs.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
		_txt_name.setText("Felt Laptop Sleeve (Charcoal)");
		_txt_rs.setText(mItems.getPrice());
		
		if (mItems.getCost_Price() != null) {
			if (mItems.getCost_Price().charAt(0) == '0') {
				txt_costrs.setVisibility(View.GONE);
			} else {
				txt_costrs.setVisibility(View.VISIBLE);
				txt_costrs.setText(mItems.getCost_Price());
			}
		}

		//Picasso.with(getActivity()).load("http://homemonkey.com/timthumb.php?src="+ mItems.getThumb_Images().replace(" ", "%20")+ "&w=400&h=0").placeholder(R.drawable.load).into(imageviewnew);
		Picasso.with(getActivity()).load(R.drawable.product).placeholder(R.drawable.load).into(imageviewnew);

		
		String stringLitersOfPetrol = null;
		df = new SimpleDateFormat("HH:mm");

		
		
		
		
		addtocart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String abc = PrefernceSettings.getUserId();
				mItems.setmax(PrefernceSettings.getMax());
				showsixe = 0;
				
				try {
					showsixe = MainActivity.dbHelpercart.countRecordbynid(mItems.getNid());
				} catch (Exception ex) {
				}
				
				MainActivity.dbHelpercart.inserRecord(mItems.getNid(),mItems.getTitle(),
						                              mItems.getThumb_Images(),Integer.valueOf(PrefernceSettings.getQuantity()),
						                              mItems.getPrice().toString(), mItems.getSizeselected(),
						                              mItems.getmax());
				
				MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()+ "");

				if (!abc.isEmpty()) {
					pidtest = (int) mItems.getNid();
					quatest = PrefernceSettings.getQuantity();
					size_name = (String) mItems.getSizeselected();
					Cartcheck ck = new Cartcheck();
					ck.execute();
				} else {
					Log.v("aa", "" + showsixe);
					Log.v("bb", "" + mItems.getmax());
					
					if (Integer.parseInt(mItems.getmax()) > showsixe) {
						Toast toast = Toast.makeText(context, "Added to Cart",Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					} else {
						Toast toast = Toast.makeText(context, "There is only "+ Integer.parseInt(mItems.getmax())
								+ " items in stock", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
					
				}
			}
		});

	

		return v;
	}

	private String getTimingCategory() {
		date = df.format(Calendar.getInstance().getTime());

		return String.valueOf(date);
	}

	

	@Override
	public void onClick(View v) {
		

	}

	
	//***************************  Product Image View & Touch ViewDialog Open  *********************** 
	
	public class ViewDialog implements OnClickListener, OnPageChangeListener {
		@SuppressWarnings("deprecation")
		public <AddListAddressd> void showDialog(Activity getActivity,String msg) {
			
			dialog = new Dialog(getActivity);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setCancelable(false);
			dialog.setContentView(R.layout.imageviewfull);
			
			ImageView gobtnnsssdd = (ImageView) dialog.findViewById(R.id.gobtnnsssdd);
			mViewPager = (ViewPager) dialog.findViewById(R.id.view_pager);
			mViewPager.setAdapter(new TouchImageAdapter());

			gobtnnsssdd.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.cancel();
				}
			});
			vpAdapter = new ViewPagerAdapter(views);

			mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
				public void onPageScrollStateChanged(int state) {

				}

				public void onPageScrolled(int position, float positionOffset,int positionOffsetPixels) {

				}

				public void onPageSelected(int position) {
					if (position < 0 || position > pics.length - 1	|| currentIndex == position) {
						return;
					}
					dots[position].setEnabled(false);
					dots[currentIndex].setEnabled(true);
					currentIndex = position;
				}
			});

			initDots();

			views = new ArrayList<View>();
			LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			for (int i = 0; i < pics.length; i++) {
				if (pics[i] != null) {
					if (!pics[i].toString().contains("null")) {
						ImageView iv = new ImageView(getActivity());
						iv.setLayoutParams(mParams);
						Log.v("aaaaaaaaaaaaaaaaaaaaaaaaa", pics[i] + i + ","+ pics.length);
						//Picasso.with(getActivity()).load(pics[i].toString().replace(" ", "%20")+ "&w=0&h=0").into(iv);
						Picasso.with(getActivity()).load(R.drawable.product).placeholder(R.drawable.load).into(iv);

						views.add(iv);
					}
				}
			}

			dialog.show();
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog.getWindow().getAttributes());
			lp.width = WindowManager.LayoutParams.MATCH_PARENT;
			lp.height = WindowManager.LayoutParams.MATCH_PARENT;
			dialog.getWindow().setAttributes(lp);
		}

		class TouchImageAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				return pics.length;
			}

			@Override
			public View instantiateItem(ViewGroup container, int position) {
				if (pics[position] != null&& (!pics[position].toString().contains("null"))) {
					
					TouchImageView img = new TouchImageView(container.getContext());
					Picasso.with(getActivity()).load(R.drawable.product).placeholder(R.drawable.load).into(img);
					//Picasso.with(container.getContext()).load(pics[position].replace(" ", "%20")).placeholder(R.drawable.load).into(img);
					container.addView(img,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
					
					return img;
				} else {
					return null;
				}

			}

			@Override
			public void destroyItem(ViewGroup container, int position,Object object) {
				container.removeView((View) object);
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

		}

		// View Pager code
		private void initDots() {
			LinearLayout ll = (LinearLayout) dialog.findViewById(R.id.ll);
			dots = new ImageView[pics.length];
			for (int i = 0; i < 3; i++) {
				ImageView ivvvv = (ImageView) ll.getChildAt(i);
				ivvvv.setVisibility(View.GONE);
			}
			for (int i = 0; i < pics.length; i++) {
				dots[i] = (ImageView) ll.getChildAt(i);
				dots[i].setVisibility(View.VISIBLE);
				dots[i].setEnabled(true);
				dots[i].setOnClickListener((OnClickListener) context);
				dots[i].setTag(i);
			}

			currentIndex = 0;
			dots[currentIndex].setEnabled(false);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
		}

		@Override
		public void onClick(View arg0) {
			int position = (Integer) arg0.getTag();

		}

	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	
	
	

	//*******************************  Card Details  **********************************

	class Cartcheck extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();

			String UERRR = Constant.Cart() + abc + "&pid=" + pidtest + ""
					                             + "&qty=" + quatest + "&size_name=" + size_name
					                             + "&action=add";
			
			Log.v("New URL", UERRR);
			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("URL", res);

			try {
				JSONObject jObject = new JSONObject(res);
				msg = jObject.getString("response_message");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {
		    Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
			if (Network.isConnectionFast(context)) {
				itemList.clear();
				Cartdetails ck = new Cartdetails();
				ck.execute();
			} else {
				Toast.makeText(context, "Person information is null",Toast.LENGTH_LONG).show();
			}
		}
	}

	class Cartdetails extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();
			String res = BaseActivity.getJsonnew(getActivity(),Constant.CartDetails() + abc);

			try {
				JSONArray jArray = new JSONArray(res);
				int number = jArray.length();
				number = number - 1;

				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jObject = jArray.getJSONObject(i);

					ItemDetails k = new ItemDetails();
					k.setNid(Long.parseLong((String) jObject.getString("nid")));
					k.setTitle(jObject.getString("title"));
					String price = jObject.getString("price");
					double f = Double.parseDouble(price);
					k.setPrice(String.format("%.2f", new BigDecimal(f)));
					k.setQty(Integer.parseInt((String) jObject.getString("qty")));
					k.setThumb_Images(((String) jObject.getString("thumb_images")));
					k.setSizeselected(((String) jObject.getString("size_id")));
					k.setmax(jObject.getString("total_stock"));
					Log.v("", jObject.getString("title"));
					itemList.add(k);

				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return null;
		}

		protected void onPostExecute(String args) {

			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					MainActivity.dbHelpercart.inserRecord(((ItemDetails) itemList.get(i)).getNid(),
														  ((ItemDetails) itemList.get(i)).getTitle(),
									                      ((ItemDetails) itemList.get(i)).getThumb_Images(),
								                          ((ItemDetails) itemList.get(i)).getQty(),
									             (String) ((ItemDetails) itemList.get(i)).getPrice(),
									                      ((ItemDetails) itemList.get(i)).getSizeselected(),
									                      ((ItemDetails) itemList.get(i)).getmax());

				}
				MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()+ "");
			}
		}
	}

	class Cartchecks extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();

			String UERRR = Constant.Cart() + abc + "&pid=" + pidtest + ""
										   + "&qty=" + quatest + "&size_name=" + size_name
					                       + "&action=add";
			Log.v("New URL", UERRR);
			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("URL", res);

			try {
				JSONObject jObject = new JSONObject(res);
				msg = jObject.getString("response_message");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {
			Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
			if (Network.isConnectionFast(context)) {
				itemList.clear();
				Cartdetailss ck = new Cartdetailss();
				ck.execute();
			} else {
				Toast.makeText(context, "Person information is null",Toast.LENGTH_LONG).show();
			}
		}
	}

	class Cartdetailss extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();
			String res = BaseActivity.getJsonnew(getActivity(),Constant.CartDetails() + abc);
			try {
				JSONArray jArray = new JSONArray(res);
				int number = jArray.length();
				number = number - 1;

				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jObject = jArray.getJSONObject(i);

					ItemDetails k = new ItemDetails();
					k.setNid(Long.parseLong((String) jObject.getString("nid")));
					k.setTitle(jObject.getString("title"));
					String price = jObject.getString("price");
					double f = Double.parseDouble(price);
					k.setPrice(String.format("%.2f", new BigDecimal(f)));
					k.setQty(Integer.parseInt((String) jObject.getString("qty")));
					k.setThumb_Images(((String) jObject.getString("thumb_images")));
					k.setSizeselected(((String) jObject.getString("size_id")));
					k.setmax(jObject.getString("total_stock"));
					Log.v("", jObject.getString("title"));
					itemList.add(k);

				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return null;
		}

		protected void onPostExecute(String args) {

			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					MainActivity.dbHelpercart.inserRecord(((ItemDetails) itemList.get(i)).getNid(),
									                      ((ItemDetails) itemList.get(i)).getTitle(),
									                      ((ItemDetails) itemList.get(i)).getThumb_Images(),
									                      ((ItemDetails) itemList.get(i)).getQty(),
									             (String) ((ItemDetails) itemList.get(i)).getPrice(),
									                      ((ItemDetails) itemList.get(i)).getSizeselected(),
									                      ((ItemDetails) itemList.get(i)).getmax());

				}
				MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()	+ "");
			}
		}
	}
	
	private void switchFragment(Fragment fragment) {
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment)
				.addToBackStack("my_fragment").commitAllowingStateLoss();
	}

	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 0) {
			getFragmentManager().popBackStack();
		}

	}
}