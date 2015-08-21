package com.xiaoliapp.app.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.xiaoliapp.app.R;

public class GradePayActivity extends BaseActivity implements View.OnClickListener {


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	protected void init() {

		//TODO 根据Intent数据设置标题
		super.setTitle("给 阿三 打分");
		super.setCancel(false);

		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_grade_pay_content, content);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.base_top_back:
				super.onBackPressed();
		}
	}
}
