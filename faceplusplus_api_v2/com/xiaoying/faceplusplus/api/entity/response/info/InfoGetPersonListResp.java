/*
 * 文件名：InfoGetPersonListResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.info;

import java.util.List;

import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询Person列表的请求返回实体类
 * @author xiaoying
 *
 */
public class InfoGetPersonListResp extends BaseResponse {
	
	private List<Person> person; 	//person列表

	/**
	 * person列表
	 * @return the person
	 */
	public List<Person> getPerson() {
		return person;
	}

	/**
	 * person列表
	 * @param person the person to set
	 */
	public void setPerson(List<Person> person) {
		this.person = person;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetPersonListResp [person=" + person + ", error=" + error
				+ ", error_code=" + error_code + "]";
	}
	
}
