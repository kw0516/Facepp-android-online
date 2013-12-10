/*
 * 文件名：IdentityReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.request.recognition;

import java.io.File;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：对于一个待查询的Face列表（或者对于给定的Image中所有的Face），在一个Group中查询最相似的Person的请求实体类
 * @author xiaoying
 *
 */
public class IdentityReq extends BaseRequest {
	private String group_id; //识别候选人组成的Group id
	private String group_name;	//识别候选人组成的Group name
//	可选	
	private String url;	//待识别图片的URL
	private File img;	// 待识别图片通过POST方法上传的二进制数据
	private String mode;	//检测模式可以是normal(默认) 或者 oneface 。在oneface模式中，检测器仅找出图片中最大的一张脸。仅当给出了url或img时，本选项有效。
	private String key_face_id;	//开发者也可以指定一个face_id的列表来表明对这些face进行识别。可以设置此参数key_face_id为一个逗号隔开的face_id列表。
	private Boolean async;	//如果置为true，该API将会以异步方式被调用；也就是立即返回一个session id，稍后可通过/info/get_session查询结果。默认值为false。
	
	public IdentityReq() {
		
	}
	
	public IdentityReq(String group, boolean isId) {
		if(isId) {
			setGroup_id(group);
		} else {
			setGroup_name(group);
		}
	}
	
	/**
	 * 识别候选人组成的Group id
	 * @return the group_id
	 */
	public String getGroup_id() {
		return group_id;
	}
	/**
	 * 识别候选人组成的Group id
	 * 与setGroup_name(String group_name)只能选其一
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	/**
	 * 识别候选人组成的Group name
	 * @return the group_name
	 */
	public String getGroup_name() {
		return group_name;
	}
	/**
	 * 识别候选人组成的Group name
	 * 与setGroup_id(String group_id)只能选其一
	 * @param group_name the group_name to set
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	/**
	 * (可选)
	 * 待识别图片的URL
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * (可选)
	 * 待识别图片的URL
	 * 与setImg(File img)只能选其一
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * (可选)
	 * 待识别图片通过POST方法上传的二进制数据
	 * @return the img
	 */
	public File getImg() {
		return img;
	}
	/**
	 * (可选)
	 * 待识别图片通过POST方法上传的二进制数据
	 * 与setUrl(String url)只能选其一
	 * @param img the img to set
	 */
	public void setImg(File img) {
		this.img = img;
	}
	/**
	 * (可选)
	 * 检测模式可以是normal(默认) 或者 oneface 。在oneface模式中，检测器仅找出图片中最大的一张脸。仅当给出了url或img时，本选项有效。
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * (可选)
	 * 检测模式可以是normal(默认) 或者 oneface 。在oneface模式中，检测器仅找出图片中最大的一张脸。仅当给出了url或img时，本选项有效。
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * (可选)
	 * 开发者也可以指定一个face_id的列表来表明对这些face进行识别。可以设置此参数key_face_id为一个逗号隔开的face_id列表。
	 * @return the key_face_id
	 */
	public String getKey_face_id() {
		return key_face_id;
	}
	/**
	 * (可选)
	 * 开发者也可以指定一个face_id的列表来表明对这些face进行识别。可以设置此参数key_face_id为一个逗号隔开的face_id列表。
	 * @param key_face_id the key_face_id to set
	 */
	public void setKey_face_id(String key_face_id) {
		this.key_face_id = key_face_id;
	}
	/**
	 * (可选)
	 * 如果置为true，该API将会以异步方式被调用；也就是立即返回一个session id，稍后可通过/info/get_session查询结果。默认值为false。
	 * @return the async
	 */
	public Boolean getAsync() {
		return async;
	}
	/**
	 * (可选)
	 * 如果置为true，该API将会以异步方式被调用；也就是立即返回一个session id，稍后可通过/info/get_session查询结果。默认值为false。
	 * @param async the async to set
	 */
	public void setAsync(Boolean async) {
		this.async = async;
	}
	
	
}
