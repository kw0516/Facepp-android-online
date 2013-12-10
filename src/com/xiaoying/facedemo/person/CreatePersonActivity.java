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
package com.xiaoying.facedemo.person;

import java.io.IOException;
import java.util.ArrayList;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaoying.facedemo.MainApplication;
import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.db.util.PersonDBUtil;
import com.xiaoying.facedemo.detect.IdentifyFaceActivity;
import com.xiaoying.facedemo.group.GroupListActivity;
import com.xiaoying.facedemo.person.adapter.AddGroupAdapter;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonSetInfoResp;
import com.xiaoying.faceplusplus.api.service.PersonService;

/**
 * 功能：创建Person
 * @author xiaoying
 */
public class CreatePersonActivity extends Activity {

	public static final String EXTRA_NEW_PERSON = "new_person";
	
	public static final String EXTRA_OLD_PERSON = "old_person";
	
	public static final String EXTRA_MODE = "mode";
	
	public static final int REQUEST_CHOOSE_GROUP = 1000;
	
	public static final int MODE_CREATE = 1;
	
	public static final int MODE_MODIFY = 2;
	
	private String tag = CreatePersonActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private EditText mEtName = null;
	
	private EditText mEtTag = null;
	
	private Button mBtnChoose = null;
	
	private ListView mLvChoosedGroup = null;
	
	private AddGroupAdapter mAdapter = null;
	
	private int mPosition = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_person);
		
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
		mTitleBar = (TitleBar) findViewById(R.id.tb_create_person);
		mEtName = (EditText) findViewById(R.id.et_create_person_name);
		mEtTag = (EditText) findViewById(R.id.et_create_person_tag);
		mBtnChoose = (Button) findViewById(R.id.btn_add_to_group);
		mLvChoosedGroup = (ListView) findViewById(R.id.lv_add_to_group_list);
		mAdapter = new AddGroupAdapter(this);
		mLvChoosedGroup.setAdapter(mAdapter);
		if(mode == MODE_CREATE) {
			findViewById(R.id.ll_add_to_group_content).setVisibility(View.VISIBLE);
			mTitleBar.setTitle(R.string.create_pserson);
		} else if(mode == MODE_MODIFY) {
			findViewById(R.id.ll_add_to_group_content).setVisibility(View.GONE);
			mTitleBar.setTitle(R.string.modify_person);
			Intent intent = getIntent();
			if(intent.hasExtra(EXTRA_OLD_PERSON)) {
				mPosition = intent.getIntExtra(PersonListActivity.EXTRA_POSITION, -1);
				Person person = (Person) intent.getSerializableExtra(EXTRA_OLD_PERSON);
				mEtName.setText(person.getPerson_name());
				mEtTag.setText(person.getTag());
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
		mBtnChoose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoChooseGroup();
			}
		});
	}
	
	private void gotoChooseGroup() {
		Intent intent = new Intent(this, GroupListActivity.class);
		intent.putExtra(GroupListActivity.EXTRA_MODE, GroupListActivity.MODE_CHOOSE);
		startActivityForResult(intent, REQUEST_CHOOSE_GROUP);
	}
	
	private void submit(int mode) {
		if(mode == MODE_CREATE) {
			if(mAdapter.getData().isEmpty()) {
				Toast.makeText(this, R.string.need_chooose_group, Toast.LENGTH_SHORT).show();
			} else {
				PersonCreateReq req = new PersonCreateReq();
				req.setPerson_name(mEtName.getText().toString());
				req.setTag(mEtTag.getText().toString());
				req.setGroup_id(getAddedGroupIds());
				if(getIntent().hasExtra(IdentifyFaceActivity.EXTRA_FACE)) {
					Face face = (Face) getIntent().getSerializableExtra(IdentifyFaceActivity.EXTRA_FACE);
					req.setFace_id(face.getFace_id());
				}
				new CreatePerson().execute(req);
			}
		} else if(mode == MODE_MODIFY) {
			Person person = (Person) getIntent().getSerializableExtra(EXTRA_OLD_PERSON);
			person.setPerson_name(mEtName.getText().toString());
			person.setTag(mEtTag.getText().toString());
			new ModifyPerson().execute(person);
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
	
	/**
	 * 去除已选的Group
	 * @param newGroups
	 * @param oldGroups
	 */
	private List<Group> handleRepeat(List<Group> groups) {
		List<Group> addedGroup = mAdapter.getData();
		if(addedGroup.isEmpty()) {
			return groups;
		}
		List<Group> result = new ArrayList<Group>(groups);
		for (Group group : groups) {
			for (Group g : addedGroup) {
				if(g.getGroup_id().equals(group.getGroup_id())) {
					result.remove(group);
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取加入的组ID，多个用逗号隔开
	 * @return
	 */
	private String getAddedGroupIds() {
		List<Group> groups = mAdapter.getData();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < groups.size(); i++) {
			if(i > 0) {
				sb.append(",");
			}
			sb.append(groups.get(i).getGroup_id());
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK && requestCode == REQUEST_CHOOSE_GROUP) {
			if(data != null && data.hasExtra(GroupListActivity.EXTRA_GROUP_ARRAY)) {
				mAdapter.addAll(handleRepeat((List<Group>) data.getSerializableExtra(GroupListActivity.EXTRA_GROUP_ARRAY)));
			}
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
	 * 新建一个Person
	 * @author xiaoying
	 *
	 */
	private class CreatePerson extends AsyncTask<PersonCreateReq, Void, PersonCreateResp> {

		private PersonService mmService = new PersonService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected PersonCreateResp doInBackground(PersonCreateReq... params) {
			try {
				return mmService.createPerson(params[0]);
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
		protected void onPostExecute(PersonCreateResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					LogUtil.w(tag, result);
					Person person = new Person();
					person.setPerson_id(result.getPerson_id());
					person.setPerson_name(result.getPerson_name());
					person.setTag(result.getTag());
					PersonDBUtil.insertPerson(CreatePersonActivity.this, person);
					Intent data = getIntent();
					data.putExtra(EXTRA_NEW_PERSON, person);
					setResult(RESULT_OK, data);
					finish();
				} else {
					Toast.makeText(CreatePersonActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(CreatePersonActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
	}
	
	/**
	 * 功能：修改Person信息
	 * @author xiaoying
	 *
	 */
	private class ModifyPerson extends AsyncTask<Person, Void, PersonSetInfoResp> {
		
		private PersonService mmService = new PersonService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected PersonSetInfoResp doInBackground(Person... params) {
			Person person = params[0];
			try {
				PersonSetInfoReq req = new PersonSetInfoReq(person.getPerson_id(), true);
				req.setName(person.getPerson_name());
				req.setTag(person.getTag());
				return mmService.setPersonInfo(req);
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
		protected void onPostExecute(PersonSetInfoResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					LogUtil.w(tag, result);
					Person person = new Person();
					person.setPerson_id(result.getPerson_id());
					person.setPerson_name(result.getPerson_name());
					person.setTag(result.getTag());
					Intent data = new Intent();
					data.putExtra(EXTRA_NEW_PERSON, person);
					data.putExtra(PersonListActivity.EXTRA_POSITION, mPosition);
					setResult(RESULT_OK, data);
					finish();
				} else {
					Toast.makeText(CreatePersonActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(CreatePersonActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
	}
}
