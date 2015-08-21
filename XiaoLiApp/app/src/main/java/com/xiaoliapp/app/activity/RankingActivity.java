package com.xiaoliapp.app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.adapter.RankingListViewAdapter;
import com.xiaoliapp.app.entity.Friend;

import java.util.*;

public class RankingActivity extends BaseActivity implements View.OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	protected void init() {
		super.setTitle("排行榜");
		super.setCancel(false);

		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_ranking_content, content);
		List<Friend> userList = new ArrayList<Friend>();
		for (int i = 0; i < 20; i++) {
			Friend user = new Friend();
			user.setName("西门庆");
			user.setGrade(new Random().nextInt(10));
			userList.add(user);
		}
		Collections.sort(userList);
		RankingListViewAdapter adapter = new RankingListViewAdapter(userList, this);
		ListView listView = (ListView) content.findViewById(R.id.activity_ranking_content_list);
		listView.setAdapter(adapter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.base_top_back:
				super.onBackPressed();
				break;
		}
	}
}
