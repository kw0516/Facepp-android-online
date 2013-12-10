/*
 * 文件名：PersonListActivity.java
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
import java.io.Serializable;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaoying.facedemo.MainApplication;
import com.xiaoying.facedemo.R;
import com.xiaoying.facedemo.detect.IdentifyFaceActivity;
import com.xiaoying.facedemo.group.GroupListActivity;
import com.xiaoying.facedemo.person.adapter.PersonListAdapter;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupAddPersonReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonDeleteReq;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupAddPersonResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetPersonListResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonDeleteResp;
import com.xiaoying.faceplusplus.api.service.GroupService;
import com.xiaoying.faceplusplus.api.service.InfoService;
import com.xiaoying.faceplusplus.api.service.PersonService;

/**
 * 功能：Person列表
 * @author xiaoying
 */
public class PersonListActivity extends Activity {

	public static final int MODE_VIEW = 1;
	
	public static final int MODE_PICK = 2;
	
	public static final int MODE_CHOOSE = 3;
	
	public static final String EXTRA_MODE = "mode";
	
	public static final String EXTRA_PERSON = "person";
	
	public static final String EXTRA_PERSON_ARRAY = "person_array";
	
	public static final String EXTRA_FACE = "face";
	
	public static final String EXTRA_POSITION = "position";
	
	public static final int REQUEST_CREATE_PERSON = 1000;
	
	public static final int REQUEST_MODIFY_PREDON = 1001;
	
	public static final int REQUEST_PICK_GROUP = 1002;
	
	private String tag = PersonListActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private ListView mListView = null;
	
	private PersonListAdapter mAdapter = null;

	private ProgressDialog mProgressDialog = null;
	
	private int mMode = MODE_VIEW;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);
	
		mMode = getIntent().getIntExtra(EXTRA_MODE, MODE_VIEW);
		
		initView();
		
		new GetPersons().execute();
	}
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.tb_person_list_title);
		mListView = (ListView) findViewById(R.id.lv_person_list);
		
		mTitleBar.setLeftButton(R.string.backe, mLeftClick);
		if(mMode == MODE_VIEW) {
			mTitleBar.setTitle(R.string.person_manager);
//			mTitleBar.setRightButton(R.string.create, new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					gotoCreatePerson();
//				}
//			});
			mTitleBar.setRightButtonVisible(false);
			mAdapter = new PersonListAdapter(this);
			mListView.setOnItemClickListener(mViewItemClick);
			mListView.setOnItemLongClickListener(mItemLongClick);
		} else if(mMode == MODE_PICK) {
			mTitleBar.setTitle(R.string.choose_person);
			mTitleBar.setRightButton(R.string.create, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					gotoCreatePerson();
				}
			});
