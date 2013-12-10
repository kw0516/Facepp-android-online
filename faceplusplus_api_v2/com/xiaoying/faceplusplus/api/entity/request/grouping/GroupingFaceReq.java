/*
 * 文件名：GroupingFaceReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.grouping;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：Grouping请求实体类
 * @author xiaoying
 *
 */
public class GroupingFaceReq extends BaseRequest {
	private String faceset_id;		//相应Faceset的id。
	private String faceset_name;	//相应Faceset的name。
	
	public GroupingFaceReq() {
		
	}
	
	public GroupingFaceReq(String faceset, boolean isId) {
		if(isId) {
			setFaceset_id(faceset);
		} else {
			setFaceset_name(faceset);
		}
	}
	
	/**
	 * 相应Faceset的id。
	 * @return the faceset_id
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	/**
	 * 相应Faceset的id。
	 * 与setFaceset_name(String faceset_name)只能选其一
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
		this.faceset_name = null;
	}
	/**
	 * 相应Faceset的name。
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	/**
	 * 相应Faceset的name。
	 * 与setFaceset_id(String faceset_id)只能选其一
	 * @param faceset_name the faceset_name to set
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
		this.faceset_id = null;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupingFace [faceset_id=" + faceset_id + ", faceset_name="
				+ faceset_name + "]";
	}
}
