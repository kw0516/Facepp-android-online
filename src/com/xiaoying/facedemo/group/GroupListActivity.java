/*
 * 文件名：GroupListActivity.java
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
import com.xiaoying.facedemo.group.adapter.GroupListAdapter;
import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.facedemo.widget.TitleBar;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupDeleteReq;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupDeleteResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetGroupListResp;
import com.xiaoying.faceplusplus.api.service.GroupService;
import com.xiaoying.faceplusplus.api.service.InfoService;

/**
 * 功能：Group列表
 * @author xiaoying
 */
public class GroupListActivity extends Activity {
	
	
	public static final int MODE_VIEW = 1;
	
	public static final int MODE_PICK = 2;
	
	public static final int MODE_CHOOSE = 3;
	
	public static final String EXTRA_MODE = "mode";

	public static final String EXTRA_GROUP = "group";
	
	public static final String EXTRA_GROUP_ARRAY = "person_array";
	
	public static final String EXTRA_POSITION = "position";
	
	public static final int REQUEST_CREATE_GROUP = 1000;
	
	public static final int REQUEST_MODIFY_GROUP = 1001;
	
	private String tag = GroupListActivity.class.getSimpleName();
	
	private TitleBar mTitleBar = null;
	
	private ListView mListView = null;
	
	private GroupListAdapter mAdapter = null;

	private ProgressDialog mProgressDialog = null;
	
	private int mMode = MODE_VIEW;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_list);
		
		mMode = getIntent().getIntExtra(EXTRA_MODE, MODE_VIEW);
		
		initView();
		
		new GetGroups().execute();
	}
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.tb_group_list_title);
		mListView = (ListView) findViewById(R.id.lv_group_list);
		mTitleBar.setLeftButton(R.string.backe, mLeftClick);
		
		mTitleBar.setLeftButton(R.string.backe, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		if(mMode == MODE_VIEW) {
			mTitleBar.setTitle(R.string.group_manager);
			mTitleBar.setRightButton(R.string.create, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					gotoCreateGroup();
				}
			});
			mAdapter = new GroupListAdapter(this);
			mListView.setOnItemClickListener(mViewItemClick);
			mListView.setOnItemLongClickListener(mItemLongClick);
		} else if(mMode == MODE_PICK) {
			mTitleBar.setTitle(R.string.choose_group);
			mTitleBar.setRightButtonVisible(false);
			mAdapter = new GroupListAdapter(this, MODE_PICK);
			mListView.setOnItemClickListener(mPicItemClick);
		} else if(mMode == MODE_CHOOSE) {
			mTitleBar.setTitle(R.string.choose_group);
			mTitleBar.setRightButton(R.string.ok, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LogUtil.e(tag, mAdapter.getCheckedItems());
					Intent data = new Intent();
					data.putExtra(EXTRA_GROUP_ARRAY, (Serializable) mAdapter.getCheckedItems());
					setResult(RESULT_OK, data);
					finish();
				}
			});
			mAdapter = new GroupListAdapter(this, MODE_CHOOSE);
			mListView.setOnItemClickListener(mChooseItemClick);
		}
		
		mListView.setAdapter(mAdapter);
		
		initProgressDialog();
	}
	
	private void gotoCreateGroup() {
		Intent intent = new Intent(this, CreateGroupActivity.class);
		intent.putExtra(CreateGroupActivity.EXTRA_MODE, CreateGroupActivity.MODE_CREATE);
		startActivityForResult(intent, REQUEST_CREATE_GROUP);
	}
	
	private void gotoModifyGroup(Group group, int position) {
		Intent intent = new Intent(this, CreateGroupActivity.class);
		intent.putExtra(CreateGroupActivity.EXTRA_OLD_GROUP, group);
		intent.putExtra(CreateGroupActivity.EXTRA_MODE, CreateGroupActivity.MODE_MODIFY);
		intent.putExtra(EXTRA_POSITION, position);
		startActivityForResult(intent, REQUEST_MODIFY_GROUP);
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
			
		}
	};
	
	/** MODE_PICK下Item单击事件 */
	private AdapterView.OnItemClickListener mPicItemClick = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			LogUtil.e(tag, mAdapter.getItem(position));
			Intent data = new Intent();
			data.putExtra(EXTRA_GROUP, mAdapter.getItem(position));
			if(getIntent().hasExtra(EXTRA_POSITION)) {
				data.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, -1));
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
			AlertDialog.Builder builder = new AlertDialog.Builder(GroupListActivity.this)
			.setItems(R.array.group_list_long_click_menu, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case 0:
						break;
					case 1:
						gotoModifyGroup(mAdapter.getItem(position), position);
						break;
					case 2:
						new DeleteGroup(position).execute(mAdapter.getItem(position));
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			if(requestCode == REQUEST_CREATE_GROUP) {
				if(data != null) {
					mAdapter.add((Group) data.getSerializableExtra(CreateGroupActivity.EXTRA_NEW_GROUP));
				}
			} else if(requestCode == REQUEST_MODIFY_GROUP) {
				if(data != null) {
					int position = data.getIntExtra(EXTRA_POSITION, -1);
					if(position < 0) {
						Toast.makeText(GroupListActivity.this, R.string.sys_err, Toast.LENGTH_SHORT).show();
						return;
					}
					mAdapter.replace(position, (Group) data.getSerializableExtra(CreateGroupActivity.EXTRA_NEW_GROUP));
				}
			}
			
		}
	};
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private class GetGroups extends AsyncTask<Void, Void, InfoGetGroupListResp> {

		private InfoService mmService = new InfoService(MainApplication.CLIENT);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.msg_getting_data));
		}
		
		@Override
		protected InfoGetGroupListResp doInBackground(Void... params) {
			
			try {
				return mmService.getGroupList();
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
		protected void onPostExecute(InfoGetGroupListResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					List<Group> groups = result.getGroup();
					if(groups == null || groups.isEmpty()) {
						Toast.makeText(GroupListActivity.this, R.string.action_settings, Toast.LENGTH_SHORT).show();
					} else {
						mAdapter.addAll(groups);
						dismissProgressDialog();
					}
				} else {
					dismissProgressDialog();
					Toast.makeText(GroupListActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			}
		}
		
	}
	
	/**
	 * 删除Group请求
	 * @author xiaoying
	 *
	 */
	private class DeleteGroup extends AsyncTask<Group, Void, GroupDeleteResp> {
		
		private GroupService mmService = new GroupService(MainApplication.CLIENT);
		
		private int mmPosition = -1;
		
		public DeleteGroup(int position) {
			this.mmPosition = position;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog(getString(R.string.deleting));
		}

		@Override
		protected GroupDeleteResp doInBackground(Group... params) {
			Group group = params[0];
			try {
				GroupDeleteReq req = new GroupDeleteReq(group.getGroup_id(), true);
				return mmService.deleteGroup(req);
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
		protected void onPostExecute(GroupDeleteResp result) {
			super.onPostExecute(result);
			if(result != null) {
				if(result.getError_code() == RespConfig.RESP_OK) {
					if(result.getDeleted() > 0) {
						mAdapter.remove(mmPosition);
					}
				} else {
					Toast.makeText(GroupListActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
				}
			}
			dismissProgressDialog();
		}
		
	}
}
