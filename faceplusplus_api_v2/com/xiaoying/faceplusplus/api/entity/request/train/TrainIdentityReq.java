/*
 * 文件名：TrainIdentityReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.train;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：针对identify功能对一个Group进行训练请求实体类
 * @author xiaoying
 *
 */
public class TrainIdentityReq extends BaseRequest {
	private String group_id;	//识别候选人组成的Group id
	private String group_name;	//识别候选人组成的Group name
	
	public TrainIdentityReq() {
		
	}
	
	public TrainIdentityReq(String group, boolean isId) {
		if(isId) {
			setGroup_id(group);
		} else {
			setGroup_name(group);
		}
	}
	
	/**
	 * 识别候选人组成的Group id
	 * @return the group_id
	 */
	public String getGroup_id() {
		return group_id;
	}
	/**
	 * 识别候选人组成的Group id
	 * 与setGroup_id(String group_id)只能选其一
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
		this.group_name = null;
	}
	/**
	 * 识别候选人组成的Group name
	 * @return the group_name
	 */
	public String getGroup_name() {
		return group_name;
	}
	/**
	 * 识别候选人组成的Group name
	 * 与setGroup_name(String group_name)只能选其一
	 * @param group_name the group_name to set
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
		this.group_id = null;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrainIdentityReq [group_id=" + group_id + ", group_name="
				+ group_name + "]";
	}
	
}
