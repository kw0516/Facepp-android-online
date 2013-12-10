/*
 * 文件名：LogUtil.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.utils;

/**
 * 功能：消息打印工具类
 * @author xiaoying
 */
public class Log {
	
	private static boolean isPrint = true;
	
	public static void e(String msg) {
		if(isPrint) {
			System.err.println(msg);
		}
	}
	
	public static void i(String msg) {
		if(isPrint) {
			System.out.println(msg);
		}
	}
}
