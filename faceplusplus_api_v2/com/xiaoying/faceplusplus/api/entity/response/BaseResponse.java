/*
 * 文件名：BaseResponse.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity.response;
/**
 * 功能：
 * @author xiaoying
 */
public class BaseResponse {

	protected String error;	//错误描述
	protected int error_code = 0;	//错误代码
	
	/**
	 * 错误描述
	 * @return
	 */
	public String getError() {
		return error;
	}
	
	/**
	 * 错误描述
	 * @param error
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * 错误代码
	 * @return
	 */
	public int getError_code() {
		return error_code;
	}
	
	/**
	 * 错误代码
	 * @param error_code
	 */
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	@Override
	public String toString() {
		return "BaseResponse [error=" + error + ", error_code=" + error_code
				+ "]";
	}
}
