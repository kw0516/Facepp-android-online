/*
 * 文件名：FacesetService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
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
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetRemoveFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetAddFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetDeleteResp;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetGetInfoResp;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetRemoveFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.faceset.FacesetSetInfoResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：Faceset 相关服务类
 * @author xiaoying
 */
public class FacesetService extends BaseService {

	/**
	 * @param client
	 */
	public FacesetService(Client client) {
		super(client);
	}

	/**
	 * 创建Faceset
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public FacesetCreateResp createFaceset(FacesetCreateReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_name", body.getFaceset_name());
		params.put("face_id", body.getFace_id());
		params.put("tag", body.getTag());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_FACESET_CREATE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		FacesetCreateResp result = new FacesetCreateResp();
		result.setFaceset_name(json.optString("faceset_name"));
		result.setFaceset_id(json.optString("faceset_id"));
		result.setTag(json.optString("tag"));
		result.setAdded_face(json.optInt("added_face"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 删除指定的Faceset
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public FacesetDeleteResp deleteFaceset(FacesetDeleteReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_name", body.getFaceset_name());
		params.put("faceset_id", body.getFaceset_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_FACESET_DELETE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		FacesetDeleteResp result = new FacesetDeleteResp();
		result.setDeleted(json.optInt("deleted"));
		result.setSuccess(json.optBoolean("success"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 向Faceset中添加Face
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public FacesetAddFaceResp addFace(FacesetAddFaceReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must be to set one");
		}
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("face_id should no be null");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_name", body.getFaceset_name());
		params.put("faceset_id", body.getFaceset_id());
		params.put("face_id", body.getFace_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_FACESET_ADD_FACE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		FacesetAddFaceResp result = new FacesetAddFaceResp();
		result.setAdded(json.optInt("added"));
		result.setSuccess(json.optBoolean("success"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}
	
	/**
	 * 从Faceset中删除Face
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public FacesetRemoveFaceResp removeFace(FacesetRemoveFaceReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must be to set one");
		}
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("face_id should no be null");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_name", body.getFaceset_name());
		params.put("faceset_id", body.getFaceset_id());
		params.put("face_id", body.getFace_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_FACESET_REMOVE_FACE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		FacesetRemoveFaceResp result = new FacesetRemoveFaceResp();
		result.setRemoved(json.optInt("removed"));
		result.setSuccess(json.optBoolean("success"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 修改Faceset信息
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public FacesetSetInfoResp setFacesetInfo(FacesetSetInfoReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_name", body.getFaceset_name());
		params.put("faceset_id", body.getFaceset_id());
		params.put("name", body.getName());
		params.put("tag", body.getTag());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_FACESET_SET_INFO, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		FacesetSetInfoResp result = new FacesetSetInfoResp();
		result.setFaceset_name(json.optString("faceset_name"));
		result.setFaceset_id(json.optString("faceset_id"));
		result.setTag(json.optString("tag"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 查询Faceset信息
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public FacesetGetInfoResp getFacesetInfo(FacesetGetInfoReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_name", body.getFaceset_name());
		params.put("faceset_id", body.getFaceset_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_FACESET_GET_INFO, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		FacesetGetInfoResp result = new FacesetGetInfoResp();
		result.setFaceset_name(json.optString("faceset_name"));
		result.setFaceset_id(json.optString("faceset_id"));
		result.setTag(json.optString("tag"));
		result.setFace(getFaceInfo(json.optJSONArray("face")));
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
}
