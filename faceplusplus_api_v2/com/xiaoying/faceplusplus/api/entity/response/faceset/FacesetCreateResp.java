/*
 * 文件名：FacesetCreateResp.java
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
 * 功能：Faceset创建请求返回实体类
 * @author xiaoying
 *
 */
public class FacesetCreateResp extends BaseResponse {
	private String faceset_name;	//相应Faceset的name
	private String faceset_id;	//相应Faceset的id
	private int added_face;	//成功加入的face数量
	private String tag;	//Faceset相关的tag
	
	/**
	 * 相应Faceset的name
	 * @return
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	
	/**
	 * 相应Faceset的name
	 * @param faceset_name
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
	}
	
	/**
	 * 相应Faceset的id
	 * @return
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	
	/**
	 * 相应Faceset的id
	 * @param faceset_id
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
	}
	
	/**
	 * 成功加入的face数量
	 * @return
	 */
	public int getAdded_face() {
		return added_face;
	}
	
	/**
	 * 成功加入的face数量
	 * @param added_face
	 */
	public void setAdded_face(int added_face) {
		this.added_face = added_face;
	}
	
	/**
	 * Faceset相关的tag
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * Faceset相关的tag
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "FacesetCreateResp [faceset_name=" + faceset_name
				+ ", faceset_id=" + faceset_id + ", added_face=" + added_face
				+ ", tag=" + tag + ", error=" + error + ", error_code="
				+ error_code + "]";
	}
}
