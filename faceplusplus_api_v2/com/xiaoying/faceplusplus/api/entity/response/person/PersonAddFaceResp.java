/*
 * 文件名：PersonAddFaceResp.java
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
 * 功能：从Person中删除人脸返回实体类
 * @author xiaoying
 */
public class PersonAddFaceResp extends BaseResponse {
	private int added; //成功加入的face数量
	private boolean success; //表示操作是否成功
	
	/**
	 * 成功加入的face数量
	 * @return
	 */
	public int getAdded() {
		return added;
	}
	
	/**
	 * 成功加入的face数量
	 * @param added
	 */
	public void setAdded(int added) {
		this.added = added;
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
		return "PersonAddFaceResp [added=" + added + ", success=" + success
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}
	
}
