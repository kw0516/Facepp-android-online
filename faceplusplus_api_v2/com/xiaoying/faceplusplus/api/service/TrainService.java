/*
 * 文件名：TrainService.java
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
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.xiaoying.faceplusplus.api.cliet.Client;
import com.xiaoying.faceplusplus.api.config.RespConfig;
import com.xiaoying.faceplusplus.api.config.UrlConfig;
import com.xiaoying.faceplusplus.api.entity.request.train.TrainIdentityReq;
import com.xiaoying.faceplusplus.api.entity.request.train.TrainSearchReq;
import com.xiaoying.faceplusplus.api.entity.request.train.TrainVerifyReq;
import com.xiaoying.faceplusplus.api.entity.response.train.TrainIdentityResp;
import com.xiaoying.faceplusplus.api.entity.response.train.TrainSearchResp;
import com.xiaoying.faceplusplus.api.entity.response.train.TrainVerifyResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：在进行verify、search、identify操作前进行Train的服务类
 * @author xiaoying
 *
 */
public class TrainService extends BaseService {

	/**
	 * @param client
	 */
	public TrainService(Client client) {
		super(client);
	}
	
	/**
	 * 针对verify功能对一个person进行训练。请注意:
	 *
	 * 在一个person内进行verify之前，必须先对该person进行Train
	 * 当一个person内的数据被修改后(例如增删Person相关的Face等)，为使这些修改生效，person应当被重新Train
	 * Train所花费的时间较长, 因此该调用是异步的，仅返回session_id。
	 * 训练的结果可以通过/info/get_session查询。当训练完成时，返回值中将包含{"success": true}
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public TrainVerifyResp trainVerify(TrainVerifyReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_TRAIN_VERIFY, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		TrainVerifyResp result = new TrainVerifyResp();
		result.setSession_id(json.optString("session_id"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 针对search功能对一个faceset进行训练。请注意:
	 * 
	 * 在一个faceset内进行search之前，必须先对该faceset进行Train
	 * 当一个faceset内的数据被修改后(例如增删Face等)，为使这些修改生效，faceset应当被重新Train
	 * Train所花费的时间较长, 因此该调用是异步的，仅返回session_id。
	 * 训练的结果可以通过 /info/get_session 查询。当训练完成时，返回值中将包含{"success": true}
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public TrainSearchResp trainSearch(TrainSearchReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_id", body.getFaceset_id());
		params.put("faceset_name", body.getFaceset_name());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_TRAIN_SEARCH, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		TrainSearchResp result = new TrainSearchResp();
		result.setSession_id(json.optString("session_id"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

	/**
	 * 针对identify功能对一个Group进行训练。请注意:
	 * 
	 * 在一个Group内进行identify之前，必须先对该Group进行Train
	 * 当一个Group内的数据被修改后(例如增删Person, 增删Person相关的Face等)，为使这些修改生效，Group应当被重新Train
	 * Train所花费的时间较长, 因此该调用是异步的，仅返回session_id。
	 * 训练的结果可以通过 /info/get_session 查询。当训练完成时，返回值中将包含{"success": true}
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public TrainIdentityResp trainIdentity(TrainIdentityReq body) throws ClientProtocolException, IOException, ParseException, JSONException {
		if(StringUtil.isEmpty(body.getGroup_id()) && StringUtil.isEmpty(body.getGroup_name())) {
			throw new IllegalArgumentException("group_id or group_name must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_id", body.getGroup_id());
		params.put("group_name", body.getGroup_name());
		
		HttpResponse resp = HttpUtil.doPost(UrlConfig.PATH_TRAIN_IDENTIFY, params);
		JSONObject json = new JSONObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		TrainIdentityResp result = new TrainIdentityResp();
		result.setSession_id(json.optString("session_id"));
		result.setError(json.optString("error"));
		result.setError_code(json.optInt("error_code", RespConfig.RESP_OK));
		return result;
	}

}
