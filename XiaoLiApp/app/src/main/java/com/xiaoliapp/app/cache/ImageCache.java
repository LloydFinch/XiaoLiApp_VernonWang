package com.xiaoliapp.app.cache;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.util.LruCache;

/**
 * Created by VennUser on 2015/8/17.
 */
public final class ImageCache {

	private LruCache<String, Bitmap> lruCache;

	public ImageCache(LruCache<String, Bitmap> lruCache) {
		this.lruCache = lruCache;
	}

	//从assets目录获取图片
	public static Bitmap getImage(int id, String keyName, Handler handler) {
		return null;
	}

	//从资源文件获取图片
	public static Bitmap getImage(int id, Handler handler) {
		return null;
	}

	//从路径获取图片
	//fromSD: true SD卡, false :网络
	public static Bitmap getBitmap(int id, String path, Handler handler, boolean fromSD) {
		return null;
	}
}
