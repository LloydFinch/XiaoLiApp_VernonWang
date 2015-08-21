package com.xiaoliapp.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RadioGroup;
import com.xiaoliapp.app.R;

public class GuidePlanFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {


	private RadioGroup radioGroup;
	private GuidePlanStationFragment stationFragment;

	private Fragment fragment;
	private FragmentManager manager;

	public GuidePlanFragment() {
		// Required empty public constructor
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		manager = getFragmentManager();

		View view = inflater.inflate(R.layout.fragment_guide_plan, container, false);

		radioGroup = (RadioGroup) view.findViewById(R.id.fragment_guide_plan_tab_bar);
		radioGroup.setOnCheckedChangeListener(this);
		return view;
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (stationFragment == null) {
			stationFragment = new GuidePlanStationFragment();
		}
		fragment = stationFragment;
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fragment_guide_plan_content, fragment);
		transaction.commit();
	}
}
