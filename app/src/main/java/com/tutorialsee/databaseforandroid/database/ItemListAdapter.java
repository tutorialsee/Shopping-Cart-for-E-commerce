package com.tutorialsee.databaseforandroid.database;

import java.util.HashMap;
import java.util.List;
import com.squareup.picasso.Picasso;
import com.tutorialsee.databaseforandroid.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListAdapter extends ArrayAdapter<ItemDetails> {
    private final Context context;
 private final List<ItemDetails> items;
 @SuppressWarnings("unused")
private LayoutInflater inflater;
 private int layoutResourceId;
 public ItemListAdapter(Context context,int layoutResourceId, List<ItemDetails> items) {
	 super(context, layoutResourceId, items);
     this.context = context;
     this.items = items;
 	this.layoutResourceId = layoutResourceId;
 }


 @SuppressWarnings("unused")
@SuppressLint({ "InflateParams", "CutPasteId" })
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView = null;
		LayoutInflater inflater;
		final ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			itemView = inflater.inflate(layoutResourceId, null, false);

		} else {
			itemView = convertView;

		}

		HashMap<String, String> resultp = new HashMap<String, String>();
		 ItemDetails currentItem = items.get(position);
		
		holder.txt_name = (TextView) itemView.findViewById(R.id.txt_nameproduct);
		holder.txt_id = (TextView) itemView.findViewById(R.id.txt_id);
		holder.txt_rss = (TextView) itemView.findViewById(R.id.txt_rs);
		holder.img_image = (ImageView) itemView.findViewById(R.id.img_image);
		
		//text strike
		holder.txt_costrs = (TextView) itemView.findViewById(R.id.txt_costrs);
		TextView txt_costrs = (TextView) itemView.findViewById(R.id.txt_costrs);
		holder.txt_costrs.setText("Text here");
		holder.txt_costrs.setPaintFlags(txt_costrs.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		
		holder.txt_costrs.setText(""+currentItem.getCost_Price());
		holder.txt_name.setText("Felt Laptop Sleeve (Charcoal)");
		holder.txt_id.setText(""+currentItem.getNid());
		holder.txt_rss.setText(""+currentItem.getPrice());
	   // Picasso.with(context).load("http://homemonkey.com/timthumb.php?src="+currentItem.getThumb_Images().replace(" ", "%20")+"&w=200&h=0").placeholder(R.drawable.load).into(holder.img_image);
		Picasso.with(context).load(R.drawable.productsmall).placeholder(R.drawable.load).into(holder.img_image);

		return itemView;

	}

	static class ViewHolder {
		TextView txt_name,txt_id,txt_rss,txt_costrs;
		ImageView img_image;
	}
}