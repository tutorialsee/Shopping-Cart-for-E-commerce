package com.tutorialsee.databaseforandroid.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefernceSettings 
{
	private static SharedPreferences myPrefs;
	private static SharedPreferences.Editor prefsEditor;

	public static void openDataBase(Context context)
	{
		try
		{
			context=context.getApplicationContext();
			myPrefs = context.getSharedPreferences("Reload", context.MODE_PRIVATE);
			prefsEditor = myPrefs.edit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static String getUserId()
	{
		String i = myPrefs.getString("KEY_UserId","");
		Log.e("dfg", i);
		return i;
	}
	public static void setUserId(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_UserId",m);
		prefsEditor.commit();
	}
	public static String getTid()
	{
		String i = myPrefs.getString("KEY_Tid","");
		Log.e("dfg", i);
		return i;
	}
	public static void setTid(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Tid",m);
		prefsEditor.commit();
	}
	public static String getUserName()
	{
		String i = myPrefs.getString("KEY_UserName","");
		Log.e("dfg", i);
		return i;
	}
	public static void setUserName(String s)
	{
		Log.e("dfgdfd", s);
		prefsEditor.putString("KEY_UserName",s);
		prefsEditor.commit();
	}
	public static String getRegFlag()
	{
		String i = myPrefs.getString("KEY_RegFlag","");
		Log.e("dfg", i);
		return i;
	}
	public static void setRegFlag(String l)
	{
		Log.e("dfgdfd", l);
		prefsEditor.putString("KEY_RegFlag",l);
		prefsEditor.commit();
	}
	public static String getOrderId()
	{
		String i = myPrefs.getString("KEY_OrderId","");
		Log.e("dfg", i);
		return i;
	}
	public static void setOrderId(String mm)
	{
		Log.e("dfgdfd", mm);
		prefsEditor.putString("KEY_OrderId",mm);
		prefsEditor.commit();
	}
	public static String getName()
	{
		String i = myPrefs.getString("KEY_Name","");
		Log.e("dfg", i);
		return i;
	}
	public static void setName(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Name",m);
		prefsEditor.commit();
	}
	public static String getMail()
	{
		String i = myPrefs.getString("KEY_Mail","");
		Log.e("dfg", i);
		return i;
	}
	public static void setMail(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Mail",m);
		prefsEditor.commit();
	}
	public static String getQuantity()
	{
		String i = myPrefs.getString("KEY_Quantity","");
		Log.e("dfg", i);
		return i;
	}
	public static void setQuantity(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Quantity",m);
		prefsEditor.commit();
	}
	public static String getSaleRes()
	{
		String i = myPrefs.getString("KEY_SaleRes","");
		Log.e("dfg", i);
		return i;
	}
	public static void setSaleRes(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_SaleRes",m);
		prefsEditor.commit();
	}
	public static String getList()
	{
		String i = myPrefs.getString("KEY_List","");
		Log.e("dfg", i);
		return i;
	}
	public static void setList(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_List",m);
		prefsEditor.commit();
	}
	public static String getMin()
	{
		String i = myPrefs.getString("KEY_Min","");
		Log.e("dfg", i);
		return i;
	}
	public static void setMin(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Min",m);
		prefsEditor.commit();
	}
	public static String getMax()
	{
		String i = myPrefs.getString("KEY_Max","");
		Log.e("dfg", i);
		return i;
	}
	public static void setMax(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Max",m);
		prefsEditor.commit();
	}
	public static String getIDONE()
	{
		String i = myPrefs.getString("KEY_IDONE","");
		Log.e("dfg", i);
		return i;
	}
	public static void setIDONE(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_IDONE",m);
		prefsEditor.commit();
	}
	public static String getIDTWO()
	{
		String i = myPrefs.getString("KEY_IDTWO","");
		Log.e("dfg", i);
		return i;
	}
	public static void setIDTWO(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_IDTWO",m);
		prefsEditor.commit();
	}
	public static String getCATID()
	{
		String i = myPrefs.getString("KEY_CATID","");
		Log.e("dfg", i);
		return i;
	}
	public static void setCATID(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_CATID",m);
		prefsEditor.commit();
	}
	public static String getCATVALUE()
	{
		String i = myPrefs.getString("KEY_CATVALUE","");
		Log.e("dfg", i);
		return i;
	}
	public static void setCATVALUE(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_CATVALUE",m);
		prefsEditor.commit();
	}
	public static String getKILOL()
	{
		String i = myPrefs.getString("KEY_KILOL","");
		Log.e("dfg", i);
		return i;
	}
	public static void setKILOL(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_KILOL",m);
		prefsEditor.commit();
	}
	public static String getITEMID()
	{
		String i = myPrefs.getString("KEY_ITEMID","");
		Log.e("dfg", i);
		return i;
	}
	public static void setITEMID(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_ITEMID",m);
		prefsEditor.commit();
	}
	public static String getPAYMENT()
	{
		String i = myPrefs.getString("KEY_PAYMENT","");
		Log.e("dfg", i);
		return i;
	}
	public static void setPAYMENT(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_PAYMENT",m);
		prefsEditor.commit();
	}
	public static String getSHIPPING()
	{
		String i = myPrefs.getString("KEY_SHIPPING","");
		Log.e("dfg", i);
		return i;
	}
	public static void setSHIPPING(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_SHIPPING",m);
		prefsEditor.commit();
	}
	public static String getBILLING()
	{
		String i = myPrefs.getString("KEY_BILLING","");
		Log.e("dfg", i);
		return i;
	}
	public static void setBILLING(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_BILLING",m);
		prefsEditor.commit();
	}
	public static String getRESBILLINGSHIPPING()
	{
		String i = myPrefs.getString("KEY_RESBILLINGSHIPPING","");
		Log.e("dfg", i);
		return i;
	}
	public static void setRESBILLINGSHIPPING(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_RESBILLINGSHIPPING",m);
		prefsEditor.commit();
	}
	public static String getFBNAME()
	{
		String i = myPrefs.getString("KEY_FBNAME","");
		Log.e("dfg", i);
		return i;
	}
	public static void setFBNAME(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_FBNAME",m);
		prefsEditor.commit();
	}
	public static String getFBEMAIL()
	{
		String i = myPrefs.getString("KEY_FBEMAIL","");
		Log.e("dfg", i);
		return i;
	}
	public static void setFBEMAIL(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_FBEMAIL",m);
		prefsEditor.commit();
	}
	public static String getSTATE()
	{
		String i = myPrefs.getString("KEY_STATE","");
		Log.e("dfg", i);
		return i;
	}
	public static void setSTATE(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_STATE",m);
		prefsEditor.commit();
	}
	public static String getCOUNTRY()
	{
		String i = myPrefs.getString("KEY_COUNTRY","");
		Log.e("dfg", i);
		return i;
	}
	public static void setCOUNTRY(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_COUNTRY",m);
		prefsEditor.commit();
	}
	public static String getUSERNID()
	{
		String i = myPrefs.getString("KEY_USERNID","");
		Log.e("dfg", i);
		return i;
	}
	public static void setUSERNID(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_USERNID",m);
		prefsEditor.commit();
	}
	public static String getCATEGORIESID()
	{
		String i = myPrefs.getString("KEY_CATEGORIESID","");
		Log.e("dfg", i);
		return i;
	}
	public static void setCATEGORIESID(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_CATEGORIESID",m);
		prefsEditor.commit();
	}
	public static String getSTATUS()
	{
		String i = myPrefs.getString("KEY_STATUS","");
		Log.e("dfg", i);
		return i;
	}
	public static void setSTATUS(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_STATUS",m);
		prefsEditor.commit();
	}
	public static String getUSERPROFILDATA()
	{
		String i = myPrefs.getString("KEY_USERPROFILDATA","");
		Log.e("dfg", i);
		return i;
	}
	public static void setUSERPROFILDATA(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_USERPROFILDATA",m);
		prefsEditor.commit();
	}
	public static String getCountrynew()
	{
		String i = myPrefs.getString("KEY_Countrynew","");
		Log.e("dfg", i);
		return i;
	}
	public static void setCountrynew(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Countrynew",m);
		prefsEditor.commit();
	}
	public static String getGift()
	{
		String i = myPrefs.getString("KEY_Gift","");
		Log.e("dfg", i);
		return i;
	}
	public static void setGift(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Gift",m);
		prefsEditor.commit();
	}
	public static String getGiftMail()
	{
		String i = myPrefs.getString("KEY_GiftMail","");
		Log.e("dfg", i);
		return i;
	}
	public static void setGiftMail(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_GiftMail",m);
		prefsEditor.commit();
	}
	public static String getMessage()
	{
		String i = myPrefs.getString("KEY_Message","");
		Log.e("dfg", i);
		return i;
	}
	public static void setMessage(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Message",m);
		prefsEditor.commit();
	}
	public static String getGiftCode()
	{
		String i = myPrefs.getString("KEY_GiftCode","");
		Log.e("dfg", i);
		return i;
	}
	public static void setGiftCode(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_GiftCode",m);
		prefsEditor.commit();
	}
	public static String getCouponCodeMail()
	{
		String i = myPrefs.getString("KEY_CouponCodeMail","");
		Log.e("dfg", i);
		return i;
	}
	public static void setCouponCodeMail(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_CouponCodeMail",m);
		prefsEditor.commit();
	}
	public static String getTotal()
	{
		String i = myPrefs.getString("KEY_Total","");
		Log.e("dfg", i);
		return i;
	}
	public static void setTotal(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_Total",m);
		prefsEditor.commit();
	}
	public static String getShippingCharge()
	{
		String i = myPrefs.getString("KEY_ShippingCharge","");
		Log.e("dfg", i);
		return i;
	}
	public static void setShippingCharge(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_ShippingCharge",m);
		prefsEditor.commit();
	}
	public static String getFinalTotal()
	{
		String i = myPrefs.getString("KEY_FinalTotal","");
		Log.e("dfg", i);
		return i;
	}
	public static void setFinalTotal(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_FinalTotal",m);
		prefsEditor.commit();
	}
	public static String getSizeSelectednew()
	{
		String i = myPrefs.getString("KEY_SizeSelectednew","");
		Log.e("dfg", i);
		return i;
	}
	public static void setSizeSelectednew(String m)
	{
		Log.e("dfgdfd", m);
		prefsEditor.putString("KEY_SizeSelectednew",m);
		prefsEditor.commit();
	}
}
