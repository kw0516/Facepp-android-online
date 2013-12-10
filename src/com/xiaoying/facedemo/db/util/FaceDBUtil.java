/*
 * 文件名：FaceDBUtil.java
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
import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.PointF;

/**
 * 功能：Face数据操作工具类
 * @author xiaoying
 */
public class FaceDBUtil {
//	db.execSQL("create table if not exists faces(face_id TEXT PRIMARY KEY, face_data TEXT, img_id TEXT, center_x REAL, center_y REAL, " +
//			"eye_left_x REAL, eye_left_y REAL, eye_right_x REAL, eye_right_y REAL, nose_x REAL, nose_y REAL, " +
//			"mouth_left_x REAL, mouth_left_y REAL, mouth_right_x REAL, mouth_right_y REAL, width REAL, height REAL, " +
//			"age_value INTEGER, age_range INTEGER, gender_value TEXT, gender_confidence REAL, race_value TEXT, " +
//			"race_confidence REAL, tag TEXT)");
	
	/**
	 * 插入一条人脸数据
	 * @param context
	 * @param face
	 * @return
	 */
	public static long insertFace(Context context, Face face) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, face);
		long id = db.insert("faces", null, values);
		db.close();
		return id;
	}
	
	/**
	 * 插入多条人脸数据
	 * @param context
	 * @param faces
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertFaces(Context context, List<Face> faces) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		ContentValues values = new ContentValues();
		for (Face face : faces) {
			values.clear();
			setValues(values, face);
			db.insert("faces", null, values);
			count++;
		}
		db.close();
		return count;
	}
	
	/**
	 * 根据ID查寻一张脸
	 * @param context
	 * @param faceId
	 * @return
	 * @throws SQLiteException
	 */
	public static Face getFace(Context context, String faceId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Face face = null;
		Cursor cursor = db.query("faces", null, "face_id = ?", new String [] {faceId, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				face = new Face();
				int [] indexs = getIndexs(cursor);
				getValues(cursor, indexs, face);
			}
			cursor.close();
		}
		db.close();
		return face;
	}
	
	/**
	 * 查询所有的Face
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static List<Face> getFaces(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Face> faces = new ArrayList<Face>();
		Cursor cursor = db.query("faces", null, null, null, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				int [] indexs = getIndexs(cursor);
				Face face = null;
				do {
					face = new Face();
					getValues(cursor, indexs, face);
				} while(cursor.moveToNext());
			}
			cursor.close();
		}
		db.close();
		return faces;
	}
	
	/**
	 * 删除指定ID的Face
	 * @param context
	 * @param faceId
	 * @return
	 */
	public static int deleteFace(Context context, String faceId) {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("faces", "face_id = ?", new String [] {faceId, });
		db.close();
		return count;
	}
	
	/**
	 * 删除指定的多个Face
	 * @param context
	 * @param faces
	 * @return
	 */
	public static int deleteFaces(Context context, List<Face> faces) {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Face face : faces) {
			count += db.delete("faces", "face_id = ?", new String [] {face.getFace_id(), });
		}
		db.close();
		return count;
	}
	
	/**
	 * 删除faces表下所有的Face
	 * @param context
	 * @return
	 */
	public static int deleteFaces(Context context) {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("faces", null, null);
		db.close();
		return count;
	}
	
	private static void getValues(Cursor c, int [] indexs, Face face) {
		face.setFace_id(c.getString(indexs[0]));
		face.setFace_data(c.getString(indexs[1]));
		face.setImg_id(c.getString(indexs[2]));
		face.setCenter(new PointF(c.getFloat(indexs[3]), c.getFloat(indexs[4])));
		face.setEye_left(new PointF(c.getFloat(indexs[5]), c.getFloat(indexs[6])));
		face.setEye_right(new PointF(c.getFloat(indexs[7]), c.getFloat(indexs[8])));
		face.setNose(new PointF(c.getFloat(indexs[9]), c.getFloat(indexs[10])));
		face.setMouth_left(new PointF(c.getFloat(indexs[11]), c.getFloat(indexs[12])));
		face.setMouth_right(new PointF(c.getFloat(indexs[13]), c.getFloat(indexs[14])));
		face.setWidth(c.getFloat(indexs[15]));
		face.setHeight(c.getFloat(indexs[16]));
		Face.Age age = new Face.Age();
		age.setValue(c.getInt(indexs[17]));
		age.setRange(c.getInt(indexs[18]));
		Face.Gender gender = new Face.Gender();
		gender.setValue(c.getString(indexs[19]));
		gender.setConfidence(c.getFloat(indexs[20]));
		Face.Race race = new Face.Race();
		race.setValue(c.getString(indexs[21]));
		race.setConfidence(c.getFloat(indexs[22]));
	}
	
	private static int [] getIndexs(Cursor c) throws SQLiteException {
		int [] indexs = new int[22];
		indexs[0] = c.getColumnIndex("face_id");
		indexs[1] = c.getColumnIndex("face_data");
		indexs[2] = c.getColumnIndex("img_id");
		indexs[3] = c.getColumnIndex("center_x");
		indexs[4] = c.getColumnIndex("center_y");
		indexs[5] = c.getColumnIndex("eye_left_x");
		indexs[6] = c.getColumnIndex("eye_left_y");
		indexs[7] = c.getColumnIndex("eye_right_x");
		indexs[8] = c.getColumnIndex("eye_right_y");
		indexs[9] = c.getColumnIndex("nose_x");
		indexs[10] = c.getColumnIndex("nose_y");
		indexs[11] = c.getColumnIndex("mouth_left_x");
		indexs[12] = c.getColumnIndex("mouth_left_y");
		indexs[13] = c.getColumnIndex("mouth_right_x");
		indexs[14] = c.getColumnIndex("mouth_right_y");
		indexs[15] = c.getColumnIndex("width");
		indexs[16] = c.getColumnIndex("height");
		indexs[17] = c.getColumnIndex("age_value");
		indexs[18] = c.getColumnIndex("age_range");
		indexs[19] = c.getColumnIndex("gender_value");
		indexs[20] = c.getColumnIndex("gender_confidence");
		indexs[21] = c.getColumnIndex("race_value");
		indexs[22] = c.getColumnIndex("race_confidence");
		return indexs;
		
	}
	
	private static void setValues(ContentValues values, Face face) {
		values.put("face_id", face.getFace_id());
		values.put("face_data", face.getFace_data());
		values.put("img_id", face.getImg_id());
		PointF center = face.getCenter();
		values.put("center_x", center.x);
		values.put("center_y", center.y);
		PointF eyeLeft = face.getEye_left();
		values.put("eye_left_x", eyeLeft.x);
		values.put("eye_left_y", eyeLeft.y);
		PointF eyeRight = face.getEye_right();
		values.put("eye_right_x", eyeRight.x);
		values.put("eye_right_y", eyeRight.y);
		PointF nose = face.getNose();
		values.put("nose_x", nose.x);
		values.put("nose_y", nose.y);
		PointF mouthLeft = face.getMouth_left();
		values.put("mouth_left_x", mouthLeft.x);
		values.put("mouth_left_y", mouthLeft.y);
		PointF mouthRight = face.getMouth_right();
		values.put("mouth_right_x", mouthRight.x);
		values.put("mouth_right_y", mouthRight.y);
		values.put("width", face.getWidth());
		values.put("height", face.getHeight());
		Face.Age age = face.getAge();
		values.put("age_value", age.value);
		values.put("age_range", age.range);
		Face.Gender gender = face.getGender();
		values.put("gender_value", gender.value);
		values.put("gender_confidence", gender.confidence);
		Face.Race race = face.getRace();
		values.put("race_value", race.value);
		values.put("race_confidence", race.confidence);
	}
}
