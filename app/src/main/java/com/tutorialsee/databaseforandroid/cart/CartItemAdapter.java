package com.tutorialsee.databaseforandroid.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorialsee.databaseforandroid.R;
import com.tutorialsee.databaseforandroid.database.AddCart;
import com.tutorialsee.databaseforandroid.database.ItemDetails;
import com.tutorialsee.databaseforandroid.database.MainActivity;
import com.tutorialsee.databaseforandroid.http.BaseActivity;
import com.tutorialsee.databaseforandroid.http.Constant;
import com.tutorialsee.databaseforandroid.http.PrefernceSettings;

import com.squareup.picasso.Picasso;

public class CartItemAdapter extends BaseAdapter {

	private int layoutResourceId;
	private List<CartItem> cartItems = Collections.emptyList();
	ImageView bClear;
	private final Context context;
	String abc = PrefernceSettings.getUserId();
	int pidtest;
	int quatest;
	String size_name;
	String title;
	String msg = "", msgs = "";
	String GV;
	long nid;

	public CartItemAdapter(Context context, int carddesign) {
		this.context = context;
		this.layoutResourceId = carddesign;
	}

	public void updateCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return cartItems.size();
	}

	@Override
	public CartItem getItem(int position) {
		return cartItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView,final ViewGroup parent) {
		View itemView = null;
		LayoutInflater inflater;
		final ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			itemView = inflater.inflate(R.layout.carddesign, null, false);
		} else {
			itemView = convertView;
		}



		holder.tvCartItemName = (TextView) itemView.findViewById(R.id.txt_name);
		holder.tvCartItemUnitPrice = (ImageView) itemView.findViewById(R.id.Img_product);
		holder.txt_size = (TextView) itemView.findViewById(R.id.txt_size);
		holder.txt_rupddde = (TextView) itemView.findViewById(R.id.txt_rupddde);
		holder.tvCartItemPrice = (TextView) itemView.findViewById(R.id.txt_rupddcce);

		final CartItem cartItem = getItem(position);

		bClear = (ImageView) itemView.findViewById(R.id.Img_delete);
		holder._decrease = (Button) itemView.findViewById(R.id.btn_minus);
		holder._increase = (Button) itemView.findViewById(R.id.btn_plus);
		holder._value = (TextView) itemView.findViewById(R.id.edit_text);
		holder._value.setText(cartItem.getQuantity() + "");

		holder._decrease.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CartItem cartItem1 = getItem(position);

				if (cartItem1.getProduct().getQty() > 1) {
					if (!cartItem1.getProduct().getTitle().contains("|")) {
						MainActivity.dbHelpercart.updateRecord(cartItem1.getProduct().getNid(), -1, cartItem1.getProduct().getmax(),
								cartItem1.getProduct().getQty(), cartItem1.getProduct().getSizeselected());
					} else {
						MainActivity.dbHelpercart.updateRecordgift(cartItem1.getProduct().getNid(), -1, cartItem1.getProduct().getmax(),
								cartItem1.getProduct().getQty(),cartItem1.getProduct().getTitle());
					}

					MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord() + "");
					AddCart.tvTotalPrice.setText(String.valueOf(BigDecimal.valueOf(MainActivity.dbHelpercart.gettotalprice())));

					CartItemAdapter uradapteobjr = (CartItemAdapter) ((ListView) parent).getAdapter();
					uradapteobjr.updateCartItems(getCartItems());
					uradapteobjr.notifyDataSetChanged();
					CartItem cartItem2 = getItem(position);
					if (!abc.isEmpty()) {
						pidtest = (int) cartItem2.getProduct().getNid();
						quatest = cartItem2.getProduct().getQty();
						size_name = (String) cartItem2.getProduct()	.getSizeselected();
						title = (String) cartItem2.getProduct().getTitle();
						Cartchecksub ck = new Cartchecksub();
						ck.execute();
					}
				}
			}
		});

		holder._increase.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				CartItem cartItem1 = getItem(position);
				if (!cartItem1.getProduct().getTitle().contains("|")) {
					MainActivity.dbHelpercart.updateRecord(cartItem1.getProduct().getNid(), 1, cartItem1.getProduct().getmax(),
							cartItem1.getProduct().getQty(), cartItem1.getProduct().getSizeselected());
				} else {
					MainActivity.dbHelpercart.updateRecordgift(cartItem1.getProduct().getNid(), 1, cartItem1.getProduct().getmax(),
							cartItem1.getProduct().getQty(),cartItem1.getProduct().getTitle());
				}

				MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()	+ "");
				AddCart.tvTotalPrice.setText(String.valueOf(BigDecimal.valueOf(MainActivity.dbHelpercart.gettotalprice())));
				CartItemAdapter uradapteobjr = (CartItemAdapter) ((ListView) parent).getAdapter();
				uradapteobjr.updateCartItems(getCartItems());
				uradapteobjr.notifyDataSetChanged();
				CartItem cartItem2 = getItem(position);

				if (!abc.isEmpty()) {
					pidtest = (int) cartItem2.getProduct().getNid();
					quatest = cartItem2.getProduct().getQty();
					size_name = (String) cartItem2.getProduct().getSizeselected();
					title = (String) cartItem2.getProduct().getTitle();
					Cartcheckadd ck = new Cartcheckadd();
					ck.execute();
				}

			}
		});

		bClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {

					if (!cartItem.getProduct().getTitle().contains("|")) {
						MainActivity.dbHelpercart.DeleteRecord(cartItem.getProduct().getNid(), cartItem.getProduct().getSizeselected());
					} else {
						MainActivity.dbHelpercart.DeleteRecordgift(cartItem.getProduct().getNid(), cartItem.getProduct().getTitle());
					}

					MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord() + "");
					AddCart.tvTotalPrice.setText(String.valueOf(BigDecimal.valueOf(MainActivity.dbHelpercart.gettotalprice())));
					CartItemAdapter uradapteobjr = (CartItemAdapter) ((ListView) parent).getAdapter();
					uradapteobjr.updateCartItems(getCartItems());
					uradapteobjr.notifyDataSetChanged();

					if (!abc.isEmpty()) {
						pidtest = (int) cartItem.getProduct().getNid();
						quatest = 0;
						size_name = (String) cartItem.getProduct().getSizeselected();
						title = (String) cartItem.getProduct().getTitle();
						Cartcheckdel ck = new Cartcheckdel();
						ck.execute();
					}

					if (AddCart.cartItemAdapter.getCount() > 0) {
						AddCart.ssd.setVisibility(View.GONE);
					} else {
						AddCart.ssd.setVisibility(View.VISIBLE);
						AddCart._rel.setVisibility(View.GONE);
					}

				} catch (Exception e) {

				}
			}

		});




		String title = cartItem.getProduct().getTitle().toString();
		if (title.contains("|")) {

			holder.tvCartItemName.setText(title.substring(0, title.indexOf("|")));
			holder.txt_size.setText(title.substring(title.lastIndexOf("|") + 1));
			// Log.v("", title.substring(title.indexOf("|")+1,
			// title.lastIndexOf("|")));
			Log.v("", title);
			holder.txt_rupddde.setText("Email:");
		} else {
			holder.tvCartItemName.setText("Felt Laptop Sleeve (Charcoal)");
			holder.txt_size.setText(Constant.getCountryByCode(String.valueOf(cartItem.getProduct().getSizeselected())));
			holder.txt_rupddde.setText("Size:");
		}
		//Picasso.with(context).load("http://homemonkey.com/timthumb.php?src="+ cartItem.getProduct().getThumb_Images().replace(" ", "%20") + "&w=80&h=110").placeholder(R.drawable.ic_launcher).into(holder.tvCartItemUnitPrice);
		Picasso.with(context).load(R.drawable.productsmall).placeholder(R.drawable.load).into(holder.tvCartItemUnitPrice);

		holder.tvCartItemPrice.setText(String.valueOf(cartItem.getProduct().getPrice()));

		return itemView;
	}

	private List<CartItem> getCartItems() {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		Cursor allitem = MainActivity.dbHelpercart.selectRecords();
		boolean b = false;
		if (allitem.moveToFirst()) {
			do {
				CartItem cartItem = new CartItem();
				ItemDetails k = new ItemDetails();
				k.setNid(allitem.getLong(1));
				k.setTitle(allitem.getString(2));
				k.setThumb_Images(allitem.getString(3));
				k.setQty(allitem.getInt(4));
				if (k.getTitle().substring(0, 2).contains("GV")) {
					b = true;
				}
				k.setPrice(allitem.getString(5));
				k.setSizeselected(allitem.getString(6));
				k.setmax(allitem.getString(7));
				cartItem.setProduct(k);
				cartItem.setQuantity(allitem.getInt(4));

				cartItems.add(cartItem);
			} while (allitem.moveToNext());
		}
		if (!b) {
			PrefernceSettings.setGiftMail("");
		}
		allitem.close();

		return cartItems;
	}

	private static class ViewHolder {
		TextView tvCartItemName;
		ImageView tvCartItemUnitPrice;
		// TextView tvCartItemQuantity;
		TextView txt_size;
		TextView tvCartItemPrice;
		Button _decrease;
		Button _increase;
		TextView _value, txt_rupddde;

	}

	class Cartcheck extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {

			String UERRR = Constant.Cart() + abc + "&pid=" + pidtest + ""
					+ "&qty=" + quatest + "&size_name=" + size_name;
			Log.v("New URL", UERRR);

			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("URL", res);

			try {
				JSONObject jObject = new JSONObject(res);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {

		}
	}

	class Cartcheckadd extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String UERRR;
			if (!title.contains("GV")) {
				UERRR = Constant.Cart() + abc + "&pid=" + pidtest + "" + "&qty="
						+ "1" + "&size_name=" + size_name + "&action=add";
				Log.v("New URL", UERRR);
			} else {
				String title1 = title.substring(0, title.indexOf("|"));
				String Msg = title.substring(title.indexOf("|") + 1,
						title.lastIndexOf("|"));
				String Mails = title.substring(title.lastIndexOf("|") + 1);
				UERRR = Constant.Cart() + abc + "&pid=" + pidtest + "" + "&qty="
						+ "1" + "&message=" + Msg + "&mail=" + Mails
						+ "&action=add";
				Log.v("New URL", UERRR);
			}
			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("URL", res);

			try {
				JSONObject jObject = new JSONObject(res);
				if (res.contains("response_message")) {
					msg = jObject.getString("response_message");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String args) {

			Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

		}
	}

	class Cartchecksub extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String UERRR;
			if (!title.contains("GV")) {
				UERRR = Constant.Cart() + abc + "&pid=" + pidtest + "" + "&qty="
						+ "1" + "&size_name=" + size_name + "&action=sub";
				Log.v("New URL", UERRR);
			} else {
				String title1 = title.substring(0, title.indexOf("|"));
				String Msg = title.substring(title.indexOf("|") + 1,
						title.lastIndexOf("|"));
				String Mails = title.substring(title.lastIndexOf("|") + 1);
				UERRR = Constant.Cart() + abc + "&pid=" + pidtest + "" + "&qty="
						+ "1" + "&message=" + Msg + "&mail=" + Mails
						+ "&action=sub";
				Log.v("New URL", UERRR);
			}
			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("URL", res);

			try {
				JSONObject jObject = new JSONObject(res);
				if (res.contains("response_message")) {
					msgs = jObject.getString("response_message");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {
			Toast toast = Toast.makeText(context, msgs, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

		}
	}

	class Cartcheckdel extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String UERRR;
			if (!title.contains("GV")) {
				UERRR = Constant.Cart() + abc + "&pid=" + pidtest + "" + "&qty="
						+ quatest + "&size_name=" + size_name + "&action=del";
				Log.v("New URL", UERRR);
			} else {

				String title1 = title.substring(0, title.indexOf("|"));
				String Msg = title.substring(title.indexOf("|") + 1,
						title.lastIndexOf("|"));
				String Mails = title.substring(title.lastIndexOf("|") + 1);

				UERRR = Constant.Cart() + abc + "&pid=" + pidtest + "" + "&qty="
						+ quatest + "&message=" + Msg + "&mail=" + Mails
						+ "&action=del";
				Log.v("jhgjhg", UERRR);
			}
			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("Response", res);

			try {
				JSONObject jObject = new JSONObject(res);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {

		}
	}

}