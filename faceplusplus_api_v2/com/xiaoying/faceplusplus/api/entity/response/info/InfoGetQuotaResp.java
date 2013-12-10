/*
 * 文件名：InfoGetQuotaResp.java
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
 * 功能：查询Quota使用情况请求返回实体类
 * @author xiaoying
 *
 */
public class InfoGetQuotaResp extends BaseResponse {
	private int total;	//当月quota使用上限额度
	private int used;	//已用quota
	private int exceed;	//超额使用量
	
	
	/**
	 * 当月quota使用上限额度
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}


	/**
	 * 当月quota使用上限额度
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}


	/**
	 * 已用quota
	 * @return the used
	 */
	public int getUsed() {
		return used;
	}


	/**
	 * 已用quota
	 * @param used the used to set
	 */
	public void setUsed(int used) {
		this.used = used;
	}


	/**
	 * 超额使用量
	 * @return the exceed
	 */
	public int getExceed() {
		return exceed;
	}


	/**
	 * 超额使用量
	 * @param exceed the exceed to set
	 */
	public void setExceed(int exceed) {
		this.exceed = exceed;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetQuotaResp [total=" + total + ", used=" + used
				+ ", exceed=" + exceed + ", error=" + error + ", error_code="
				+ error_code + "]";
	}
}
