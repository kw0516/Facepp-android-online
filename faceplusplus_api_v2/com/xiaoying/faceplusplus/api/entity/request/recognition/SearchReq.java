/*
 * 文件名：SearchReq.java
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
 * 功能：给定一个Face和一个Faceset，在该Faceset内搜索最相似的Face请求实体类
 * @author xiaoying
 *
 */
public class SearchReq extends BaseRequest {
	
	private String key_face_id;	//待搜索的Face的face_id
 	private String faceset_id; //指定搜索范围的Faceset id
 	private String faceset_name;	//指定搜索范围的Faceset name
	private Integer count;	//表示一共获取不超过count个搜索结果。默认count=3
 	private Boolean async;	//如果置为true，该API将会以异步方式被调用；也就是立即返回一个session id，稍后可通过/info/get_session查询结果。默认值为false。
	
 	public SearchReq() {
 		
 	}
 	
 	public SearchReq(String key_face_id, String faceset, boolean isId) {
 		setKey_face_id(key_face_id);
 		if(isId) {
 			setFaceset_id(faceset);
 		} else {
 			setFaceset_name(faceset);
 		}
 	}
 	
 	/**
	 * 待搜索的Face的face_id
	 * @return the key_face_id
	 */
	public String getKey_face_id() {
		return key_face_id;
	}
	/**
	 * 待搜索的Face的face_id
	 * @param key_face_id the key_face_id to set
	 */
	public void setKey_face_id(String key_face_id) {
		this.key_face_id = key_face_id;
	}
	/**
	 * 指定搜索范围的Faceset id
	 * @return the faceset_id
	 */
	public String getFaceset_id() {
		return faceset_id;
	}
	/**
	 * 指定搜索范围的Faceset id
	 * 与setFaceset_name(String faceset_name)只能选其一
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
		this.faceset_name = null;
	}
	/**
	 * 指定搜索范围的Faceset name
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	/**
	 * 指定搜索范围的Faceset name
	 * 与setFaceset_id(String faceset_id)只能选其一
	 * @param faceset_name the faceset_name to set
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
		this.faceset_id = null;
	}
	/**
	 * (可选)
	 * 表示一共获取不超过count个搜索结果。默认count=3
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * (可选)
	 * 表示一共获取不超过count个搜索结果。默认count=3
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * (可选)
	 * 该API将会以异步方式被调用
	 * @return the async
	 */
	public Boolean getAsync() {
		return async;
	}
	/**
	 * (可选)
	 * 该API将会以异步方式被调用
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
		return "SearchReq [key_face_id=" + key_face_id + ", faceset_id="
				+ faceset_id + ", faceset_name=" + faceset_name + ", count="
				+ count + ", async=" + async + "]";
	}

 	
}
