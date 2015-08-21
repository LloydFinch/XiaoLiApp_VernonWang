package com.xiaoliapp.app.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.adapter.RelationInnerAdapter;

public class GuideInnerFragment extends Fragment {


	private ListView listView;
	private RelationInnerAdapter adapter;
	private Context context;

	public GuideInnerFragment() {

	}

	public void setContext(Context context) {
		this.context = context;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guide_inner, container, false);
		listView = (ListView) view.findViewById(R.id.fragment_guide_inner_list);
		adapter = new RelationInnerAdapter(getActivity());
		listView.setAdapter(adapter);
		return view;
	}


}
