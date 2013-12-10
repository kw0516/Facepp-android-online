/*
 * 文件名：TrainSearchResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.train;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：Train Search（Faceset）请求返回实体类
 * @author xiaoying
 *
 */
public class TrainSearchResp extends BaseResponse {
	private String session_id;	//请求session_id

	/**
	 * //请求session_id
	 * @return the session_id
	 */
	public String getSession_id() {
		return session_id;
	}

	/**
	 * //请求session_id
	 * @param session_id the session_id to set
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrainSearchResp [session_id=" + session_id + ", error=" + error
				+ ", error_code=" + error_code + "]";
	}
	
}
