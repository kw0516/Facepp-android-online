/*
 * 文件名：ImageDBUtil.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-17
 * 修改人：xiaoying
 * 修改时间：2013-5-17
 * 版本：v1.0
 */
package com.xiaoying.facedemo.db.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.xiaoying.facedemo.db.DBHelper;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.faceplusplus.api.entity.Image;
import com.xiaoying.faceplusplus.api.entity.Person;

/**
 * 功能：数据库图片表数据操作工具类
 * @author xiaoying
 */
public class ImageDBUtil {
//	img_id TEXT PRIMARY KEY, img TEXT, url TEXT, width INTEGER, height INTEGER
	
	/**
	 * 插入一个Image数据
	 * @param context
	 * @param image
	 * @return
	 * @throws SQLiteException
	 */
	public static long insertImage(Context context, Image image) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValue(values, image);
		long id = db.insertOrThrow("images", null, values);
		db.close();
		return id;
	}
	
	/**
	 * 插入多条Image数据
	 * @param context
	 * @param images
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertImages(Context context, List<Image> images) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		ContentValues values = new ContentValues();
		for (Image image : images) {
			values.clear();
			setValue(values, image);
			db.insertOrThrow("images", null, values);
			count++;
		}
		db.close();
		return count;
	}
	
	public static int insertImageifNeed(Context context, Image image) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		if(contains(db, image)) {
			return 0;
		}
		ContentValues values = new ContentValues();
		setValue(values, image);
		db.insertOrThrow("images", null, values);
		db.close();
		return 1;
	}
	
	private static boolean contains(SQLiteDatabase db, Image image) throws SQLiteException {
		boolean contains = false;
		Cursor cursor = db.query("images", null, "img_id = ? and img = ?", 
				new String [] {image.getImageId(), image.getImg(), }, null, null, null);
		if(cursor != null) {
			contains = cursor.moveToFirst();
			cursor.close();
		}
		return contains;
	}
	
	/**
	 * 根据ID查找一张Image
	 * @param context
	 * @param id
	 * @return
	 * @throws SQLiteException
	 */
	public static Image getImage(Context context, String id) throws SQLiteException {
		Image image = null;
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Cursor cursor = db.query("images", null, "img_id = ?", new String [] {id, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				image = new Image();
				image.setImageId(cursor.getString(cursor.getColumnIndex("img_id")));
				image.setImg(cursor.getString(cursor.getColumnIndex("img")));
				image.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				image.setWidth(cursor.getInt(cursor.getColumnIndex("width")));
				image.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
			}
			cursor.close();
		}
		db.close();
		return image;
	}
	
	/**
	 * 查找一个Person下相关的Image
	 * @param context
	 * @param person
	 * @return
	 * @throws SQLiteException
	 */
	public static List<Image> getImages(Context context, Person person) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Image> images = new ArrayList<Image>();
		String [] culumns = new String [] {"images.img_id as img_id", "images.img as img", "images.url as url", 
				"images.width as width", "images.height as height", };
		Cursor cursor = db.query("images,faces,face_person", 
				culumns, 
				"face_person.face_id = faces.face_id and images.img_id = faces.img_id and face_person.person_id = ?", 
				new String [] {person.getPerson_id(), }	, null, null, null);
		if(cursor != null) {
			String [] names = cursor.getColumnNames();
			for (String string : names) {
				LogUtil.e("DB", string);
			}
			if(cursor.moveToFirst()) {
				Image image = null;
				int id = cursor.getColumnIndex("img_id");
				int img = cursor.getColumnIndex("img");
				int url = cursor.getColumnIndex("url");
				int width = cursor.getColumnIndex("width");
				int height = cursor.getColumnIndex("height");
				do {
					image = new Image();
					image.setImageId(cursor.getString(id));
					image.setImg(cursor.getString(img));
					image.setUrl(cursor.getString(url));
					image.setWidth(cursor.getInt(width));
					image.setHeight(cursor.getInt(height));
					images.add(image);
				} while(cursor.moveToNext());
			}
			cursor.close();
		}
		LogUtil.i("DB", images);
		return images;
	}
	
	/**
	 * 查找所有的Image
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static List<Image> getImages(Context context) throws SQLiteException {
		List<Image> images = new ArrayList<Image>();
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Cursor cursor = db.query("images", null, null, null, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				Image image = null;
				int idIdex = cursor.getColumnIndex("img_id");
				int imgIndex = cursor.getColumnIndex("img");
				int urlIndex = cursor.getColumnIndex("url");
				int widthIndex = cursor.getColumnIndex("width");
				int heightIndex = cursor.getColumnIndex("height");
				do {
					image = new Image();
					image.setImageId(cursor.getString(idIdex));
					image.setImg(cursor.getString(imgIndex));
					image.setUrl(cursor.getString(urlIndex));
					image.setWidth(cursor.getInt(widthIndex));
					image.setHeight(cursor.getInt(heightIndex));
					images.add(image);
				} while(cursor.moveToNext());
			}
			cursor.close();
		}
		db.close();
		return images;
	}
	
	/**
	 * 根据ID删除某一条记录
	 * @param context
	 * @param id
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteImage(Context context, String id) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("images", "img_id = ?", new String [] {id, });
		db.close();
		return count;
	}
	
	/**
	 * 删除指定的Image
	 * @param context
	 * @param images
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteImages(Context context, List<Image> images) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Image image : images) {
			count += db.delete("images", "img_id = ?", new String [] {image.getImageId(), });
		}
		db.close();
		return count;
	}
	
	/**
	 * 删除所有记录
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteImages(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("images", null, null);
		db.close();
		return count;
	}
	
	private static void setValue(ContentValues values, Image image) {
		values.put("img_id", image.getImageId());
		values.put("img", image.getImg());
		values.put("url", image.getUrl());
		values.put("width", image.getWidth());
		values.put("height", image.getHeight());
	}
}
