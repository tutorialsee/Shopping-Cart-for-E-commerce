package com.tutorialsee.databaseforandroid.database;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tutorialsee.databaseforandroid.R;
import com.tutorialsee.databaseforandroid.http.BaseActivity;
import com.tutorialsee.databaseforandroid.http.Constant;
import com.tutorialsee.databaseforandroid.http.EndlessScrollListener;
import com.tutorialsee.databaseforandroid.http.MyProgressDialog;
import com.tutorialsee.databaseforandroid.http.Network;
import com.tutorialsee.databaseforandroid.http.PrefernceSettings;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Kilol extends Fragment implements OnItemSelectedListener {
	@SuppressWarnings("unused")
	private static WeakReference<Kilol> wrActivity = null;
	Context context ;
	protected static final String ARG_DETIALS_ID = null;
	ArrayList<HashMap<String, String>> array,array1;
	List<ItemDetails> itemList = new ArrayList<ItemDetails>();
	List<ItemDetails> itemList1 = new ArrayList<ItemDetails>();
	Long abdd;
	ArrayList<HashMap<String, String>> arraylist;
	List<ItemDetails> itemList2 = new ArrayList<ItemDetails>();
	GridView listview;
	ItemListAdapter ap_adap;
	String city_name;
	TextView ssd;
	View layout;
	RelativeLayout popudsd;
	LinearLayout lind;
	Boolean back = false;
	RelativeLayout realtive_title4;
	static String s_min1 = "0";
	static String s_max1 = "10";
	static String s_min = "0";
	static String s_max = "0";
	int Totalnew = 1;
	TextView tv1,tv2,txt_cancle;
	Parcelable state;
	static LinearLayout Lookingfor;
	HashMap<String, String> sizees = new HashMap<String, String>();
	HashMap<String, String> sizeesf = new HashMap<String, String>();
	static boolean projectexit = true;
	@SuppressWarnings("unused")
	private int visibleThreshold = 1;
	private int current_page = 1;
	@SuppressWarnings("unused")
	private int previousTotalItemCount = 0;
	@SuppressWarnings("unused")
	private boolean loading = true;
	@SuppressWarnings("unused")
	private int startingPageIndex = 0;
	String flg="L";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp=2;
		wrActivity = new WeakReference<Kilol>(this);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.kilol_activity, container, false);
		MainActivity.actionBar.show();
		context = container.getContext();
		PrefernceSettings.openDataBase(context);



		ssd = (TextView) v. findViewById(R.id.ssd);


		realtive_title4 = (RelativeLayout) v. findViewById(R.id.realtive_title4);
		tv1 = (TextView)v. findViewById(R.id.textView1);
		tv1 = (TextView)v. findViewById(R.id.textView1);


		listview = (GridView) v.findViewById(R.id.gridView2);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ItemDetails currentItem = (ItemDetails) parent.getItemAtPosition(position);
				abdd= currentItem.getNid();
				usernid();
			}
		});




		if(Network.isConnectionFast(context)){

			itemList.clear();
			current_page = 1;
			new Kilollist().execute();
			listview.setOnScrollListener(new EndlessScrollListener() {
				public void onLoadMore(int page, int totalItemsCount) {
					if(current_page<=Totalnew){
						current_page++;
						new Kilollist().execute();
					}
				}
			});
		}else{
			Toast toast = Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}

		return v;
	}




	protected void usernid() {
		if(Network.isConnectionFast(context)){
			usernid msp = new usernid();
			msp.execute();
		} else {
			Toast toast = Toast.makeText(getActivity(),"Please check your internet connection", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	class usernid extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			MyProgressDialog.show(context);
		}

		@Override
		protected String doInBackground(String... params) {
			arraylist = new ArrayList<HashMap<String, String>>();
			String res = BaseActivity.getJsonnew(getActivity(),Constant.SEARCHNID() + abdd);
			try {
				JSONObject parentObject = new JSONObject(res);
				itemList2.clear();
				ItemDetails k = new ItemDetails();
				k.setNid(Long.parseLong((String) parentObject.get("nid")));
				k.setTitle(parentObject.getString("title"));
				String price = parentObject.getString("price");
				double f = Double.parseDouble(price);
				k.setPrice(String.format("%.2f", new BigDecimal(f)));
				k.setBody(parentObject.getString("body"));
				k.setCare(parentObject.getString("care"));
				String data = parentObject.getString("size");
				k.setSize(data);
				String datas = parentObject.getString("size_stock");
				k.setSize_Stock(datas);
				k.setSize_Chart(parentObject.getString("size_chart"));
				JSONArray jsonMainNode1 = parentObject .optJSONArray("thumb_images");
				k.setThumb_Images(jsonMainNode1.getString(0).replace("", ""));
				JSONArray jsonMainNode2 = parentObject.optJSONArray("fullimages");
				if (jsonMainNode2.length() > 2) {
					k.setImagePath1(jsonMainNode2.getString(0).replace("",  ""));
					k.setFullImages(jsonMainNode2.getString(1).replace("",  ""));
					k.setImagePath(jsonMainNode2.getString(2).replace("",   ""));
				} else if (jsonMainNode2.length() > 1) {
					k.setImagePath1(jsonMainNode2.getString(0).replace("",  ""));
					k.setFullImages(jsonMainNode2.getString(1).replace("",  ""));						      
					k.setImagePath("null");
				} else if (jsonMainNode2.length() > 0) {
					k.setImagePath1(jsonMainNode2.getString(0).replace("",  ""));
					k.setFullImages("null");
					k.setImagePath("null");
				} else {
					k.setImagePath1("null");
					k.setFullImages("null");
					k.setImagePath("null");
				}
				itemList2.add(k);


			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(String args) {
			MyProgressDialog.close(context);
			Fragment newContent = new ProductDetails();
			ItemDetails currentItem = (ItemDetails) itemList2.get(0);
			Bundle arguments = new Bundle();
			arguments.putSerializable(ProductDetails.ARG_DETIALS_ID, currentItem);            
			newContent.setArguments(arguments);
			if (newContent != null) {
				newContent.setArguments(arguments);
				switchFragment(newContent);
			}

		}
	}





	class Kilollist extends AsyncTask<String, Void, String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			MyProgressDialog.show(context);
		}
		protected String doInBackground(String... params) {

			array = new ArrayList<HashMap<String, String>>();
			Log.v("Array size", hashCode()+"");
			String res = BaseActivity.getJsonnew(getActivity(), Constant.KILOL()+"?current_page="+current_page );
			try {
				JSONObject parentObject = new JSONObject(res);
				String brand = parentObject.getString("brand");
				String[] spinnerArray = null ;
				JSONObject jsObject;
				try {
					jsObject = new JSONObject(brand);
					spinnerArray=new String[jsObject.length()]	;
					Iterator<String> iter = jsObject.keys();
					int t=0;
					while (iter.hasNext()) 
					{
						String key = iter.next();
						try {
							String value = (String) jsObject.get(key);
							sizees.put(value,key);
							spinnerArray[t] = value;
							PrefernceSettings.setRegFlag(flg);
							PrefernceSettings.setCATID(key);
							t++;
						} catch (JSONException e) {
						}

					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}




				JSONArray jArray = new JSONArray(res.substring(res.indexOf("nid")-3)+"");
				int number = jArray.length();
				number = number - 1;
				for (int i = 0; i < jArray.length(); i++) {

					ItemDetails k = new ItemDetails();
					JSONObject jObject = jArray.getJSONObject(i);
					k.setNid(Long.parseLong((String) jObject.get("nid")));
					k.setTitle(jObject.getString("title"));
					String price = jObject.getString("price");
					double f = Double.parseDouble(price);
					k.setPrice(String.format("%.2f", new BigDecimal(f)));
					k.setThumb_Images(((String) jObject.get("thumb_images")));

					itemList.add(k);
				}
			} catch (Exception e) {
			}
			return null;
		}
		protected void onPostExecute(String args) {
			MyProgressDialog.close(context);
			if(itemList.size() == 0){
				//btn_cardasa.setVisibility(View.GONE);    
				ssd.setVisibility(View.VISIBLE);    
				//backright.setVisibility(View.GONE);  
			}else{
				ap_adap = new ItemListAdapter(getActivity(),R.layout.product_grid, itemList);
				state = listview.onSaveInstanceState();
				listview.setAdapter(ap_adap);
				listview.onRestoreInstanceState(state);

			}
		}
	}

	private void switchFragment(Fragment fragment) {
		getActivity(). getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, fragment)
		.addToBackStack("my_fragment").commitAllowingStateLoss();
	}

	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 0) {
			getFragmentManager().popBackStack();
		} 
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

}