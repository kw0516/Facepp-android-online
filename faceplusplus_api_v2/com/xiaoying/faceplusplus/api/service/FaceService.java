/*
 * 文件名：FaceService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.service;

import java.io.FileNotFoundException;
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
import com.xiaoying.faceplusplus.api.entity.PointF;
import com.xiaoying.faceplusplus.api.entity.request.face.DetectReq;
import com.xiaoying.faceplusplus.api.entity.response.face.DetectResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：人脸检测
 * @author xiaoying
 */
public class FaceService extends BaseService {
	
	public FaceService(Client client) {
		super(client);
	}

	/**
	 * 人脸检测
	 * @param body 人脸检测请求体
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public DetectResp detect(DetectReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getUrl()) && body.getImg() == null) {
			throw new IllegalArgumentException("Must set a image url or image file");
		}
		if(body.getImg() != null && !body.getImg().exists()) {
			throw new FileNotFoundException("File " + body.getImg().getPath() + "not found.");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("img", body.getImg());
		params.put("url", body.getUrl());
		params.put("mode", body.getMode());
		params.put("attribute", body.getAttribute());
		params.put("tag", body.getTag());
		params.put("async", body.isAsync());
		return getResponse(params);
	}
	
	private DetectResp getResponse(Map<String, Object> params) throws ClientProtocolException, IOException, ParseException, JSONException {
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_DETECT, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		DetectResp result = new DetectResp();
		result.setSession_id(json.optString("session_id"));
		result.setImg_id(json.optString("img_id"));
		result.setUrl(json.optString("url"));
		result.setImg_width(json.optInt("img_width"));
		result.setImg_height(json.optInt("img_height"));
		JSONArray faceArray = json.optJSONArray("face");
		List<Face> faces = new ArrayList<Face>();
		if(faceArray != null) {
			JSONObject faceObj = null;
			Face face = null;
			for(int i = 0; i < faceArray.length(); i++) {
				faceObj = faceArray.optJSONObject(i);
				face = new Face();
				face.setFace_id(faceObj.optString("face_id"));
				face.setImg_id(result.getImg_id());
				face.setUrl(result.getUrl());
				face.setTag(faceObj.optString("tag"));
				setAttribute(faceObj.optJSONObject("attribute"), face);
				setPosition(faceObj.optJSONObject("position"), face);
				faces.add(face);
			}
		}
		result.setFace(faces);
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}
	
	public static void setAttribute(JSONObject attributeObj, Face face) {
		JSONObject ageObj = attributeObj.optJSONObject("age");
		Face.Age age = new Face.Age();
		age.setValue(ageObj.optInt("value"));
		age.setRange(ageObj.optInt("range"));
		face.setAge(age);
		JSONObject genderObj = attributeObj.optJSONObject("gender");
		Face.Gender gender = new Face.Gender();
		gender.setValue(genderObj.optString("value"));
		gender.setConfidence((float)genderObj.optDouble("confidence"));
		face.setGender(gender);
		JSONObject raceObj = attributeObj.optJSONObject("race");
		Face.Race race = new Face.Race();
		race.setValue(raceObj.optString("value"));
		race.setConfidence((float)raceObj.optDouble("confidence"));
		face.setRace(race);
	}
	
	/**
	 * 从JSONObject解析Face的position信息
	 * @param positionObj
	 * @return
	 */
	public static void setPosition(JSONObject positionObj, Face face) {
		JSONObject centerObj = positionObj.optJSONObject("center");
		PointF center = new PointF((float)centerObj.optDouble("x"), (float)centerObj.optDouble("y"));
		face.setCenter(center);
		JSONObject eyeLeftObj = positionObj.optJSONObject("eye_left");
		PointF eyeLeft = new PointF((float)eyeLeftObj.optDouble("x"), (float)eyeLeftObj.optDouble("y"));
		face.setEye_left(eyeLeft);
		JSONObject eyeRightObj = positionObj.optJSONObject("eye_right");
		PointF eyeRight = new PointF((float)eyeRightObj.optDouble("x"), (float)eyeRightObj.optDouble("y"));
		face.setEye_right(eyeRight);
		JSONObject nosehtObj = positionObj.optJSONObject("nose");
		PointF nose = new PointF((float)nosehtObj.optDouble("x"), (float)nosehtObj.optDouble("y"));
		face.setNose(nose);
		JSONObject mouthLeftObj = positionObj.optJSONObject("mouth_left");
		PointF mouthLeft = new PointF((float)mouthLeftObj.optDouble("x"), (float)mouthLeftObj.optDouble("y"));
		face.setMouth_left(mouthLeft);
		JSONObject mouthRightObj = positionObj.optJSONObject("mouth_right");
		PointF mouthRight = new PointF((float)mouthRightObj.optDouble("x"), (float)mouthRightObj.optDouble("y"));
		face.setMouth_right(mouthRight);
		face.setWidth((float)positionObj.optDouble("width"));
		face.setHeight((float)positionObj.optDouble("height"));
	}
}
