/*
 * 文件名：FacesetCreateReq.java
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
 * 功能：Faceset创建请求实体类
 * @author xiaoying
 *
 */
public class FacesetCreateReq extends BaseRequest {
	private String faceset_name;//Faceset的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	private String face_id;	//一组用逗号分隔的face_id, 表示将这些Face加入到该Faceset中
	private String tag;	//Faceset相关的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255
	
	public FacesetCreateReq() {
		
	}
	
	public FacesetCreateReq(String faceset_name) {
		setFaceset_name(faceset_name);
	}
	
	/**
	 * (可选)
	 * Faceset的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	 * @return
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	
	/**
	 * Faceset的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	 * @param faceset_name
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的face_id, 表示将这些Face加入到该Faceset中
	 * @return
	 */
	public String getFace_id() {
		return face_id;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的face_id, 表示将这些Face加入到该Faceset中
	 * @param face_id
	 */
	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}
	
	/**
	 * (可选)
	 * Faceset相关的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * (可选)
	 * Faceset相关的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "FacesetCreateReq [faceset_name=" + faceset_name + ", face_id="
				+ face_id + ", tag=" + tag + "]";
	}
}
