/*
 * 文件名：DetectActivity.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-17
 * 修改人：xiaoying
 * 修改时间：2013-5-17
 * 版本：v1.0
 */
package com.xiaoying.facedemo.detect;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.xiaoying.facedemo.MainActivity;
import com.xiaoying.facedemo.MainApplication;
import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.db.util.FaceDBUtil;
import com.xiaoying.facedemo.db.util.FacePersonDBUtil;
import com.xiaoying.facedemo.db.util.ImageDBUtil;
import com.xiaoying.facedemo.person.CreatePersonActivity;
import com.xiaoying.facedemo.person.PersonListActivity;
import com.xiaoying.facedemo.utils.BitmapUtil;
import com.xiaoying.facedemo.utils.FileUtil;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.MarkFaceView;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.cliet.Client;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.Image;
import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.request.face.DetectReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.IdentityReq;
import com.xiaoying.faceplusplus.api.entity.request.train.TrainIdentityReq;
import com.xiaoying.faceplusplus.api.entity.response.face.DetectResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonAddFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.recognition.IdentifyResp;
import com.xiaoying.faceplusplus.api.entity.response.recognition.IdentifyResp.Candidate;
import com.xiaoying.faceplusplus.api.entity.response.train.TrainIdentityResp;
import com.xiaoying.faceplusplus.api.service.FaceService;
import com.xiaoying.faceplusplus.api.service.InfoService;
import com.xiaoying.faceplusplus.api.service.PersonService;
import com.xiaoying.faceplusplus.api.service.RecognitionService;
import com.xiaoying.faceplusplus.api.service.TrainService;

/**
 * 功能：人脸识别的Activity
 * @author xiaoying
 *
 */
public class DetectActivity extends Activity {
	
	public static final int REQUEST_PICK_PERSON = 1000;
	
	public static final int REQUEST_CREATE_PERSON = 1001;
	
	private String tag = DetectActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private MarkFaceView mMarkView = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private Bitmap mBitmap = null;
	
	private String mBitmapPath = null;
	
	private Image mImage = new Image();
	
//	private List<Face> mFaces = new ArrayList<Face>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_detect);
		
		initView();
		
		initData();
		
		DisplayMetrics m = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(m);
		
		mBitmap = BitmapUtil.loadBitmap(mBitmapPath, m.widthPixels, m.heightPixels);
		LogUtil.w(tag, "Bitmap size++++>>>(" + mBitmap.getWidth() + ", " + mBitmap.getHeight()  + ")");
		mMarkView.setBitmap(mBitmap);
		
