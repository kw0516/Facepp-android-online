/*
 * 文件名：FacesetCreateActivity.java
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
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoying.facedemo.MainApplication;
import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.entity.Faceset;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetSetInfoResp;
import com.xiaoying.faceplusplus.api.service.FacesetService;

/**
 * 功能：创建Faceset
 * @author xiaoying
 *
 */
public class CreateFacesetActivity extends Activity {
	
	public static final String EXTRA_NEW_FACESET = "new_faceset";
	
	public static final String EXTRA_OLD_FACESET = "old_faceset";
	
	public static final String EXTRA_MODE = "mode";
	
	public static final int MODE_CREATE = 1;
	
	public static final int MODE_MODIFY = 2;
	
	private String tag = CreateFacesetActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private EditText mEtName = null;
	
	private EditText mEtTag = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_faceset);
		
		Intent intent = getIntent();
		
		if(intent.hasExtra(EXTRA_MODE)) {
			int mode = intent.getIntExtra(EXTRA_MODE, MODE_CREATE);
			initView(mode);
		} else {
			Toast.makeText(this, R.string.sys_err, Toast.LENGTH_SHORT).show();
			finish();
		}
		
		initProgressDialog();
	}
	
	private void initView(final int mode) {
		mTitleBar = (TitleBar) findViewById(R.id.tb_create_faceset);
		if(mode == MODE_CREATE) {
			mTitleBar.setTitle(R.string.create_faceset);
		} else if(mode == MODE_MODIFY) {
			mTitleBar.setTitle(R.string.modify_faceset);
		}
		mTitleBar.setLeftButton(R.string.cancel, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cancel();
			}
		});
		mTitleBar.setRightButton(R.string.submit, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				submit(mode);
			}
		});
		mEtName = (EditText) findViewById(R.id.et_create_faceset_name);
		mEtTag = (EditText) findViewById(R.id.et_create_faceset_tag);
	}
	
	private void submit(int mode) {
		if(mode == MODE_CREATE) {
			Faceset faceset = new Faceset();
			faceset.setFaceset_name(mEtName.getText().toString());
			faceset.setTag(mEtTag.getText().toString());
			new CreateFaceset().execute(faceset);
		} else if(mode == MODE_MODIFY) {
			Faceset faceset = (Faceset) getIntent().getSerializableExtra(EXTRA_OLD_FACESET);
			new ModifyFaceset().execute(faceset);
		}
	}
	
	private void cancel() {
		setResult(RESULT_CANCELED);
		finish();
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
			cancel();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private class CreateFaceset extends AsyncTask<Faceset, Void, FacesetCreateResp> {

		private FacesetService mmService = new FacesetService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected FacesetCreateResp doInBackground(Faceset... params) {
			try {
				Faceset faceset = params[0];
				FacesetCreateReq req = new FacesetCreateReq(faceset.getFaceset_name());
				req.setTag(faceset.getTag());
				return mmService.createFaceset(req);
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
		protected void onPostExecute(FacesetCreateResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					LogUtil.w(tag, result);
					Faceset faceset = new Faceset();
					faceset.setFaceset_id(result.getFaceset_id());
					faceset.setFaceset_name(result.getFaceset_name());
					faceset.setTag(result.getTag());
					Intent data = new Intent();
					data.putExtra(EXTRA_NEW_FACESET, faceset);
					setResult(RESULT_OK, data);
					finish();
				} else {
					Toast.makeText(CreateFacesetActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(CreateFacesetActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
	}
	
	/**
	 * 功能：修改Faceset信息
	 * @author xiaoying
	 *
	 */
	private class ModifyFaceset extends AsyncTask<Faceset, Void, FacesetSetInfoResp> {
		
		private FacesetService mmService = new FacesetService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected FacesetSetInfoResp doInBackground(Faceset... params) {
			try {
				Faceset faceset = params[0];
				FacesetSetInfoReq req = new FacesetSetInfoReq(faceset.getFaceset_id(), true);
				req.setFaceset_name(faceset.getFaceset_name());
				req.setTag(faceset.getTag());
				return mmService.setFacesetInfo(req);
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
		protected void onPostExecute(FacesetSetInfoResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					LogUtil.w(tag, result);
					Faceset faceset = new Faceset();
					faceset.setFaceset_id(result.getFaceset_id());
					faceset.setFaceset_name(result.getFaceset_name());
					faceset.setTag(result.getTag());
					Intent data = new Intent();
					data.putExtra(EXTRA_NEW_FACESET, faceset);
					setResult(RESULT_OK, data);
					finish();
				} else {
					Toast.makeText(CreateFacesetActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(CreateFacesetActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
	}
}
