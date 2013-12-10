/*
 * 文件名：IdentifyFaceActivity.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-22
 * 修改人：xiaoying
 * 修改时间：2013-5-22
 * 版本：v1.0
 */

package com.xiaoying.facedemo.detect;

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
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaoying.facedemo.MainApplication;
import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.db.util.FaceDBUtil;
import com.xiaoying.facedemo.db.util.FacePersonDBUtil;
import com.xiaoying.facedemo.db.util.ImageDBUtil;
import com.xiaoying.facedemo.detect.adapter.IdentifyResultAdapter;
import com.xiaoying.facedemo.person.CreatePersonActivity;
import com.xiaoying.facedemo.person.PersonListActivity;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.Image;
import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonAddFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.recognition.IdentifyResp;
import com.xiaoying.faceplusplus.api.service.PersonService;

/**
 * 功能：人脸识别
 * @author xiaoying
 *
 */
public class IdentifyFaceActivity extends Activity {
	
	public static final String EXTRA_PERSON_ARRAY = "person_array";

	public static final String EXTRA_FACE = "face";
	
	public static final String EXTRA_IMAGE = "image";
	
	public static final int REQUEST_PICK_PERSON = 1000;
	
	private String tag = IdentifyFaceActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private ListView mListView = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private IdentifyResultAdapter mAdapter = null;
	
	private Face mFace = null;
	
	private Image mImage = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_identify_face);
		
		initView();
		
		initData();
	}
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.tb_indentity_face_title);
		mListView = (ListView) findViewById(R.id.lv_indentity_face_list);
		mAdapter = new IdentifyResultAdapter(this);
		mListView.setAdapter(mAdapter);
		mTitleBar.setLeftButton(R.string.backe, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mTitleBar.setTitle(R.string.identify_result);
		mTitleBar.setRightButtonVisible(false);
		mAdapter.setOnAddAction(new IdentifyResultAdapter.OnAddAction() {
			@Override
			public void onAdd(int position) {
				if(mFace != null) {
					IdentifyResp.Candidate candidate = mAdapter.getItem(position);
					PersonAddFaceReq req = new PersonAddFaceReq(candidate.getPerson_id(), true);
					req.setFace_id(mFace.getFace_id());
					new AddToPerson().execute(req);
				}
			}
		});
		findViewById(R.id.btn_indentity_face_add_to_person).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoPickPerson();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void initData() {
		Intent intent = getIntent();
		if(intent.hasExtra(EXTRA_PERSON_ARRAY) && intent.hasExtra(EXTRA_FACE) && intent.hasExtra(EXTRA_IMAGE)) {
			List<IdentifyResp.Candidate> datas = (List<IdentifyResp.Candidate>) intent.getSerializableExtra(EXTRA_PERSON_ARRAY);
			if(datas.isEmpty()) {
				Toast.makeText(IdentifyFaceActivity.this, R.string.no_person_identify, Toast.LENGTH_SHORT).show();
			}
			mAdapter.addAll(datas);
			mFace = (Face) intent.getSerializableExtra(EXTRA_FACE);
			mImage = (Image) intent.getSerializableExtra(EXTRA_IMAGE);
		} else {
			Toast.makeText(this, R.string.sys_err, Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	

	private void gotoPickPerson() {
		Intent intent = new Intent(this, PersonListActivity.class);
		intent.putExtra(PersonListActivity.EXTRA_MODE, PersonListActivity.MODE_PICK);
		intent.putExtra(PersonListActivity.EXTRA_FACE, mFace);
		startActivityForResult(intent, REQUEST_PICK_PERSON);
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK && requestCode == REQUEST_PICK_PERSON) {
			if(data != null) {
				Person person = null;
				if(data.hasExtra(PersonListActivity.EXTRA_PERSON)) {
					person = (Person) data.getSerializableExtra(PersonListActivity.EXTRA_PERSON);
					PersonAddFaceReq req = new PersonAddFaceReq();
					req.setPerson_id(person.getPerson_id());
					req.setFace_id(mFace.getFace_id());
					new AddToPerson().execute(req);
				} else if(data.hasExtra(CreatePersonActivity.EXTRA_NEW_PERSON)) {
					person = (Person) data.getSerializableExtra(CreatePersonActivity.EXTRA_NEW_PERSON);
					Toast.makeText(IdentifyFaceActivity.this, R.string.add_to_succues, Toast.LENGTH_SHORT).show();
					FaceDBUtil.insertFace(IdentifyFaceActivity.this, mFace);
					ImageDBUtil.insertImageifNeed(IdentifyFaceActivity.this, mImage);
					FacePersonDBUtil.insertFace(IdentifyFaceActivity.this, mFace, person.getPerson_id());
					finish();
				}
			}
		}
	}
	
	/**
	 * 功能：把人脸添加到Person中
	 * @author xiaoying
	 *
	 */
	private class AddToPerson extends AsyncTask<PersonAddFaceReq, Void, PersonAddFaceResp> {

		private PersonService mmService = new PersonService(MainApplication.CLIENT);
		
		private String mmPersonId = null;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected PersonAddFaceResp doInBackground(PersonAddFaceReq... params) {
			mmPersonId = params[0].getPerson_id();
			
			try {
				return mmService.addFace(params[0]);
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
		protected void onPostExecute(PersonAddFaceResp result) {
			super.onPostExecute(result);
			if(result != null) {
				LogUtil.i(tag, result);
				if(result.getError_code() == RespConfig.RESP_OK) {
					if(result.getAdded() > 0) {
						Toast.makeText(IdentifyFaceActivity.this, R.string.add_to_succues, Toast.LENGTH_SHORT).show();
						FaceDBUtil.insertFace(IdentifyFaceActivity.this, mFace);
						ImageDBUtil.insertImageifNeed(IdentifyFaceActivity.this, mImage);
						FacePersonDBUtil.insertFace(IdentifyFaceActivity.this, mFace, mmPersonId);
					} else {
						Toast.makeText(IdentifyFaceActivity.this, R.string.add_to_fail, Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(IdentifyFaceActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(IdentifyFaceActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
	}
	
}
