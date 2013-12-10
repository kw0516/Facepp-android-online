/*
 * 文件名：FacesetListActivity.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-21
 * 修改人：xiaoying
 * 修改时间：2013-5-21
 * 版本：v1.0
 */

package com.xiaoying.facedemo.faceset;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaoying.facedemo.MainApplication;
import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.faceset.adapter.FacesetListAdapter;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.entity.Faceset;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetFacesetListResp;
import com.xiaoying.faceplusplus.api.service.InfoService;

/**
 * 功能：Faceset列表
 * @author xiaoying
 *
 */
public class FacesetListActivity extends Activity {

	public static final int MODE_VIEW = 1;
	
	public static final int MODE_PICK = 2;
	
	public static final String EXTRA_MODE = "mode";
	
	public static final int REQUEST_CREATE_FACESET = 1000;
	
	private String tag = FacesetListActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private ListView mListView = null;
	
	private FacesetListAdapter mAdapter = null;

	private ProgressDialog mProgressDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faceset_list);
	
		initView();
		
		new GetFacesets().execute();
	}
	
	private void initView() {
		
		mTitleBar = (TitleBar) findViewById(R.id.tb_faceset_list_title);
		
		mTitleBar.setLeftButton(R.string.backe, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mTitleBar.setRightButton(R.string.create, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoCreateFaceset();
			}
		});
		
		mListView = (ListView) findViewById(R.id.lv_faceset_list);
		mAdapter = new FacesetListAdapter(this);
		mListView.setAdapter(mAdapter);
		
		initProgressDialog();
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
	
	private void gotoCreateFaceset() {
		Intent intent = new Intent(this, CreateFacesetActivity.class);
		intent.putExtra(CreateFacesetActivity.EXTRA_MODE, CreateFacesetActivity.MODE_CREATE);
		startActivityForResult(intent, REQUEST_CREATE_FACESET);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_CREATE_FACESET && resultCode == RESULT_OK) {
			if(data != null && data.hasExtra(CreateFacesetActivity.EXTRA_NEW_FACESET)) {
				mAdapter.add((Faceset) data.getSerializableExtra(CreateFacesetActivity.EXTRA_NEW_FACESET));
			}
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
	
	/**
	 * 功能：获取Faceset列表
	 * @author xiaoying
	 *
	 */
	private class GetFacesets extends AsyncTask<Void, Void, InfoGetFacesetListResp> {

		private InfoService mmService = new InfoService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.msg_getting_data));
		}
		
		@Override
		protected InfoGetFacesetListResp doInBackground(Void... params) {
			
			try {
				return mmService.getFacsetList();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(InfoGetFacesetListResp result) {
			super.onPostExecute(result);
			if(result != null) {
				LogUtil.w(tag, result);
				if(result.getError_code() == RespConfig.RESP_OK) {
					List<Faceset> faceset = result.getFaceset();
					if(faceset != null && !faceset.isEmpty()) {
						mAdapter.addAll(faceset);
					} else {
						Toast.makeText(FacesetListActivity.this, R.string.no_data, Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(FacesetListActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(FacesetListActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
	}

}
