/*
 * 文件名：FacePersonDBUtil.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-19
 * 修改人：xiaoying
 * 修改时间：2013-5-19
 * 版本：v1.0
 */
package com.xiaoying.facedemo.db.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.xiaoying.facedemo.db.DBHelper;
import com.xiaoying.faceplusplus.api.entity.Face;

/**
 * 功能：把Face添加到Person数据操作工具类
 * @author xiaoying
 */
public class FacePersonDBUtil {

	/**
	 * 添加一个face到Persons表中
	 * @param context
	 * @param face
	 * @param personId
	 * @return
	 */
	public static long insertFace(Context context, Face face, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, face, personId);
		long id = db.insert("face_person", null, values);
		db.close();
		return id;
		
	}
	
	/**
	 * 添加多个Face
	 * @param context
	 * @param faces
	 * @param personId
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertFaces(Context context, List<Face> faces, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		int count = 0;
		for (Face face : faces) {
			values.clear();
			setValues(values, face, personId);
			db.insert("face_person", null, values);
			count++;
		}
		db.close();
		return count;
	}
	
	/**
	 * 从Persons表中删除一个
	 * @param context
	 * @param face
	 * @param personId
	 * @return
	 */
	public static long deleteFace(Context context, Face face, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		long id = db.delete("face_person", "person_id = ? AND face_id = ?", new String [] {personId, face.getFace_id(), });
		db.close();
		return id;
		
	}
	
	/**
	 * 从Person删除多个Face
	 * @param context
	 * @param faces
	 * @param personId
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteFaces(Context context, List<Face> faces, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Face face : faces) {
			db.delete("face_person", "person_id = ? AND face_id = ?", new String [] {personId, face.getFace_id(), });
			count++;
		}
		db.close();
		return count;
	}
	
	
	public static List<Face> getFacesById(Context context, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Face> faces = new ArrayList<Face>();
		db.close();
		return faces;
	}
	
	public static List<Face> getFacesByName(Context context, String personName) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Face> faces =  new ArrayList<Face>();
		db.close();
		return faces;
	}
	
	private static void setValues(ContentValues values, Face face, String personId) {
		values.put("face_id", face.getFace_id());
		values.put("person_id", personId);
	}
}
