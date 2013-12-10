/*
 * 文件名：Client.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.cliet;

import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：客户端工具
 * @author xiaoying
 *
 */
public class Client {
	private String appKey;	//APP_KEY
	private String appSecret;	//APP_SECREET
	
	public Client(String appKey, String appSecret) {
		if(StringUtil.isEmpty(appKey) || StringUtil.isEmpty(appSecret)) {
			throw new IllegalArgumentException("APP_KEY and APP_Secret must be not null.");
		}
		this.appKey = appKey;
		this.appSecret = appSecret;
	}
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	@Override
	public String toString() {
		return "Client [appKey=" + appKey + ", appSecret=" + appSecret + "]";
	}
}
