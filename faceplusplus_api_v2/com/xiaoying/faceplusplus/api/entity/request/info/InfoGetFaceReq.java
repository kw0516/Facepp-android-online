/*
 * 文件名：InfoGetFaceReq.java
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
 * 功能：查询人脸信息的请求实体类
 * @author xiaoying
 *
 */
public class InfoGetFaceReq extends BaseRequest {
	private String face_id;	//一组用逗号分割的face_id
	
	public InfoGetFaceReq() {
		
	}
	
	public InfoGetFaceReq(String face_id) {
		setFace_id(face_id);
	}

	/**
	 * 一组用逗号分割的face_id
	 * @return the face_id
	 */
	public String getFace_id() {
		return face_id;
	}

	/**
	 * 一组用逗号分割的face_id
	 * @param face_id the face_id to set
	 */
	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetFaceReq [face_id=" + face_id + "]";
	}
	
}
