/*
 * 文件名：Image.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-17
 * 修改人：xiaoying
 * 修改时间：2013-5-17
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity;

import java.io.Serializable;

/**
 * 功能：图片实体类
 * @author xiaoying
 */
public class Image implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -4819969692048074149L;
//	img_id TEXT PRIMARY KEY, img TEXT, url TEXT, width INTEGER, height INTEGER
	private String imageId;
	private String img;
	private String url;
	private int width;
	private int height;
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", img=" + img + ", url=" + url
				+ ", width=" + width + ", height=" + height + "]";
	}
}
