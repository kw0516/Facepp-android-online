/*
 * 文件名：PersonDeleteResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.response.person;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：删除Person的返回实体类
 * @author xiaoying
 */
public class PersonDeleteResp extends BaseResponse {
	private int deleted;	//成功删除的Person数量
	private boolean success;//表示操作是否成功
	
	/**
	 * 成功删除的Person数量
	 * @return
	 */
	public int getDeleted() {
		return deleted;
	}
	
	/**
	 * 成功删除的Person数量
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonDeleteResp [deleted=" + deleted + ", success=" + success
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}
	
	
}