		initProgressDialog();
	}
	
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.tb_title);
		mMarkView = (MarkFaceView) findViewById(R.id.mfv_mark_face);
		mTitleBar.setTitle(R.string.detect_face);
		mTitleBar.setLeftButton(R.string.backe, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mTitleBar.setRightButton(R.string.upload_detect, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				File file = new File(mBitmapPath);
				if(FileUtil.getFileSize(mBitmapPath) < 3 * 1024 * 1024) {
					new DetectFace(MainApplication.CLIENT).execute(file);
				} else {
					Toast.makeText(DetectActivity.this, "图片不能大于3M", Toast.LENGTH_LONG).show();
				}
			}
		});
		mMarkView.setOnFaceClickListener(mFaceClickListener);
	}
	

	MarkFaceView.OnFceClickListener mFaceClickListener = new MarkFaceView.OnFceClickListener() {
		
		@Override
		public void onFaceClicked(Face face, int position) {
			LogUtil.w(tag, "Position++++++++++>>>" + position);
			LogUtil.w(tag, face);
			showOptionMenu(face, position);
		}
	};
	
	private void showOptionMenu(final Face face, final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(DetectActivity.this)
		.setItems(R.array.face_click_menu, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					new IdentifyFace().execute(face);
					break;
				case 1:
					gotoPickPerson(face);
					break;
				case 2:
					break;
				default:
					break;
				}
			}
		});
		builder.create().show();
	}
	
	private void initData() {
		Intent intent = getIntent();
		if(intent.hasExtra(MainActivity.EXTRA_IMAGE)) {
			mBitmapPath = intent.getStringExtra(MainActivity.EXTRA_IMAGE);
			LogUtil.i(tag, "+++>>>" + mBitmapPath);
		} else {
			Toast.makeText(this, R.string.pick_image_err, Toast.LENGTH_SHORT).show();
			finish();
		}
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
	
//	private void gotoCreatePerson() {
//		Intent intent = new Intent(this, CreatePersonActivity.class);
//		intent.putExtra(CreatePersonActivity.EXTRA_MODE, CreatePersonActivity.MODE_CREATE);
//		startActivityForResult(intent, REQUEST_CREATE_PERSON);
//	}
	
	private void gotoPickPerson(Face face) {
		Intent intent = new Intent(this, PersonListActivity.class);
		intent.putExtra(PersonListActivity.EXTRA_MODE, PersonListActivity.MODE_PICK);
		intent.putExtra(PersonListActivity.EXTRA_FACE, face);
		startActivityForResult(intent, REQUEST_PICK_PERSON);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK) {
			switch (requestCode) {
				case REQUEST_PICK_PERSON :
					if(data != null) {
						if(data.hasExtra(PersonListActivity.EXTRA_PERSON) && data.hasExtra(PersonListActivity.EXTRA_FACE)) {
							Person person = (Person) data.getSerializableExtra(PersonListActivity.EXTRA_PERSON);
							Face face = (Face) data.getSerializableExtra(PersonListActivity.EXTRA_FACE);
							PersonAddFaceReq req = new PersonAddFaceReq(person.getPerson_id(), true);
							req.setFace_id(face.getFace_id());
							new AddToPerson(face).execute(req);
						} else if(data.hasExtra(CreatePersonActivity.EXTRA_NEW_PERSON)) {
							Toast.makeText(DetectActivity.this, R.string.add_to_succues, Toast.LENGTH_SHORT).show();
							Person person = (Person) data.getSerializableExtra(CreatePersonActivity.EXTRA_NEW_PERSON);
							Face face = (Face) data.getSerializableExtra(PersonListActivity.EXTRA_FACE);
							FaceDBUtil.insertFace(DetectActivity.this, face);
							ImageDBUtil.insertImageifNeed(DetectActivity.this, mImage);
							FacePersonDBUtil.insertFace(DetectActivity.this, face, person.getPerson_id());
						}
					}
					break;

				default :
					break;
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
	 * 功能：上传人脸进行识别
	 * @author xiaoying
	 *
	 */
	private class DetectFace extends AsyncTask<File, Void, DetectResp> {
		
		private Client mClient = null;
		
		public DetectFace(Client client) {
			this.mClient = client;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getResources().getString(R.string.msg_detecting));
		}
		
		@Override
		protected DetectResp doInBackground(File... params) {
			DetectReq req = new DetectReq(params[0]);
			req.setAsync(false);
			FaceService service = new FaceService(mClient);
			try {
				return service.detect(req);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(DetectResp result) {
			super.onPostExecute(result);
			if(result != null) {
				LogUtil.i(tag, result);
				if(result.getError_code() == RespConfig.RESP_OK) {
					List<Face> faces = result.getFace();
					if(faces.isEmpty()) {
						dismissProgressDialog();
					} else {
						mMarkView.markFaces(faces);
						mImage.setImageId(result.getImg_id());
						mImage.setImg(mBitmapPath);
						mImage.setUrl(result.getUrl());
						mImage.setWidth(result.getImg_width());
						mImage.setHeight(result.getImg_height());
//						ImageDBUtil.insertImage(DetectActivity.this, mImage);
//						new AddFaceToFaceset(faces).execute();
					}
				} else {
					Toast.makeText(DetectActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
				dismissProgressDialog();
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
		
		private Face mmFace = null;
		
		private String mmPersonId = null;
		
		public AddToPerson(Face face) {
			this.mmFace = face;
		}
		
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
				if(result.getError_code() == RespConfig.RESP_OK) {
					if(result.getAdded() > 0) {
						Toast.makeText(DetectActivity.this, R.string.add_to_succues, Toast.LENGTH_SHORT).show();
						FaceDBUtil.insertFace(DetectActivity.this, mmFace);
						ImageDBUtil.insertImageifNeed(DetectActivity.this, mImage);
						FacePersonDBUtil.insertFace(DetectActivity.this, mmFace, mmPersonId);
					} else {
						Toast.makeText(DetectActivity.this, R.string.add_to_fail, Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(DetectActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(DetectActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
	}
	
	/**
	 * 功能：识别
	 * @author xiaoying
	 *
	 */
	private class IdentifyFace extends AsyncTask<Face, Void, List<IdentifyResp.Candidate>> {

		private Face mmFace = null;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.msg_identity));
		}
		
		@Override
		protected List<IdentifyResp.Candidate> doInBackground(Face... params) {
			mmFace = params[0];
			try {
				List<IdentifyResp.Candidate> persons = new ArrayList<IdentifyResp.Candidate>();
				List<Group> groups = getGroups();
				for (Group group : groups) {
					TrainIdentityResp resp = trainIdentity(group);
					if(resp.getError_code() == RespConfig.RESP_OK) {
						List<IdentifyResp.Candidate> candidates = identity(params[0], group);
						List<IdentifyResp.Candidate> sons = new ArrayList<IdentifyResp.Candidate>();
						for (IdentifyResp.Candidate candidate : candidates) {
							LogUtil.e(tag, "Confidence is low ? ++++++++>>> " + (candidate.getConfidence() > 25f));
							if(candidate.getConfidence() > 25f) {
								sons.add(candidate);
							}
						}
						merge(persons, sons);
					}
				}
				Collections.sort(persons, new CandidateComparator());	//排序
				return persons;
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
		protected void onPostExecute(List<IdentifyResp.Candidate> result) {
			super.onPostExecute(result);
			if(result != null) {
				LogUtil.w(tag, result);
//				if(result.isEmpty()) {
//					Toast.makeText(DetectActivity.this, R.string.no_person_identify, Toast.LENGTH_SHORT).show();
//				} else {
					Intent intent = new Intent(DetectActivity.this, IdentifyFaceActivity.class);
					intent.putExtra(IdentifyFaceActivity.EXTRA_PERSON_ARRAY, (Serializable) result);
					intent.putExtra(IdentifyFaceActivity.EXTRA_FACE, mmFace);
					intent.putExtra(IdentifyFaceActivity.EXTRA_IMAGE, mImage);
					startActivity(intent);
//				}
			} else {
				Toast.makeText(DetectActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
		private List<Group> getGroups() throws ClientProtocolException, ParseException, IOException, JSONException {
			InfoService service = new InfoService(MainApplication.CLIENT);
			return service.getGroupList().getGroup();
		}
		
		private TrainIdentityResp trainIdentity(Group group) throws ClientProtocolException, ParseException, IOException, JSONException {
			TrainService service = new TrainService(MainApplication.CLIENT);
			return service.trainIdentity(new TrainIdentityReq(group.getGroup_id(), true));
		}
		
		private List<IdentifyResp.Candidate> identity(Face face, Group group) throws ClientProtocolException, ParseException, IOException, JSONException {
			List<IdentifyResp.Candidate> persons = new ArrayList<IdentifyResp.Candidate>();
			RecognitionService service = new RecognitionService(MainApplication.CLIENT);
			IdentityReq req = new IdentityReq();
			req.setGroup_id(group.getGroup_id());
			req.setKey_face_id(face.getFace_id());
			IdentifyResp resp = service.identity(req);
			List<IdentifyResp.IdentifyFace> identityFaces = resp.getFace();
			for (IdentifyResp.IdentifyFace identityFace : identityFaces) {
				if(face.getFace_id().equals(identityFace.getFace().getFace_id())) {
					persons.addAll(identityFace.getCandidates());
				}
			}
			return persons;
		}
		
		private void merge(List<IdentifyResp.Candidate> parents, List<IdentifyResp.Candidate> sons) {
			if(sons.isEmpty()) {
				return ;
			}
			for (IdentifyResp.Candidate candidate : parents) {
				for (IdentifyResp.Candidate candidate2 : sons) {
					if(candidate.getPerson_id().equals(candidate2.getPerson_id())) {
						sons.remove(candidate2);
					}
				}
			}
			parents.addAll(sons);
		}
	}
	
	
//	/**
//	 * 功能：识别
//	 * @author xiaoying
//	 *
//	 */
//	private class Identify extends AsyncTask<List<Face>, Void, List<IdentifyResp.Candidate>> {
//
////		private Face mmFace = null;
//		
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			showProgressDialog(getString(R.string.msg_identity));
//		}
//		
//		@Override
//		protected List<IdentifyResp.Candidate> doInBackground(List<Face>... params) {
////			mmFace = params[0];
//			try {
////				List<IdentifyResp.Candidate> persons = new ArrayList<IdentifyResp.Candidate>();
//				List<IdentifyResp.IdentifyFace> faceResults = new ArrayList<IdentifyResp.IdentifyFace>();
//				List<Group> groups = getGroups();
//				for (Group group : groups) {
//					trainIdentity(group);
//				}
//				for (Group group : groups) {
//					IdentifyResp resp = identity(params[0], group);
//					faceResults.addAll(resp.getFace());
////					List<IdentityResp.Candidate> candidates = identity(params[0], group);
////					List<IdentityResp.Candidate> sons = new ArrayList<IdentityResp.Candidate>();
////					for (IdentityResp.Candidate candidate : candidates) {
////						LogUtil.e(tag, "Confidence is low ? ++++++++>>> " + (candidate.getConfidence() > 25f));
////						if(candidate.getConfidence() > 25f) {
////							sons.add(candidate);
////						}
////					}
////					merge(persons, sons);
//				}
//				List<IdentifyResp.Candidate> persons = getCandidates(faceResults);
//				Collections.sort(persons, new CandidateComparator());	//排序
//				return persons;
//			} catch (ClientProtocolException e) {
//				e.printStackTrace();
//			} catch (ParseException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(List<IdentifyResp.Candidate> result) {
//			super.onPostExecute(result);
//			if(result != null) {
//				LogUtil.w(tag, result);
////				if(result.isEmpty()) {
////					Toast.makeText(DetectActivity.this, R.string.no_person_identify, Toast.LENGTH_SHORT).show();
////				} else {
//					Intent intent = new Intent(DetectActivity.this, IdentifyFaceActivity.class);
//					intent.putExtra(IdentifyFaceActivity.EXTRA_PERSON_ARRAY, (Serializable) result);
////					intent.putExtra(IdentifyFaceActivity.EXTRA_FACE, mmFace);
//					intent.putExtra(IdentifyFaceActivity.EXTRA_IMAGE, mImage);
//					startActivity(intent);
////				}
//			} else {
//				Toast.makeText(DetectActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
//			}
//			dismissProgressDialog();
//		}
//		
//		private List<Group> getGroups() throws ClientProtocolException, ParseException, IOException, JSONException {
//			InfoService service = new InfoService(MainApplication.CLIENT);
//			return service.getGroupList().getGroup();
//		}
//		
//		private TrainIdentityResp trainIdentity(Group group) throws ClientProtocolException, ParseException, IOException, JSONException {
//			TrainService service = new TrainService(MainApplication.CLIENT);
//			return service.trainIdentity(new TrainIdentityReq(group.getGroup_id(), true));
//		}
//
//		//匹配
//		private IdentifyResp identity(List<Face> faces, Group group) throws ClientProtocolException, ParseException, IOException, JSONException {
//			RecognitionService service = new RecognitionService(MainApplication.CLIENT);
//			IdentityReq req = new IdentityReq();
//			req.setGroup_id(group.getGroup_id());
//			req.setKey_face_id(getFaceIds(faces));
//			IdentifyResp resp = service.identity(req);
//			return resp;
//		}
//		
//		private String getFaceIds(List<Face> faces) {
//			StringBuilder sb = new StringBuilder();
//			for(int i = 0; i < faces.size(); i++) {
//				if(i > 0) {
//					sb.append(",");
//				}
//				sb.append(faces.get(i).getFace_id());
//			}
//			return sb.toString();
//		}
//		
//		private List<IdentifyResp.Candidate> getCandidates(List<IdentifyResp.IdentifyFace> faces) {
//			List<IdentifyResp.Candidate> candidates = new ArrayList<IdentifyResp.Candidate>();
//			for (IdentifyResp.IdentifyFace face : faces) {
////				candidates.addAll(face.getCandidates());
//				merge(candidates, face.getCandidates());
//			}
//			return candidates;
//		}
//		
//		private void merge(List<IdentifyResp.Candidate> parents, List<IdentifyResp.Candidate> sons) {
//			if(sons.isEmpty()) {
//				return ;
//			}
//			for (IdentifyResp.Candidate candidate : parents) {
//				for (IdentifyResp.Candidate candidate2 : sons) {
//					if(candidate.getPerson_id().equals(candidate2.getPerson_id())) {
//						sons.remove(candidate2);
//					}
//				}
//			}
//			parents.addAll(sons);
//		}
//	}
	
	private class CandidateComparator implements Comparator<IdentifyResp.Candidate> {

		@Override
		public int compare(Candidate lhs, Candidate rhs) {
			return -Float.valueOf(lhs.getConfidence()).compareTo(Float.valueOf(rhs.getConfidence()));
		}
		
	}
}
