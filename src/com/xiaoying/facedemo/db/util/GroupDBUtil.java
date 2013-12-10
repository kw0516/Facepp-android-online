/*
 * 文件名：GroupDBUtilDBUtil.java
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
import com.xiaoying.faceplusplus.api.entity.Group;

/**
 * 功能：Groups表数据操作工具类
 * @author xiaoying
 */
public class GroupDBUtil {

	/**
	 * 插入一条Group数据
	 * @param context
	 * @param group
	 * @return
	 * @throws SQLiteException
	 */
	public static long insertGroup(Context context, Group group) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		long id = insertGroup(db, group);
		db.close();
		return id;
	}
	
	private static long insertGroup(SQLiteDatabase db, Group group) throws SQLiteException {
		ContentValues values = new ContentValues();
		setValues(values, group);
		return db.insert("groups", null, values);
	}
	
	/**
	 * 插入多个Group数据
	 * @param context
	 * @param groups
	 * @return
	 * @throws SQLiteException
	 */
	public static int insertGroups(Context context, List<Group> groups) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = insertGroups(db, groups);
		db.close();
		return count;
	}
	
	private static int insertGroups(SQLiteDatabase db, List<Group> groups) throws SQLiteException {
		int count = 0;
		ContentValues values = new ContentValues();
		for (Group group : groups) {
			values.clear();
			setValues(values, group);
			db.insert("groups", null, values);
			count++;
		}
		return count;
	}
	
	/**
	 * 删除一个Group数据
	 * @param context
	 * @param groupId
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteGroup(Context context, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("groups", "group_id = ?", new String [] {groupId, });
		db.close();
		return count;
	}
	
	/**
	 * 删除多个Group数据
	 * @param context
	 * @param groups
	 * @return
	 * @throws SQLiteException
	 */
	public static int deletePersons(Context context, List<Group> groups) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = 0;
		for (Group group : groups) {
			count += db.delete("groups", "groups_id = ?", new String [] {group.getGroup_id(), });
		}
		db.close();
		return count;
	}
	
	/**
	 * 删除Groups表下的所有数据
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static int deleteGroups(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = db.delete("groups", null, null);
		db.close();
		return count;
	}
	
	/**
	 * 根据ID查询一个Person数据
	 * @param context
	 * @param groupId
	 * @return
	 * @throws SQLiteException
	 */
	public static Group getGroupById(Context context, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Group group = null;
		Cursor cursor = db.query("groups", null, "group_id = ?", new String [] {groupId, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				group = new Group();
				group.setGroup_id(cursor.getString(cursor.getColumnIndex("group_id")));
				group.setGroup_name(cursor.getString(cursor.getColumnIndex("group_name")));
				group.setTag(cursor.getString(cursor.getColumnIndex("tag")));
			}
			cursor.close();
		}
		db.close();
		return group;
	}

	/**
	 * 根据Group_name查找Group
	 * @param context
	 * @param groupName
	 * @return
	 * @throws SQLiteException
	 */
	public static Group getGroupByName(Context context, String groupName) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Group group = null;
		Cursor cursor = db.query("groups", null, "group_name = ?", new String [] {groupName, }, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				group = new Group();
				group.setGroup_id(cursor.getString(cursor.getColumnIndex("group_id")));
				group.setGroup_name(cursor.getString(cursor.getColumnIndex("group_name")));
				group.setTag(cursor.getString(cursor.getColumnIndex("tag")));
			}
			cursor.close();
		}
		db.close();
		return group;
	}
	
	/**
	 * 查询groups表下所有的group
	 * @param context
	 * @return
	 * @throws SQLiteException
	 */
	public static List<Group> getGroups(Context context) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		List<Group> groups = new ArrayList<Group>();
		Cursor cursor = db.query("groups", null, null, null, null, null, null);
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				Group group = null;
				int idIndex = cursor.getColumnIndex("group_id");
				int nameIndex = cursor.getColumnIndex("group_name");
				int tagIndex = cursor.getColumnIndex("tag");
				do {
					group = new Group();
					group.setGroup_id(cursor.getString(idIndex));
					group.setGroup_name(cursor.getString(nameIndex));
					group.setTag(cursor.getString(tagIndex));
					groups.add(group);
				} while(cursor.moveToNext());
			}
			cursor.close();
		}
		db.close();
		return groups;
	}
	
	/**
	 * 更新一条Group数据
	 * @param context
	 * @param group
	 * @return
	 * @throws SQLiteException
	 */
	public static int updateGroup(Context context, Group group) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = updateGroup(db, group);
		db.close();
		return count;
	}
	
	private static int updateGroup(SQLiteDatabase db, Group group) throws SQLiteException {
		ContentValues values = new ContentValues();
		setValues(values, group);
		return db.update("groups", values, "group_id = ?", new String [] {group.getGroup_id(), });
	}
	
	/**
	 * 更新多条Group数据
	 * @param context
	 * @param groups
	 * @return
	 * @throws SQLiteException
	 */
	public static int updateGroups(Context context, List<Group> groups) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		int count = updateGroups(db, groups);
		db.close();
		return count;
	}
	
	private static int updateGroups(SQLiteDatabase db, List<Group> groups) throws SQLiteException {
		ContentValues values = new ContentValues();
		int count = 0;
		for (Group group : groups) {
			values.clear();
			setValues(values, group);
			count += db.update("groups", values, "group_id = ?", new String [] {group.getGroup_id(), });
		}
		return count;
	}
	
	/**
	 * 更新或者删除一条数据
	 * @param context
	 * @param group
	 * @return
	 * @throws SQLiteException
	 */
	public static long updateOrInsertGroup(Context context, Group group) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		long count = 0;
		if(containsGroup(db, group)) {
			count = updateGroup(db, group);
		} else {
			count = insertGroup(db, group);
		}
		return count;
	}

	/**
	 * 更新或者删除多个Group数据
	 * @param context
	 * @param groups
	 * @return
	 * @throws SQLiteException
	 */
	public static long updateOrInsertGroups(Context context, List<Group> groups) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		long count = 0;
		for (Group group : groups) {
			if(containsGroup(db, group)) {
				count = updateGroup(db, group);
			} else {
				count = insertGroup(db, group);
			}
		}
		return count;
	}
	
	/**
	 * 是否包含该数据
	 * @param db
	 * @return
	 * @throws SQLiteException
	 */
	private static boolean containsGroup(SQLiteDatabase db, Group group) throws SQLiteException {
		Cursor cursor = db.query("groups", null, "group_id = ?", new String [] {group.getGroup_id(), }, null, null, null);
		return cursor != null && cursor.moveToFirst();
	}
	
	/**
	 * 查询某个Person下包含的person个数
	 * @param context
	 * @param groupId
	 * @return
	 * @throws SQLiteException
	 */
	public static int getPersonCount(Context context, String groupId) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Cursor cursor = db.query("groups", new String [] {"person_count", }, "group_id = ?", 
				new String [] {groupId, }, null, null, null);
		int count = 0;
		if(cursor != null) {
			if(cursor.moveToFirst()) {
				count = cursor.getInt(cursor.getColumnIndex("person_count"));
			}
			cursor.close();
		}
		db.close();
		return count;
	}
	
	/**
	 * 设置Person下face个数
	 * @param context
	 * @param groupId
	 * @param personCount
	 * @return
	 * @throws SQLiteException
	 */
	public static int setPersonCount(Context context, String groupId, int personCount) throws SQLiteException {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("person_count", personCount);
		int count = db.update("groups", values, "group_id = ?", new String [] {groupId, });
		db.close();
		return count;
	}
	
	private static void setValues(ContentValues values, Group group) {
		values.put("group_id", group.getGroup_id());
		values.put("group_name", group.getGroup_name());
		values.put("tag", group.getTag());
	}
}
