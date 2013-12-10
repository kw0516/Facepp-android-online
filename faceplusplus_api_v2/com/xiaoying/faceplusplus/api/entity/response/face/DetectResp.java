/*
 * 文件名：DetectResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.response.face;

import java.util.List;

import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：人脸检测返回实体类
 * 
 * @author xiaoying
 */
public class DetectResp extends BaseResponse {
	private String session_id;
	private String img_id;
	private String url;
	private int img_height;
	private int img_width;
	private List<Face> face;
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getImg_height() {
		return img_height;
	}
	public void setImg_height(int img_height) {
		this.img_height = img_height;
	}
	public int getImg_width() {
		return img_width;
	}
	public void setImg_width(int img_width) {
		this.img_width = img_width;
	}
	public List<Face> getFace() {
		return face;
	}
	public void setFace(List<Face> face) {
		this.face = face;
	}
	
	@Override
	public String toString() {
		return "DetectResp [session_id=" + session_id + ", img_id="
				+ img_id + ", url=" + url + ", img_height=" + img_height
				+ ", img_width=" + img_width + ", face=" + face + ", error="
				+ error + ", error_code=" + error_code + "]";
	}
}
