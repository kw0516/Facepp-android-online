/*
 * 文件名：InfoGetFaceResp.java
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
import com.xiaoying.faceplusplus.api.entity.Faceset;
import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：查询Face信息请求返回实体类
 * @author xiaoying
 *
 */
public class InfoGetFaceResp extends BaseResponse {

	private List<FaceInfo> face_info; //人脸相关信息
	
	/**
	 * 人脸相关信息
	 * @return the face_info
	 */
	public List<FaceInfo> getFace_info() {
		return face_info;
	}

	/**
	 * 人脸相关信息
	 * @param face_info the face_info to set
	 */
	public void setFace_info(List<FaceInfo> face_info) {
		this.face_info = face_info;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoGetFaceResp [face_info=" + face_info + ", error=" + error
				+ ", error_code=" + error_code + "]";
	}

	public static class FaceInfo {
		private Face face;	//人脸信息
		private List<Person> person;	//包含该face的Person信息
		private List<Faceset> faceset;	//包含该face的faceset信息
		
		
		/**
		 * 人脸信息
		 * @return
		 */
		public Face getFace() {
			return face;
		}
		
		/**
		 * 人脸信息
		 * @param face
		 */
		public void setFace(Face face) {
			this.face = face;
		}

		/**
		 * 包含该face的Person信息
		 * @return the person
		 */
		public List<Person> getPerson() {
			return person;
		}
		/**
		 * 包含该face的Person信息
		 * @param person the person to set
		 */
		public void setPerson(List<Person> person) {
			this.person = person;
		}
		/**
		 * 包含该face的faceset信息
		 * @return the faceset
		 */
		public List<Faceset> getFaceset() {
			return faceset;
		}
		/**
		 * 包含该face的faceset信息
		 * @param faceset the faceset to set
		 */
		public void setFaceset(List<Faceset> faceset) {
			this.faceset = faceset;
		}

		@Override
		public String toString() {
			return "FaceInfo [face=" + face + ", person=" + person
					+ ", faceset=" + faceset + "]";
		}
		
	}
}
