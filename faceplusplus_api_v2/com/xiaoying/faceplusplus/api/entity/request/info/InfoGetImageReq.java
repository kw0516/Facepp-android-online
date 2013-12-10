/*
 * 文件名：InfoGetImageReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.info;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：查询Image信息请求实体类
 * @author xiaoying
 *
 */
public class InfoGetImageReq extends BaseRequest {
	private String img_id;	//目标图片的img_id
	
	public InfoGetImageReq() {
		
	}
	
	public InfoGetImageReq(String img_id) {
		this.img_id = img_id;
	}

	/**
	 * 目标图片的img_id
	 * @return the img_id
	 */
	public String getImg_id() {
		return img_id;
	}

	/**
	 * 目标图片的img_id
	 * @param img_id the img_id to set
	 */
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetImageReq [img_id=" + img_id + "]";
	}
}
