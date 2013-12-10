/*
 * 文件名：GroupCreateReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.request.group;
/**
 * 功能：Group 创建请求实体类
 * @author xiaoying
 */
public class GroupCreateReq {
	private String group_name; //Group的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	//可选	
	private String tag;//Group的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255。
	private String person_id;//一组用逗号分隔的person_id,表示将这些Person加入到该Group中。注意，一个Person可以被加入到多个Group中。
	private String person_name; //一组用逗号分隔的person_name, 表示将这些Person加入到该Group中。注意，一个Person可以被加入到多个Group中。
	
 	public GroupCreateReq() {
		
	}
	
	public GroupCreateReq(String group_name) {
		setGroup_name(group_name);
	}
	
	/**
	 * Group的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	 * @return
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * Group的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	 * @param group_name
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	/**
	 * (可选)
	 * Group的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255。
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * (可选)
	 * Group的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255。
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的person_id,表示将这些Person加入到该Group中。注意，一个Person可以被加入到多个Group中。
	 * @return
	 */
	public String getPerson_id() {
		return person_id;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的person_id,表示将这些Person加入到该Group中。注意，一个Person可以被加入到多个Group中。
	 * 与setPerson_name(String person_name)只能选其一
	 * @param person_id
	 */
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
		this.person_name = null;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的person_name, 表示将这些Person加入到该Group中。注意，一个Person可以被加入到多个Group中。
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的person_name, 表示将这些Person加入到该Group中。注意，一个Person可以被加入到多个Group中。
	 * 与setPerson_id(String person_id)只能选其一
	 * @param person_name
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
		this.person_id = null;
	}
	
	@Override
	public String toString() {
		return "GroupCreateReq [group_name=" + group_name + ", tag=" + tag
				+ ", person_id=" + person_id + ", person_name=" + person_name
				+ "]";
	}
}
