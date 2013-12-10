/*
 * 文件名：GroupAddPersonReq.java
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
 * 功能：想Group中添加Person请求实体类
 * @author xiaoying
 */
public class GroupAddPersonReq extends BaseRequest {
	
	private String group_id;	//相应Group的id
	private String group_name;	//相应Group的name
	private String person_id;	//一组用逗号分隔的person_id，表示将这些Person加入到相应Group中。
	private String person_name;	//一组用逗号分隔的person_name，表示将这些Person加入到相应Group中。
	
 	public GroupAddPersonReq() {
		
	}
	
	public GroupAddPersonReq(String group, boolean isId) {
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
	 * 一组用逗号分隔的person_id，表示将这些Person加入到相应Group中。
	 * @return
	 */
	public String getPerson_id() {
		return person_id;
	}
	
	/**
	 * 一组用逗号分隔的person_id，表示将这些Person加入到相应Group中。
	 * 与setPerson_name(String person_name)只能选其一
	 * @param person_id
	 */
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
		this.person_name = null;
	}
	
	/**
	 * 一组用逗号分隔的person_name，表示将这些Person加入到相应Group中。
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * 一组用逗号分隔的person_name，表示将这些Person加入到相应Group中。
	 * 与setPerson_id(String person_id)只能选其一
	 * @param person_name
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
		this.person_id = null;
	}
	@Override
	public String toString() {
		return "GroupAddPersonReq [group_id=" + group_id + ", group_name="
				+ group_name + ", person_id=" + person_id + ", person_name="
				+ person_name + "]";
	}
}
