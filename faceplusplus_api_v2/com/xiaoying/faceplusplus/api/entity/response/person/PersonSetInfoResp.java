/*
 * 文件名：PersonSetInfoResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.response.person;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：设置Person信息的返回实体类
 * @author xiaoying
 */
public class PersonSetInfoResp extends BaseResponse {
	private String tag;	//person相关的tag
	private String person_name;	//相应person的name
	private String person_id;	//相应person的id
	
	/**
	 * person相关的tag
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * person相关的tag
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * 相应person的name
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * 相应person的name
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	
	/**
	 * 相应person的id
	 * @return
	 */
	public String getPerson_id() {
		return person_id;
	}
	
	/**
	 * 相应person的id
	 * @param person_id
	 */
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonSetInfoResp [tag=" + tag + ", person_name=" + person_name
				+ ", person_id=" + person_id + ", error=" + error
				+ ", error_code=" + error_code + "]";
	}
	
	
}
