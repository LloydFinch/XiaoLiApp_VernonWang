package com.xiaoliapp.app.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by VennUser on 2015/8/17.
 */
public class MyEditTextSearch extends EditText {
	public MyEditTextSearch(Context context) {
		super(context);
	}

	public MyEditTextSearch(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(0, 0, getWidth() - 30, 60, paint);
	}
}
