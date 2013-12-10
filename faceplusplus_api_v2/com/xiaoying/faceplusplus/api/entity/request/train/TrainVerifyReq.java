/*
 * 文件名：TrainVerifyReq.java
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
 * 功能：TrainVerify请求实体类
 * @author xiaoying
 *
 */
public class TrainVerifyReq extends BaseRequest {
	private String person_id;	//验证对象person_id
	private String person_name;	//验证对象person_name
	
	public TrainVerifyReq() {
		
	}
	
	public TrainVerifyReq(String person, boolean isId) {
		if(isId) {
			setPerson_id(person);
		} else {
			setPerson_name(person);
		}
	}
	/**
	 * 验证对象person_id
	 * @return the person_id
	 */
	public String getPerson_id() {
		return person_id;
	}
	/**
	 * 验证对象person_id
	 * 与setPerson_name(String person_name)只能选其一
	 * @param person_id the person_id to set
	 */
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
		this.person_name = null;
	}
	/**
	 * 验证对象person_name
	 * @return the person_name
	 */
	public String getPerson_name() {
		return person_name;
	}
	/**
	 * 验证对象person_name
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
		return "TrainVerifyReq [person_id=" + person_id + ", person_name="
				+ person_name + "]";
	}
	
	
}
