package com.timeline.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ListView;

import com.timeline.adapter.PastMeetingAdapter;
import com.timeline.main.R;

public class SearchAc extends BaseActivity {
	
	private ListView lv_SearchListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		 //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        
        lv_SearchListView = (ListView)findViewById(R.id.id_searchList);
        
        //测试数据
        List<Map<String, Object>> searchList=getData();  
        lv_SearchListView.setAdapter(new PastMeetingAdapter(this,searchList));
	}
	
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String, Object> map=new HashMap<String, Object>();  
            map.put("searchTagImage", null);  
            map.put("searchContent", "12:00 - 13:00  交互文档编写");
            list.add(map);  
        }  
        return list;  
	}
}
