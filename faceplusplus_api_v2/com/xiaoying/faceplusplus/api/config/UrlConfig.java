/*
 * 文件名：UrlConfig.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.config;
/**
 * 功能：配置类
 * @author xiaoying
 */
public class UrlConfig {
	
	public static final String CHART_SET = "UTF-8";

	public static final String BASE_URL = "https://apicn.faceplusplus.com/v2";
	
	//Face
	public static final String PATH_DETECT = "/detection/detect";
	
	//Person
	public static final String PATH_PERSON_CREATE = "/person/create";
	
	public static final String PATH_PERSON_DELETE = "/person/delete";
	
	public static final String PATH_PERSON_ADD_FACE = "/person/add_face";
	
	public static final String PATH_PERSON_REMOVE_FACE = "/person/remove_face";
	
	public static final String PATH_PERSON_SET_INFO = "/person/set_info";
	
	public static final String PATH_PERSON_GET_INFO = "/person/get_info";
	
	//Group
	public static final String PATH_GROUP_CREATE = "/group/create";
	
	public static final String PATH_GROUP_DELETE = "/group/delete";
	
	public static final String PATH_GROUP_ADD_PERSON = "/group/add_person";
	
	public static final String PATH_GROUP_REMOVE_PERSON = "/group/remove_person";
	
	public static final String PATH_GROUP_SET_INFO = "/group/set_info";
	
	public static final String PATH_GROUP_GET_INFO = "/group/get_info";
	
	//Faceset
	public static final String PATH_FACESET_CREATE = "/faceset/create";

	public static final String PATH_FACESET_DELETE = "/faceset/delete";
	
	public static final String PATH_FACESET_ADD_FACE = "/faceset/add_face";
	
	public static final String PATH_FACESET_REMOVE_FACE = "/faceset/remove_face";
	
	public static final String PATH_FACESET_SET_INFO = "/faceset/set_info";
	
	public static final String PATH_FACESET_GET_INFO = "/faceset/get_info";

	//Grouping
	public static final String PATH_GROUPING = "/grouping/grouping";
	
	//Info
	public static final String PATH_INFO_GET_IMAGE = "/info/get_image";
	
	public static final String PATH_INFO_GET_FACE = "/info/get_face";
	
	public static final String PATH_INFO_GET_PERSON_LIST = "/info/get_person_list";
	
	public static final String PATH_INFO_GET_FACESET_LIST = "/info/get_faceset_list";
	
	public static final String PATH_INFO_GET_GROUP_LIST = "/info/get_group_list";
	
	public static final String PATH_INFO_GET_SESSION = "/info/get_session";
	
	public static final String PATH_INFO_GET_QUOTA = "/info/get_quota";
	
	public static final String PATH_INFO_GET_APP = "/info/get_app";
	
	//train
	public static final String PATH_TRAIN_VERIFY = "/train/verify";

	public static final String PATH_TRAIN_SEARCH = "/train/search";
	
	public static final String PATH_TRAIN_IDENTIFY = "/train/identify";
	
	//recognition
	public static final String PATH_RECOGNITION_COMPARE = "/recognition/compare";
	
	public static final String PATH_RECOGNITION_VERIFY = "/recognition/verify";
	
	public static final String PATH_RECOGNITION_SEARCH = "/recognition/search";
	
	public static final String PATH_RECOGNITION_IDENTIFY = "/recognition/identify";
	
}
