/*
 * 文件名：Faceset.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：Faceset实体类
 * @author xiaoying
 *
 */
public class Faceset implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2977773864016787357L;
	private String faceset_name;	//相应Faceset的name
	private String faceset_id;	//相应Faceset的id
	private String tag;	//Faceset相关的tag
	private int face_count;	//这个Faceset下包含的face个数
	private List<Face> face;	//属于该faceset的face信息
	/**
	 * 相应Faceset的name
	 * @return the faceset_name
	 */
	public String getFaceset_name() {
		return faceset_name;
	}
	/**
	 * 相应Faceset的name
	 * @param faceset_name the faceset_name to set
	 */
	public void setFaceset_name(String faceset_name) {
		this.faceset_name = faceset_name;
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
	 * @param faceset_id the faceset_id to set
	 */
	public void setFaceset_id(String faceset_id) {
		this.faceset_id = faceset_id;
	}
	/**
	 * Faceset相关的tag
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * Faceset相关的tag
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * 这个Faceset下包含的face个数
	 * @return the face_count
	 */
	public int getFace_count() {
		return face_count;
	}
	/**
	 * 这个Faceset下包含的face个数
	 * @param face_count the face_count to set
	 */
	public void setFace_count(int face_count) {
		this.face_count = face_count;
	}
	/**
	 * 属于该faceset的face信息
	 * @return the face
	 */
	public List<Face> getFace() {
		return face;
	}
	/**
	 * 属于该faceset的face信息
	 * @param face the face to set
	 */
	public void setFace(List<Face> face) {
		this.face = face;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Faceset [faceset_name=" + faceset_name + ", faceset_id="
				+ faceset_id + ", tag=" + tag + ", face_count=" + face_count
				+ ", face=" + face + "]";
	}
}
