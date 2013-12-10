/*
 * 文件名：PersonDBUtil.java
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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.xiaoying.facedemo.db.DBHelper;
import com.xiaoying.faceplusplus.api.entity.Person;

/**
 * 功能：Person表数据操作工具类
 * @author xiaoying
 */
public class PersonDBUtil {

	/**
	 * 插入一条Persn数据
	 * @param context
	 * @param person
	 * @return
	 * @throws SQLiteException
	 */
	public static long insertPerson(Context context, Person person) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, person);
		long id = db.insert("persons", null, values);
		db.close();
		return id;
	}
	
	/**
	 * 插入多个Person数据
	 * @param context
	 * @param persons
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertPersons(Context context, List<Person> persons) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		ContentValues values = new ContentValues();
		for (Person person : persons) {
			values.clear();
			setValues(values, person);
			db.insert("persons", null, values);
			count++;
		}
		db.close();
		return count;
	}
	
	/**
	 * 删除一个Person数据
	 * @param context
	 * @param personId
	 * @return
	 * @throws SQLiteException
	 */
	public static int deletePerson(Context context, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("persons", "person_id = ?", new String [] {personId, });
		db.close();
		return count;
	}
	
	/**
	 * 删除多个Person数据
	 * @param context
	 * @param persons
	 * @return
	 * @throws SQLiteException
	 */
	public static int deletePersons(Context context, List<Person> persons) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Person person : persons) {
			count += db.delete("persons", "person_id = ?", new String [] {person.getPerson_id(), });
		}
		db.close();
		return count;
	}
	
	/**
	 * 删除Persons表下的所有数据
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static int deletePersons(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("persons", null, null);
		db.close();
		return count;
	}
	
	/**
	 * 根据ID查询一个Person数据
	 * @param context
	 * @param personId
	 * @return
	 * @throws SQLiteException
	 */
	public static Person getPersonById(Context context, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Person person = null;
		Cursor cursor = db.query("persons", null, "person_id = ?", new String [] {personId, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				person = new Person();
				person.setPerson_id(cursor.getString(cursor.getColumnIndex("person_id")));
				person.setPerson_name(cursor.getString(cursor.getColumnIndex("person_name")));
				person.setTag(cursor.getString(cursor.getColumnIndex("tag")));
			}
			cursor.close();
		}
		db.close();
		return person;
	}
	
	/**
	 * 根据perons_name查询Person
	 * @param context
	 * @param personName
	 * @return
	 * @throws SQLiteException
	 */
	public static Person getPersonByName(Context context, String personName) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Person person = null;
		Cursor cursor = db.query("persons", null, "person_name = ?", new String [] {personName, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				person = new Person();
				person.setPerson_id(cursor.getString(cursor.getColumnIndex("person_id")));
				person.setPerson_name(cursor.getString(cursor.getColumnIndex("person_name")));
				person.setTag(cursor.getString(cursor.getColumnIndex("tag")));
			}
			cursor.close();
		}
		db.close();
		return person;
	}
	
	/**
	 * 查询persons表下所有的Person
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static List<Person> getPersons(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Person> persons = new ArrayList<Person>();
		Cursor cursor = db.query("persons", null, null, null, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				Person person = null;
				int idIndex = cursor.getColumnIndex("person_id");
				int nameIndex = cursor.getColumnIndex("person_name");
				int tagIndex = cursor.getColumnIndex("tag");
				do {
					person = new Person();
					person.setPerson_id(cursor.getString(idIndex));
					person.setPerson_name(cursor.getString(nameIndex));
					person.setTag(cursor.getString(tagIndex));
					persons.add(person);
				} while(cursor.moveToNext());
			}
			cursor.close();
		}
		db.close();
		return persons;
	}
	
	/**
	 * 更新一条Person数据
	 * @param context
	 * @param person
	 * @return
	 * @throws SQLiteException
	 */
	public static int updatePerson(Context context, Person person) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		setValues(values, person);
		int count = db.update("persons", values, "person_id = ?", new String [] {person.getPerson_id(), });
		db.close();
		return count;
	}
	
	/**
	 * 更新多条Person数据
	 * @param context
	 * @param persons
	 * @return
	 * @throws SQLiteException
	 */
	public static int updatePersons(Context context, List<Person> persons) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		int count = 0;
		for (Person person : persons) {
			values.clear();
			setValues(values, person);
			count += db.update("persons", values, "person_id = ?", new String [] {person.getPerson_id(), });
		}
		db.close();
		return count;
	}
	
	/**
	 * 查询某个Person下包含的person个数
	 * @param context
	 * @param personId
	 * @return
	 * @throws SQLiteException
	 */
	public static int getFaceCount(Context context, String personId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Cursor cursor = db.query("persons", new String [] {"face_count", }, "person_id = ?", 
				new String [] {personId, }, null, null, null);
		int count = 0;
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				count = cursor.getInt(cursor.getColumnIndex("face_count"));
			}
			cursor.close();
		}
		db.close();
		return count;
	}
	
	/**
	 * 设置Person下face个数
	 * @param context
	 * @param personId
	 * @param faceCount
	 * @return
	 * @throws SQLiteException
	 */
	public static int setFaceCount(Context context, String personId, int faceCount) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("face_count", faceCount);
		int count = db.update("persons", values, "person_id = ?", new String [] {personId, });
		db.close();
		return count;
	}
	
	private static void setValues(ContentValues values, Person person) {
		values.put("person_id", person.getPerson_id());
		values.put("person_name", person.getPerson_name());
		values.put("tag", person.getTag());
	}
}
