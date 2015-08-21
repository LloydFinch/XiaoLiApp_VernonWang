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
	private Fragment fragment;
	private GuidePlanStationFragment stationFragment;
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
		radioGroup.check(R.id.fragment_guide_plan_tab_station);
		return view;
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {

		//TODO 情礼攻略计划中四个子项Fragment的替换
		switch (checkedId) {
			case R.id.fragment_guide_plan_tab_station:
				if (stationFragment == null) {
					stationFragment = new GuidePlanStationFragment();
				}
				fragment = stationFragment;
				break;
			case R.id.fragment_guide_plan_tab_object:
				if (stationFragment == null) {
					stationFragment = new GuidePlanStationFragment();
				}
				fragment = stationFragment;
				break;
			case R.id.fragment_guide_plan_tab_love:
				if (stationFragment == null) {
					stationFragment = new GuidePlanStationFragment();
				}
				fragment = stationFragment;
				break;
			case R.id.fragment_guide_plan_tab_feast:
				if (stationFragment == null) {
					stationFragment = new GuidePlanStationFragment();
				}
				fragment = stationFragment;
				break;
			default:
				break;
		}
		if (fragment != null) {
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.fragment_guide_plan_content, fragment);
			transaction.commit();
		}
	}
}
