/*
 * 文件名：VerifyResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.recognition;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：给定一个Face和一个Person，返回是否是同一个人的判断以及置信度请求返回实体类
 * @author xiaoying
 *
 */
public class VerifyResp extends BaseResponse {
	private String session_id;	//相应请求的session标识符，可用于结果查询
	private boolean is_same_person;	//两个输入是否为同一人的判断
	private float confidence;	//系统对这个判断的置信度。
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
	 * 两个输入是否为同一人的判断
	 * @return the is_same_person
	 */
	public boolean isIs_same_person() {
		return is_same_person;
	}
	/**
	 * 两个输入是否为同一人的判断
	 * @param is_same_person the is_same_person to set
	 */
	public void setIs_same_person(boolean is_same_person) {
		this.is_same_person = is_same_person;
	}
	/**
	 * 系统对这个判断的置信度。
	 * @return the confidence
	 */
	public float getConfidence() {
		return confidence;
	}
	/**
	 * 系统对这个判断的置信度。
	 * @param confidence the confidence to set
	 */
	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VerifyResp [session_id=" + session_id + ", is_same_person="
				+ is_same_person + ", confidence=" + confidence + ", error="
				+ error + ", error_code=" + error_code + "]";
	}
	
	
}
