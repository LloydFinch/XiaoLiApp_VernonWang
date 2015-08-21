package com.xiaoliapp.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.entity.Friend;

import java.util.List;

/**
 * Created by VennUser on 2015/8/19.
 */
public class RelationAllListAdapter extends BaseAdapter implements SectionIndexer {

	private Context context;
	private List<Friend> friendList;
	private View.OnClickListener clickListener;

	public RelationAllListAdapter(Context context, List<Friend> friendList, View.OnClickListener clickListener) {
		this.context = context;
		this.friendList = friendList;
		this.clickListener = clickListener;
	}

	public void updateListView(List<Friend> friendList) {
		this.friendList = friendList;
		notifyDataSetChanged();
	}

	public int getCount() {
		return friendList == null ? 0 : friendList.size();
	}

	public Object getItem(int position) {
		return friendList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.item_relation_list, parent,
				false) : convertView;
		ViewHolder holder = (ViewHolder) view.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.tvLetter = (TextView) view.findViewById(R.id.relation_item_head_letter);
			holder.headImage = (ImageView) view.findViewById(R.id.relation_item_head);
			holder.nameText = (TextView) view.findViewById(R.id.relation_item_name);
			holder.numberText = (TextView) view.findViewById(R.id.relation_item_number);
			holder.checkGifts = (CheckBox) view.findViewById(R.id.relation_item_gifts);
			holder.checkImpress = (CheckBox) view.findViewById(R.id.relation_item_impress);
			holder.checkGrade = (CheckBox) view.findViewById(R.id.relation_item_grade);

			view.setTag(holder);
		}


		Friend friend = friendList.get(position);
		holder.headImage.setImageResource(R.mipmap.head);
		holder.nameText.setText(friend.getName());
		holder.numberText.setText(friend.getNumber());
		holder.checkImpress.setText("92");
		holder.checkGrade.setText(friend.getGrade() + "");
		holder.headImage.setOnClickListener(clickListener);

		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);

		//如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			holder.tvLetter.setVisibility(View.VISIBLE);
			holder.tvLetter.setText(friend.getSortLetters());
		} else {
			holder.tvLetter.setVisibility(View.GONE);
		}

		return view;
	}

	private static class ViewHolder {
		TextView tvLetter;
		ImageView headImage;
		TextView nameText, numberText;
		CheckBox checkGifts, checkImpress, checkGrade;
	}

	//根据ListView的当前位置获取分类的首字母的Char ascii值
	public int getSectionForPosition(int position) {
		return friendList.get(position).getSortLetters().charAt(0);
	}

	//根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = friendList.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	//提取英文的首字母，非英文字母用#代替。
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}
