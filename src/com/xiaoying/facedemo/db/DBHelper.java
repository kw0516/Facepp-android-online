/*
 * 文件名：DBHelper.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-16
 * 修改人：xiaoying
 * 修改时间：2013-5-16
 * 版本：v1.0
 */

package com.xiaoying.facedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 功能：数据库帮助类
 * @author xiaoying
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	
	private final static int DB_VERSION = 1;
	
	private final static String DB_NAME = "facedetect.db";
	
	private static DBHelper mDBHelper = null;
	
	public static DBHelper getInstance(Context context) {
		if(mDBHelper == null) {
			mDBHelper = new DBHelper(context);
		}
		return mDBHelper;
	}
	
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//images表,上传或网络识别的图片
		db.execSQL("create table if not exists images(img_id TEXT PRIMARY KEY, img TEXT, url TEXT, width INTEGER, height INTEGER)");
		
		//faces表，保存所有的face
		db.execSQL("create table if not exists faces(face_id TEXT PRIMARY KEY, face_data TEXT, img_id TEXT, center_x REAL, center_y REAL, " +
				"eye_left_x REAL, eye_left_y REAL, eye_right_x REAL, eye_right_y REAL, nose_x REAL, nose_y REAL, " +
				"mouth_left_x REAL, mouth_left_y REAL, mouth_right_x REAL, mouth_right_y REAL, width REAL, height REAL, " +
				"age_value INTEGER, age_range INTEGER, gender_value TEXT, gender_confidence REAL, race_value TEXT, " +
				"race_confidence REAL, tag TEXT)");
		
		//persons表，保存所有的person
		db.execSQL("create table if not exists persons(person_id TEXT PRIMARY KEY, person_name TEXT, tag TEXT, face_count INTEGER)");
		
		//groups表，保存所有的group
		db.execSQL("create table if not exists groups(group_id TEXT PRIMARY KEY, group_name TEXT, tag TEXT, fperson_count INTEGER)");
		
		//facesets表，保存所有的faceset
		db.execSQL("create table if not exists facesets(faceset_id TEXT PRIMARY KEY, faceset_name TEXT, tag TEXT, face_count INTEGER)");
		
		//face_person表，保存着face和person之间的联系
		db.execSQL("create table if not exists face_person(_id INTEGER PRIMARY KEY AUTOINCREMENT, face_id TEXT, person_id TEXT)");
		
		//face_faceset表，保存face和faceset之间的联系
		db.execSQL("create table if not exists face_faceset(_id INTEGER PRIMARY KEY AUTOINCREMENT, face_id TEXT, faceset_id TEXT)");
		
		//person_group表，保存person与group之间的联系
		db.execSQL("create table if not exists person_group(_id INTEGER PRIMARY KEY AUTOINCREMENT, person_id TEXT, group_id TEXT)");
		
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
