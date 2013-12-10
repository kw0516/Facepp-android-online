/*
 * 文件名：InfoGetFacesetListResp.java
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

import com.xiaoying.faceplusplus.api.entity.Faceset;
import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询Faceset列表的请求返回类
 * @author xiaoying
 *
 */
public class InfoGetFacesetListResp extends BaseResponse {

	private List<Faceset> faceset;	//faceset列表

	/**
	 * faceset列表
	 * @return the faceset
	 */
	public List<Faceset> getFaceset() {
		return faceset;
	}

	/**
	 * faceset列表
	 * @param faceset the faceset to set
	 */
	public void setFaceset(List<Faceset> faceset) {
		this.faceset = faceset;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetFacesetListResp [faceset=" + faceset + ", error="
				+ error + ", error_code=" + error_code + "]";
	}
	
}
