package com.xiaoliapp.app.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoliapp.app.R;

public class GuideStoryFragment extends Fragment {


	private GuidePlanStationFragment stationFragment;

	public GuideStoryFragment() {
		// Required empty public constructor
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guide_story, container, false);
		FragmentManager manager = getChildFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		if (stationFragment == null) {
			stationFragment = new GuidePlanStationFragment();
		}
		transaction.replace(R.id.fragment_guide_story_content, stationFragment);
		transaction.commit();

		return view;
	}
}
