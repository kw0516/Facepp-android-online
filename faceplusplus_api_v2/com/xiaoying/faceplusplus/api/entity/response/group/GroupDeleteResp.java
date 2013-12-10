/*
 * 文件名：GroupDeleteResp.java
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
 * 功能：Group删除返回实体类
 * @author xiaoying
 */
public class GroupDeleteResp extends BaseResponse {
	private int deleted;	//成功删除的group数量
	private boolean success;	//表示操作是否成功
	
	/**
	 * 成功删除的group数量
	 * @return
	 */
	public int getDeleted() {
		return deleted;
	}
	
	/**
	 * 成功删除的group数量
	 * @param deleted
	 */
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * 表示操作是否成功
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * 表示操作是否成功
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "GroupDeleteResp [deleted=" + deleted + ", success=" + success
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}
}
