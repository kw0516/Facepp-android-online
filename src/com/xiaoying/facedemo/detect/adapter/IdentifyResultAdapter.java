/*
 * 文件名：IdentifyResultAdapter.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-22
 * 修改人：xiaoying
 * 修改时间：2013-5-22
 * 版本：v1.0
 */
package com.xiaoying.facedemo.detect.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoying.facedemo.R;
import com.xiaoying.faceplusplus.api.entity.response.recognition.IdentifyResp;

/**
 * 功能：人脸识别结果列表适配器
 * @author xiaoying
 */
public class IdentifyResultAdapter extends BaseAdapter {

	private Context mContext = null;
	
	private List<IdentifyResp.Candidate> mReslts = new ArrayList<IdentifyResp.Candidate>();
	
	private OnAddAction mOnAddAction = null;
	
	public IdentifyResultAdapter(Context context) {
		this.mContext = context;
	}
	
	public void setOnAddAction(OnAddAction action) {
		this.mOnAddAction = action;
	}
	
	public void add(IdentifyResp.Candidate obj) {
		mReslts.add(obj);
		notifyDataSetChanged();
	}
	
	public void add(int position, IdentifyResp.Candidate obj) {
		mReslts.add(position, obj);
		notifyDataSetChanged();
	}
	
	
	public void addAll(List<IdentifyResp.Candidate> objs) {
		mReslts.addAll(objs);
		notifyDataSetChanged();
	}
	
	public void remove(int position) {
		mReslts.remove(position);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mReslts.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mReslts.size();
	}

	@Override
	public IdentifyResp.Candidate getItem(int position) {
		return mReslts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHoder viewHoder = null;
		if(convertView == null) {
			viewHoder = new ViewHoder();
			convertView = View.inflate(mContext, R.layout.item_identity_result, null);
			viewHoder.icon = (ImageView) convertView.findViewById(R.id.iv_item_identify_icon);
			viewHoder.text1 = (TextView) convertView.findViewById(R.id.tv_item_identify_text1);
			viewHoder.text2 = (TextView) convertView.findViewById(R.id.tv_item_identify_text2);
			viewHoder.text3 = (TextView) convertView.findViewById(R.id.tv_item_identify_text3);
			viewHoder.button = (Button) convertView.findViewById(R.id.btn_item_identify_add);
			convertView.setTag(viewHoder);
		} else {
			viewHoder = (ViewHoder) convertView.getTag();
		}
		IdentifyResp.Candidate tmp = getItem(position);
		viewHoder.icon.setImageResource(R.drawable.ic_launcher);
		viewHoder.text1.setText(tmp.getPerson_name());
		viewHoder.text2.setText(mContext.getString(R.string.similarity) + ":" +  tmp.getConfidence() + "%");
		viewHoder.text3.setText(tmp.getTag());
		viewHoder.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mOnAddAction != null) {
					mOnAddAction.onAdd(position);
				}
			}
		});
		if(position == 0) {
			convertView.setBackgroundColor(mContext.getResources().getColor(R.color.light_blue));
		} else {
			convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
		}
//		if(tmp.getConfidence() > 50f) {
//			convertView.setBackgroundColor(R.color.light_blue);
//		} else {
//			convertView.setBackgroundColor(R.color.white);
//		}
		return convertView;
	}

	private static class ViewHoder {
		ImageView icon;
		TextView text1;
		TextView text2;
		TextView text3;
		Button button;
	}
	
	public static interface OnAddAction {
		public void onAdd(int position);
	}
}
