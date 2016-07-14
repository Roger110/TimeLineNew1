package com.timeline.adapter;

import java.util.List;

import com.timeline.bean.MeetingDetailPlanBean;
import com.timeline.bean.MeetingPlanBean;
import com.timeline.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MeetingPlanAdapter extends BaseExpandableListAdapter{
	private Context context;// 运行上下文
	private List<MeetingPlanBean> falistItems;// 父数据集合
	//private List<MKOLSearchRecord> chlistItems;// 子数据集合
	private LayoutInflater listContainer;// 视图容器
	private int faitemViewResource;// 父自定义项视图源
	private int chitemViewResource;// 子自定义项视图源
	
	static class faListItemView { // 自定义控件集合，与listitem_fameetplan布局一致

		public TextView fadate;
		public TextView fatime;
	}
	
	static class chListItemView { // 自定义控件集合，与item_meeting_plan布局一致
		public TextView chtime;
		public LinearLayout chLayout;
	}
	
	
	/**
	 * 实例化Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public MeetingPlanAdapter(Context context, List<MeetingPlanBean> data,int faresource,int chresource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.faitemViewResource = faresource;
		this.chitemViewResource = chresource;
		this.falistItems = data;
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return falistItems.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		if (falistItems.get(groupPosition).getDetails().size() !=0) {
			return falistItems.get(groupPosition).getDetails().size();
		}
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return falistItems.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		if (falistItems.get(groupPosition).getDetails() !=null) {
			return falistItems.get(groupPosition).getDetails().get(childPosition);
		}
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			MeetingPlanBean Record = falistItems.get(groupPosition);

			// 自定义视图
			faListItemView listItemView = null;
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.faitemViewResource, null);

			listItemView = new faListItemView();
			// 获取控件对象
			listItemView.fadate = (TextView) convertView
					.findViewById(R.id.layout_fameetplan_date);
			listItemView.fatime = (TextView) convertView
					.findViewById(R.id.layout_fameetplan_time);

			// 设置控件集到convertView
			convertView.setTag(listItemView);
			listItemView.fadate.setText(Record.getTimeString());
			return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		chListItemView listItemView = null;
		if (convertView == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.chitemViewResource, null);

			listItemView = new chListItemView();
			// 获取控件对象
			listItemView.chtime = (TextView) convertView
					.findViewById(R.id.item_plan_title);
			listItemView.chLayout = (LinearLayout) convertView
					.findViewById(R.id.item_plan_content);


			// 设置控件集到convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (chListItemView) convertView.getTag();
		}
		
		MeetingDetailPlanBean deplan = falistItems.get(groupPosition).getDetails().get(childPosition);
		listItemView.chtime.setText(deplan.getDetime());
		for (int i = 0; i < deplan.getPlans().size(); i++) {
			TextView tv = new TextView(context);
			tv.setText(deplan.getPlans().get(i));
			listItemView.chLayout.addView(tv);
		}
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
