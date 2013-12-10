/*
 * 文件名：InfoGetImageResp.java
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

import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询Image信息请求返回实体类
 * @author xiaoying
 *
 */
public class InfoGetImageResp extends BaseResponse {
	private String img_id; //Face++系统中的图片标识符，用于标识用户请求中的图片
	private String url; //请求中图片的url
	private List<Face> face; //被检测出的人脸的列表
	/**
	 * 
	 * @return the img_id
	 */
	public String getImg_id() {
		return img_id;
	}
	/**
	 * 
	 * @param img_id the img_id to set
	 */
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	/**
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 
	 * @return the face
	 */
	public List<Face> getFace() {
		return face;
	}
	/**
	 * 
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
		return "InfoGetImageResp [img_id=" + img_id + ", url=" + url
				+ ", face=" + face + ", error=" + error + ", error_code="
				+ error_code + "]";
	}
}