//			mTitleBar.setRightButtonVisible(false);
			mAdapter = new PersonListAdapter(this, MODE_PICK);
			mListView.setOnItemClickListener(mPicItemClick);
		} else if(mMode == MODE_CHOOSE) {
			mTitleBar.setTitle(R.string.choose_person);
			mTitleBar.setRightButton(R.string.ok, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LogUtil.e(tag, mAdapter.getCheckedItems());
					Intent data = new Intent();
					data.putExtra(EXTRA_PERSON_ARRAY, (Serializable) mAdapter.getCheckedItems());
					setResult(RESULT_OK, data);
					finish();
				}
			});
			mAdapter = new PersonListAdapter(this, MODE_CHOOSE);
			mListView.setOnItemClickListener(mChooseItemClick);
		}
		
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
	
	private void gotoCreatePerson() {
		Intent intent = new Intent(this, CreatePersonActivity.class);
		intent.putExtra(CreatePersonActivity.EXTRA_MODE, CreatePersonActivity.MODE_CREATE);
		if(getIntent().hasExtra(IdentifyFaceActivity.EXTRA_FACE)) {
			intent.putExtra(IdentifyFaceActivity.EXTRA_FACE, getIntent().getSerializableExtra(IdentifyFaceActivity.EXTRA_FACE));
		}
		startActivityForResult(intent, REQUEST_CREATE_PERSON);
	}
	
	private void gotoPickGroup(int position) {
		Intent intent = new Intent(this, GroupListActivity.class);
		intent.putExtra(GroupListActivity.EXTRA_MODE, GroupListActivity.MODE_PICK);
		intent.putExtra(GroupListActivity.EXTRA_POSITION, position);
		startActivityForResult(intent, REQUEST_PICK_GROUP);
	}
	
	private View.OnClickListener mLeftClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (mMode) {
				case MODE_PICK:
				case MODE_CHOOSE:
					setResult(RESULT_CANCELED);
				default :
					break;
			}
			finish();
		}
	};
	
	/** MODE_VIEW下Item单击事件 */
	private AdapterView.OnItemClickListener mViewItemClick = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			gotoPersonDetail(mAdapter.getItem(position));
		}
	};
	
	/** MODE_PICK下Item单击事件 */
	private AdapterView.OnItemClickListener mPicItemClick = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			LogUtil.e(tag, mAdapter.getItem(position));
			Intent data = new Intent();
			data.putExtra(EXTRA_PERSON, mAdapter.getItem(position));
			if(getIntent().hasExtra(EXTRA_POSITION)) {
				data.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, -1));
			}
			if(getIntent().hasExtra(EXTRA_FACE)) {
				data.putExtra(EXTRA_FACE, getIntent().getSerializableExtra(EXTRA_FACE));
			}
			setResult(RESULT_OK, data);
			finish();
		}
	};
	
	/** MODE_CHOOSE下Item单击事件 */
	private AdapterView.OnItemClickListener mChooseItemClick = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			mAdapter.setChecked(position, !mAdapter.isChecked(position));
		}
	};
	
	/** Item长按事件(只在MODE_VIEW下) **/
	private AdapterView.OnItemLongClickListener mItemLongClick = new AdapterView.OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
			AlertDialog.Builder builder = new AlertDialog.Builder(PersonListActivity.this)
			.setItems(R.array.person_list_long_click_menu, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case 0:
						gotoPersonDetail(mAdapter.getItem(position));
						break;
					case 1:
						gotoModifyPerson(mAdapter.getItem(position), position);
						break;
					case 2:
						new DeletePerson(position).execute(mAdapter.getItem(position));
						break;
					case 3:
						gotoPickGroup(position);
						break;
					default:
						break;
					}
				}
			});
			builder.create().show();
			return false;
		}
	};
	
	private void gotoPersonDetail(Person person) {
		Intent intent = new Intent(this, PersonDetailActivity.class);
		intent.putExtra(EXTRA_PERSON, person);
		startActivity(intent);
	}
	
	private void gotoModifyPerson(Person person, int position) {
		Intent intent = new Intent(this, CreatePersonActivity.class);
		intent.putExtra(CreatePersonActivity.EXTRA_OLD_PERSON, person);
		intent.putExtra(CreatePersonActivity.EXTRA_MODE, CreatePersonActivity.MODE_MODIFY);
		intent.putExtra(EXTRA_POSITION, position);
		startActivityForResult(intent, REQUEST_MODIFY_PREDON);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			switch (requestCode) {
				case REQUEST_CREATE_PERSON :
					if(data != null) {
						mAdapter.add((Person) data.getSerializableExtra(CreatePersonActivity.EXTRA_NEW_PERSON));
						setResult(RESULT_OK, data);
						finish();
					}
					break;
				case REQUEST_MODIFY_PREDON:
					if(data != null) {
						int position = data.getIntExtra(EXTRA_POSITION, -1);
						if(position < 0) {
							Toast.makeText(PersonListActivity.this, R.string.sys_err, Toast.LENGTH_SHORT).show();
							return;
						}
						mAdapter.replace(position, (Person) data.getSerializableExtra(CreatePersonActivity.EXTRA_NEW_PERSON));
					}
					break;
				case REQUEST_PICK_GROUP:
					if(data != null && data.hasExtra(GroupListActivity.EXTRA_GROUP) && data.hasExtra(GroupListActivity.EXTRA_POSITION)) {
						GroupAddPersonReq req = new GroupAddPersonReq();
						req.setGroup_id(((Group) data.getSerializableExtra(GroupListActivity.EXTRA_GROUP)).getGroup_id());
						req.setPerson_id(mAdapter.getItem(data.getIntExtra(GroupListActivity.EXTRA_POSITION, -1)).getPerson_id());
						new AddToGroup().execute(req);
					}
					break;
				default :
					break;
			}
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(RESULT_CANCELED);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 获取Person列表
	 * @author xiaoying
	 *
	 */
	private class GetPersons extends AsyncTask<Void, Void, InfoGetPersonListResp> {

		private InfoService mmService = new InfoService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.msg_getting_data));
		}
		
		@Override
		protected InfoGetPersonListResp doInBackground(Void... params) {
			
			try {
				return mmService.getPersonList();
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
		protected void onPostExecute(InfoGetPersonListResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					List<Person> persons = result.getPerson();
					if(persons != null && !persons.isEmpty()) {
						mAdapter.addAll(persons);
					} else {
						Toast.makeText(PersonListActivity.this, R.string.no_data, Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(PersonListActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(PersonListActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
	}
	
	/**
	 * 删除Person请求
	 * @author xiaoying
	 *
	 */
	private class DeletePerson extends AsyncTask<Person, Void, PersonDeleteResp> {
		
		private PersonService mmService = new PersonService(MainApplication.CLIENT);
		
		private int mmPosition = -1;
		
		public DeletePerson(int position) {
			this.mmPosition = position;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.deleting));
		}

		@Override
		protected PersonDeleteResp doInBackground(Person... params) {
			Person person = params[0];
			try {
				PersonDeleteReq req = new PersonDeleteReq(person.getPerson_id(), true);
				return mmService.deletePerson(req);
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
		protected void onPostExecute(PersonDeleteResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					if(result.getDeleted() > 0) {
						mAdapter.remove(mmPosition);
					}
				} else {
					Toast.makeText(PersonListActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			}
			dismissProgressDialog();
		}
		
	}
	
	/**
	 * 功能：添加Perosn到Group
	 * @author xiaoying
	 *
	 */
	private class AddToGroup extends AsyncTask<GroupAddPersonReq, Void, GroupAddPersonResp> {

		private GroupService mmService = new GroupService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.submiting_data));
		}
		
		@Override
		protected GroupAddPersonResp doInBackground(GroupAddPersonReq... params) {
			try {
				return mmService.addPerson(params[0]);
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
		protected void onPostExecute(GroupAddPersonResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					if(result.getAdded() > 0) {
						Toast.makeText(PersonListActivity.this, R.string.add_to_succues, Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(PersonListActivity.this, R.string.add_to_fail, Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(PersonListActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(PersonListActivity.this, R.string.net_err, Toast.LENGTH_SHORT).show();
			}
			dismissProgressDialog();
		}
		
	}
}
