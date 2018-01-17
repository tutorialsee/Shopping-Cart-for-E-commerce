package com.tutorialsee.databaseforandroid.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.tutorialsee.databaseforandroid.R;

@SuppressLint("DefaultLocale")
public class BaseActivity extends Activity {

	AlertDialog.Builder alert ;
	Context context ;
	TextView tv_error ;
	static ProgressDialog mProgressDialog ;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_layout);
		context = this;
	}

	public static String convertInputStreamToString(InputStream inputStream) {
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		try {
			while((line = bufferedReader.readLine()) != null)
				result += line;
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getJsonnew(
			Context applicationContext,String url) {
		InputStream is = null;
		String result = "";
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
		}
		try{
			if(is != null){
				result = convertInputStreamToString(is);
				Log.e("result", result);
			}else{
				result = "Did not work!";
			}
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}
		return result;
	}



	public static String getJson(List<NameValuePair> nameValuePairs,
			Context applicationContext,String url) {
		InputStream is = null;
		String result = "";
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			Log.e("",String.valueOf(httpclient.getParams().toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
		}
		try{
			if(is != null){
				result = convertInputStreamToString(is);
				Log.e("result", result);
			}else{
				result = "Did not work!";
			}
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}
		return result;
	}







	public static void loader(Context context2){
		mProgressDialog = new ProgressDialog(context2);
		mProgressDialog.setMessage("Loading...");
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);
		mProgressDialog.getWindow().setGravity(Gravity.CENTER);
		mProgressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

		mProgressDialog.show();
		mProgressDialog.setContentView(R.layout.base_layout);
	}




	public static void unloader(Context context2) {
		if(mProgressDialog!=null && mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
		}
	}

	public static boolean isNetworkConnected(Context context2) {
		ConnectivityManager conManager = (ConnectivityManager) context2.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conManager.getActiveNetworkInfo();
		return ( netInfo != null && netInfo.isConnected() );
	}

	public static  boolean isEmailValid(String email) {
		boolean isValid = false;

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;

	}
}
