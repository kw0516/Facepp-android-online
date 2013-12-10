/*
 * 文件名：TitleBar.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-17
 * 修改人：xiaoying
 * 修改时间：2013-5-17
 * 版本：v1.0
 */

package com.xiaoying.facedemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoying.facedemo.R;

/**
 * 功能：顶部标题栏
 * @author xiaoying
 *
 */
public class TitleBar extends RelativeLayout {
	
	private Button mBtnLeft = null;
	
	private TextView mTvCenter = null;
	
	private Button mBtnRight = null;
	
	public TitleBar(Context context) {
		super(context);
		initView(context);
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		View view = View.inflate(context, R.layout.layout_top_title, null);
		mBtnLeft = (Button) view.findViewById(R.id.btn_top_left);
		mTvCenter = (TextView) view.findViewById(R.id.tv_top_center);
		mBtnRight = (Button) view.findViewById(R.id.btn_top_right);
		addView(view, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
	}
	
	
	public void setTitle(CharSequence text) {
		mTvCenter.setText(text);
	}
	
	public void setTitle(int resid) {
		mTvCenter.setText(resid);
	}
	
	public void setLeftButtonText(CharSequence text) {
		mBtnLeft.setText(text);
	}
	
	public void setLeftButtonText(int resid) {
		mBtnLeft.setText(resid);
	}
	
	public void setLeftButtonIcon(int resid) {
		mBtnLeft.setBackgroundResource(resid);
	}
	
	public void setLeftButtonListener(View.OnClickListener listener) {
		mBtnLeft.setOnClickListener(listener);
	}
	
	public void setLeftButton(CharSequence text, View.OnClickListener listener) {
		setLeftButtonText(text);
		setLeftButtonListener(listener);
	}
	
	public void setLeftButton(int resid, View.OnClickListener listener) {
		setLeftButtonText(resid);
		setLeftButtonListener(listener);
	}

	public void setRightButtonText(CharSequence text) {
		mBtnRight.setText(text);
	}
	
	public void setRightButtonText(int resid) {
		mBtnRight.setText(resid);
	}
	
	public void setRightButtonIcon(int resid) {
		mBtnRight.setBackgroundResource(resid);
	}
	
	public void setRightButtonListener(View.OnClickListener listener) {
		mBtnRight.setOnClickListener(listener);
	}
	
	public void setRightButton(CharSequence text, View.OnClickListener listener) {
		setRightButtonText(text);
		setRightButtonListener(listener);
	}
	
	public void setRightButton(int resid, View.OnClickListener listener) {
		setRightButtonText(resid);
		setRightButtonListener(listener);
	}
	
	public void setLeftButtonVisible(boolean isVisible) {
		mBtnLeft.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
	}
	
	public void setRightButtonVisible(boolean isVisible) {
		mBtnRight.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
	}
	
}
