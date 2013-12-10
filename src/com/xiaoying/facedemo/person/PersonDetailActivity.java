/*
 * 文件名：PersonDetailActivity.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-20
 * 修改人：xiaoying
 * 修改时间：2013-5-20
 * 版本：v1.0
 */
package com.xiaoying.facedemo.person;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.db.util.ImageDBUtil;
import com.xiaoying.facedemo.person.adapter.ImageAdapter;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.entity.Image;
import com.xiaoying.faceplusplus.api.entity.Person;

/**
 * 功能：Person详情
 * @author xiaoying
 */
public class PersonDetailActivity extends Activity {
	
	private String tag = PersonDetailActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private TextView mTvName = null;
	
	private TextView mTvTag = null;
	
	private GridView mGvPics = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private Person mPerson = null;
	
	private ImageAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_detail);
		
		initData();
		
		initView();
		
		new GetImages().execute(mPerson);
	}
	
	private void initData() {
		Intent intent = getIntent();
		if(intent.hasExtra(PersonListActivity.EXTRA_PERSON)) {
			mPerson = (Person) intent.getSerializableExtra(PersonListActivity.EXTRA_PERSON);
			LogUtil.w(tag, mPerson);
		} else {
			Toast.makeText(PersonDetailActivity.this, R.string.sys_err, Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.tb_person_detail);
		mTvName = (TextView) findViewById(R.id.tv_person_detail_name);
		mTvTag = (TextView) findViewById(R.id.tv_person_detail_tag);
		mGvPics = (GridView) findViewById(R.id.gv_person_detail_pics);
		mAdapter = new ImageAdapter(this);
		mGvPics.setAdapter(mAdapter);
		mTitleBar.setLeftButton(R.string.backe, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mTitleBar.setTitle(mPerson.getPerson_name());
		mTitleBar.setRightButtonVisible(false);
		mTvName.setText(mPerson.getPerson_name());
		mTvTag.setText(mPerson.getTag());
	}
	

	private void initProgressDialog() {
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(true);
	}
	
	private void showProgressDialog(CharSequence message) {
		if(mProgressDialog == null) {
			initProgressDialog();
		}
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
	}
	
	private void dismissProgressDialog() {
		if(mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private class GetImages extends AsyncTask<Person, Void, List<Image>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.msg_getting_data));
		}
		
		@Override
		protected List<Image> doInBackground(Person... params) {
			return ImageDBUtil.getImages(PersonDetailActivity.this, params[0]);
		}
		
		@Override
		protected void onPostExecute(List<Image> result) {
			super.onPostExecute(result);
			if(result != null) {
				mAdapter.addAll(result);
			}
			dismissProgressDialog();
		}
		
	}
}
