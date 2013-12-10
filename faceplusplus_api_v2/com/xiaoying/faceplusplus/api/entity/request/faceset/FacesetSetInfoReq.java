/*
 * 文件名：FacesetSetInfoReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.faceset;

/**
 * 功能：Faceset修改信息请求实体类
 * @author xiaoying
 *
 */
public class FacesetSetInfoReq {
	private String faceset_id;	//相应faceset的id
	private String faceset_name;	//相应faceset的name
	private String name;	//新的name
	private String tag;	//新的tag
	
 	public FacesetSetInfoReq() {
		
	}
	
	public FacesetSetInfoReq(String faceset, boolean isId) {
		if(isId) {
			setFaceset_id(faceset);
		} else {
			setFaceset_name(faceset);
		}
	}
	/**
	 * 相应faceset的id
	 * @return the faceset_id
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	/**
	 * 相应faceset的id
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
	}
	/**
	 * 相应faceset的name
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	/**
	 * 相应faceset的name
	 * @param faceset_name the faceset_name to set
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
	}
	/**
	 * （可选）
	 * 新的name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * （可选）
	 * 新的name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * （可选）
	 * 新的tag
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * （可选）
	 * 新的tag
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
		return "FacesetSetInfoReq [faceset_id=" + faceset_id
				+ ", faceset_name=" + faceset_name + ", name=" + name
				+ ", tag=" + tag + "]";
	}
	
}
