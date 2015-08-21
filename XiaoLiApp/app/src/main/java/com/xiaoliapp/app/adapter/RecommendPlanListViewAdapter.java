package com.xiaoliapp.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaoliapp.app.R;

/**
 * Created by VennUser on 2015/8/18.
 */
public class RecommendPlanListViewAdapter extends BaseAdapter {

	//TODO 需要数据

	private Context context;

	public RecommendPlanListViewAdapter(Context context) {
		this.context = context;
	}

	public int getCount() {
		return 10;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.item_recommend_plan_list,
				parent, false) : convertView;
		ViewHolder holder = (ViewHolder) view.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.imageView = (ImageView) view.findViewById(R.id.activity_recommend_plan_item_image);
			holder.textView = (TextView) view.findViewById(R.id.activity_recommend_plan_item_text);
			view.setTag(holder);
		}

		return view;
	}

	private static class ViewHolder {
		ImageView imageView;
		TextView textView;
	}
}
