/*
 * 文件名：PersonListAdapter.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-20
 * 修改人：xiaoying
 * 修改时间：2013-5-20
 * 版本：v1.0
 */
package com.xiaoying.facedemo.person.adapter;

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
import com.xiaoying.faceplusplus.api.entity.Person;

/**
 * 功能：Person适配器
 * @author xiaoying
 */
public class PersonListAdapter extends BaseAdapter {

	private Context mContext = null;
	
	private List<Person> mPersons = new ArrayList<Person>();
	
	private List<Boolean> mChecked = new ArrayList<Boolean>();
	
	private int mMode = PersonListActivity.MODE_VIEW;
	
	public PersonListAdapter(Context context) {
		this.mContext = context;
	}
	
	public PersonListAdapter(Context context, int mode) {
		this.mContext = context;
		this.mMode = mode;
	}
	
	public void add(Person person) {
		mPersons.add(person);
		mChecked.add(false);
		notifyDataSetChanged();
	}
	
	public void add(int position, Person person) {
		mPersons.add(position, person);
		mChecked.add(position, false);
		notifyDataSetChanged();
	}
	
	
	public void addAll(List<Person> persons) {
		mPersons.addAll(persons);
		for (int i = 0; i < persons.size(); i++) {
			mChecked.add(false);
		}
		notifyDataSetChanged();
	}
	
	public void remove(int position) {
		mPersons.remove(position);
		mChecked.remove(position);
		notifyDataSetChanged();
	}
	
	public void replace(int position, Person person) {
		mPersons.remove(position);
		mPersons.add(position, person);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mPersons.clear();
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
	
	public List<Person> getCheckedItems() {
		List<Person> selectedPerson = new ArrayList<Person>();
		for (int i = 0; i < mChecked.size(); i++) {
			if(mChecked.get(i)) {
				selectedPerson.add(mPersons.get(i));
			}
		}
		return selectedPerson;
	}

	@Override
	public int getCount() {
		return mPersons.size();
	}

	@Override
	public Person getItem(int position) {
		return mPersons.get(position);
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
		Person person = getItem(position);
		viewHoder.icon.setImageResource(R.drawable.ic_launcher);
		viewHoder.text1.setText(person.getPerson_name());
		viewHoder.text2.setText(person.getPerson_id());
		viewHoder.text3.setText(person.getTag());
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
