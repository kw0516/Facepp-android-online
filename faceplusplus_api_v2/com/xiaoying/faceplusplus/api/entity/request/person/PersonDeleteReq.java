/*
 * 文件名：PersonDeleteReq.java
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
 * 功能：删除Person的请求实体类
 * @author xiaoying
 */
public class PersonDeleteReq extends BaseRequest{
	private String person_name;	//用逗号隔开的待删除的Person id列表
	private String person_id;	//用逗号隔开的待删除的Person name列表
	
	public PersonDeleteReq() {
		
	}
	
	public PersonDeleteReq(String person, boolean isId) {
		if(isId) {
			setPerson_id(person);
		} else {
			setPerson_name(person);
		}
	}
	
	/**
	 * 用逗号隔开的待删除的Person id列表
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * 用逗号隔开的待删除的Person id列表
	 * 与setPerson_id(String person_id)只能选其一
	 * @param person_name
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
		this.person_id = null;
	}
	
	/**
	 * 用逗号隔开的待删除的Person name列表
	 * @return
	 */
	public String getPerson_id() {
		return person_id;
	}
	
	/**
	 * 用逗号隔开的待删除的Person name列表
	 * 与setPerson_name(String person_name)只能选其一
	 * @param person_id
	 */
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
		this.person_name = null;
	}

	@Override
	public String toString() {
		return "PersonDeleteReq [person_name=" + person_name + ", person_id="
				+ person_id + "]";
	}
	
}
