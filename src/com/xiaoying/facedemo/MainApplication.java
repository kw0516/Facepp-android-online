
package com.xiaoying.facedemo;

import java.io.File;

import android.app.Application;
import android.os.Environment;

import com.xiaoying.faceplusplus.api.cliet.Client;

public class MainApplication extends Application {
	
	public static final String APP_KEY = "YOUR-APP_KEY ";
	
	public static final String APP_SECRET = "YOUR-APP_SECRET ";
	
	public static final Client CLIENT = new Client(APP_KEY, APP_SECRET);
	
	public static String APP_ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FaceDemo/";
	
	public static String APP_PIC_PATH = APP_ROOT_DIR + "image/";
	
	public static String USER_NAME = "xiaoying";
	
	public static int mScreenWidth = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		
		File file = new File(APP_ROOT_DIR);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		file = new File(APP_PIC_PATH);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
}
