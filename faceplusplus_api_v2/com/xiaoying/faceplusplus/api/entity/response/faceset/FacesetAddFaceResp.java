/*
 * 文件名：FacesetAddFaceResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.faceset;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：Faceset添加Face请求实体类
 * @author xiaoying
 *
 */
public class FacesetAddFaceResp extends BaseResponse {
	private int added;	//成功加入的face数量
	private boolean success;	//表示操作是否成功
	
	
	/**
	 * 成功加入的face数量
	 * @return the added
	 */
	public int getAdded() {
		return added;
	}


	/**
	 * 成功加入的face数量
	 * @param added the added to set
	 */
	public void setAdded(int added) {
		this.added = added;
	}


	/**
	 * 表示操作是否成功
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}


	/**
	 * 表示操作是否成功
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FacesetAddFaceResp [added=" + added + ", success=" + success
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}
	
	
}
