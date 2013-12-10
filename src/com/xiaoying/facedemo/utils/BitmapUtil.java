package com.xiaoying.facedemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
	private static String tag = BitmapUtil.class.getSimpleName();

	public static final long MAX_SIZE = 3 * 1024 * 1024;

	public static Bitmap loadBitmap(String path, int reqWidth, int reqHeight) {
		LogUtil.i(tag, FileUtil.getFileSize(path));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		options.inSampleSize = calculateInSampleSize(path, reqWidth, reqHeight);
		LogUtil.e(tag, "inSimpleSize =====>>>>" + options.inSampleSize);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}
	
	public static Bitmap loadBitmap(String path, int inSampleSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = inSampleSize;
		LogUtil.e(tag, "inSimpleSize =====>>>>" + options.inSampleSize);
		return BitmapFactory.decodeFile(path, options);
	}

	public static int calculateInSampleSize(String path, int reqWidth, int reqHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		LogUtil.e(tag, "Bitmap size++++>>> (" + width + ", " + height + ")");
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}
}
