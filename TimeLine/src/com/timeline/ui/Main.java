package com.timeline.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.timeline.interf.FragmentCallBack;
import com.timeline.interf.VolleyListenerInterface;
import com.timeline.main.R;
import com.timeline.slidedatetimepicker.SlideDateTimeListener;
import com.timeline.slidedatetimepicker.SlideDateTimePicker;
import com.timeline.webapi.HttpFactory;
import com.timeline.app.AppContext;
import com.timeline.bean.MeetingDescribe;
import com.timeline.bean.MeetingInfo;
import com.timeline.bean.ReturnInfo;
import com.timeline.bean.User;
import com.timeline.common.DateTimeHelper;
import com.timeline.common.JsonToEntityUtils;
import com.timeline.common.UIHelper;
import com.timeline.controls.ChangeColorIconWithText;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class Main extends BaseActivity implements FragmentCallBack{

	private ViewPager mViewPager;
	private View viewDay,viewWeek,viewMonth,viewMeeting;
	private ArrayList<View> mTabs = new ArrayList<View>();
	
	private int currIndex = 0;// 当前页卡编号
	private PagerAdapter mAdapter;
	
	private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();
	private ImageButton btnSeach;
	private ImageButton btnMyInfo;
	private ImageButton btnadd;
	
	private VolleyListenerInterface loginvolleyListener;
	
	//day控件
	String daydate;
	private VolleyListenerInterface dayvolleyListener;//当前日期会议搜索监听
	
	//week控件
	
	
	
	//month控件
	
	
	//meeting控件
	private String meetid;
	private VolleyListenerInterface meetingvolleyListener;//当前日期会议搜索监听
	private MeetingDescribe nowDescribe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initView();
        initData();
        mViewPager.setAdapter(mAdapter);
        initAllPagerData();
        if (AppContext.getInstance().getIslogin()) {
        	HttpFactory.Login(AppContext.getInstance().getAccount(),AppContext.getInstance().getPsw(), loginvolleyListener);
		}
    }
    
	/*
	 * 初始化各栏目数据项
	 */
	private void initAllPagerData() {
		btnSeach = (ImageButton)findViewById(R.id.home_head_morebutton);
		btnMyInfo =(ImageButton)findViewById(R.id.img_head);
		btnadd = (ImageButton)findViewById(R.id.home_add);
		btnSeach.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIHelper.showMeetingDetail(Main.this);
			}
		});
		btnMyInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UIHelper.showMySign(Main.this);
			}
		});
		btnadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIHelper.showNewEvent(Main.this);
			}
		});
		loginvolleyListener = new VolleyListenerInterface(Main.this) {

			@Override
			public void onMySuccess(String result) {
				// TODO Auto-generated method stub
				try {
					JSONObject myJsonObject = new JSONObject(result);
					String rest = myJsonObject.getString("re_st");
					if (rest.equals("success")) {
						User us = JsonToEntityUtils.jsontoUser( myJsonObject.getString("re_info"));
						AppContext.setUser(us);
					}else {
						ReturnInfo info = JsonToEntityUtils.jsontoReinfo(result);
						UIHelper.ToastMessage(Main.this,info.getRe_info() );
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void onMyError(VolleyError error) {
				// TODO Auto-generated method stub

			}

		};
		//day
		daydate = DateTimeHelper.getDateNow();
		dayvolleyListener = new VolleyListenerInterface(Main.this){
			@Override
			public void onMySuccess(String result) {
				// TODO Auto-generated method stub
				try {
					JSONObject myJsonObject = new JSONObject(result);
					String rest = myJsonObject.getString("re_st");
					if (rest.equals("success")) {
						Message msg = Message.obtain();
						MeetingInfo[] meetings
						= JsonToEntityUtils.jsontoMeetingInfo( myJsonObject.getString("re_info"));
						meetid = meetings[0].getId();
						msg.what = 0;
						msg.obj = meetings;
						AppContext.getInstance().mDayTagGetHandler.sendMessage(msg);
				}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			@Override
			public void onMyError(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		
		//week
		
		
		
		//month
		
		
		//meeting
		meetingvolleyListener = new VolleyListenerInterface(Main.this){
			@Override
			public void onMySuccess(String result) {
				// TODO Auto-generated method stub
				try {
					JSONObject myJsonObject = new JSONObject(result);
					String res = myJsonObject.getString("re_st");
					if (res.equals("success")) {
						String rest = myJsonObject.getString("re_info");
						JSONObject meetingObject = new JSONObject(rest);
						MeetingDescribe meetingdes = JsonToEntityUtils
								.jsontoMeetingDes(meetingObject
										.getString("meeting_info"));
						nowDescribe = meetingdes;
				}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			@Override
			public void onMyError(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	  public void Interaction_Click(View v){
		  UIHelper.showInterac(this);
	  }
	  public void Signin_Click(View v){
		  UIHelper.showGuSign(meetid,this);
	  }
	  
    @Override
	  protected void onResume() {
		super.onResume();
		HttpFactory.getMeetingjoin_list(daydate, dayvolleyListener);
	}
	  
    private void initData() {
		// TODO Auto-generated method stub
		LayoutInflater mLi = LayoutInflater.from(this);
		viewDay = mLi.inflate(R.layout.frame_day,null);
		viewWeek = mLi.inflate(R.layout.frame_week,null);
		viewMonth = mLi.inflate(R.layout.frame_month,null);
		viewMeeting = mLi.inflate(R.layout.frame_meeting,null);
		
		ViewWeekHelper.InitViewWeek(viewWeek, this);
		
		
		mTabs.add(viewDay);
		mTabs.add(viewWeek);
		mTabs.add(viewMonth);
		mTabs.add(viewMeeting);
		
		mAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return mTabs.size();
			}

			@Override  
            public int getItemPosition(Object object) {  
                return super.getItemPosition(object);  
            }  			
			
			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(mTabs.get(position));
			}
			
			//@Override
			//public CharSequence getPageTitle(int position) {
				//return titles.get(position);
			//}
			
			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(mTabs.get(position));
				return mTabs.get(position);
			}
		};
		
		mViewPager.setAdapter(mAdapter);		
		mViewPager.setCurrentItem(currIndex);

    }

	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager)findViewById(R.id.id_viewpager);
		
		ChangeColorIconWithText day = (ChangeColorIconWithText)findViewById(R.id.indicator_day);
		mTabIndicators.add(day);
		ChangeColorIconWithText week = (ChangeColorIconWithText)findViewById(R.id.indicator_week);
		mTabIndicators.add(week);
		ChangeColorIconWithText month = (ChangeColorIconWithText)findViewById(R.id.indicator_month);
		mTabIndicators.add(month);
		ChangeColorIconWithText meeting = (ChangeColorIconWithText)findViewById(R.id.indicator_meeting);
		mTabIndicators.add(meeting);
		
		day.setOnClickListener(new IndicatorClickListener());
		week.setOnClickListener(new IndicatorClickListener());
		month.setOnClickListener(new IndicatorClickListener());
		meeting.setOnClickListener(new IndicatorClickListener());
		
		day.SetIconAlpha(1.0f);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
    
    public class IndicatorClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			resetOtherTabs();
			
			switch(v.getId())
			{
			case R.id.indicator_day:
				mTabIndicators.get(0).SetIconAlpha(1.0f);
				mViewPager.setCurrentItem(0,false);
				refreshData(v.getId());
				break;
			case R.id.indicator_week:
				mTabIndicators.get(1).SetIconAlpha(1.0f);
				mViewPager.setCurrentItem(1,false);
				refreshData(v.getId());
				break;
			case R.id.indicator_month:
				mTabIndicators.get(2).SetIconAlpha(1.0f);
				mViewPager.setCurrentItem(2,false);
				refreshData(v.getId());
				break;
			case R.id.indicator_meeting:
				mTabIndicators.get(3).SetIconAlpha(1.0f);
				mViewPager.setCurrentItem(3,false);
				refreshData(v.getId());
				break;	
			}
		}
    }
    /**
     *数据实时刷新
     * @param Id
     */
    private void refreshData(int Id)
    {
		switch(Id)
		{
		case R.id.indicator_day:
			
			
			break;
		case R.id.indicator_week:

			break;
		case R.id.indicator_month:

			break;
		case R.id.indicator_meeting:
			HttpFactory.getMeetingDescribe(meetid, meetingvolleyListener);
			break;

		}
    }
    
    private void resetOtherTabs() {
		// TODO Auto-generated method stub
		for(int i =0; i< mTabIndicators.size(); i++)
		{
			mTabIndicators.get(i).SetIconAlpha(0);
		}
	}

	@Override
	public void callbackFun1(String date) {
		// TODO Auto-generated method stub
		String[] str = date.split("-");
		if (Integer.valueOf(str[1])<10) {
			date = str[0]+"-0"+str[1]+"-"+str[2];
		}
		HttpFactory.getMeetingjoin_list(date, dayvolleyListener);
	}

	@Override
	public void callbackFun2(Bundle arg) {
		// TODO Auto-generated method stub
		
	}
}
