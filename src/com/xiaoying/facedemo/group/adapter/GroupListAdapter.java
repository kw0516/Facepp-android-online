/*
 * 文件名：GroupListAdapter.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-20
 * 修改人：xiaoying
 * 修改时间：2013-5-20
 * 版本：v1.0
 */
package com.xiaoying.facedemo.group.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.person.PersonListActivity;
import com.xiaoying.faceplusplus.api.entity.Group;

/**
 * 功能：Group列表适配器
 * @author xiaoying
 */
public class GroupListAdapter extends BaseAdapter {
	
	private Context mContext = null;
	
	private List<Group> mGroups = new ArrayList<Group>();
	
	private List<Boolean> mChecked = new ArrayList<Boolean>();
	
	private int mMode = PersonListActivity.MODE_VIEW;
	
	public GroupListAdapter(Context context) {
		this.mContext = context;
	}
	
	public GroupListAdapter(Context context, int mode) {
		this.mContext = context;
		this.mMode = mode;
	}
	
	public void add(Group group) {
		mGroups.add(group);
		mChecked.add(false);
		notifyDataSetChanged();
	}
	
	public void add(int position, Group group) {
		mGroups.add(position, group);
		mChecked.add(position, false);
		notifyDataSetChanged();
	}
	
	
	public void addAll(List<Group> groups) {
		mGroups.addAll(groups);
		for (int i = 0; i < groups.size(); i++) {
			mChecked.add(false);
		}
		notifyDataSetChanged();
	}
	
	public void remove(int position) {
		mGroups.remove(position);
		mChecked.remove(position);
		notifyDataSetChanged();
	}
	
	public void replace(int position, Group group) {
		mGroups.remove(position);
		mGroups.add(position, group);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mGroups.clear();
		mChecked.clear();
		notifyDataSetChanged();
	}
	
	public void setChecked(int position, boolean isChecked) {
		mChecked.remove(position);
		mChecked.add(position, isChecked);
		notifyDataSetChanged();
	}
	
	public boolean isChecked(int position) {
		return mChecked.get(position);
	}
	
	public List<Group> getCheckedItems() {
		List<Group> selectedItems = new ArrayList<Group>();
		for (int i = 0; i < mChecked.size(); i++) {
			if(mChecked.get(i)) {
				selectedItems.add(mGroups.get(i));
			}
		}
		return selectedItems;
	}

	@Override
	public int getCount() {
		return mGroups.size();
	}

	@Override
	public Group getItem(int position) {
		return mGroups.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoder viewHoder = null;
		if(convertView == null) {
			viewHoder = new ViewHoder();
			convertView = View.inflate(mContext, R.layout.item_list, null);
			viewHoder.icon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			viewHoder.text1 = (TextView) convertView.findViewById(R.id.tv_item_text1);
			viewHoder.text2 = (TextView) convertView.findViewById(R.id.tv_item_text2);
			viewHoder.text3 = (TextView) convertView.findViewById(R.id.tv_item_text3);
			viewHoder.check = (CheckBox) convertView.findViewById(R.id.cb_item_checked);
			convertView.setTag(viewHoder);
		} else {
			viewHoder = (ViewHoder) convertView.getTag();
		}
		Group group = getItem(position);
		viewHoder.icon.setImageResource(R.drawable.ic_launcher);
		viewHoder.text1.setText(group.getGroup_name());
		viewHoder.text2.setText(group.getGroup_id());
		viewHoder.text3.setText(group.getTag());
		if(mMode == PersonListActivity.MODE_CHOOSE) {
			viewHoder.check.setVisibility(View.VISIBLE);
			viewHoder.check.setChecked(mChecked.get(position));
		} else {
			viewHoder.check.setVisibility(View.GONE);
		}
		return convertView;
	}

	private static class ViewHoder {
		ImageView icon;
		TextView text1;
		TextView text2;
		TextView text3;
		CheckBox check;
	}
}
