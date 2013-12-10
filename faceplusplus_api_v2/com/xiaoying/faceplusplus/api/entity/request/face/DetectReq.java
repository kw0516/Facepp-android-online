/*
 * 文件名：DetectReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.request.face;

import java.io.File;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：
 * @author xiaoying
 */
public class DetectReq extends BaseRequest {
	
	public static final String DETECT_MODE_NORMAL = "normal";
	public static final String DETECT_MODE_ONEFACE = "oneface";
	
	public static final String ATTRIBUTE_ALL = "all";
	public static final String ATTRIBUTE_NONE = "none";
	public static final String ATTRIBUTE_GENDER = "gender";
	public static final String ATTRIBUTE_AGE = "age";
	public static final String ATTRIBUTE_RACE = "race";
	
	private String url;	//url 或 img[POST]	待检测图片的URL 或者 通过POST方法上传的二进制数据，原始图片大小需要小于3M
	private File img;
	//以下是可选
	private String mode;	//检测模式可以是normal(默认) 或者 oneface 。在oneface模式中，检测器仅找出图片中最大的一张脸。
	private String attribute;	//可以是 all(默认) 或者 none或者由逗号分割的属性列表。目前支持的属性包括：gender, age, race
	private String tag;	//可以为图片中检测出的每一张Face指定一个不包含^@,&=*'"等非法字符且不超过255字节的字符串作为tag，tag信息可以通过 /info/get_face 查询
	private Boolean async = false;	//如果置为true，该API将会以异步方式被调用；也就是立即返回一个session id，稍后可通过/info/get_session查询结果。默认值为false。

	public DetectReq() {
		
	}
	
	public DetectReq(String url) {
		setUrl(url);
	}
	
	public DetectReq(File img) {
		setImg(img);
	}

	/**
	 * 待检测图片的URL
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 待检测图片的URL,
	 * 与setImg(File img)两者只能选其一
	 */
	public void setUrl(String url) {
		this.url = url;
		this.img = null;
	}

	/**
	 * 通过POST方法上传的二进制数据(图片文件File)
	 * @return
	 */
	public File getImg() {
		return img;
	}

	/**
	 * 通过POST方法上传的二进制数据(图片文件File),原始图片大小需要小于3M
	 * 与setUrl(String url)两者只能选其一
	 * @param img
	 */
	public void setImg(File img) {
		this.img = img;
		this.url = null;
	}

	/**
	 * (可选)
	 * 检测模式可以是normal(默认) 或者 oneface 。在oneface模式中，检测器仅找出图片中最大的一张脸。
	 * @return
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * (可选)
	 * 检测模式可以是normal(默认) 或者 oneface 。在oneface模式中，检测器仅找出图片中最大的一张脸。
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * (可选)
	 * 可以是 all(默认) 或者 none或者由逗号分割的属性列表。目前支持的属性包括：gender, age, race
	 * @return
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * (可选)
	 * 可以是 all(默认) 或者 none或者由逗号分割的属性列表。目前支持的属性包括：gender, age, race
	 * @param attribute
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * (可选)
	 * 可以为图片中检测出的每一张Face指定一个不包含^@,&=*'"等非法字符且不超过255字节的字符串作为tag
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * (可选)
	 * 可以为图片中检测出的每一张Face指定一个不包含^@,&=*'"等非法字符且不超过255字节的字符串作为tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * (可选)
	 * 如果置为true，该API将会以异步方式被调用
	 * @return
	 */
	public Boolean isAsync() {
		return async;
	}

	/**
	 * (可选)
	 * 如果置为true，该API将会以异步方式被调用
	 * @param async
	 */
	public void setAsync(Boolean async) {
		this.async = async;
	}

	@Override
	public String toString() {
		return "DetectFaceReq [url=" + url + ", img=" + img + ", mode=" + mode
				+ ", attribute=" + attribute + ", tag=" + tag + ", async="
				+ async + "]";
	}
}
