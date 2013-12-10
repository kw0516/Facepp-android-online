/*
 * 文件名：GroupDeleteReq.java
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
 * 功能：Group删除请求实体类
 * @author xiaoying
 */
public class GroupDeleteReq extends BaseRequest {
	private String group_name;	//一组用逗号分割的group_id，表示删除这些Group
	private String group_id;	//一组用逗号分割的group_name，表示删除这些Group
	
 	public GroupDeleteReq() {
		
	}
	
	public GroupDeleteReq(String group, boolean isId) {
		if(isId) {
			setGroup_id(group);
		} else {
			setGroup_name(group);
		}
	}
	
	/**
	 * 一组用逗号分割的group_name，表示删除这些Group
	 * 
	 * @return
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * 一组用逗号分割的group_name，表示删除这些Group
	 * 与setGroup_id(String group_id)只能选其一
	 * @param group_name
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
		this.group_id = null;
	}
	
	/**
	 * 一组用逗号分割的group_id，表示删除这些Group
	 * @return
	 */
	public String getGroup_id() {
		return group_id;
	}
	
	/**
	 * 一组用逗号分割的group_id，表示删除这些Group
	 * 与setGroup_id(String group_id)只能选其一
	 * @param group_id
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
		this.group_name = null;
	}
	
	@Override
	public String toString() {
		return "GroupDeleteReq [group_name=" + group_name + ", group_id="
				+ group_id + "]";
	}
}
