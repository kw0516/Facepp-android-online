/*
 * 文件名：FacesetListAdapter.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-21
 * 修改人：xiaoying
 * 修改时间：2013-5-21
 * 版本：v1.0
 */

package com.xiaoying.facedemo.faceset.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoying.facedemo.R;
import com.xiaoying.faceplusplus.api.entity.Faceset;

/**
 * 功能：Faceset列表适配器
 * @author xiaoying
 *
 */
public class FacesetListAdapter extends BaseAdapter {

	private Context mContext = null;
	
	private List<Faceset> mFacesets = new ArrayList<Faceset>();
	
	public FacesetListAdapter(Context context) {
		this.mContext = context;
	}
	
	public void add(Faceset faceset) {
		mFacesets.add(faceset);
		notifyDataSetChanged();
	}
	
	public void addAll(List<Faceset> facesets) {
		mFacesets.addAll(facesets);
		notifyDataSetChanged();
	}
	
	public void remove(int position) {
		mFacesets.remove(position);
		notifyDataSetChanged();
	}
	
	public void remove(Object object) {
		mFacesets.remove(object);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mFacesets.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mFacesets.size();
	}

	@Override
	public Faceset getItem(int position) {
		return mFacesets.get(position);
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
			convertView.setTag(viewHoder);
		} else {
			viewHoder = (ViewHoder) convertView.getTag();
		}
		Faceset faceset = getItem(position);
		viewHoder.icon.setImageResource(R.drawable.ic_launcher);
		viewHoder.text1.setText(faceset.getFaceset_name());
		viewHoder.text2.setText(faceset.getFaceset_id());
		viewHoder.text3.setText(faceset.getTag());
		return convertView;
	}

	private static class ViewHoder {
		ImageView icon;
		TextView text1;
		TextView text2;
		TextView text3;
	}
}
