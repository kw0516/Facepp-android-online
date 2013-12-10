
package com.xiaoying.facedemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

public class DateUtil {
	
	/**
	 * 获取当前时间
	 * @param pattern
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getNowDate(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date(System.currentTimeMillis()));
	}
}
