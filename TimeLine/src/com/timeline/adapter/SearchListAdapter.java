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

public class SearchListAdapter extends BaseAdapter {

	private List<Map<String, Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	
	public SearchListAdapter(Context context,List<Map<String, Object>> data){
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
		SearchListItemControls searchListItemControls=null;
		if(convertView==null){
			searchListItemControls=new SearchListItemControls();
			//获得组件，实例化组件
			convertView=layoutInflater.inflate(R.layout.listitem_search, null);
			searchListItemControls.searchTagImage=(ImageView)convertView.findViewById(R.id.id_searchTagImage);
			searchListItemControls.searchContent=(TextView)convertView.findViewById(R.id.id_searchContent);
			convertView.setTag(searchListItemControls);
		}else{
			searchListItemControls=(SearchListItemControls)convertView.getTag();
		}
		//绑定数据
		//contentListItemControls.meetingImage.setBackgroundColor((Integer)data.get(position).get("splitColor"));
		searchListItemControls.searchContent.setText((String)data.get(position).get("searchContent"));
		return convertView;
	}
	
	/**
	 * 组件集合，对应listitem_search.xml中的控件 
	 * @author Administrator
	 */
	public final class SearchListItemControls{
		public ImageView searchTagImage;
		public TextView searchContent;
	}

}
