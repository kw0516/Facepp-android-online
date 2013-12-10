/*
 * 文件名：InfoGetAppResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.info;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询App信息
 * @author xiaoying
 *
 */
public class InfoGetAppResp extends BaseResponse {
	private String name;//App的名称信息
	private String description;//App的描述信息
	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetAppResp [name=" + name + ", description=" + description
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}
}
