/*
 * 文件名：GroupSetInfoResp.java
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
 * 功能：
 * @author xiaoying
 */
public class GroupSetInfoResp extends BaseResponse {
	private String group_name;	//相应group的name
	private String group_id;	//相应group的id
	private String tag;	//group相关的tag
	
	/**
	 * 相应group的name
	 * @return
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * 相应group的name
	 * 与setGroup_id(String group_id)只能选其一
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
	 * 与setGroup_name(String group_name)只能选其一
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
	@Override
	public String toString() {
		return "GroupSetInfoResp [group_name=" + group_name + ", group_id="
				+ group_id + ", tag=" + tag + ", error=" + error
				+ ", error_code=" + error_code + "]";
	}
}
