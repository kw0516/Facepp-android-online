/*
 * 文件名：CreatePersonActivity.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-20
 * 修改人：xiaoying
 * 修改时间：2013-5-20
 * 版本：v1.0
 */
package com.xiaoying.facedemo.group;

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
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupSetInfoResp;
import com.xiaoying.faceplusplus.api.service.GroupService;

/**
 * 功能：创建Group
 * @author xiaoying
 */
public class CreateGroupActivity extends Activity {
	
	public static final String EXTRA_NEW_GROUP = "new_group";
	
	public static final String EXTRA_OLD_GROUP= "old_group";
	
	public static final String EXTRA_MODE = "mode";
	
	public static final int MODE_CREATE = 1;
	
	public static final int MODE_MODIFY = 2;
	
	private String tag = CreateGroupActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private EditText mEtName = null;
	
	private EditText mEtTag = null;
	
	private int mPosition = -1;
	
	private Group mOldGroup = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_group);
		

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
		mTitleBar = (TitleBar) findViewById(R.id.tb_create_group);
		mEtName = (EditText) findViewById(R.id.et_create_group_name);
		mEtTag = (EditText) findViewById(R.id.et_create_group_tag);
		if(mode == MODE_CREATE) {
			mTitleBar.setTitle(R.string.create_group);
		} else if(mode == MODE_MODIFY) {
			mTitleBar.setTitle(R.string.modify_group);
			Intent intent = getIntent();
			if(intent.hasExtra(EXTRA_OLD_GROUP)) {
				mPosition = intent.getIntExtra(GroupListActivity.EXTRA_POSITION, -1);
				mOldGroup = (Group) intent.getSerializableExtra(EXTRA_OLD_GROUP);
				mEtName.setText(mOldGroup.getGroup_name());
				mEtTag.setText(mOldGroup.getTag());
			} else {
				Toast.makeText(this, R.string.sys_err, Toast.LENGTH_SHORT).show();
				finish();
			}
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
	}
	
	private void submit(int mode) {
		if(mode == MODE_CREATE) {
			Group group = new Group();
			group.setGroup_name(mEtName.getText().toString());
			group.setTag(mEtTag.getText().toString());
			new CreateGroup().execute(group);
		} else if(mode == MODE_MODIFY) {
			mOldGroup.setGroup_name(mEtName.getText().toString());
			mOldGroup.setTag(mEtTag.getText().toString());
			new ModifyGroup().execute(mOldGroup);
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
	
	/**
	 * 功能：新建一个Group
	 * @author xiaoying
	 *
	 */
	private class CreateGroup extends AsyncTask<Group, Void, GroupCreateResp> {

		private GroupService mmService = new GroupService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected GroupCreateResp doInBackground(Group... params) {
			try {
				Group group = params[0];
				GroupCreateReq req = new GroupCreateReq(group.getGroup_name());
				req.setTag(group.getTag());
				return mmService.createGroup(req);
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
		protected void onPostExecute(GroupCreateResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					Group group = new Group();
					group.setGroup_id(result.getGroup_id());
					group.setGroup_name(result.getGroup_name());
					group.setTag(result.getTag());
					Intent data = new Intent();
					data.putExtra(EXTRA_NEW_GROUP, group);
					setResult(RESULT_OK, data);
					finish();
				} else {
					Toast.makeText(CreateGroupActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(CreateGroupActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
	}
	
	/**
	 * 功能：修改Group信息
	 * @author xiaoying
	 *
	 */
	private class ModifyGroup extends AsyncTask<Group, Void, GroupSetInfoResp> {
		
		private GroupService mmService = new GroupService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected GroupSetInfoResp doInBackground(Group... params) {
			Group group = params[0];
			try {
				GroupSetInfoReq req = new GroupSetInfoReq(group.getGroup_id(), true);
				req.setName(group.getGroup_name());
				req.setTag(group.getTag());
				return mmService.setGroupInfo(req);
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
		protected void onPostExecute(GroupSetInfoResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					LogUtil.w(tag, result);
					Group group = new Group();
					group.setGroup_id(result.getGroup_id());
					group.setGroup_name(result.getGroup_name());
					group.setTag(result.getTag());
					Intent data = new Intent();
					data.putExtra(EXTRA_NEW_GROUP, group);
					data.putExtra(GroupListActivity.EXTRA_POSITION, mPosition);
					setResult(RESULT_OK, data);
					finish();
				} else {
					Toast.makeText(CreateGroupActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(CreateGroupActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
	}
}
