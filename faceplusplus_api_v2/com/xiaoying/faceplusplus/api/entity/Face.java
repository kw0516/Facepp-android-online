/*
 * 文件名：Face.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity;

import java.io.Serializable;

/**
 * 功能：人脸实体类
 * @author xiaoying
 */
public class Face implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8603825983713166688L;
	// face array 被检测出的人脸的列表
	private String face_id; //被检测出的每一张人脸都在Face++系统中的标识符
	private String face_data; //人脸本地截图的路径，如果有的话，没有为空
	private String img_id; //人脸相关的图片id
	private String url;	//相关图片的url
	
	private float width; // 0~100之间的实数，表示检出的脸的宽度在图片中百分比
	private float height; // 0~100之间的实数，表示检出的脸的高度在图片中百分比
	private PointF center; // 检出的人脸框的中心点坐标, x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	private PointF eye_left; // 相应人脸的左眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	private PointF eye_right; // 相应人脸的右眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	private PointF mouth_left; // 相应人脸的左侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	private PointF mouth_right; // 相应人脸的右侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	private PointF nose; // 相应人脸的鼻尖坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比 (0~100之间的实数)
	
	// attribute object 包含一系列人脸的属性分析结果 gender object 包含性别分析结果，value的值为Male/Female, confidence表示置信度
	private Gender gender;
	// age object 包含年龄分析结果，value的值为一个非负整数表示估计的年龄, range表示估计年龄的正负区间
	private Age age;
	// race object 包含人种分析结果，value的值为Asian/White/Black, confidence表示置信度
	private Race race;
	
	private String tag;	//人脸描述
	
	/**
	 * 被检测出的每一张人脸都在Face++系统中的标识符
	 * @return
	 */
	public String getFace_id() {
		return face_id;
	}

	/**
	 * 被检测出的每一张人脸都在Face++系统中的标识符
	 * @param face_id
	 */
	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}
	
	

	/**
	 * 人脸本地截图的路径，如果有的话，没有为空
	 * @return the face_data
	 */
	public String getFace_data() {
		return face_data;
	}

	/**
	 * 人脸本地截图的路径，如果有的话，没有为空
	 * @param face_data the face_data to set
	 */
	public void setFace_data(String face_data) {
		this.face_data = face_data;
	}

	/**
	 * 人脸相关的图片id
	 * @return
	 */
	public String getImg_id() {
		return img_id;
	}

	/**
	 * 人脸相关的图片id
	 * @param img_id
	 */
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	
	/**
	 * 图片url
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 图片url
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 0~100之间的实数，表示检出的脸的宽度在图片中百分比
	 * @return
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * 0~100之间的实数，表示检出的脸的宽度在图片中百分比
	 * @param width
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	
	/**
	 * 0~100之间的实数，表示检出的脸的高度在图片中百分比
	 * @return
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * 0~100之间的实数，表示检出的脸的高度在图片中百分比
	 * @param height
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	
	/**
	 * 检出的人脸框的中心点坐标, x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @return
	 */
	public PointF getCenter() {
		return center;
	}
	
	/**
	 * 检出的人脸框的中心点坐标, x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @param center
	 */
	public void setCenter(PointF center) {
		this.center = center;
	}
	/**
	 *  相应人脸的左眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @return
	 */
	public PointF getEye_left() {
		return eye_left;
	}
	
	/**
	 *  相应人脸的左眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @param eye_left
	 */
	public void setEye_left(PointF eye_left) {
		this.eye_left = eye_left;
	}
	
	/**
	 * 相应人脸的右眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @return
	 */
	public PointF getEye_right() {
		return eye_right;
	}
	
	/**
	 * 相应人脸的右眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @param eye_right
	 */
	public void setEye_right(PointF eye_right) {
		this.eye_right = eye_right;
	}
	
	/**
	 * 相应人脸的左侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @return
	 */
	public PointF getMouth_left() {
		return mouth_left;
	}
	
	/**
	 * 相应人脸的左侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @param mouth_left
	 */
	public void setMouth_left(PointF mouth_left) {
		this.mouth_left = mouth_left;
	}
	
	/**
	 * 相应人脸的右侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @return
	 */
	public PointF getMouth_right() {
		return mouth_right;
	}
	
	/**
	 * 相应人脸的右侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
	 * @param mouth_right
	 */
	public void setMouth_right(PointF mouth_right) {
		this.mouth_right = mouth_right;
	}
	
	/**
	 * 相应人脸的鼻尖坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比 (0~100之间的实数)
	 * @return
	 */
	public PointF getNose() {
		return nose;
	}
	/**
	 * 相应人脸的鼻尖坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比 (0~100之间的实数)
	 * @param nose
	 */
	public void setNose(PointF nose) {
		this.nose = nose;
	}
	

	/**
	 * 性别分析结果
	 * @return
	 */
	public Gender getGender() {
		return gender;
	}
	
	/**
	 * 性别分析结果
	 * @param gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	/**
	 * 年龄分析结果
	 * @return
	 */
	public Age getAge() {
		return age;
	}
	
	/**
	 * 年龄分析结果
	 * @param age
	 */
	public void setAge(Age age) {
		this.age = age;
	}
	
	/**
	 * 人种分析结果
	 * @return
	 */
	public Race getRace() {
		return race;
	}
	
	/**
	 * 人种分析结果
	 * @param race
	 */
	public void setRace(Race race) {
		this.race = race;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Face [face_id=" + face_id + ", face_data=" + face_data
				+ ", img_id=" + img_id + ", url=" + url + ", width=" + width
				+ ", height=" + height + ", center=" + center + ", eye_left="
				+ eye_left + ", eye_right=" + eye_right + ", mouth_left="
				+ mouth_left + ", mouth_right=" + mouth_right + ", nose="
				+ nose + ", gender=" + gender + ", age=" + age + ", race="
				+ race + ", tag=" + tag + "]";
	}

	/**
	 * 人脸描述
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 人脸描述
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * 性别
	 * @author xiaoying
	 */
	public static class Gender implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3604771785466469504L;
		// gender object 包含性别分析结果，value的值为Male/Female, confidence表示置信度
		public String value; //值为Male/Female
		public float confidence; //置信度
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public float getConfidence() {
			return confidence;
		}
		public void setConfidence(float confidence) {
			this.confidence = confidence;
		}
		@Override
		public String toString() {
			return "Gender [value=" + value + ", confidence=" + confidence
					+ "]";
		}

	}

	/**
	 * 年龄
	 * @author xiaoying
	 */
	public static class Age implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 221737413026253122L;
		// age object 包含年龄分析结果，value的值为一个非负整数表示估计的年龄, range表示估计年龄的正负区间
		public int value;	//一个非负整数表示估计的年龄
		public int range;	//估计年龄的正负区间
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getRange() {
			return range;
		}
		public void setRange(int range) {
			this.range = range;
		}
		@Override
		public String toString() {
			return "Age [value=" + value + ", range=" + range + "]";
		}
		
	}

	/**
	 * 种族
	 * @author xiaoying
	 */
	public static class Race implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 888019375173484388L;
		// race object 包含人种分析结果，value的值为Asian/White/Black, confidence表示置信度
		public String value; //值为Asian/White/Black
		public float confidence; //置信度
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public float getConfidence() {
			return confidence;
		}
		public void setConfidence(float confidence) {
			this.confidence = confidence;
		}
		@Override
		public String toString() {
			return "Race [value=" + value + ", confidence=" + confidence + "]";
		}
		
	}
}
