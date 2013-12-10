/*
 * 文件名：GroupGetInfoReq.java
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
 * 功能：查询Group信息的请求实体类
 * @author xiaoying
 */
public class GroupGetInfoReq extends BaseRequest {
	public static final String GROUP_NONE = "none";
	private String group_id;	//待查询Group的id。开发者也可以指定group_id=none，此时将返回所有未加入任何Group的Person。
	private String group_name;	//待查询Group的name。开发者也可以指定group_id=none，此时将返回所有未加入任何Group的Person。
	
 	public GroupGetInfoReq() {
		
	}
	
 	/**
 	 * 若指定group_id=none，此时将返回所有未加入任何Group的Person。
 	 * @param group
 	 * @param isId
 	 */
	public GroupGetInfoReq(String group, boolean isId) {
		if(isId) {
			setGroup_id(group);
		} else {
			setGroup_name(group);
		}
	}
	
	/**
	 * 待查询Group的id。开发者也可以指定group_id=none，此时将返回所有未加入任何Group的Person。
	 * @return
	 */
	public String getGroup_id() {
		return group_id;
	}
	
	/**
	 * 待查询Group的id。开发者也可以指定group_id=none，此时将返回所有未加入任何Group的Person。
	 * 与setGroup_name(String group_name)只能选其一
	 * @param group_id
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
		this.group_name = null;
	}
	
	/**
	 * 待查询Group的id。开发者也可以指定group_id=none，此时将返回所有未加入任何Group的Person。
	 * @return
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * 待查询Group的id。开发者也可以指定group_id=none，此时将返回所有未加入任何Group的Person。
	 * 与setGroup_id(String group_id)只能选其一
	 * @param group_name
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
		this.group_id = null;
	}
	@Override
	public String toString() {
		return "GroupGetInfoReq [group_id=" + group_id + ", group_name="
				+ group_name + "]";
	}
}
