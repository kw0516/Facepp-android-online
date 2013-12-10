/*
 * 文件名：InfoGetSessionResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.info;

import org.json.JSONObject;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询Session请求返回实体类
 * @author xiaoying
 *
 */
public class InfoGetSessionResp extends BaseResponse {
	private String session_id;	///相应请求的session标识符，可用于结果查询
	private int create_time;	///任务开始时间，单位：秒
	private int finish_time;	///任务结束时间，单位：秒
	private JSONObject result;	///返回session_id对应的结果内容
	private String status;	///可能取值有：INQUEUE(队列中), SUCC(成功) 和FAILED(失败)
	
	
	/**
	 * 相应请求的session标识符，可用于结果查询
	 * @return the session_id
	 */
	public String getSession_id() {
		return session_id;
	}


	/**
	 * 相应请求的session标识符，可用于结果查询
	 * @param session_id the session_id to set
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}


	/**
	 * 任务开始时间，单位：秒
	 * @return the create_time
	 */
	public int getCreate_time() {
		return create_time;
	}


	/**
	 * 任务开始时间，单位：秒
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(int create_time) {
		this.create_time = create_time;
	}


	/**
	 * 任务结束时间，单位：秒
	 * @return the finish_time
	 */
	public int getFinish_time() {
		return finish_time;
	}


	/**
	 * 任务结束时间，单位：秒
	 * @param finish_time the finish_time to set
	 */
	public void setFinish_time(int finish_time) {
		this.finish_time = finish_time;
	}


	/**
	 * 返回session_id对应的结果内容
	 * @return the result
	 */
	public JSONObject getResult() {
		return result;
	}


	/**
	 * 返回session_id对应的结果内容
	 * @param result the result to set
	 */
	public void setResult(JSONObject result) {
		this.result = result;
	}


	/**
	 * 可能取值有：INQUEUE(队列中), SUCC(成功) 和FAILED(失败)
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * 可能取值有：INQUEUE(队列中), SUCC(成功) 和FAILED(失败)
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetSessionResp [session_id=" + session_id
				+ ", create_time=" + create_time + ", finish_time="
				+ finish_time + ", result=" + result + ", status=" + status
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}
}
