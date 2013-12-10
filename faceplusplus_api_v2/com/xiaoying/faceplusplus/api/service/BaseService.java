/*
 * 文件名：BaseService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.service;

import com.xiaoying.faceplusplus.api.cliet.Client;

/**
 * 功能：服务Base类
 * @author xiaoying
 */
public class BaseService {
	protected Client client = null;
	
	public BaseService(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "BaseService [client=" + client + "]";
	}
}
