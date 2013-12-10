/*
 * 文件名：VerifyReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.recognition;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：给定一个Face和一个Person，返回是否是同一个人的判断以及置信度请求实体类
 * @author xiaoying
 *
 */
public class VerifyReq extends BaseRequest {
	private String face_id;	//待verify的face_id
	private String person_id;	//对应的Person id
	private String person_name;	//对应的Person name
	
	public VerifyReq() {
		
	}
	
	public VerifyReq(String face_id, String person, boolean isId) {
		setFace_id(face_id);
		if(isId) {
			setPerson_id(person);
		} else {
			setPerson_name(person);
		}
	}
	
	/**
	 * 待verify的face_id
	 * @return the face_id
	 */
	public String getFace_id() {
		return face_id;
	}
	/**
	 * 待verify的face_id
	 * @param face_id the face_id to set
	 */
	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}
	/**
	 * 对应的Person id
	 * @return the person_id
	 */
	public String getPerson_id() {
		return person_id;
	}
	/**
	 * 对应的Person id
	 * 与setPerson_name(String person_name)只能选其一
	 * @param person_id the person_id to set
	 */
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
		this.person_name = null;
	}
	/**
	 * 对应的Person name
	 * @return the person_name
	 */
	public String getPerson_name() {
		return person_name;
	}
	/**
	 * 对应的Person name
	 * 与setPerson_id(String person_id)只能选其一
	 * @param person_name the person_name to set
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
		this.person_id = null;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VerifyReq [face_id=" + face_id + ", person_id=" + person_id
				+ ", person_name=" + person_name + "]";
	}
	
	
}
