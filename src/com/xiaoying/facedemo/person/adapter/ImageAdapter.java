/*
 * 文件名：ImageAdapter.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-23
 * 修改人：xiaoying
 * 修改时间：2013-5-23
 * 版本：v1.0
 */

package com.xiaoying.facedemo.person.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xiaoying.facedemo.MainApplication;
import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.utils.ImageLoadTask;
import com.xiaoying.faceplusplus.api.entity.Image;

/**
 * 功能：
 * @author xiaoying
 *
 */
public class ImageAdapter extends BaseAdapter {

private Context mContext;
	
	private List<Image> mImages = new ArrayList<Image>();
	
	private static final int NUM_OF_COLUMN = 3;
	
	private int mRequiteWidth = MainApplication.mScreenWidth / NUM_OF_COLUMN;

	public ImageAdapter(Context context) {
		this.mContext = context;
	}
	
	public void add(Image image) {
		mImages.add(image);
		notifyDataSetChanged();
	}
	
	public void addAll(List<Image> images) {
		mImages.addAll(images);
		notifyDataSetChanged();
	}
	
	public void remove(int position) {
		mImages.remove(position);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mImages.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mImages.size();
	}

	@Override
	public Image getItem(int position) {
		return mImages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoder viewHoder = null;
		if(null == convertView) {
			viewHoder = new ViewHoder();
			convertView = View.inflate(mContext, R.layout.item_image_list, null);
			viewHoder.image = (ImageView) convertView.findViewById(R.id.iv_image);
			convertView.setLayoutParams(new AbsListView.LayoutParams(mRequiteWidth, mRequiteWidth));
			convertView.setTag(viewHoder);
		} else {
			viewHoder = (ViewHoder) convertView.getTag();
		}
		viewHoder.image.setTag(position);
		viewHoder.image.setImageResource(R.drawable.ic_launcher);
		Image tmp = getItem(position);
		new ImageLoadTask(position, viewHoder.image, mRequiteWidth).execute(tmp);
		return convertView;
	}
	
	private class ViewHoder {
		ImageView image;
	}
}
