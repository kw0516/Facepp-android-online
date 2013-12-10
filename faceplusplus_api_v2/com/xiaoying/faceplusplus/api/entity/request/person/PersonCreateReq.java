/*
 * 文件名：PersonCreateReq.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.request.person;

import com.xiaoying.faceplusplus.api.entity.request.BaseRequest;

/**
 * 功能：创建Person的请求实体类
 * @author xiaoying
 */
public class PersonCreateReq extends BaseRequest {
	private String person_name;	//Person的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	private String face_id;	//一组用逗号分隔的face_id, 表示将这些Face加入到该Person中
	private String tag;	//Person相关的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255。
	private String group_id;
	private String group_name;	//一组用逗号分割的group id列表或者group name列表。如果该参数被指定，该Person被create之后就会被加入到这些组中。
	
	public PersonCreateReq() {
		
	}
	
	public PersonCreateReq(String person_name) {
		setPerson_name(person_name);
	}
	
	/**
	 * (可选)
	 * Person的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	 * @return
	 */
	public String getPerson_name() {
		return person_name;
	}
	
	/**
	 * (可选)
	 * Person的Name信息，必须在App中全局唯一。Name不能包含^@,&=*'"等非法字符，且长度不得超过255。Name也可以不指定，此时系统将产生一个随机的name。
	 * @return
	 */
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的face_id, 表示将这些Face加入到该Person中
	 * @return
	 */
	public String getFace_id() {
		return face_id;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分隔的face_id, 表示将这些Face加入到该Person中
	 * @return
	 */
	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}
	
	/**
	 * (可选)
	 * Person相关的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255。
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * (可选)
	 * Person相关的tag，不需要全局唯一，不能包含^@,&=*'"等非法字符，长度不能超过255。
	 * @return
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分割的group id列表。如果该参数被指定，该Person被create之后就会被加入到这些组中。
	 * 
	 * @return
	 */
	public String getGroup_id() {
		return group_id;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分割的group id列表。如果该参数被指定，该Person被create之后就会被加入到这些组中。
	 * 与setGroup_name(String group_name)只能选其一
	 * @return
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
		this.group_name = null;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分割的group name列表。如果该参数被指定，该Person被create之后就会被加入到这些组中。
	 * @return
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * (可选)
	 * 一组用逗号分割的group name列表。如果该参数被指定，该Person被create之后就会被加入到这些组中。
	 * 与setGroup_id(String group_id)只能选其一
	 * @return
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
		this.group_id = null;
	}
}
