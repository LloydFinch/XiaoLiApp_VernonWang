package com.xiaoliapp.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.adapter.RelationGroupExpandListAdapter;
import com.xiaoliapp.app.librarys.sortlistview.CharacterParser;
import com.xiaoliapp.app.librarys.sortlistview.ClearEditText;
import com.xiaoliapp.app.entity.Friend;

import java.util.ArrayList;
import java.util.List;

public class RelationGroupFragment extends Fragment {

	private ExpandableListView contentList;
	private ClearEditText txtSearch;
	private RelationGroupExpandListAdapter adapter;

	private List<String> parents;
	private List<List<Friend>> children;
	private List<Friend> SourceDateList;

	private View.OnClickListener onClickListener;

	private CharacterParser characterParser;

	public RelationGroupFragment() {
		// Required empty public constructor
	}

	public void setOnClickListener(View.OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public void setTxtSearch(ClearEditText txtSearch) {
		this.txtSearch = txtSearch;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		characterParser = CharacterParser.getInstance();
		View view = inflater.inflate(R.layout.fragment_relation_group, container, false);

		contentList = (ExpandableListView) view.findViewById(R.id.relation_content_all_list);

		//根据输入框输入值的改变来过滤搜索
		txtSearch.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
			                              int after) {

			}

			public void afterTextChanged(Editable s) {
			}
		});

		init();
		return view;
	}

	private void init() {

		//初始化外层
		parents = new ArrayList<String>();
		parents.add("同事(28)");
		parents.add("同学(18)");
		parents.add("我");

		//初始化内层数据
		SourceDateList = filledData(getResources().getStringArray(R.array.date));
		children = new ArrayList<List<Friend>>();
		children.add(SourceDateList);
		children.add(SourceDateList);
		children.add(SourceDateList);

		adapter = new RelationGroupExpandListAdapter(getActivity(), parents, children,
				onClickListener);
		contentList.setAdapter(adapter);
	}

	//根据输入框中的值来过滤数据并更新ListView
	private void filterData(String filterStr) {
		List<String> filterParent = new ArrayList<String>();
		filterParent.add("已找到");
		List<Friend> filterChildList = new ArrayList<Friend>();
		List<List<Friend>> allFindChildren = new ArrayList<List<Friend>>();
		if (TextUtils.isEmpty(filterStr)) {
			filterParent = parents;
			allFindChildren = children;
		} else {
			filterChildList.clear();
			for (Friend friend : SourceDateList) {
				String name = friend.getName();
				if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr
						.toString())) {
					filterChildList.add(friend);
				}
			}
			allFindChildren.add(filterChildList);
		}

		adapter.updateListView(filterParent, allFindChildren);
		if (allFindChildren.size() == 1) {
			contentList.expandGroup(0);
		}
	}

	//为ListView填充数据
	private List<Friend> filledData(String[] date) {
		List<Friend> mSortList = new ArrayList<Friend>();

		for (int i = 0; i < date.length; i++) {
			Friend friend = new Friend();
			friend.setNumber("182-3658-6285");
			friend.setName(date[i]);
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				friend.setSortLetters(sortString.toUpperCase());
			} else {
				friend.setSortLetters("#");
			}
			mSortList.add(friend);
		}
		return mSortList;
	}
}
