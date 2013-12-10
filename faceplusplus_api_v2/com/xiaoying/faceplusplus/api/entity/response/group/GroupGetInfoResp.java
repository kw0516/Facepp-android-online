/*
 * 文件名：GroupGetInfoResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.group;

import java.util.List;

import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询Group信息请求返回实体类
 * @author xiaoying
 *
 */
public class GroupGetInfoResp extends BaseResponse {
	private String group_name;	//相应group的name
	private String group_id;	//相应group的id
	private String tag;	//group相关的tag
	private List<Person> person;	//属于该group的person信息
	
	/**
	 * 相应group的name
	 * @return
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * 相应group的name
	 * @param group_name
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	/**
	 * 相应group的id
	 * @return
	 */
	public String getGroup_id() {
		return group_id;
	}
	
	/**
	 * 相应group的id
	 * @param group_id
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	/**
	 * group相关的tag
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * group相关的tag
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * 属于该group的person信息
	 * @return
	 */
	public List<Person> getPerson() {
		return person;
	}
	
	/**
	 * 属于该group的person信息
	 * @param person
	 */
	public void setPerson(List<Person> person) {
		this.person = person;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupGetInfoResp [group_name=" + group_name + ", group_id="
				+ group_id + ", tag=" + tag + ", person=" + person + ", error="
				+ error + ", error_code=" + error_code + "]";
	}
	
}
