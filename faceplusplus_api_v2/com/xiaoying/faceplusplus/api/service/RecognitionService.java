/*
 * 文件名：RecognitionService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
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
import com.xiaoying.faceplusplus.api.entity.request.recognition.CompareReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.IdentityReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.SearchReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.VerifyReq;
import com.xiaoying.faceplusplus.api.entity.response.recognition.CompareResp;
import com.xiaoying.faceplusplus.api.entity.response.recognition.IdentifyResp;
import com.xiaoying.faceplusplus.api.entity.response.recognition.SearchResp;
import com.xiaoying.faceplusplus.api.entity.response.recognition.VerifyResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：人脸匹配相关服务类
 * @author xiaoying
 *
 */
public class RecognitionService extends BaseService {

	/**
	 * @param client
	 */
	public RecognitionService(Client client) {
		super(client);
	}
	
	/**
	 * 计算两个Face的相似性以及五官相似度
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public CompareResp compare(CompareReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFace_id1()) || StringUtil.isEmpty(body.getFace_id2())) {
			throw new IllegalArgumentException("face_id1 and face_id2 should not be null");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("face_id1", body.getFace_id1());
		params.put("face_id2", body.getFace_id2());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_RECOGNITION_COMPARE, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		CompareResp result = new CompareResp();
		result.setSession_id(json.optString("session_id"));
		result.setSimilarity((float)json.optDouble("similarity"));
		JSONObject jsObj = json.optJSONObject("component_similarity");
		CompareResp.ComponentSimilarity obj = new CompareResp.ComponentSimilarity();
		obj.setEye((float)jsObj.optDouble("eye"));
		obj.setMouth((float) jsObj.optDouble("mouth"));
		obj.setNose((float) jsObj.optDouble("nose"));
		obj.setEyebrow((float) jsObj.optDouble("eyebrow"));
		result.setComponent_similarity(obj);
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code"));
		return result;
	}

	/**
	 * 给定一个Face和一个Person，返回是否是同一个人的判断以及置信度。
	 * 
	 * 注意，当Person中的信息被修改之后（增加，删除了Face等），为了保证结果与最新数据一致，Person应当被重新train。 
	 * 见 @see {@link TrainService}
	 * 否则调用此API时将使用最后一次train时的数据。
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public VerifyResp verify(VerifyReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("face_id should not be null");
		}
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_id or person_name must to be set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("face_id", body.getFace_id());
		params.put("person_id", body.getPerson_id());
		params.put("person_name", body.getPerson_name());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_RECOGNITION_VERIFY, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		VerifyResp result = new VerifyResp();
		result.setSession_id(json.optString("session_id"));
		result.setIs_same_person(json.optBoolean("is_same_person"));
		result.setConfidence((float)json.optDouble("confidence"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 给定一个Face和一个Faceset，在该Faceset内搜索最相似的Face。提示：若搜索集合需要包含超过10000张人脸，可以分成多个faceset分别调用search功能再将结果按confidence顺序合并即可。
	 * 
	 * 注意，当Faceset中的信息被修改之后（增加，删除了Face等），为了保证结果与最新数据一致，Faceset应当被重新train. 
	 * 见 @see {@link TrainService}
	 * 否则调用此API时将使用最后一次train时的数据。
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public SearchResp search(SearchReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getKey_face_id())) {
			throw new IllegalArgumentException("key_face_id should not be null");
		}
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must to be set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("key_face_id", body.getKey_face_id());
		params.put("faceset_id", body.getFaceset_id());
		params.put("faceset_name", body.getFaceset_name());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_RECOGNITION_SEARCH, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		SearchResp result = new SearchResp();
		result.setSession_id(json.optString("session_id"));
		result.setCandidates(getSearchCanditate(json.optJSONArray("candidate")));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	public IdentifyResp identity(IdentityReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getGroup_id()) && StringUtil.isEmpty(body.getGroup_name())) {
			throw new IllegalArgumentException("group_id or group_name must to be set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_id", body.getGroup_id());
		params.put("group_name", body.getGroup_name());
		params.put("url", body.getUrl());
		params.put("img", body.getImg());
		params.put("mode", body.getMode());
		params.put("key_face_id", body.getKey_face_id());
		params.put("async", body.getAsync());
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_RECOGNITION_IDENTIFY, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		IdentifyResp result = new IdentifyResp();
		result.setSession_id(json.optString("session_id"));
		result.setFace(getIdentityFace(json.optJSONArray("face")));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}
	
	/**
	 * 解析JSONArray中canditate返回结果
	 * @param candidateArray
	 * @return
	 */
	private List<SearchResp.Candidate> getSearchCanditate(JSONArray candidateArray) {
		List<SearchResp.Candidate> candidates = new ArrayList<SearchResp.Candidate>();
		if(candidateArray != null) {
			JSONObject candidateObj = null;
			SearchResp.Candidate candidate = null;
			for(int i = 0; i < candidateArray.length(); i++ ) {
				candidateObj = candidateArray.optJSONObject(i);
				candidate = new SearchResp.Candidate();
				candidate.setFace_id(candidateObj.optString("face_id"));
				candidate.setTag(candidateObj.optString("tag"));
				candidate.setSimilarity((float) candidateObj.optDouble("similarity"));
				candidates.add(candidate);
			}
		}
		return candidates;
	}
	
	/**
	 * 解析JSONArray识别返回的Face信息
	 * @param faceArray
	 * @return
	 */
	public List<IdentifyResp.IdentifyFace> getIdentityFace(JSONArray faceArray) {
		List<IdentifyResp.IdentifyFace> faces = new ArrayList<IdentifyResp.IdentifyFace>();
		if(faceArray != null) {
			JSONObject faceObj = null;
			IdentifyResp.IdentifyFace iFace = null;
			Face face = null;
			for(int i = 0; i < faceArray.length(); i++) {
				faceObj = faceArray.optJSONObject(i);
				iFace = new IdentifyResp.IdentifyFace();
				face = new Face();
				face.setFace_id(faceObj.optString("face_id"));
				FaceService.setPosition(faceObj.optJSONObject("position"), face);
				iFace.setCandidates(getIdentityCanditate(faceObj.optJSONArray("candidate")));
//				iFace.setPosition(FaceService.getPosition(faceObj.optJSONObject("position")));
				iFace.setFace(face);
				faces.add(iFace);
			}
		}
		return faces;
	}

	/**
	 * 解析JSONArray识别返回的Face信息下的candidate信息，包括Person id，name，tag和confidence置信度
	 * @param candidateArray
	 * @return
	 */
	private List<IdentifyResp.Candidate> getIdentityCanditate(JSONArray candidateArray) {
		List<IdentifyResp.Candidate> candidates = new ArrayList<IdentifyResp.Candidate>();
		if(candidateArray != null) {
			JSONObject candidateObj = null;
			IdentifyResp.Candidate candidate = null;
			for(int i = 0; i < candidateArray.length(); i++) {
				candidateObj = candidateArray.optJSONObject(i);
				candidate = new IdentifyResp.Candidate();
				candidate.setPerson_id(candidateObj.optString("person_id"));
				candidate.setPerson_name(candidateObj.optString("person_name"));
				candidate.setTag(candidateObj.optString("tag"));
				candidate.setConfidence((float) candidateObj.optDouble("confidence"));
				candidates.add(candidate);
			}
		}
		return candidates;
	}
	
}
