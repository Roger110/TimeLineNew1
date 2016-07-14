package com.timeline.adapter;

import java.util.List;
import java.util.Map;

import com.timeline.main.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MonthContentListAdapter extends BaseAdapter {
	
		private List<Map<String, Object>> data;
		private LayoutInflater layoutInflater;
		private Context context;
		public MonthContentListAdapter(Context context,List<Map<String, Object>> data){
			this.context=context;
			this.data=data;
			this.layoutInflater=LayoutInflater.from(context);
		}
		/**
		 * 组件集合，对应listitem_monthcontent.xml.xml中的控件
		 * @author Administrator
		 */
		public final class ContentListItemControls{
			public LinearLayout split;
			public TextView startTime;
			public TextView endTime;
			public TextView contentTitle;
		}
		@Override
		public int getCount() {
			return data.size();
		}
		/**
		 * 获得某一位置的数据
		 */
		@Override
		public Object getItem(int position) {
			return data.get(position);
		}
		/**
		 * 获得唯一标识
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ContentListItemControls contentListItemControls=null;
			if(convertView==null){
				contentListItemControls=new ContentListItemControls();
				//获得组件，实例化组件
				convertView=layoutInflater.inflate(R.layout.listitem_monthcontent, null);
				contentListItemControls.startTime=(TextView)convertView.findViewById(R.id.id_startTime);
				contentListItemControls.endTime=(TextView)convertView.findViewById(R.id.id_endTime);
				contentListItemControls.split=(LinearLayout)convertView.findViewById(R.id.id_split);
				contentListItemControls.contentTitle=(TextView)convertView.findViewById(R.id.id_contentTitle);
				convertView.setTag(contentListItemControls);
			}else{
				contentListItemControls=(ContentListItemControls)convertView.getTag();
			}
			//绑定数据
			//contentListItemControls.split.setBackgroundColor((Integer)data.get(position).get("splitColor")); 
			contentListItemControls.startTime.setText((String)data.get(position).get("startTime"));
			contentListItemControls.endTime.setText((String)data.get(position).get("endTime"));
			contentListItemControls.contentTitle.setText((String)data.get(position).get("contentTitle"));
			return convertView;
		}

	}

