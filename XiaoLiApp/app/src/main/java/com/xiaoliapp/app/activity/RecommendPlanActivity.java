package com.xiaoliapp.app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.adapter.RecommendPlanListViewAdapter;

public class RecommendPlanActivity extends BaseActivity implements View.OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	protected void init() {
		super.setTitle("推荐方案");
		super.setCancel(false);
		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_recommend_plan_content,
				content);
		ListView contentList = (ListView) content.findViewById(R.id.activity_recommend_plan_list);
		RecommendPlanListViewAdapter adapter = new RecommendPlanListViewAdapter(this);
		contentList.setAdapter(adapter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.base_top_back:
				super.onBackPressed();
				break;
		}

	}
}
