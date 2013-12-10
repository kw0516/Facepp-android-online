/*
 * 文件名：SearchResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-15
 * 修改人：xiaoying
 * 修改时间：2013-5-15
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.entity.response.recognition;

import java.util.List;

import com.xiaoying.faceplusplus.api.entity.response.BaseResponse;

/**
 * 功能：给定一个Face和一个Faceset，在该Faceset内搜索最相似的Face请求返回实体类
 * @author xiaoying
 *
 */
public class SearchResp extends BaseResponse {
	private String session_id;//相应请求的session标识符，可用于结果查询
	private List<Candidate> candidates;//搜索结果，包含相应face信息与相应的置信度
	
	/**
	 * 相应请求的session标识符，可用于结果查询
	 * @return the session_id
	 */
	public String getSession_id() {
		return session_id;
	}

	/**
	 * 相应请求的session标识符，可用于结果查询
	 * @param session_id the session_id to set
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	/**
	 * 搜索结果，包含相应face信息与相应的置信度
	 * @return the candidates
	 */
	public List<Candidate> getCandidates() {
		return candidates;
	}

	/**
	 * 搜索结果，包含相应face信息与相应的置信度
	 * @param candidates the candidates to set
	 */
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResp [session_id=" + session_id + ", candidates="
				+ candidates + ", error=" + error + ", error_code="
				+ error_code + "]";
	}



	public static class Candidate {
		private String face_id;	//匹配的Face id
        private float similarity;	//匹配人脸和key_face的相似度，是0～100之间的实数
        private String tag;	//匹配人脸相关tag
		/**
		 * 匹配的Face id
		 * @return the face_id
		 */
		public String getFace_id() {
			return face_id;
		}
		/**
		 * 匹配的Face id
		 * @param face_id the face_id to set
		 */
		public void setFace_id(String face_id) {
			this.face_id = face_id;
		}
		/**
		 * 匹配人脸和key_face的相似度，是0～100之间的实数
		 * @return the similarity
		 */
		public float getSimilarity() {
			return similarity;
		}
		/**
		 * 匹配人脸和key_face的相似度，是0～100之间的实数
		 * @param similarity the similarity to set
		 */
		public void setSimilarity(float similarity) {
			this.similarity = similarity;
		}
		/**
		 * 匹配人脸相关tag
		 * @return the tag
		 */
		public String getTag() {
			return tag;
		}
		/**
		 * 匹配人脸相关tag
		 * @param tag the tag to set
		 */
		public void setTag(String tag) {
			this.tag = tag;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Canditate [face_id=" + face_id + ", similarity="
					+ similarity + ", tag=" + tag + "]";
		}
        
	}
}
