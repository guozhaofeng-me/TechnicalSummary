package utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.view.WindowInsets;

import java.util.List;

public class DensityUtil {
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int getScreenWidth(Context context) {

		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		return dm.widthPixels;
	}

	public static int getScreenHeight(Context context) {

		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 获取异形屏title高度（启动状态）
	 * @param context
	 * @return
	 */
	public static int getCutoutHeight(Context context) {
		int cutoutHeight = 0;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			WindowInsets windowInsets = ((Activity) context).getWindow().getDecorView().getRootWindowInsets();
			if (windowInsets != null) {
				DisplayCutout displayCutout = windowInsets.getDisplayCutout();
				if (displayCutout != null) {
					List<Rect> rects = displayCutout.getBoundingRects();
					// 通过判断是否存在rects来确定是否为刘海屏手机
					if (rects != null && rects.size() > 0) {
						cutoutHeight = rects.get(0).height();
					}
				}
			}
		}
		return cutoutHeight;
	}
}
