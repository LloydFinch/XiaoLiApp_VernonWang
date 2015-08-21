package com.xiaoliapp.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.entity.Friend;

import java.util.List;

/**
 * Created by VennUser on 2015/8/17.
 */
public class RankingListViewAdapter extends BaseAdapter {

	private List<Friend> userList;
	private Context context;

	public RankingListViewAdapter(List<Friend> userList, Context context) {
		this.userList = userList;
		this.context = context;
	}

	public int getCount() {
		return userList.size();
	}

	public Object getItem(int position) {
		return userList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.item_ranking_list, parent,
				false) : convertView;
		ViewHolder holder = (ViewHolder) view.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.headImage = (ImageView) view.findViewById(R.id.activity_ranking_item_head);
			holder.textRanking = (TextView) view.findViewById(R.id.activity_ranking_item_rank);
			holder.textName = (TextView) view.findViewById(R.id.activity_ranking_item_name);
			holder.textGrade = (TextView) view.findViewById(R.id.activity_ranking_item_grade);
			view.setTag(holder);
		}

		Friend user = userList.get(position);
		if (user != null) {
			holder.headImage.setImageResource(R.mipmap.head);
			holder.textRanking.setText(String.valueOf(position + 1));
			holder.textName.setText(user.getName());
			holder.textGrade.setText(user.getGrade() + "分");
		}

		return view;
	}

	private static class ViewHolder {
		TextView textRanking;
		TextView textName;
		TextView textGrade;
		ImageView headImage;
	}
}
