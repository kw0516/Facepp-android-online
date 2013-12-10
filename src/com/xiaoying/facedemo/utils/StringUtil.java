
package com.xiaoying.facedemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;


public class StringUtil {

	@SuppressLint("SimpleDateFormat")
	public static String createImageName() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return "IMG" + dateFormat.format(new Date(System.currentTimeMillis())) + ".jpg";
	}
}
