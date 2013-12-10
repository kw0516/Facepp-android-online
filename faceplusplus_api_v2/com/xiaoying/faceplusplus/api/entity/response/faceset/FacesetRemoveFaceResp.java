/*
 * 文件名：FacesetRemoveFaceResp.java
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
 * 功能：Faceset删除Face请求返回实体类
 * @author xiaoying
 *
 */
public class FacesetRemoveFaceResp extends BaseResponse {
	private int removed;	//成功删除的face数量
	private boolean success;	//表示操作是否成功
	/**
	 * 成功删除的face数量
	 * @return the removed
	 */
	public int getRemoved() {
		return removed;
	}
	/**
	 * 成功删除的face数量
	 * @param removed the removed to set
	 */
	public void setRemoved(int removed) {
		this.removed = removed;
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
		return "FacesetRemoveFaceResp [removed=" + removed + ", success="
				+ success + ", error=" + error + ", error_code=" + error_code
				+ "]";
	}
}
