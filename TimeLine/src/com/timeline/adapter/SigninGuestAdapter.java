package com.timeline.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.timeline.bean.SigninPerson;
import com.timeline.bean.guest;
import com.timeline.main.R;
import com.timeline.main.R.color;
import com.timeline.widget.CircleImageView;

public class SigninGuestAdapter extends BaseAdapter {
	private Context context;// 运行上下文
	public List<SigninPerson> listItems;// 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源

	static class ListItemView { // 自定义控件集合，与listitem_signin布局一致
		public TextView name;
		public TextView no;
		public CircleImageView image;
	}

	/**
	 * 实例化Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public SigninGuestAdapter(Context context, List<SigninPerson> data,
			int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	/**
	 * ListView Item设置
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 自定义视图
		ListItemView listItemView = null;

		if (convertView == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);

			listItemView = new ListItemView();
			// 获取控件对象

			listItemView.name = (TextView) convertView
					.findViewById(R.id.item_signin_name);
			listItemView.no = (TextView) convertView
					.findViewById(R.id.item_signin_no);
			listItemView.image = (CircleImageView)convertView
					.findViewById(R.id.item_signin_ima);
			// 设置控件集到convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// 设置文字和图片
		SigninPerson gu = listItems.get(position);
		if (position ==0) {
			listItemView.no.setTextColor(context.getResources().getColor(R.color.yellow));
		}else if(position ==1){
			listItemView.no.setTextColor(context.getResources().getColor(R.color.red));
		}
		else if(position ==2){
			listItemView.no.setTextColor(context.getResources().getColor(R.color.green));
		}else {
			listItemView.no.setTextColor(context.getResources().getColor(R.color.black));
		}
		listItemView.name.setText(gu.getName());
		listItemView.name.setTag(gu);// 设置隐藏参数(实体类)
		listItemView.no.setText(String.valueOf(position+1));


		return convertView;
	}
}
