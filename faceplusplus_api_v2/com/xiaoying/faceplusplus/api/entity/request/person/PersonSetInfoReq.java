/*
 * 文件名：PersonSetInfoReq.java
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
 * 功能：设置Person信息的请求实体类
 * @author xiaoying
 */
public class PersonSetInfoReq extends BaseRequest {
	
	private String person_id;	//相应Person的id
	private String person_name;	//相应Person的name
	private String name;	//新的name
	private String tag;	//新的tag

	public PersonSetInfoReq() {
		
	}
	
	public PersonSetInfoReq(String person, boolean isId) {
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
		this.person_name = null;
	}

	/**
	 * 相应Person的name
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}

	/**
	 * 相应Person的name与
	 * 只setPerson_id(String person_id)能选其一
	 * @param person_name
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
		this.person_id = null;
	}

	/**
	 * (可选)
	 * 新的name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * (可选)
	 * 新的name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * (可选)
	 * 新的tag
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * (可选)
	 * 新的tag
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "PersonSetInfoReq [person_id=" + person_id + ", person_name="
				+ person_name + ", name=" + name + ", tag=" + tag + "]";
	}
}
