/*
 * 文件名：GroupCreateResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.response.group;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：Group创建返回实体类
 * @author xiaoying
 */
public class GroupCreateResp extends BaseResponse {
	
	private String group_name;	//相应group的name
	private String group_id;	//相应group的id
	private String tag;	//group相关的tag
	private int added_person;	//成功加入的person数量
	
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
	 * 成功加入的person数量
	 * @return
	 */
	public int getAdded_person() {
		return added_person;
	}
	
	/**
	 * 成功加入的person数量
	 * @param added_person
	 */
	public void setAdded_person(int added_person) {
		this.added_person = added_person;
	}
	
	@Override
	public String toString() {
		return "GroupCreateResp [group_name=" + group_name + ", group_id="
				+ group_id + ", tag=" + tag + ", added_person=" + added_person
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}
}
