package com.timeline.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.timeline.controls.MonthDateView;
import com.timeline.controls.MonthDateView.DateClick;
import com.timeline.adapter.MonthContentListAdapter;
import com.timeline.bean.MomentBean;
import com.timeline.calendar.CalendarUtils;
import com.timeline.calendar.MomentAdapter;
import com.timeline.main.R;
import com.timeline.ui.PastMeetingsAc;
import com.timeline.ui.SearchAc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MonthFragment extends Fragment {
	
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_month;
	private TextView tv_dateText;
	private MonthDateView monthDateView;
	
	private ListView lv_monthContent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = (View) inflater.inflate(R.layout.fragment_month, null);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(12);
		list.add(15);
		list.add(16);

		iv_left = (ImageView) view.findViewById(R.id.iv_left);
		iv_right = (ImageView) view.findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) view.findViewById(R.id.monthDateView);
		tv_month = (TextView) view.findViewById(R.id.id_monthtext);
		lv_monthContent = (ListView)view.findViewById(R.id.id_contentlist_month);
		
		tv_dateText = (TextView)view.findViewById(R.id.id_dateText);
		
		//测试数据
		List<Map<String, Object>> contentList=getData();  
		lv_monthContent.setAdapter(new MonthContentListAdapter(getActivity(),contentList)); 
		
		monthDateView.setTextView(tv_month);
		monthDateView.setDaysHasThingList(list);
		monthDateView.setDateClick(new DateClick() {
			
			@Override
			public void onClickOnDate() {
				//Toast.makeText(getActivity(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
				initValues();
			}
		});

		initValues();
		
		setOnlistener();
		
		
		
		return view;
	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String, Object> map=new HashMap<String, Object>();  
            map.put("split", Color.GREEN);  
            map.put("startTime", "14:00");  
            map.put("endTime", "15:30");  
            map.put("contentTitle", "技术交流"); 
            list.add(map);  
        }  
        return list;  
	}

	private void initValues() {
		// 获得Service实例
		tv_dateText.setText(monthDateView.getmSelMonth()+"月"+monthDateView.getmSelDay()+"日");
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	private void setOnlistener(){
		iv_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.onLeftClick();
				initValues();
			}
		});
		
		iv_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.onRightClick();
				tv_dateText.setText(monthDateView.getmSelMonth()+"月"+monthDateView.getmSelDay()+"日");
				initValues();
			}
		});
		
//		tv_today.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				monthDateView.setTodayToView();
//			}
//		});
	}
}
