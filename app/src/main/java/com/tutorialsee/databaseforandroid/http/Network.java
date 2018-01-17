package com.tutorialsee.databaseforandroid.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Network {

  /** The absence of a connection type. */
  public static final int TYPE_NONE = -1;
  /** Unknown network class. */
  public static final int NETWORK_CLASS_UNKNOWN = 0;
  /** Class of broadly defined "2G" networks. */
  public static final int NETWORK_CLASS_2_G = 1;
  /** Class of broadly defined "3G" networks. */
  public static final int NETWORK_CLASS_3_G = 2;
  /** Class of broadly defined "4G" networks. */
  public static final int NETWORK_CLASS_4_G = 3;

  
  public static NetworkInfo getInfo(Context context) {
    return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
        .getActiveNetworkInfo();
  }

  
  public static int getType(Context context) {
    NetworkInfo info = getInfo(context);
    if (info == null || !info.isConnected()) {
      return TYPE_NONE;
    }
    return info.getType();
  }

  
  public static int getSubType(Context context) {
    NetworkInfo info = getInfo(context);
    if (info == null || !info.isConnected()) {
      return TYPE_NONE;
    }
    return info.getSubtype();
  }

  /** Returns the NETWORK_TYPE_xxxx for current data connection. */
  public static int getNetworkType(Context context) {
    return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
        .getNetworkType();
  }

  /** Check if there is any connectivity */
  public static boolean isConnected(Context context) {
    return getType(context) != TYPE_NONE;
  }

  /** Check if there is any connectivity to a Wifi network */
 /* public static boolean isWifiConnection(Context context) {
    NetworkInfo info = getInfo(context);
    if (info == null || !info.isConnected()) {
      return false;
    }
    switch (info.getType()) {
      case ConnectivityManager.TYPE_WIFI:
        return true;
      default:
        return false;
    }
  }*/
  
	//if(Network.isConnectionFast(context)){

  /** Check if there is any connectivity to a mobile network */
  public static boolean isMobileConnection(Context context) {
    NetworkInfo info = getInfo(context);
    if (info == null || !info.isConnected()) {
      return false;
    }
    switch (info.getType()) {
      case ConnectivityManager.TYPE_MOBILE:
        return true;
      default:
        return false;
    }
  }

  /** Check if the current connection is fast. */
  public static boolean isConnectionFast(Context context) {
    NetworkInfo info = getInfo(context);
    if (info == null || !info.isConnected()) {
      return false;
    }
    switch (info.getType()) {
      case ConnectivityManager.TYPE_WIFI:
      case ConnectivityManager.TYPE_ETHERNET:
        return true;
      case ConnectivityManager.TYPE_MOBILE:
        int networkClass = getNetworkClass(getNetworkType(context));
        switch (networkClass) {
          case NETWORK_CLASS_UNKNOWN:
          
            return false;
          case NETWORK_CLASS_2_G:
          case NETWORK_CLASS_3_G:
          case NETWORK_CLASS_4_G:
            return true;
        }
      default:
        return false;
    }
  }

  private static int getNetworkClassReflect(int networkType)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method getNetworkClass = TelephonyManager.class.getDeclaredMethod("getNetworkClass", int.class);
    if (!getNetworkClass.isAccessible()) {
      getNetworkClass.setAccessible(true);
    }
	return networkType;
  }

  
  public static int getNetworkClass(int networkType) {
    try {
      return getNetworkClassReflect(networkType);
    } catch (Exception ignored) {
    }

    switch (networkType) {
      case TelephonyManager.NETWORK_TYPE_GPRS:
      case 16: // TelephonyManager.NETWORK_TYPE_GSM:
      case TelephonyManager.NETWORK_TYPE_EDGE:
      case TelephonyManager.NETWORK_TYPE_CDMA:
      case TelephonyManager.NETWORK_TYPE_1xRTT:
      case TelephonyManager.NETWORK_TYPE_IDEN:
        return NETWORK_CLASS_2_G;
      case TelephonyManager.NETWORK_TYPE_UMTS:
      case TelephonyManager.NETWORK_TYPE_EVDO_0:
      case TelephonyManager.NETWORK_TYPE_EVDO_A:
      case TelephonyManager.NETWORK_TYPE_HSDPA:
      case TelephonyManager.NETWORK_TYPE_HSUPA:
      case TelephonyManager.NETWORK_TYPE_HSPA:
      case TelephonyManager.NETWORK_TYPE_EVDO_B:
      case TelephonyManager.NETWORK_TYPE_EHRPD:
      case TelephonyManager.NETWORK_TYPE_HSPAP:
      case 17: // TelephonyManager.NETWORK_TYPE_TD_SCDMA:
        return NETWORK_CLASS_3_G;
      case TelephonyManager.NETWORK_TYPE_LTE:
      case 18: // TelephonyManager.NETWORK_TYPE_IWLAN:
        return NETWORK_CLASS_4_G;
      default:
        return NETWORK_CLASS_UNKNOWN;
    }
  }

  private Network() {
    throw new AssertionError();
  }

}