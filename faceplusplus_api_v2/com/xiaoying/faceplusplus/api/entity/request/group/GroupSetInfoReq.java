/*
 * 文件名：GroupSetInfoReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.request.group;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：设置Group信息的请求实体类
 * @author xiaoying
 */
public class GroupSetInfoReq extends BaseRequest {
	private String group_id;	//相应Group的id
	private String group_name;	//相应Group的name
	//可选	
	private String name;	//新的group_name
	private String tag;	//新的tag
	
	public GroupSetInfoReq() {
		
	}
	
	public GroupSetInfoReq(String group, boolean isId) {
		if(isId) {
			setGroup_id(group);
		} else {
			setGroup_name(group);
		}
	}
	
	/**
	 * 相应Group的id
	 * @return
	 */
	public String getGroup_id() {
		return group_id;
	}
	
	/**
	 * 相应Group的id
	 * 与setGroup_name(String group_name)只能选其一
	 * @param group_id
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
		this.group_name = null;
	}
	
	/**
	 * 相应Group的name
	 * @return
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * 相应Group的name
	 * 与setGroup_id(String group_id)只能选其一
	 * @param group_name
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
		this.group_id = null;
	}
	
	/**
	 * (可选)
	 * 新的group_name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * (可选)
	 * 新的group_name
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
		return "GroupSetInfoReq [group_id=" + group_id + ", group_name="
				+ group_name + ", name=" + name + ", tag=" + tag + "]";
	}
	
}
