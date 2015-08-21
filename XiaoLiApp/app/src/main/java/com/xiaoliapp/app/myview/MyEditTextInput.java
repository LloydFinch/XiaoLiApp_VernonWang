package com.xiaoliapp.app.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by VennUser on 2015/8/14.
 */
public class MyEditTextInput extends EditText {
	public MyEditTextInput(Context context) {
		super(context);
	}

	public MyEditTextInput(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(0, 0, getWidth()-30, 60, paint);
	}
}
