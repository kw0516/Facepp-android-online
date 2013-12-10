/*
 * 文件名：CompareResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.recognition;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：计算两个Face的相似性以及五官相似度请求返回实体类
 * @author xiaoying
 *
 */
public class CompareResp extends BaseResponse {
	private String session_id;	//请求的session_id
	private float similarity;	//一个0~100之间的实数，表示两个face的相似性
	private ComponentSimilarity component_similarity;	//包含人脸中各个部位的相似性，目前包含eyebrow(眉毛)、eye(眼睛)、nose(鼻子)与mouth（嘴）的相似性。每一项的值为一个0~100之间的实数，表示相应部位的相似性

	/**
	 * 请求的session_id
	 * @return the session_id
	 */
	public String getSession_id() {
		return session_id;
	}

	/**
	 * 请求的session_id
	 * @param session_id the session_id to set
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	/**
	 * 一个0~100之间的实数，表示两个face的相似性
	 * @return the similarity
	 */
	public float getSimilarity() {
		return similarity;
	}

	/**
	 * 一个0~100之间的实数，表示两个face的相似性
	 * @param similarity the similarity to set
	 */
	public void setSimilarity(float similarity) {
		this.similarity = similarity;
	}

	/**
	 * 包含人脸中各个部位的相似性，目前包含eyebrow(眉毛)、eye(眼睛)、nose(鼻子)与mouth（嘴）的相似性。每一项的值为一个0~100之间的实数，表示相应部位的相似性
	 * @return the component_similarity
	 */
	public ComponentSimilarity getComponent_similarity() {
		return component_similarity;
	}

	/**
	 * 包含人脸中各个部位的相似性，目前包含eyebrow(眉毛)、eye(眼睛)、nose(鼻子)与mouth（嘴）的相似性。每一项的值为一个0~100之间的实数，表示相应部位的相似性
	 * @param component_similarity the component_similarity to set
	 */
	public void setComponent_similarity(ComponentSimilarity component_similarity) {
		this.component_similarity = component_similarity;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompareResp [session_id=" + session_id + ", similarity="
				+ similarity + ", component_similarity=" + component_similarity
				+ ", error=" + error + ", error_code=" + error_code + "]";
	}


	public static class ComponentSimilarity {
		private float eye;		//eye(眼睛)的相似性,值为一个0~100之间的实数
		private float mouth;	//mouth（嘴）的相似性,值为一个0~100之间的实数
		private float nose;		//nose(鼻子)的相似性,值为一个0~100之间的实数
		private float eyebrow;	//eyebrow(眉毛)的相似性,值为一个0~100之间的实数
		/**
		 * eye(眼睛)的相似性,值为一个0~100之间的实数
		 * @return the eye
		 */
		public float getEye() {
			return eye;
		}
		/**
		 * eye(眼睛)的相似性,值为一个0~100之间的实数
		 * @param eye the eye to set
		 */
		public void setEye(float eye) {
			this.eye = eye;
		}
		/**
		 * mouth（嘴）的相似性,值为一个0~100之间的实数
		 * @return the mouth
		 */
		public float getMouth() {
			return mouth;
		}
		/**
		 * mouth（嘴）的相似性,值为一个0~100之间的实数
		 * @param mouth the mouth to set
		 */
		public void setMouth(float mouth) {
			this.mouth = mouth;
		}
		/**
		 * nose(鼻子)的相似性,值为一个0~100之间的实数
		 * @return the nose
		 */
		public float getNose() {
			return nose;
		}
		/**
		 * nose(鼻子)的相似性,值为一个0~100之间的实数
		 * @param nose the nose to set
		 */
		public void setNose(float nose) {
			this.nose = nose;
		}
		/**
		 * eyebrow(眉毛)的相似性,值为一个0~100之间的实数
		 * @return the eyebrow
		 */
		public float getEyebrow() {
			return eyebrow;
		}
		/**
		 * eyebrow(眉毛)的相似性,值为一个0~100之间的实数
		 * @param eyebrow the eyebrow to set
		 */
		public void setEyebrow(float eyebrow) {
			this.eyebrow = eyebrow;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ComponetSimilarity [eye=" + eye + ", mouth=" + mouth
					+ ", nose=" + nose + ", eyebrow=" + eyebrow + "]";
		}
		
	}
}
