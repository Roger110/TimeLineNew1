package com.timeline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.timeline.app.AppContext;
import com.timeline.common.UIHelper;
import com.timeline.main.R;
import com.timeline.widget.CircleImageView;

public class MySigninAc extends BaseActivity{

	private CircleImageView headImg;
	private RelativeLayout pastMeetingsRl;
	private RelativeLayout collecsRl;
	
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_my_signin);
	        InitView();
	  }
	  
	  
	  public void SetClick(View v){
		  UIHelper.showSetting(this);
	  }
	  
	  public void Btn_login(View v){
		  UIHelper.showLogin(this);
	  }
	  
	  public void btn_Collect(View v){
		  
	  }
	  
	  public void btn_Advice(View v){
		  UIHelper.showAdvice(this);
	  }
	  
	  private void InitView() {
		  headImg = (CircleImageView)findViewById(R.id.my_head_ima);
		  headImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UIHelper.showMyInfo(MySigninAc.this);
			}
		});
		  collecsRl = (RelativeLayout)findViewById(R.id.ll_tools_collect);
		  pastMeetingsRl = (RelativeLayout)findViewById(R.id.my_info_last);
		  pastMeetingsRl.setOnClickListener(new OnClickListener(){
			  
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MySigninAc.this, PastMeetingsAc.class);
				startActivity(intent);
			}
			  
		  });
	}
	  @Override
	  protected void onResume() {
		super.onResume();
		if (!AppContext.getInstance().getIslogin()) {
			collecsRl.setVisibility(View.GONE);
			pastMeetingsRl.setVisibility(View.GONE);
		}
	}
}
