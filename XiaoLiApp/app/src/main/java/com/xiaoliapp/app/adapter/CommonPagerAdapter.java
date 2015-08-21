package com.xiaoliapp.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by VennUser on 2015/8/19.
 */
public class CommonPagerAdapter extends FragmentPagerAdapter {

	List<Fragment> fragmentList;

	public CommonPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}

	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	public int getCount() {
		return fragmentList.size();
	}
}
