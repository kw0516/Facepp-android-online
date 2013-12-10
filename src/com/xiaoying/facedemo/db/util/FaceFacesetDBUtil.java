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
 * 功能：把Face添加到Faceset数据操作工具类
 * @author xiaoying
 */
public class FaceFacesetDBUtil {

	/**
	 * 添加一个face到Facesets表中
	 * @param context
	 * @param face
	 * @param facesetId
	 * @return
	 */
	public static long insertFace(Context context, Face face, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, face, facesetId);
		long id = db.insert("face_faceset", null, values);
		db.close();
		return id;
		
	}
	
	/**
	 * 添加多个Face
	 * @param context
	 * @param faces
	 * @param facesetId
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertFaces(Context context, List<Face> faces, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		int count = 0;
		for (Face face : faces) {
			values.clear();
			setValues(values, face, facesetId);
			db.insert("face_faceset", null, values);
			count++;
		}
		db.close();
		return count;
	}
	
	/**
	 * 从Facesets表中删除一个
	 * @param context
	 * @param face
	 * @param facesetId
	 * @return
	 */
	public static long deleteFace(Context context, Face face, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		long id = db.delete("face_faceset", "faceset_id = ? AND face_id = ?", new String [] {facesetId, face.getFace_id(), });
		db.close();
		return id;
		
	}
	
	/**
	 * 从facsets删除多个Face
	 * @param context
	 * @param faces
	 * @param facesetId
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteFaces(Context context, List<Face> faces, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Face face : faces) {
			db.delete("face_faceset", "faceset_id = ? AND face_id = ?", new String [] {facesetId, face.getFace_id(), });
			count++;
		}
		db.close();
		return count;
	}
	
	
	public static List<Face> getFacesById(Context context, String facesetId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Face> faces = new ArrayList<Face>();
		db.close();
		return faces;
	}
	
	public static List<Face> getFacesByName(Context context, String facesetName) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Face> faces =  new ArrayList<Face>();
		db.close();
		return faces;
	}
	
	private static void setValues(ContentValues values, Face face, String facesetId) {
		values.put("face_id", face.getFace_id());
		values.put("faceset_id", facesetId);
	}
}
