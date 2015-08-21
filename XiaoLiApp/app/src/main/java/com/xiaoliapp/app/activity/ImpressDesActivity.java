package com.xiaoliapp.app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.librarys.org.apmem.tools.layouts.FlowLayout;

public class ImpressDesActivity extends BaseActivity implements View.OnClickListener {

	private FlowLayout layoutTag;
	private ImageButton buttonAdd;
	private EditText txtDes;
	private ScrollView scrollView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	protected void init() {

		super.setTitle("给 阿三 的印象");
		super.setCancel(false);

		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_impress_des_content, content);

		layoutTag = (FlowLayout) content.findViewById(R.id.activity_impress_des_line2_tag);
		txtDes = (EditText) content.findViewById(R.id.activity_impress_des_txt_tag);
		buttonAdd = (ImageButton) content.findViewById(R.id.activity_impress_des_button_addtag);
		buttonAdd.setOnClickListener(this);

		scrollView = (ScrollView) content.findViewById(R.id.activity_impress_des_scroll);
	}


	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.base_top_back:
				super.onBackPressed();
				break;
			case R.id.activity_impress_des_button_addtag:
				addTag();
				break;
			default:
				break;
		}
	}

	private void addTag() {
		String tagContent = txtDes.getText().toString().trim();
		if (tagContent != null && tagContent.length() > 0) {

			LinearLayout layout = new LinearLayout(this);
			layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_tag_layout, layout);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
					.WRAP_CONTENT, ViewGroup
					.LayoutParams.WRAP_CONTENT);
			layout.setLayoutParams(params);

			TextView txtTag = (TextView) layout.findViewById(R.id.text_tag_example);
			txtTag.setText(tagContent);
			int position = layoutTag.getChildCount();
			layoutTag.addView(layout, position - 1);
			scrollView.fullScroll(ScrollView.FOCUS_DOWN);
		} else {
			Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
		}
	}
}
