package com.timeline.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.timeline.main.R;


public class WeekFragment extends Fragment{

	//一周的活动
	private List<View> days = new ArrayList<View>();

	
	private GestureDetector gestureDetector;
	private ViewFlipper mFlipper;  

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		gestureDetector = new GestureDetector(getActivity(),onGestureListener);
		View view = (View) inflater.inflate(R.layout.fragment_week, null);
		
//		mFlipper = (ViewFlipper) view.findViewById(R.id.id_flipper);
//		mFlipper.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				return gestureDetector.onTouchEvent(event);
//			}
//		}); 
//		
//		view.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				return gestureDetector.onTouchEvent(event);
//			}
//		});
		days.add(view.findViewById(R.id.id_weekitem1));
		days.add(view.findViewById(R.id.id_weekitem2));
		days.add(view.findViewById(R.id.id_weekitem3));
		days.add(view.findViewById(R.id.id_weekitem4));
		days.add(view.findViewById(R.id.id_weekitem5));
		days.add(view.findViewById(R.id.id_weekitem6));
		days.add(view.findViewById(R.id.id_weekitem7));
		
		setAllChildren(days);
		
		return view;
	}
	
	/*
	 * 获取所有子控件
	 */
	private void setAllChildren(List<View> days) {
		for(View dayView : days ){
			
			HorizontalScrollView hsv = new HorizontalScrollView(getActivity());
			LinearLayout.LayoutParams hsvParam=new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//定义布局管理器的参数
			
			final LinearLayout allContents = new LinearLayout(getActivity());
			LinearLayout.LayoutParams allContentsParam=new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//定义布局管理器的参数
			allContents.setOrientation(LinearLayout.HORIZONTAL);//所有组件水平摆放
			
			for(int i =0; i<20; i++){
				
				WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
	 
			    int width = (wm.getDefaultDisplay().getWidth() - 15)/14*12/2;
				
				LinearLayout content = new LinearLayout(getActivity());
				LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(
						width, ViewGroup.LayoutParams.MATCH_PARENT);//定义布局管理器的参数
				param.setMargins(5,0,0,0);
				param.gravity = Gravity.CENTER_HORIZONTAL;
				content.setOrientation(LinearLayout.VERTICAL);//所有组件垂直摆放
				content.setBackgroundColor(Color.GREEN);

				//定义显示组件的布局管理器，为了简单，本次只定义一个TextView组件
				LinearLayout.LayoutParams topicText=new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT, 0, 2.0f);//定义文本显示组件
				topicText.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
				TextView topic = new TextView(getActivity());
				topic.setLayoutParams(topicText);//配置文本显示组件的参数
				topic.setText("动态生成内容1");//配置显示文字
				topic.setTextSize(13);
				topic.setTextColor(Color.BLACK);
				topic.getPaint().setFakeBoldText(true);
				content.addView(topic, topicText);
				
				//定义显示组件的布局管理器，为了简单，本次只定义一个TextView组件
				LinearLayout.LayoutParams orgnizerText=new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1.0f);//定义文本显示组件
				orgnizerText.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
				TextView orgnizer = new TextView(getActivity());
				orgnizer.setLayoutParams(orgnizerText);//配置文本显示组件的参数
				orgnizer.setText("动态生成内容2");//配置显示文字
				orgnizer.setTextSize(12);
				orgnizer.setTextColor(Color.BLACK);
				content.addView(orgnizer, orgnizerText);
				
				content.setOnClickListener(new ContentClickListener());

				allContents.addView(content, param);
				
			}
			hsv.addView(allContents,allContentsParam);
			LinearLayout dayContent = (LinearLayout)dayView.findViewById(R.id.id_dayContent);
			dayContent.addView(hsv,hsvParam);
			
		}
	}


	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	/*
	 * content点击事件
	 */
	public class ContentClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(), v.getId()+"", Toast.LENGTH_SHORT).show();
			showPopupWindow(v);
		}
	}
	
	private GestureDetector.OnGestureListener onGestureListener =   
	        new GestureDetector.SimpleOnGestureListener() {  
	        @Override  
	        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
	                float velocityY) {  
	            float x = e2.getX() - e1.getX();  
	            float y = e1.getY() - e2.getY();  
	  
	            if (y > 100) {  
	                Toast.makeText(getActivity(), "next", Toast.LENGTH_SHORT).show();
	            } else if (x < -100) {  
	            	Toast.makeText(getActivity(), "previous", Toast.LENGTH_SHORT).show();
	            }  
	            return true;  
	        }  
	    }; 
	    
	    public boolean onTouchEvent(MotionEvent event) {  
	        return gestureDetector.onTouchEvent(event);  
	    }
	    
	    private void showPopupWindow(View view) {

	        // 一个自定义的布局，作为显示的内容
	        View contentView = LayoutInflater.from(getActivity()).inflate(
	                R.layout.contentpopwindow, null);
	        // 设置按钮的点击事件
//	        Button button = (Button) contentView.findViewById(R.id.button1);
//	        button.setOnClickListener(new OnClickListener() {
//
//	            @Override
//	            public void onClick(View v) {
//	                Toast.makeText(getActivity(), "button is pressed",
//	                        Toast.LENGTH_SHORT).show();
//	            }
//	        });

	        final PopupWindow popupWindow = new PopupWindow(contentView,
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);

	        popupWindow.setTouchable(true);

	        popupWindow.setTouchInterceptor(new OnTouchListener() {

	            @Override
	            public boolean onTouch(View v, MotionEvent event) {

	                Log.i("mengdd", "onTouch : ");

	                return false;
	                // 这里如果返回true的话，touch事件将被拦截
	                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
	            }
	        });

	        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	        // 我觉得这里是API的一个bug
	        popupWindow.setBackgroundDrawable(getResources().getDrawable(
	                R.color.white));

	        // 设置好参数之后再show
	        popupWindow.showAsDropDown(view);

	    }
}
