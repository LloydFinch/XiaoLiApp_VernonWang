package com.xiaoliapp.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.adapter.RelationAllListAdapter;
import com.xiaoliapp.app.entity.Friend;
import com.xiaoliapp.app.librarys.sortlistview.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelationAllFragment extends Fragment {

	private ListView contentList;
	private ClearEditText txtSearch;
	private SideBar sideBar;
	private TextView dialog;
	private RelationAllListAdapter adapter;
	private View.OnClickListener onClickListener;

	//汉子转化为拼音
	private CharacterParser characterParser;
	private List<Friend> SourceDateList;

	//拼音比较器
	private PinyinComparator pinyinComparator;


	public RelationAllFragment() {
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
		pinyinComparator = new PinyinComparator();

		View view = inflater.inflate(R.layout.fragment_relation_all, container, false);

		sideBar = (SideBar) view.findViewById(R.id.sidebar);
		dialog = (TextView) view.findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		contentList = (ListView) view.findViewById(R.id.relation_content_all_list);
		contentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
			                        int position, long id) {
				//利用adapter.getItem(position)来获取当前position所对应的对象
				Toast.makeText(getActivity().getApplication(), ((Friend) adapter.getItem(position)).getName(),
						Toast.LENGTH_SHORT).show();
			}
		});

		SourceDateList = filledData(getResources().getStringArray(R.array.date));

		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new RelationAllListAdapter(getActivity(), SourceDateList, onClickListener);
		contentList.setAdapter(adapter);

		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					contentList.setSelection(position);
				}
			}
		});

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

		return view;
	}

	//根据输入框中的值来过滤数据并更新ListView
	private void filterData(String filterStr) {
		List<Friend> filterDateList = new ArrayList<Friend>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (Friend friend : SourceDateList) {
				String name = friend.getName();
				if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr
						.toString())) {
					filterDateList.add(friend);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
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
