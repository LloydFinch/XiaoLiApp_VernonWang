package com.xiaoliapp.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.entity.Friend;

import java.util.List;

/**
 * Created by VennUser on 2015/8/15.
 */
public class RelationGroupExpandListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> parents;
	private List<List<Friend>> children;
	private View.OnClickListener clickListener;

	public RelationGroupExpandListAdapter(Context context, List<String> parents, List<List<Friend>> children, View
			.OnClickListener clickListener) {
		this.context = context;
		this.parents = parents;
		this.children = children;
		this.clickListener = clickListener;
	}

	public void updateListView(List<String> parents, List<List<Friend>> children) {
		this.parents = parents;
		this.children = children;
		notifyDataSetChanged();
	}

	public int getGroupCount() {
		return parents == null ? 0 : parents.size();
	}

	public int getChildrenCount(int groupPosition) {
		return children == null ? 0 : children.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition) {
		return parents == null ? null : parents.get(groupPosition);
	}

	public Object getChild(int groupPosition, int childPosition) {
		return children == null ? null : children.get(groupPosition).get(childPosition);
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public boolean hasStableIds() {
		return false;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.item_relation_parent, parent,
				false) : convertView;
		TextView textView = (TextView) view.getTag();
		if (textView == null) {
			textView = (TextView) view.findViewById(R.id.relation_item_parent_name);
			view.setTag(textView);
		}
		textView.setText(parents.get(groupPosition));
		return view;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup
			parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.item_relation_child, parent,
				false) : convertView;
		ViewHolder holder = (ViewHolder) view.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.headImage = (ImageView) view.findViewById(R.id.relation_item_head);
			holder.nameText = (TextView) view.findViewById(R.id.relation_item_name);
			holder.numberText = (TextView) view.findViewById(R.id.relation_item_number);
			holder.checkGifts = (CheckBox) view.findViewById(R.id.relation_item_gifts);
			holder.checkImpress = (CheckBox) view.findViewById(R.id.relation_item_impress);
			holder.checkGrade = (CheckBox) view.findViewById(R.id.relation_item_grade);

			view.setTag(holder);
		}
		Friend friend = children.get(groupPosition).get(childPosition);
		holder.headImage.setImageResource(R.mipmap.head);
		holder.nameText.setText(friend.getName());
		holder.numberText.setText(friend.getNumber());
		holder.checkImpress.setText("92");
		holder.checkGrade.setText(friend.getGrade() + "");
		holder.headImage.setOnClickListener(clickListener);
		return view;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	private static class ViewHolder {
		ImageView headImage;
		TextView nameText, numberText;
		CheckBox checkGifts, checkImpress, checkGrade;
	}
}
