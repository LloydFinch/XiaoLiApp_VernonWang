package com.xiaoliapp.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.adapter.CommonPagerAdapter;
import com.xiaoliapp.app.fragments.RelationAllFragment;
import com.xiaoliapp.app.fragments.RelationGroupFragment;
import com.xiaoliapp.app.librarys.sortlistview.ClearEditText;
import com.xiaoliapp.app.myview.MyAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class RelationActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

	private ClearEditText txtSearch;
	private RadioGroup tabRadioGroup;
	private ViewPager contentPager;
	private RelationAllFragment allFragment;
	private RelationGroupFragment groupFragment;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	protected void init() {
		super.setTitle(false);
		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_relation_content, content);
		txtSearch = (ClearEditText) content.findViewById(R.id.relation_top_txt_search);

		contentPager = (ViewPager) content.findViewById(R.id.relation_center_content_pager);

		List<Fragment> fragmentList = new ArrayList<Fragment>();

		allFragment = allFragment == null ? new RelationAllFragment() : allFragment;
		allFragment.setTxtSearch(txtSearch);
		allFragment.setOnClickListener(this);
		fragmentList.add(allFragment);

		groupFragment = groupFragment == null ? new RelationGroupFragment() : groupFragment;
		groupFragment.setTxtSearch(txtSearch);
		groupFragment.setOnClickListener(this);
		fragmentList.add(groupFragment);

		CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), fragmentList);
		contentPager.setAdapter(adapter);
		contentPager.addOnPageChangeListener(this);

		tabRadioGroup = (RadioGroup) content.findViewById(R.id.relation_center_tab_bar);
		tabRadioGroup.setOnCheckedChangeListener(this);
		tabRadioGroup.check(R.id.relation_center_tab_all);
	}

	//处理全部、分组的切换联动
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		super.onCheckedChanged(group, checkedId);
		int position = 0;
		switch (checkedId) {
			case R.id.relation_center_tab_all:
				position = 0;
				break;
			case R.id.relation_center_tab_group:
				position = 1;
				break;
			default:
				break;
		}
		contentPager.setCurrentItem(position);
	}

	public void onPageScrolled(int pre, float v, int post) {

	}

	public void onPageSelected(int position) {
		int id = R.id.relation_center_tab_all;
		switch (position) {
			case 0:
				id = R.id.relation_center_tab_all;
				break;
			case 1:
				id = R.id.relation_center_tab_group;
				break;
		}
		tabRadioGroup.check(id);
	}

	public void onPageScrollStateChanged(int position) {

	}

	private MyAlertDialog alertDialog;

	public void onClick(View v) {

		Intent intent = null;
		switch (v.getId()) {

			//关系管理上方功能部分的点击处理
			case R.id.relation_item_func_impress:
				intent = new Intent(this, SendGiftActivity.class);
				break;
			case R.id.relation_item_func_ranking:
				intent = new Intent(this, RankingActivity.class);
				break;
			case R.id.relation_item_func_contacts:
				//TODO 暂时跳转到印象描述界面
				intent = new Intent(this, ImpressDesActivity.class);
				break;
			case R.id.relation_item_func_grade:
				//TODO 暂时跳转到打分界面
				intent = new Intent(this, GradePayActivity.class);
				break;
			case R.id.relation_item_func_relation:
				intent = new Intent(this, RecommendPlanActivity.class);
				break;
			case R.id.relation_item_func_group:

				//跳转相册的隐式意图
				intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				break;

			//ListView/ExpandableListView的item的点击处理
			case R.id.relation_item_head:
				//TODO 点击头像的处理
				showDialog();
				break;

			//对话框的按钮处理
			case R.id.dialog_button_ok:
				alertDialog.dismiss();
				break;
			case R.id.dialog_button_cancel:
				alertDialog.dismiss();
				break;
			default:
				break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	private void showDialog() {
		alertDialog = new MyAlertDialog(this);
		alertDialog.show();
		Button btnOK = (Button) alertDialog.findViewById(R.id.dialog_button_ok);
		btnOK.setOnClickListener(this);
		Button btnCancel = (Button) alertDialog.findViewById(R.id.dialog_button_cancel);
		btnCancel.setOnClickListener(this);
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
}
