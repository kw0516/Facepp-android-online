/*
 * 文件名：PersonGetInfoReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.request.person;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：Person信息查询请求类
 * @author xiaoying
 */
public class PersonGetInfoReq extends BaseRequest {
	private String person_id;	//相应Person的id
	private String person_name;	//相应Person的name
	
	public PersonGetInfoReq() {
		
	}
	
	public PersonGetInfoReq(String person, boolean isId) {
		if(isId) {
			setPerson_id(person);
		} else {
			setPerson_name(person);
		}
	}
	
	/**
	 * 相应Person的id
	 * @return
	 */
	public String getPerson_id() {
		return person_id;
	}
	
	/**
	 * 相应Person的id
	 * 与setPerson_name(String person_name)只能选其一
	 * @param person_id
	 */
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	
	/**
	 * 相应Person的name
	 * 
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * 相应Person的name
	 * 与setPerson_id(String person_id)只能选其一
	 * @param person_name
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	@Override
	public String toString() {
		return "PersonGetInfoReq [person_id=" + person_id + ", person_name="
				+ person_name + "]";
	}
	
	
}
