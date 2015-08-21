package com.xiaoliapp.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoliapp.app.R;

public class GuideStoryFragment extends Fragment {


	public GuideStoryFragment() {
		// Required empty public constructor
	}


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_guide_story, container, false);
	}
}
