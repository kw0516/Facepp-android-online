/*
 * 文件名：FacesetDeleteReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.faceset;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：Faceset删除请求实体类
 * @author xiaoying
 *
 */
public class FacesetDeleteReq extends BaseRequest {
	private String faceset_name;	//用逗号隔开的待删除的faceset name列表
	private String faceset_id;	//用逗号隔开的待删除的faceset id列表
	
	public FacesetDeleteReq() {
		
	}
	
	public FacesetDeleteReq(String faceset, boolean isId) {
		if(isId) {
			setFaceset_id(faceset);
		} else {
			setFaceset_name(faceset);
		}
	}
	
	/**
	 * 用逗号隔开的待删除的faceset name列表
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	
	/**
	 * 用逗号隔开的待删除的faceset name列表
	 * 与setFaceset_id(String faceset_id)只能选其一
	 * @param faceset_name the faceset_name to set
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
		this.faceset_id = null;
	}
	
	/**
	 * 用逗号隔开的待删除的faceset ide列表
	 * @return the faceset_id
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	
	/**
	 * 用逗号隔开的待删除的faceset id列表
	 * 与setFaceset_name(String faceset_name)只能选其一
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
		this.faceset_name = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FacesetDeleteReq [faceset_name=" + faceset_name
				+ ", faceset_id=" + faceset_id + "]";
	}
	
}
