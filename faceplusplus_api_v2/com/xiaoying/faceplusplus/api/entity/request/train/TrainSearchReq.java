/*
 * 文件名：TrainSearchReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.train;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：Train Search（Faceset）请求实体类
 * @author xiaoying
 *
 */
public class TrainSearchReq extends BaseRequest {
	private String faceset_id;	//用于搜索的face组成的faceset_id
	private String faceset_name;//用于搜索的face组成的faceset_name
	
	public TrainSearchReq() {
		
	}
	
	public TrainSearchReq(String faceset, boolean isId) {
		if(isId) {
			setFaceset_id(faceset);
		} else {
			setFaceset_name(faceset);
		}
	}
	
	/**
	 * 用于搜索的face组成的faceset_id
	 * @return the faceset_id
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	/**
	 * 用于搜索的face组成的faceset_id
	 * 与setFaceset_name(String faceset_name)只能选其一
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
		this.faceset_name = null;
	}
	/**
	 * 用于搜索的face组成的faceset_name
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	/**
	 * 用于搜索的face组成的faceset_name
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
		return "TrainSearchReq [faceset_id=" + faceset_id + ", faceset_name="
				+ faceset_name + "]";
	}
	
	
}
