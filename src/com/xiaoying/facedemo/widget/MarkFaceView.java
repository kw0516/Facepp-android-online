/*
 * 文件名：MarkFaceView.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-20
 * 修改人：xiaoying
 * 修改时间：2013-5-20
 * 版本：v1.0
 */
package com.xiaoying.facedemo.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.xiaoying.facedemo.utils.LogUtil;
import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.PointF;
/**
 * 
 * 功能：标识人脸的View
 * @author xiaoying
 *
 */
public class MarkFaceView extends FrameLayout {
	
	private String tag = MarkFaceView.class.getSimpleName();
	/** 显示图片的ImageView */
	private ImageView mImageView = null;
	/** Bitmap显示的左边缘 */
	private float mImageLeft = getLeft();
	/** Bitmap显示的上边缘 */
	private float mImageTop = getTop();
	/** Bitmap显示的右边缘 */
	private float mImageRight = getRight();
	/** Bitmap显示的下边缘 */
	private float mImageBottom = getBottom();
	/** 外框宽度 **/
	private float mOuterBorderWidth = 8f;
	/** 内框宽度 **/
	private float mInnerBorderWidth = 2f;
	/** 内外框距离 **/
	private float mIODist = 5f;
	/** 框透明度 */
	private int mAlpha = 130;
	/** 框颜色 */
	private int mBorderColor = Color.argb(mAlpha, 0x00, 0x9A, 0xD6);
	
	private float mTextSize = 30f;
	/** Bitmap显示和实际的缩放比例 */
	private float mScale = 1.0f;
	/** Bitmap对象，当调用setBitmap(Bitmap bitmap)方法时，或将参数中的bitmap复制到这个对象中，并且是Mutable的 */
	private Bitmap mBitmap = null;
	/** 画布 */
	private Canvas mCanvas = new Canvas();
	
	private Paint mPaint = new Paint();
	/** 人脸信息 */
	private List<Face> mFaces = null;
	/** 在显示出来的界面中，人脸的位置 */
	private List<RectF> mRects = new ArrayList<RectF>();
	
	private MarkFaceView.OnFceClickListener mListener;

	public MarkFaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public MarkFaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MarkFaceView(Context context) {
		super(context);
		initView();
	}
	
