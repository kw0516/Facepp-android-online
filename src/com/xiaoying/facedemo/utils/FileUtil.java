
package com.xiaoying.facedemo.utils;

import java.io.File;

public class FileUtil {

	/**
	 * 获取文件大小
	 * @param path
	 * @return
	 */
	public static long getFileSize(String path) {
		return new File(path).length();
	}
}
