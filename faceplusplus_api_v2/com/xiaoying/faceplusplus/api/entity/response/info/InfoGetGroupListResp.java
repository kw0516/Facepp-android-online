/*
 * 文件名：InfoGetGroupListResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.info;

import java.util.List;

import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询所有Group请求返回实体类
 * @author xiaoying
 *
 */
public class InfoGetGroupListResp extends BaseResponse {

	private List<Group> group;	//Group列表

	/**
	 * Group列表
	 * @return the group
	 */
	public List<Group> getGroup() {
		return group;
	}

	/**
	 * Group列表
	 * @param group the group to set
	 */
	public void setGroup(List<Group> group) {
		this.group = group;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetGroupListResp [group=" + group + ", error=" + error
				+ ", error_code=" + error_code + "]";
	}
}
