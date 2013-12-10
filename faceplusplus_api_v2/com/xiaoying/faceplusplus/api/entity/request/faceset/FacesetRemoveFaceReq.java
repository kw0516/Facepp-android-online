/*
 * 文件名：FacesetRemoveFaceReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.faceset;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：Faceset删除Face请求实体类
 * @author xiaoying
 *
 */
public class FacesetRemoveFaceReq extends BaseRequest {
	private String faceset_name;	//相应Faceset的name
	private String faceset_id;	//相应Faceset的id
 	private String face_id;	//一组用逗号分隔的face_id,表示将这些Face加入到相应Faceset中。
 	
 	public FacesetRemoveFaceReq() {
		
	}
	
	public FacesetRemoveFaceReq(String faceset, boolean isId) {
		if(isId) {
			setFaceset_id(faceset);
		} else {
			setFaceset_name(faceset);
		}
	}
	/**
	 * 相应Faceset的name
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	/**
	 * 相应Faceset的name
	 * 与setFaceset_id(String faceset_id)只能选其一
	 * @param faceset_name the faceset_name to set
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
		this.faceset_id = null;
	}
	/**
	 * 相应Faceset的id
	 * @return the faceset_id
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	/**
	 * 相应Faceset的id
	 * 与setFaceset_name(String faceset_name)只能选其一
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
		this.faceset_name = null;
	}
	/**
	 * 一组用逗号分隔的face_id,表示将这些Face加入到相应Faceset中。
	 * @return the face_id
	 */
	public String getFace_id() {
		return face_id;
	}
	/**
	 * 一组用逗号分隔的face_id,表示将这些Face加入到相应Faceset中。
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
		return "FacesetRemoveFaceReq [faceset_name=" + faceset_name
				+ ", faceset_id=" + faceset_id + ", face_id=" + face_id + "]";
	}
}
