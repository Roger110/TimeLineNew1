package com.timeline.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.R.drawable;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import com.timeline.adapter.MonthContentListAdapter;
import com.timeline.adapter.PastMeetingAdapter;
import com.timeline.main.R;

/**
 * 往期会议
 * @author lxb
 *
 */
public class PastMeetingsAc extends BaseActivity {

	private ListView lv_pastMeetings;
	
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pastmeetings);
		
		//透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        lv_pastMeetings = (ListView)findViewById(R.id.id_pastMeetingsLst);
        
        //测试数据
        List<Map<String, Object>> pastMeetingsList=getData();  
        lv_pastMeetings.setAdapter(new PastMeetingAdapter(this,pastMeetingsList));
	}
	
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String, Object> map=new HashMap<String, Object>();  
            map.put("meetingImage", null);  
            map.put("meetingOrgnizer", "美国神经内科学会");  
            map.put("meetingTitle", "2016年第68届美国神经学会年会");  
            map.put("meetingDate", "16-04-17"); 
            list.add(map);  
        }  
        return list;  
	}
	
}
