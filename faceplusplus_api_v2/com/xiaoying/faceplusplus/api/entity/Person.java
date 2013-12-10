/*
 * 文件名：Person.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：人实体类
 * @author xiaoying
 */
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7808261630401601077L;
	private String person_name;	//相应person的name
	private String person_id;	//相应person的id
	private String tag;			//person相关的tag
	
	private List<Face> face;	//添加到Person的Face
	
	private List<Group> group;	//已加入的Group
	
	/**
	 * 相应person的name
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * 相应person的name
	 * @param person_name
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
	 * 添加到Person的Face
	 * 只返回Face的face_id,tag
	 * @return
	 */
	public List<Face> getFace() {
		return face;
	}

	/**
	 * 添加到Person的Face
	 * 只返回Face的face_id,tag
	 * @param face
	 */
	public void setFace(List<Face> face) {
		this.face = face;
	}

	/**
	 * 已加入的Group
	 * 只返回Group的group_id,group_name,tag
	 * @return
	 */
	public List<Group> getGroup() {
		return group;
	}

	/**
	 * 已加入的Group
	 * 只返回Group的group_id,group_name,tag
	 * @param group
	 */
	public void setGroup(List<Group> group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "PersonGetInfoResp [person_name=" + person_name + ", person_id="
				+ person_id + ", tag=" + tag + ", face=" + face + ", group="
				+ group + "]";
	}
}
