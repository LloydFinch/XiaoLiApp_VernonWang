package com.xiaoliapp.app.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import com.xiaoliapp.app.R;

/**
 * Created by VennUser on 2015/8/18.
 */
public class MyAlertDialog extends Dialog {

	public MyAlertDialog(Context context) {
		super(context);
	}

	public MyAlertDialog(Context context, int theme) {
		super(context, theme);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow();
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.dialog_send);
	}
}