	private void initView() {
		mImageView = new ImageView(getContext());
		mImageView.setScaleType(ScaleType.CENTER_INSIDE);
		addView(mImageView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 设置显示要进行人脸识别的图片
	 * @param bitmap
	 */
	public void setBitmap(final Bitmap bitmap) {
		if(bitmap == null) {
			throw new IllegalArgumentException("Bitmap shut not be null");
		}
		LogUtil.i(tag, "Bitmap size=====>>>(" + bitmap.getWidth() + ", " + bitmap.getHeight() + ")");
		mBitmap = bitmap.copy(Config.ARGB_8888, true);
		mImageView.setImageBitmap(mBitmap);
		mCanvas.setBitmap(mBitmap);
	}
	
	/**
	 * 计算图片显示的位置和与原Bimtap的缩放比例
	 * @param bitmap
	 */
	private void calculatePosition(Bitmap bitmap) {
		LogUtil.e(tag, "Content++++>>>(" + getLeft() + ", " + getTop() + ", " + getRight() + ", " + getBottom() + ")");
		float scaleX = ((float) getWidth()) / bitmap.getWidth();
		float scaleY = ((float) getHeight()) / bitmap.getHeight();
		if(scaleX < scaleY && scaleX < 1) {	//以X为准， 并且Bitmap比显示宽度大
			mImageLeft = 0;
			mImageTop = ((float) (getHeight() - bitmap.getHeight() * scaleX)) / 2;
			mImageRight = getRight();
			mImageBottom = mImageTop + bitmap.getHeight() * scaleX;
			mScale = scaleX;
		} else if(scaleX > scaleY && scaleY < 1){	//以Y为准，并且Bitmap比显示宽度大
			mImageLeft = ((float) (getWidth() - bitmap.getWidth() * scaleY)) / 2;
			mImageTop = 0;
			mImageRight = mImageLeft + bitmap.getWidth() * scaleY;
			mImageBottom = getBottom();
			mScale = scaleY;
		} else {
			mImageLeft = ((float) (getWidth() - bitmap.getWidth())) / 2;
			mImageTop = ((float) (getHeight() - bitmap.getHeight())) / 2;
			mImageRight = mImageLeft + bitmap.getWidth();
			mImageBottom = mImageTop + bitmap.getHeight();
			mScale = 1f;
		}
	}
	
	/**
	 * 标出人脸
	 * @param faces
	 */
	public void markFaces(List<Face> faces) {
		this.mFaces = faces;
		drawRects(mFaces);
	}
	
	/**
	 * 绘制标识边框
	 * @param faces
	 */
	public void drawRects(List<Face> faces) {
		calculatePosition(mBitmap);
		int bmWidth = mBitmap.getWidth();
		int bmHeight = mBitmap.getHeight();
//		float outerBorderWidth = getNeedSize(bmWidth, bmHeight, mOuterBorderWidth);
//		float innerBorderWidth = getNeedSize(bmWidth, bmHeight, mInnerBorderWidth);
		mPaint.setColor(mBorderColor);
		for (Face face : faces) {
//			mPaint.setStrokeWidth(outerBorderWidth);
			drawOuterRect(mCanvas, mPaint, face, bmWidth, bmHeight);
			
//			drawTagBackgroupd(mCanvas, mPaint, face, bmWidth, bmHeight);
//			
//			drawTagText(mCanvas, mPaint, face, bmWidth, bmHeight, "TAG");

//			mPaint.setStrokeWidth(innerBorderWidth);
			drawInerRect(mCanvas, mPaint, face, bmWidth, bmHeight);
			
		}
		mImageView.setImageBitmap(mBitmap);
	}
	
	/**
	 * 绘制外框
	 * @param canvas
	 * @param paint
	 * @param face
	 * @param bmWidth
	 * @param bmHeight
	 */
	private void drawOuterRect(Canvas canvas, Paint paint, Face face, int bmWidth, int bmHeight) {
		PointF center = face.getCenter();
		float width = face.getWidth();
		float height = face.getHeight();
		float left = (bmWidth * (center.x / 100) - bmWidth * (width / 100) / 2) - mOuterBorderWidth / mScale;
		float top = (bmHeight * (center.y / 100) - bmHeight * (height / 100) / 2) - mOuterBorderWidth / mScale;
		float right = (bmWidth * (center.x / 100) + bmWidth * (width / 100) / 2) + mOuterBorderWidth / mScale;
		float bottom = (bmHeight * (center.y / 100) + bmHeight * (height / 100) / 2) + mOuterBorderWidth / mScale;
		RectF rect = new RectF(left, top, right, bottom);
//		mRects.add(new RectF(mImageLeft + (left + paint.getStrokeWidth()) * mScale, 
//				mImageTop + (top + paint.getStrokeWidth()) * mScale, 
//				mImageLeft + (right - paint.getStrokeWidth()) * mScale, 
//				mImageTop + (bottom - paint.getStrokeWidth()) * mScale));
		mRects.add(new RectF(mImageLeft + left * mScale + mOuterBorderWidth, 
				mImageTop + top * mScale + mOuterBorderWidth, 
				mImageLeft + right * mScale - mOuterBorderWidth, 
				mImageTop + bottom * mScale - mOuterBorderWidth));
		mPaint.setColor(mBorderColor);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(mOuterBorderWidth / mScale);
		canvas.drawRect(rect, paint);
	}
	
	/**
	 * 绘制内框
	 * @param canvas
	 * @param paint
	 * @param face
	 * @param bmWidth
	 * @param bmHeight
	 */
	private void drawInerRect(Canvas canvas, Paint paint, Face face, int bmWidth, int bmHeight) {
		PointF center = face.getCenter();
		float width = face.getWidth();
		float height = face.getHeight();
		float left = (bmWidth * (center.x / 100) - bmWidth * (width / 100) / 2) + mIODist / mScale;
		float top = (bmHeight * (center.y / 100) - bmHeight * (height / 100) / 2) + mIODist / mScale;
		float right = (bmWidth * (center.x / 100) + bmWidth * (width / 100) / 2) - mIODist / mScale;
		float bottom = (bmHeight * (center.y / 100) + bmHeight * (height / 100) / 2) - mIODist / mScale;
		mPaint.setColor(mBorderColor);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(mInnerBorderWidth / mScale);
		canvas.drawLines(getInerPoints(left, top, right, bottom), paint);
	}
	
	
	private float [] getInerPoints(float left, float top, float right, float bottom) {
		float inerLength = (right - left) / 4;
		return new float [] {
				left, top, left + inerLength, top, 
				left, top, left, top + inerLength, 
				right - inerLength, top, right, top, 
				right, top, right, top + inerLength, 
				left, bottom - inerLength, left, bottom, 
				left, bottom, left + inerLength, bottom,
				right - inerLength, bottom, right, bottom, 
				right, bottom - inerLength, right, bottom,
		};
	}
	
//	private void drawTagBackgroupd(Canvas canvas, Paint paint, Face face, int bmWidth, int bmHeight) {
//		PointF center = face.getCenter();
//		float width = face.getWidth();
//		float height = face.getHeight();
//		float left = (bmWidth * (center.x / 100) - bmWidth * (width / 100) / 2) - paint.getStrokeWidth() * 3 /2;
//		float top = (bmHeight * (center.y / 100) - bmHeight * (height / 100) / 2) - paint.getStrokeWidth() * 3 / 2 - mTextSize / mScale;
//		float right = (bmWidth * (center.x / 100) + bmWidth * (width / 100) / 2) + paint.getStrokeWidth() * 3 / 2;
//		float bottom = (bmHeight * (center.y / 100) - bmHeight * (height / 100) / 2) - paint.getStrokeWidth() * 3 / 2;
//		RectF rect = new RectF(left, top, right, bottom);
////		mRects.add(new RectF(mImageLeft + (left + paint.getStrokeWidth()) * mScale, 
////				mImageTop + (top + paint.getStrokeWidth()) * mScale, 
////				mImageLeft + (right - paint.getStrokeWidth()) * mScale, 
////				mImageTop + (bottom - paint.getStrokeWidth()) * mScale));
//		mPaint.setColor(mBorderColor);
//		mPaint.setStyle(Style.FILL);
//		canvas.drawRect(rect, paint);
//	}
	
//	private void drawTagText(Canvas canvas, Paint paint, Face face, int bmWidth, int bmHeight, String text) {
//		PointF center = face.getCenter();
//		float width = face.getWidth();
//		float height = face.getHeight();
//		float x = (bmWidth * (center.x / 100) - bmWidth * (width / 100) / 2) - paint.getStrokeWidth() / 2;
//		float y = (bmHeight * (center.y / 100) - bmHeight * (height / 100) / 2) - paint.getStrokeWidth() * 3 / 2;
//		mPaint.setColor(Color.BLACK);
//		mPaint.setStyle(Style.FILL_AND_STROKE);
//		mPaint.setStrokeWidth(1f);
//		mPaint.setTextSize(mTextSize / mScale);
//		canvas.drawText(text, x, y, paint);
//	}
//	
//	/**
//	 * 根据Bitmap显示在ImageViwe中的缩放比例，算出在手机显示相应像素的实际像素
//	 * @param bmWidth
//	 * @param bmHeight
//	 * @param real
//	 * @return
//	 */
//	private float getNeedSize(int bmWidth, int bmHeight, float real) {
//		float ivWidth = mImageRight - mImageLeft;
//		float ivHeight = mImageBottom - mImageTop;
//		if(ivWidth > bmWidth && ivHeight > bmHeight) {
//			return real;
//		}
//		float wScale = (float)bmWidth / ivWidth;
//		float hScale = (float) bmHeight / ivHeight;
//		float scale = wScale < hScale ? wScale : hScale;
//		return real * scale;
//	}

	/**
	 * 设置外框宽度
	 * @param width
	 */
	public void setOuterBorderWidth(float width) {
		this.mOuterBorderWidth = width;
	}
	
	/**
	 * 获取外框宽度
	 * @return
	 */
	public float getOuterBorderWidth() {
		return mOuterBorderWidth;
	}
	
	/**
	 * 设置内边框宽度
	 * @param width
	 */
	public void setInnerBorderWidth(float width) {
		this.mInnerBorderWidth = width;
	}
	
	/**
	 * 获取内边框宽度
	 * @return
	 */
	public float getInnerBorderWidth() {
		return mInnerBorderWidth;
	}
	
	/**
	 * 设置人脸点击监听
	 * @param listener
	 */
	public void setOnFaceClickListener(MarkFaceView.OnFceClickListener listener) {
		this.mListener = listener;
	}
	
	/**
	 * 设置边框绘制的透明度
	 * @param alpha
	 */
	public void setBorderAlpha(int alpha) {
		this.mAlpha = alpha;
	}
	
	/**
	 * 获取边框的透明度
	 * @return
	 */
	public int getBorderAlpha() {
		return mAlpha;
	}
	
	/**
	 * 设置边框颜色
	 * @param color
	 */
	public void setBorderColor(int color) {
		mBorderColor = Color.argb(mAlpha, Color.red(color), Color.green(color), Color.blue(color));
	}
	
	/**
	 * 获取边框颜色
	 * @return
	 */
	public int getBorderColor() {
		return mBorderColor;
	}
	
	/**
	 * 设置内外边框的距离
	 * @param dist
	 */
	public void setIODist(float dist) {
		this.mIODist = dist;
	}
	
	/**
	 * 获取内外边框的距离
	 * @return
	 */
	public float getIODist() {
		return mIODist;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN :
				x = event.getX();
				y = event.getY();
				for (int i = 0; i < mRects.size(); i++) {
					RectF rect = mRects.get(i);
					if(x > rect.left && x < rect.right && y > rect.top && y < rect.bottom) {
						LogUtil.e(tag, "Clicked in a face++++>>" + i);
						mListener.onFaceClicked(mFaces.get(i), i);
						break;
					}
				}
				break;
			default :
				break;
		}
		return super.onTouchEvent(event);
	}
	
	/**
	 * 功能：实现点击人脸时操作的interface
	 * @author xiaoying
	 *
	 */
	public static interface OnFceClickListener {
		public void onFaceClicked(Face face, int position);
	}
}
