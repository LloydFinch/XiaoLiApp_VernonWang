package com.xiaoliapp.app.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by VennUser on 2015/8/20.
 */
public class MyHorScrollView extends HorizontalScrollView {
	public MyHorScrollView(Context context) {
		super(context);
	}

	public MyHorScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void fling(int velocityX) {
		super.fling(velocityX / 4);
	}
}
