/*
 * 文件名：PersonCreateResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-11
 * 修改人：xiaoying
 * 修改时间：2013-5-11
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.response.person;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：创建Person的返回实体类
 * @author xiaoying
 */
public class PersonCreateResp extends BaseResponse {
	private int added_group;	//成功被加入的group数量
	private int added_face;	//成功加入的face数量
	private String tag;	//person相关的tag
	private String person_name;	//相应person的name
	private String person_id;	//相应person的id
	
	/**
	 * 成功被加入的group数量
	 * @return
	 */
	public int getAdded_group() {
		return added_group;
	}
	
	/**
	 * 成功被加入的group数量
	 * @param added_group
	 */
	public void setAdded_group(int added_group) {
		this.added_group = added_group;
	}
	
	/**
	 * 成功加入的face数量
	 * @return
	 */
	public int getAdded_face() {
		return added_face;
	}
	
	/**
	 * 成功加入的face数量
	 * @param added_face
	 */
	public void setAdded_face(int added_face) {
		this.added_face = added_face;
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
	 * 相应person的name
	 * @param tag
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * 相应person的name
	 * @param tag
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
		return "PersonCreateResp [added_group=" + added_group + ", added_face="
				+ added_face + ", tag=" + tag + ", person_name=" + person_name
				+ ", person_id=" + person_id + ", error=" + error
				+ ", error_code=" + error_code + "]";
	}
}
