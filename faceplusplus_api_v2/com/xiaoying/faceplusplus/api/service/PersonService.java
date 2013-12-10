/*
 * 文件名：PersonService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-11
 * 修改人：xiaoying
 * 修改时间：2013-5-11
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xiaoying.faceplusplus.api.cliet.Client;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.config.UrlConfig;
import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonRemoveFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonAddFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonDeleteResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonGetInfoResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonRemoveFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonSetInfoResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：Person相关服务类
 * @author xiaoying
 */
public class PersonService extends BaseService {

	public PersonService(Client client) {
		super(client);
	}
	
	/**
	 * 创建Person
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public PersonCreateResp createPerson(PersonCreateReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("face_id", body.getFace_id());
		params.put("tag", body.getTag());
		params.put("group_id", body.getGroup_id());
		params.put("group_name", body.getGroup_name());
		return getCreateRespose(params);
	}
	
	private PersonCreateResp getCreateRespose(Map<String, Object> params) throws ClientProtocolException, IOException, ParseException, JSONException {
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_PERSON_CREATE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonCreateResp result = new PersonCreateResp();
		result.setAdded_group(json.optInt("added_group"));
		result.setAdded_face(json.optInt("added_face"));
		result.setTag(json.optString("tag"));
		result.setPerson_name(json.optString("person_name"));
		result.setPerson_id(json.optString("person_id"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 删除Person
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public PersonDeleteResp deletePerson(PersonDeleteReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_PERSON_DELETE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonDeleteResp result = new PersonDeleteResp();
		result.setDeleted(json.optInt("deleted"));
		result.setSuccess(json.optBoolean("success"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}
	
	/**
	 * 向Person中添加Face
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public PersonAddFaceResp addFace(PersonAddFaceReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("face_id must be set");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		params.put("face_id", body.getFace_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_PERSON_ADD_FACE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonAddFaceResp result = new PersonAddFaceResp();
		result.setAdded(json.optInt("added"));
		result.setSuccess(json.optBoolean("success"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}
	
	/**
	 * 从Person中删除Face
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public PersonRemoveFaceResp removeFace(PersonRemoveFaceReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("face_id must be set");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		params.put("face_id", body.getFace_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_PERSON_REMOVE_FACE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonRemoveFaceResp result = new PersonRemoveFaceResp();
		result.setRemoved(json.optInt("removed"));
		result.setSuccess(json.optBoolean("success"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}
	
	/**
	 * 修改Person信息
	 * @param body
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public PersonSetInfoResp setPersonInfo(PersonSetInfoReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		params.put("name", body.getName());
		params.put("tag", body.getTag());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_PERSON_SET_INFO, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonSetInfoResp result = new PersonSetInfoResp();
		result.setPerson_id(json.optString("person_id"));
		result.setPerson_name(json.optString("person_name"));
		result.setTag(json.optString("tag"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 查询Person的信息
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public PersonGetInfoResp getPersonInfo(PersonGetInfoReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_PERSON_GET_INFO, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonGetInfoResp result = new PersonGetInfoResp();
		result.setPerson_id(json.optString("person_id"));
		result.setPerson_name(json.optString("person_name"));
		result.setFace(getFaceInfo(json.optJSONArray("face")));
		result.setGroup(getGroupInfo(json.optJSONArray("group")));
		result.setTag(json.optString("tag"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}
	
	
	private List<Face> getFaceInfo(JSONArray faceArray) {
		List<Face> faces = new ArrayList<Face>();
		if(faceArray != null) {
			JSONObject faceObj = null;
			Face face = null;
			for(int i = 0; i < faceArray.length(); i++) {
				faceObj = faceArray.optJSONObject(i);
				face = new Face();
				face.setFace_id(faceObj.optString("face_id"));
				face.setTag(faceObj.optString("tag"));
				faces.add(face);
			}
		}
		return faces;
	}
	
	private List<Group> getGroupInfo(JSONArray groupArray) {
		List<Group> groups = new ArrayList<Group>();
		if(groupArray != null) {
			JSONObject groupObj = null;
			Group group = null;
			for(int i = 0; i < groupArray.length(); i++) {
				groupObj = groupArray.optJSONObject(i);
				group = new Group();
				group.setGroup_id(groupObj.optString("group_id"));
				group.setGroup_name(groupObj.optString("group_name"));
				group.setTag(groupObj.optString("tag"));
				groups.add(group);
			}
		}
		return groups;
	}
	
}
