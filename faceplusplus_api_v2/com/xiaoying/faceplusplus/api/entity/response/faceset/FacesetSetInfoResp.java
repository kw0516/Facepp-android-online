/*
 * 文件名：FacesetSetInfoResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.faceset;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：Faceset修改信息请求返回实体类
 * @author xiaoying
 *
 */
public class FacesetSetInfoResp extends BaseResponse {
	private String faceset_name;	//相应Faceset的name
	private String faceset_id;	//相应Faceset的id
	private String tag;	//Faceset相关的tag
	/**
	 * 相应Faceset的name
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	/**
	 * 相应Faceset的name
	 * @param faceset_name the faceset_name to set
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
	}
	/**
	 * 相应Faceset的id
	 * @return the faceset_id
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	/**
	 * 相应Faceset的id
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
	}
	/**
	 * Faceset相关的tag
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * Faceset相关的tag
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FacesetSetInfoResp [faceset_name=" + faceset_name
				+ ", faceset_id=" + faceset_id + ", tag=" + tag + ", error="
				+ error + ", error_code=" + error_code + "]";
	}
}
