package com.timeline.adapter;

import java.util.List;
import java.util.Map;

import com.timeline.adapter.MonthContentListAdapter.ContentListItemControls;
import com.timeline.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PastMeetingAdapter extends BaseAdapter {

	private List<Map<String, Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	
	public PastMeetingAdapter(Context context,List<Map<String, Object>> data){
		this.context=context;
		this.data=data;
		this.layoutInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		PastMeetingListItemControls pastMeetingListItemControls=null;
		if(convertView==null){
			pastMeetingListItemControls=new PastMeetingListItemControls();
			//获得组件，实例化组件
			convertView=layoutInflater.inflate(R.layout.listitem_pastmeeting, null);
			pastMeetingListItemControls.meetingImage=(ImageView)convertView.findViewById(R.id.id_meetingImage);
			pastMeetingListItemControls.meetingOrgnizer=(TextView)convertView.findViewById(R.id.id_meetingOrgnizer);
			pastMeetingListItemControls.meetingTitle=(TextView)convertView.findViewById(R.id.id_meetingTitle);
			pastMeetingListItemControls.meetingDate=(TextView)convertView.findViewById(R.id.id_meetingDate);
			convertView.setTag(pastMeetingListItemControls);
		}else{
			pastMeetingListItemControls=(PastMeetingListItemControls)convertView.getTag();
		}
		//绑定数据
		//contentListItemControls.meetingImage.setBackgroundColor((Integer)data.get(position).get("splitColor")); 
		pastMeetingListItemControls.meetingOrgnizer.setText((String)data.get(position).get("meetingOrgnizer"));
		pastMeetingListItemControls.meetingTitle.setText((String)data.get(position).get("meetingTitle"));
		pastMeetingListItemControls.meetingDate.setText((String)data.get(position).get("meetingDate"));
		return convertView;
	}
	
	/**
	 * 组件集合，对应listitem_pastmeeting.xml中的控件 
	 * @author Administrator
	 */
	public final class PastMeetingListItemControls{
		public ImageView meetingImage;
		public TextView meetingOrgnizer;
		public TextView meetingTitle;
		public TextView meetingDate;
	}

}
