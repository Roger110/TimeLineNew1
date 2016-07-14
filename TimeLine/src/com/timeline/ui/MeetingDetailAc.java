package com.timeline.ui;

import java.util.ArrayList;
import java.util.List;

import com.timeline.adapter.MeetingGuestAdapter;
import com.timeline.adapter.MeetingPlanAdapter;
import com.timeline.bean.MeetingDetailPlanBean;
import com.timeline.bean.MeetingPlanBean;
import com.timeline.bean.guest;
import com.timeline.main.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MeetingDetailAc extends BaseActivity{
	
	private ViewPager mTabPager;
	//填充界面
	private View viewMeg, viewPlan, viewGuest;
	private TextView txtTab1, txtTab2,txtTab4;
	private LinearLayout ll_Tab1, ll_Tab2, ll_Tab4;
	private int zero = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int one;// 单个水平动画位移
	private int two;
	private int three;
	
	//会议通知控件
	
	
	//会议日程控件
	private ExpandableListView planlistview;
	private MeetingPlanAdapter planAdapter;
	private List<MeetingPlanBean> plans = new ArrayList<MeetingPlanBean>();
	
	//会议嘉宾控件
	private ListView guestlistview;
	private MeetingGuestAdapter guestAdapter;
	private List<guest> guests = new ArrayList<guest>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meeting_detail);
		initViewPager();
		changeViewPager(currIndex);
		// 初始化各栏数据
		initAllPagerData();

	}
	/*
	 * 初始化各栏目数据项
	 */
	private void initAllPagerData() {
		//会议通知
		
		
		//会议日程
		List<String> strings = new ArrayList<String>();
		strings.add("大会签到");
		strings.add("大会签到");
		strings.add("大会签到");
		for (int i = 0; i < 5; i++) {
			MeetingPlanBean plan = new MeetingPlanBean();
			plan.setTimeString("2015-11-27 上午 （12:00-13：00）");
			for (int j = 0; j < 2; j++) {
				MeetingDetailPlanBean dePlan = new MeetingDetailPlanBean();
				dePlan.setDetime("07:00-07:35");
				dePlan.setPlans(strings);
				plan.getDetails().add(dePlan);
			}
			plans.add(plan);
		}
		planlistview = (ExpandableListView)viewPlan.findViewById(R.id.meeing_plan_listview);
		planAdapter = new MeetingPlanAdapter(this, plans, R.layout.listitem_faomeetplan, R.layout.item_meeting_plan);
		planlistview.setAdapter(planAdapter);
		planlistview.setGroupIndicator(null);
		//会议嘉宾
		for (int i = 0; i < 10; i++) {
			guest gu = new guest();
			gu.setName("蒋一峰");
			gu.setJob("中国医疗器械行业副会长");
			guests.add(gu);
		}
		guestlistview =(ListView)viewGuest.findViewById(R.id.meeting_tab_listview);
		guestAdapter = new MeetingGuestAdapter(MeetingDetailAc.this,guests,R.layout.listitem_meeting_people);
		guestlistview.setAdapter(guestAdapter);
	}
	
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		mTabPager = (ViewPager) findViewById(R.id.Meeting_Tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

		txtTab1 = (TextView) findViewById(R.id.meeting_mes);
		txtTab2 = (TextView) findViewById(R.id.meeting_shc);
		// txtTab3 = (TextView) findViewById(R.id.temptask_tab_recorder);
		txtTab4 = (TextView) findViewById(R.id.meeting_peo);

		txtTab1.setOnClickListener(new MyOnClickListener(0));
		txtTab2.setOnClickListener(new MyOnClickListener(1));
		// txtTab3.setOnClickListener(new MyOnClickListener(2));
		txtTab4.setOnClickListener(new MyOnClickListener(2));

		ll_Tab1 = (LinearLayout) findViewById(R.id.meeting_tab_mes);
		ll_Tab2 = (LinearLayout) findViewById(R.id.meeting_tab_shc);
		// ll_Tab3 = (LinearLayout) findViewById(R.id.ll_temptask_tab_recorder);
		ll_Tab4 = (LinearLayout) findViewById(R.id.meeting_tab_peo);

		ll_Tab1.setOnClickListener(new MyOnClickListener(0));
		ll_Tab2.setOnClickListener(new MyOnClickListener(1));
		// ll_Tab3.setOnClickListener(new MyOnClickListener(2));
		ll_Tab4.setOnClickListener(new MyOnClickListener(2));

		Display currDisplay = getWindowManager().getDefaultDisplay();// 获取屏幕当前分辨率
		int displayWidth = currDisplay.getWidth();
		int displayHeight = currDisplay.getHeight();
		one = displayWidth / 4; // 设置水平动画平移大小
		two = one * 2;
		three = one * 3;
		// Log.i("info", "获取的屏幕分辨率为" + one + two + three + "X" + displayHeight);

		// InitImageView();//使用动画
		// 将要分页显示的View装入数组中
		LayoutInflater mLi = LayoutInflater.from(this);
		viewMeg = mLi.inflate(R.layout.meeting_tab_meg, null);
		viewPlan = mLi.inflate(R.layout.meeting_tab_she, null);
		viewGuest = mLi.inflate(R.layout.meeting_tab_people, null);

		// 每个页面的view数据
		final ArrayList<View> views = new ArrayList<View>();
		views.add(viewMeg);
		views.add(viewPlan);
		views.add(viewGuest);
		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			// @Override
			// public CharSequence getPageTitle(int position) {
			// return titles.get(position);
			// }

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};
		mTabPager.setAdapter(mPagerAdapter);
		mTabPager.setCurrentItem(0);//
	}
	/*
	 * 页卡切换监听(原作者:D.Winter)
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			changeViewPager(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	/**
	 * ViewPager图标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
	/**
	 * 切换Page
	 */
	private void changeViewPager(int arg0) {

		switch (arg0) {
		case 0:// 描述
			ll_Tab1.setEnabled(true);
			ll_Tab2.setEnabled(false);
			ll_Tab4.setEnabled(false);

			txtTab1.setTextColor(Color.RED);
			txtTab2.setTextColor(Color.BLACK);
			txtTab4.setTextColor(Color.BLACK);
			break;
		case 1:// 拍照
			ll_Tab1.setEnabled(false);
			ll_Tab2.setEnabled(true);
			ll_Tab4.setEnabled(false);

			txtTab1.setTextColor(Color.BLACK);
			txtTab2.setTextColor(Color.RED);
			// txtTab3.setTextColor(Color.BLACK);
			txtTab4.setTextColor(Color.BLACK);

			break;

		 
		case 2:// 历史
			ll_Tab1.setEnabled(false);
			ll_Tab2.setEnabled(false);
			ll_Tab4.setEnabled(true);

			txtTab1.setTextColor(Color.BLACK);
			txtTab2.setTextColor(Color.BLACK);
			txtTab4.setTextColor(Color.RED);
			break;
		}
		currIndex = arg0;


	}

}

