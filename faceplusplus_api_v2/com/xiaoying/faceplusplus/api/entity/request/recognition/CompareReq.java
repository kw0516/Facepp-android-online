/*
 * 文件名：CompareReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.recognition;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：计算两个Face的相似性以及五官相似度请求实体类
 * @author xiaoying
 *
 */
public class CompareReq extends BaseRequest {
	private String face_id1;	//第一个Face的face_id
	private String face_id2;	//第二个Face的face_id
	private Boolean async;		//该API是否为异步调用
	
	public CompareReq() {
		
	}
	
	public CompareReq(String face_id1, String face_id2) {
		this.face_id1 = face_id1;
		this.face_id2 = face_id2;
	}
	/**
	 * 第一个Face的face_id
	 * @return the face_id1
	 */
	public String getFace_id1() {
		return face_id1;
	}
	/**
	 * 第一个Face的face_id
	 * @param face_id1 the face_id1 to set
	 */
	public void setFace_id1(String face_id1) {
		this.face_id1 = face_id1;
	}
	/**
	 * 第二个Face的face_id
	 * @return the face_id2
	 */
	public String getFace_id2() {
		return face_id2;
	}
	/**
	 * 第二个Face的face_id
	 * @param face_id2 the face_id2 to set
	 */
	public void setFace_id2(String face_id2) {
		this.face_id2 = face_id2;
	}
	
	/**
	 * （可选）
	 * 如果置为true，该API将会以异步方式被调用；也就是立即返回一个session id，稍后可通过/info/get_session查询结果。默认值为false。
	 * @return the async
	 */
	public Boolean getAsync() {
		return async;
	}
	
	/**
	 * （可选）
	 * 如果置为true，该API将会以异步方式被调用；也就是立即返回一个session id，稍后可通过/info/get_session查询结果。默认值为false。
	 * @param async the async to set
	 */
	public void setAsync(Boolean async) {
		this.async = async;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompareReq [face_id1=" + face_id1 + ", face_id2=" + face_id2
				+ ", async=" + async + "]";
	}
	
	
}
