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
import com.xiaoying.faceplusplus.api.entity.Person;

/**
 * 功能：把Person添加到Group数据操作工具类
 * @author xiaoying
 */
public class PersonGroupDBUtil {

	/**
	 * 添加一个person到Group表中
	 * @param context
	 * @param person
	 * @param groupId
	 * @return
	 */
	public static long insertPerson(Context context, Person person, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, person, groupId);
		long id = db.insert("person_group", null, values);
		db.close();
		return id;
		
	}
	
	/**
	 * 添加多个Person
	 * @param context
	 * @param persons
	 * @param groupId
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertFaces(Context context, List<Person> persons, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		int count = 0;
		for (Person person : persons) {
			values.clear();
			setValues(values, person, groupId);
			db.insert("person_group", null, values);
			count++;
		}
		db.close();
		return count;
	}
	
	/**
	 * 从Group表中删除一个Person
	 * @param context
	 * @param person
	 * @param groupId
	 * @return
	 */
	public static long deleteFace(Context context, Person person, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		long id = db.delete("face_faceset", "faceset_id = ? AND face_id = ?", new String [] {groupId, person.getPerson_id(), });
		db.close();
		return id;
		
	}
	
	/**
	 * 从Group删除多个Person
	 * @param context
	 * @param persons
	 * @param groupId
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteFaces(Context context, List<Person> persons, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Person person : persons) {
			db.delete("face_faceset", "faceset_id = ? AND face_id = ?", new String [] {groupId, person.getPerson_id(), });
			count++;
		}
		db.close();
		return count;
	}
	
	
	public static List<Person> getPersonssById(Context context, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Person> persons = new ArrayList<Person>();
		db.close();
		return persons;
	}
	
	public static List<Person> getFacesByName(Context context, String groupName) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Person> persons =  new ArrayList<Person>();
		db.close();
		return persons;
	}
	
	private static void setValues(ContentValues values, Person person, String groupId) {
		values.put("face_id", person.getPerson_id());
		values.put("faceset_id", groupId);
	}
}
