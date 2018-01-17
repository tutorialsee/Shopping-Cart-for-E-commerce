package com.tutorialsee.databaseforandroid.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbhelpercart extends SQLiteOpenHelper {

	private static String CREATE_TABLE1;

	static String DATABASE_NAME = "Cartdbtes";

	public static final String TABLE1_NAME = "Cart";

	// Fields for table
	public static final String Nid = "Nid";
	public static final String Title = "title";
	public static final String Thumb_images = "thumb_images";
	public static final String Qty = "qty";
	public static final String Price = "price";
	public static final String Size_id = "size_id";
	public static final String Total_stock = "total_stock";
	private static final String ID = "id";
	
	Context context;
	private ContentValues cValues;
	private SQLiteDatabase dataBase = null;
	private Cursor cursor;

	public dbhelpercart(Context context) {
		super(context, context.getExternalFilesDir(null).getAbsolutePath()+ "/" + DATABASE_NAME, null, 1);
		context = context;
	}
	
    //Table create in database
	@Override
	public void onCreate(SQLiteDatabase db) {
		CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + "(" + ID
				+ " INTEGER PRIMARY KEY autoincrement, " + Nid + " long, "
				+ Title + " TEXT, " + Thumb_images + " TEXT, " + Qty
				+ " TEXT, " + Price + " TEXT, " + Size_id + " TEXT, "
				+ Total_stock + " TEXT)";

		db.execSQL(CREATE_TABLE1);
		System.out.println("Table is created...........................!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);

		onCreate(db);
	}

	public void inserRecord(long l, String title, String thumb_images, int i,
			String bigDecimal, String size_id, String total_stock) {

		dataBase = getWritableDatabase();

		cursor = dataBase.rawQuery("select count(*) from " + TABLE1_NAME
				+ " WHERE Nid =" + l + " and Size_id=" + "'" + size_id + "'"
				+ "and Title=" + "'" + title + "'", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		Log.v("size_id", size_id);
		if (count <= 0) {
			Log.v("", "Not Found");
			cValues = new ContentValues();
			cValues.put(Nid, l);
			cValues.put(Title, title);
			cValues.put(Thumb_images, thumb_images);
			cValues.put(Qty, i);
			cValues.put(Price, bigDecimal);
			cValues.put(Size_id, size_id);
			cValues.put(Total_stock, total_stock);
			dataBase.insert(TABLE1_NAME, null, cValues);
		} else {
			Log.v("", "Found");
			cursor = dataBase.rawQuery("select Qty from " + TABLE1_NAME
					+ " WHERE Nid = " + l + " and Size_id=" + "'" + size_id
					+ "'", null);
			cursor.moveToFirst();
			int count1 = cursor.getInt(0);
			updateRecord(l, i, total_stock, count1, size_id);
		}
		dataBase.close();
	}

	public void updateRecord(long nid, int Quan, String total, int preqty,
			String Size_id) {

		dataBase = getWritableDatabase();

		cValues = new ContentValues();
		if (Quan + preqty <= Integer.parseInt(total)) {

			cValues.put(Qty, Quan + preqty);

		} else {
			cValues.put(Qty, Integer.parseInt(total));
			Log.v("total", total);

		}
		if (Quan + preqty > 0) {
			dataBase.update(dbhelpercart.TABLE1_NAME, cValues, "Nid=" + nid
					+ " and Size_id=" + Size_id, null);
		}
		dataBase.close();

	}

	public void updateRecordgift(long nid, int Quan, String total, int preqty,
			String title) {

		dataBase = getWritableDatabase();

		cValues = new ContentValues();
		if (Quan + preqty <= Integer.parseInt(total)) {

			cValues.put(Qty, Quan + preqty);

		} else {
			cValues.put(Qty, Integer.parseInt(total));
			Log.v("total", total);

		}
		if (Quan + preqty > 0) {
			dataBase.update(dbhelpercart.TABLE1_NAME, cValues, "Nid=" + nid
					+ " and Title='" + title + "'", null);
		}
		dataBase.close();

	}

	public void DeleteRecord(long nid, String string) {

		dataBase = getWritableDatabase();
		dataBase.delete(dbhelpercart.TABLE1_NAME, "nid=? and Size_id=? ",
				new String[] { String.valueOf(nid), String.valueOf(string) });
		dataBase.close();
	}

	public void DeleteRecordgift(long nid, String string) {

		dataBase = getWritableDatabase();
		dataBase.delete(dbhelpercart.TABLE1_NAME, "nid=? and Title=? ",
				new String[] { String.valueOf(nid), String.valueOf(string) });
		dataBase.close();
	}

	public int countRecord() {
		int count = 0;
		dataBase = getReadableDatabase();
		cursor = dataBase.rawQuery("select sum(Qty) from " + TABLE1_NAME, null);
		cursor.moveToFirst();
		count = cursor.getInt(0);
		dataBase.close();
		return count;
	}
	public int countRecordbynid(long l) {
		int count = 0;
		dataBase = getReadableDatabase();
		cursor = dataBase.rawQuery("select Qty from " + TABLE1_NAME + " WHERE Nid = " + l  , null);
		cursor.moveToFirst();
		count = cursor.getInt(0);
		dataBase.close();
		return count;
	}
	
	
	public int gettotalprice() {
		int count = 0;
		dataBase = getReadableDatabase();
		cursor = dataBase.rawQuery("select sum(Qty*Price) from " + TABLE1_NAME,
				null);
		cursor.moveToFirst();
		count = cursor.getInt(0);
		dataBase.close();
		return count;
	}

	public Cursor selectRecords() {

		dataBase = getReadableDatabase();

		// Getting data from database table
		cursor = dataBase.rawQuery("select * from " + TABLE1_NAME, null);
		return cursor;
	}

	public void deleteRecord() {

		dataBase = getWritableDatabase();

		// Deleting all records from database table
		dataBase.delete(TABLE1_NAME, null, null);

		dataBase.close();
	}
	/*public int countCheck() {
		int count = 0;
		dataBase = getReadableDatabase();
		cursor = dataBase.rawQuery("select count(*) from " + TABLE1_NAME
				+ " WHERE Title LIKE '%GV%'", null);
		cursor.moveToFirst();
		count = cursor.getInt(0);
		dataBase.close();
		return count;
	}*/

}