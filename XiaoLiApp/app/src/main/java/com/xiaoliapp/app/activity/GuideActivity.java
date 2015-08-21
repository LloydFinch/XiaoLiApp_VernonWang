package com.xiaoliapp.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.adapter.CommonPagerAdapter;
import com.xiaoliapp.app.constants.Constants;
import com.xiaoliapp.app.fragments.GuidePlanFragment;
import com.xiaoliapp.app.fragments.GuideStoryFragment;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


	private RadioGroup topRadioGroup;
	private ViewPager contentPager;

	private GuidePlanFragment planFragment;
	private GuideStoryFragment storyFragment;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		RadioButton radioButton = (RadioButton) this.findViewById(R.id.bottom_tab_guide);
		radioButton.setChecked(true);
	}

	protected void init() {

		super.setTitle(false);
		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_guide_content, content);
		contentPager = (ViewPager) content.findViewById(R.id.fragment_guide_top_pager);

		List<Fragment> fragmentList = new ArrayList<Fragment>();
		planFragment = new GuidePlanFragment();
		fragmentList.add(planFragment);
		storyFragment = new GuideStoryFragment();
		fragmentList.add(storyFragment);

		CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), fragmentList);
		contentPager.setAdapter(adapter);
		contentPager.addOnPageChangeListener(this);

		topRadioGroup = (RadioGroup) content.findViewById(R.id.fragment_guide_top_tab);
		topRadioGroup.setOnCheckedChangeListener(this);
		topRadioGroup.check(R.id.fragment_guide_top_tab_plan);
	}

	public void onPageScrolled(int pre, float v, int post) {

	}

	public void onPageSelected(int position) {
		int id = R.id.fragment_guide_top_tab_plan;
		switch (position) {
			case 0:
				id = R.id.fragment_guide_top_tab_plan;
				break;
			case 1:

				//TODO 情礼攻略故事部分的Fragment内容待改
				id = R.id.fragment_guide_top_tab_story;
				break;
		}
		topRadioGroup.check(id);
	}

	public void onPageScrollStateChanged(int i) {

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int position = 0;
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		switch (checkedId) {
			case R.id.fragment_guide_top_tab_plan:
				position = 0;
				break;
			case R.id.fragment_guide_top_tab_story:
				position = 1;
				break;

			//底部Activity的跳转
			case R.id.bottom_tab_relation:
				intent.setAction("com.xiaoli.activity.RelationActivity");
				break;
			case R.id.bottom_tab_discover:
				intent.setAction("com.xiaoli.activity.DiscoverActivity");
				break;
			case R.id.bottom_tab_profile:
				intent.setAction("com.xiaoli.activity.ProfileActivity");
				break;
		}
		if (intent.getAction() != null) {
			startActivity(intent);
		}
		contentPager.setCurrentItem(position);
	}

	boolean exit = false;

	public void onBackPressed() {
		if (!exit) {
			Toast.makeText(this, "连按此项以退出", Toast.LENGTH_SHORT).show();
		} else {
			super.onBackPressed();
		}
		exit = true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.relation_top_btn_search:

				//搜索
				startSearch();
				break;
			default:
				break;
		}
	}

	//启动搜索Activity
	private void startSearch() {
		Intent intent = new Intent(this, SearchActivity.class);
		startActivityForResult(intent, Constants.CODE_ACTIVITY_GUIDE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == Constants.CODE_ACTIVITY_GUIDE && resultCode == Constants.CODE_ACTIVITY_SEARCH) {

			//TODO 处理intent返回的数据

		}
	}
}
