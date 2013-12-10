/*
 * 文件名：ImageLoadTask.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-23
 * 修改人：xiaoying
 * 修改时间：2013-5-23
 * 版本：v1.0
 */

package com.xiaoying.facedemo.utils;

import java.lang.ref.WeakReference;

import com.xiaoying.faceplusplus.api.entity.Image;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 功能：
 * @author xiaoying
 *
 */
public class ImageLoadTask extends AsyncTask<Image, Void, Bitmap>{
	
	private final WeakReference<ImageView> mImageViewReference; //防止内存溢出
	
	private int mRequitWidth = 4;
	
	private int mId = 0;
	

	public ImageLoadTask(int id, ImageView imageView, int requitWidth) {
		mImageViewReference = new WeakReference<ImageView>(imageView);
		this.mRequitWidth = requitWidth;
		this.mId = id;
	}

	@Override
	protected Bitmap doInBackground(Image... params) {
		return BitmapCache.getInstance().getBitmap(params[0], mRequitWidth);
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (isCancelled()) {
			bitmap = null;
		}

		if (mImageViewReference != null) {
			ImageView imageView = mImageViewReference.get();
			if (imageView != null && bitmap != null) {
				if((Integer) imageView.getTag() == this.mId) {
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}
}
