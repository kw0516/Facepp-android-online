/*
 * 文件名：FacesetDBUtil.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-18
 * 修改人：xiaoying
 * 修改时间：2013-5-18
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
import com.xiaoying.faceplusplus.api.entity.Faceset;

/**
 * 功能：facesets数据表数据操作工具类
 * @author xiaoying
 */
public class FacesetDBUtil {
//	create table if not exists facesets(faceset_id TEXT PRIMARY KEY, faceset_name TEXT, tag TEXT, face_count INTEGER)
	
	/**
	 * 插入一条faceset数据
	 * @param context
	 * @param faceset
	 * @return
	 * @throws SQLiteException
	 */
	public static long insertFaceset(Context context, Faceset faceset) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, faceset);
		long id = db.insert("facesets", null, values);
		db.close();
		return id;
	}
	
	/**
	 * 插入多个faceset
	 * @param context
	 * @param facesets
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertFacesets(Context context, List<Faceset> facesets) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		ContentValues values = new ContentValues();
		for (Faceset faceset : facesets) {
			values.clear();
			setValues(values, faceset);
			db.insert("facesets", null, values);
			count++;
		}
		db.close();
		return count;
	}
	
	/**
	 * 根据ID查询一个Faceset
	 * @param context
	 * @param facesetId
	 * @return
	 * @throws SQLiteException
	 */
	public static Faceset getFacesetById(Context context, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Faceset faceset = null;
		Cursor cursor = db.query("facesets", null, "faceset_id = ?", new String [] {facesetId, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				faceset = new Faceset();
				faceset.setFaceset_id(cursor.getString(cursor.getColumnIndex("faceset_id")));
				faceset.setFaceset_name(cursor.getString(cursor.getColumnIndex("faceset_name")));
				faceset.setTag(cursor.getString(cursor.getColumnIndex("tag")));
			}
			cursor.close();
		}
		db.close();
		return faceset;
	}
	
	/**
	 * 根据Faceset_name查找某个Faceset
	 * @param context
	 * @param facesetName
	 * @return
	 * @throws SQLiteException
	 */
	public static Faceset getFacesetByName(Context context, String facesetName) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Faceset faceset = null;
		Cursor cursor = db.query("facesets", null, "faceset_name = ?", new String [] {facesetName, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				faceset = new Faceset();
				faceset.setFaceset_id(cursor.getString(cursor.getColumnIndex("faceset_id")));
				faceset.setFaceset_name(cursor.getString(cursor.getColumnIndex("faceset_name")));
				faceset.setTag(cursor.getString(cursor.getColumnIndex("tag")));
			}
			cursor.close();
		}
		db.close();
		return faceset;
	}
	
	/**
	 * 查询所有的Faceset
	 * @param context
	 * @return
	 */
	public static List<Faceset> getFacesets(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Faceset> facesets = new ArrayList<Faceset>();
		Faceset faceset = null;
		Cursor cursor = db.query("facesets", null, null, null, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				int idIndex = cursor.getColumnIndex("faceset_id");
				int nameIndex = cursor.getColumnIndex("faceset_name");
				int tagIndex = cursor.getColumnIndex("tag");
				do {
					faceset = new Faceset();
					faceset.setFaceset_id(cursor.getString(idIndex));
					faceset.setFaceset_name(cursor.getString(nameIndex));
					faceset.setTag(cursor.getString(tagIndex));
					facesets.add(faceset);
				} while(cursor.moveToNext());
			}
			cursor.close();
		}
		db.close();
		return facesets;
	}

	/**
	 * 根据关键字后模糊查找相关的Faceset列表
	 * @param context
	 * @param keyName
	 * @return
	 * @throws SQLiteException
	 */
	public static List<Faceset> getFacesetsByKeyName(Context context, String keyName) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Faceset> facesets = new ArrayList<Faceset>();
		Faceset faceset = null;
		Cursor cursor = db.query("facesets", null, "faceset_name like ?", new String [] {keyName + "%", }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				int idIndex = cursor.getColumnIndex("faceset_id");
				int nameIndex = cursor.getColumnIndex("faceset_name");
				int tagIndex = cursor.getColumnIndex("tag");
				do {
					faceset = new Faceset();
					faceset.setFaceset_id(cursor.getString(idIndex));
					faceset.setFaceset_name(cursor.getString(nameIndex));
					faceset.setTag(cursor.getString(tagIndex));
					facesets.add(faceset);
				} while(cursor.moveToNext());
			}
			cursor.close();
		}
		db.close();
		return facesets;
	}
	
	/**
	 * 根据ID删除一个Faceset
	 * @param context
	 * @param facesetId
	 * @return
	 * @throws SQLiteException
	 */
	public static long deleteFaceset(Context context, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		long id = db.delete("facesets", "faceset_id = ?", new String [] {facesetId, });
		db.close();
		return id;
	}
	
	/**
	 * 删除多个指定的Faceset
	 * @param context
	 * @param facesets
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteFacesets(Context context, List<Faceset> facesets) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Faceset faceset : facesets) {
			db.delete("facesets", "faceset_id = ?", new String [] {faceset.getFaceset_id(), });
		}
		db.close();
		return count;
	}
	
	/**
	 * 删除所有的Faceset数据
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteFacesets(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("facesets", null, null);
		db.close();
		return count;
	}
	
	/**
	 * 更新一个Faceset
	 * @param context
	 * @param faceset
	 * @return
	 * @throws SQLiteException
	 */
	public static int alterFaceset(Context context, Faceset faceset) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, faceset);
		int count = db.update("facesets", values, "faceset_id = ?", new String [] {faceset.getFaceset_id(), });
		db.close();
		return count;
	}
	
	/**
	 * 获取某个Faceset下的Face个数
	 * @param context
	 * @param facesetId
	 * @return
	 * @throws SQLiteException
	 */
	public static int getFaceCount(Context context, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		int count = 0;
		Cursor cursor = db.query("facesets", null, "faceset_id = ?", new String [] {facesetId, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				count = cursor.getInt(cursor.getColumnIndex("face_count"));
			}
			cursor.close();
		}
		cursor.close();
		return count;
	}
	
	/**
	 * 设置某个Faceset的Face个数
	 * @param context
	 * @param facesetId
	 * @param count
	 * @throws SQLiteException
	 */
	public static int setFaceCount(Context context, String facesetId, int count) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("face_count", count);
		int updateCount = db.update("facesets", values, "faceset_id = ?", new String[] {facesetId, });
		db.close();
		return updateCount;
	}
	
	private static void setValues(ContentValues values, Faceset faceset) {
		values.put("faceset_id", faceset.getFaceset_id());
		values.put("faceset_name", faceset.getFaceset_name());
		values.put("tag", faceset.getTag());
		values.put("face_count", 0);
	}
}
