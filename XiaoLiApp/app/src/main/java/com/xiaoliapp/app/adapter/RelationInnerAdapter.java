package com.xiaoliapp.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.xiaoliapp.app.R;

import javax.net.ssl.HandshakeCompletedListener;

/**
 * Created by VennUser on 2015/8/20.
 */
public class RelationInnerAdapter extends BaseAdapter {

	private Context context;

	public RelationInnerAdapter(Context context) {
		this.context = context;
	}

	public int getCount() {
		return 20;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.item_guide_inner_list, parent,
				false) : convertView;
		ViewHolder holder = (ViewHolder) view.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.imageView = (ImageView) view.findViewById(R.id.item_guide_inner_list_image);
			holder.msgCheckBox = (CheckBox) view.findViewById(R.id.item_guide_inner_list_msg);
			holder.scanCheckBox = (CheckBox) view.findViewById(R.id.item_guide_inner_list_scan);
			holder.shareCheckBox = (CheckBox) view.findViewById(R.id.item_guide_inner_list_share);
			holder.cleCheckBox = (CheckBox) view.findViewById(R.id.item_guide_inner_list_collection);
			view.setTag(holder);
		}

		holder.imageView.setImageResource(R.mipmap.show_pager);
		holder.msgCheckBox.setText("23");
		holder.scanCheckBox.setText("328");
		holder.shareCheckBox.setText("92");
		holder.cleCheckBox.setText("14");
		return view;
	}

	private static class ViewHolder {
		ImageView imageView;
		CheckBox msgCheckBox, scanCheckBox, shareCheckBox, cleCheckBox;
	}
}
