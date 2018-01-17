package com.tutorialsee.databaseforandroid.http;

import com.tutorialsee.databaseforandroid.R;
import com.tutorialsee.databaseforandroid.database.MainActivity;

public class Constant {

	public static boolean mobileswall = false, pxx = false,
			wallpapermob = false;



	public static String KILOL() {
		String KILOL1 = "";
		try {
			KILOL1 = AESHelper
					.decrypt(
							"KILOL",
							"8544665D8E4CD065F31A67D7D502646A4F545D5BDEB2EDE6A20C9789B6CD34A1");
			return KILOL1;
		} catch (Exception e) {
			return KILOL1;

		}
	}

	public static String SEARCHNID() {
		String SEARCHNID1 = "";
		try {
			SEARCHNID1 = AESHelper
					.decrypt(
							"SEARCHNID",
							"FD60220D23FDE1343790B1FCD79FF7E1DE91DEA335C00DE0744AE2549D0AE7E5430B665F4D7E115E7099C79159FAD52D");
			return SEARCHNID1;
		} catch (Exception e) {
			return SEARCHNID1;

		}
	}

	public static String Cart() {
		String Cart1 = "";
		try {
			Cart1 = AESHelper
					.decrypt(
							"Cart",
							"977EC6D0B31DEFDA7E901443DABC42BBC08F8C504027A31C4AD6930DCD91300F68E297BA6F6C1833AB29D46F2A70AB26");
			return Cart1;
		} catch (Exception e) {
			return Cart1;

		}
	}
	public static String CartDetails() {
		String CartDetails1 = "";
		try {
			CartDetails1 = AESHelper
					.decrypt(
							"CartDetails",
							"87E5DE0B5D60C8BE4A66A8A4A6664D98643E698BCFEEADB97E8E921CF616AED39F6C405DEF88482B5F88E35FF068DEA0");
			return CartDetails1;
		} catch (Exception e) {
			return CartDetails1;

		}
	}
	public static String CartDelete() {
		String CartDelete1 = "";
		try {
			CartDelete1 = AESHelper
					.decrypt(
							"CartDelete",
							"4BDA15FA90ABD563024658317CD2A86F8B24DB9C861E414F20D4D7185EEDC71D587A22726024649C049EBC7B979A9156");
			return CartDelete1;
		} catch (Exception e) {
			return CartDelete1;

		}
	}

	public static String ADDRESSLIST() {
		String ADDRESSLIST1 = "";
		try {
			ADDRESSLIST1 = AESHelper
					.decrypt(
							"ADDRESSLIST",
							"F896547649ED9FB422569632D74C146715201733A21EF06429EF7F9B2D87C64F9ABB664FA90B6FC7DF999D684CCEE2B6");
			return ADDRESSLIST1;
		} catch (Exception e) {
			return ADDRESSLIST1;

		}
	}


	public static String shipping = "0";
	public static String payment = "0";
	public static String country = "0";

	public static String getCountryByCode(String code) {
		int i = -1;
		for (String cc:MainActivity.context.getResources().getStringArray(R.array.codes)) {
			i++;
			if (cc.equals(code))
				break;
		}
		return MainActivity.context.getResources().getStringArray(R.array.names)[i];
	}
}