package com.timeline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.timeline.common.UIHelper;
import com.timeline.main.R;

public class MyInfoAc extends BaseActivity{
	
	private TextView nameTextView;
	private TextView sexualView;
	private TextView hospitalView;
	private TextView departmentView;
	private TextView dutyView;
	private TextView jobtitleView;
	
	private String type;
	private String name;
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_my_info);
	        InitView();
	  }
	  
	  private void InitView() {
		  nameTextView = (TextView)findViewById(R.id.myinfo_name_val);
		  sexualView =(TextView)findViewById(R.id.myinfo_sexual_val);
		  departmentView =(TextView)findViewById(R.id.myinfo_department_val);
		  dutyView =(TextView)findViewById(R.id.myinfo_duty_val);
		  jobtitleView =(TextView)findViewById(R.id.myinfo_jobtitle_val);
		  hospitalView=(TextView)findViewById(R.id.myinfo_hospital_val);
		  
	}
	  @Override
	  protected void onNewIntent(Intent intent) {

		   super.onNewIntent(intent);
		   setIntent(intent);//must store the new intent unless getIntent() will return the old one
	        Bundle bundle = getIntent().getExtras();;
	        type = bundle.getString("type"); 
	        name =  bundle.getString("name"); 
	        if (type.equals("name")) {
	        	nameTextView.setText(name);
			}else if(type.equals("sexual")){
				sexualView.setText(name);
			}else if(type.equals("department")){
				departmentView.setText(name);
			}else if(type.equals("duty")){
				dutyView.setText(name);
			}else if(type.equals("jobtitle")){
				jobtitleView.setText(name);
			}else if(type.equals("hospital")){
				hospitalView.setText(name);
			}

		 }
	  public void btn_HeadSet(View v) {
		
	  }
	  
	  public void btn_NameSet(View v) {
			UIHelper.showInfoEdit(this,"name",nameTextView.getText().toString());
	  }
	  
	  public void btn_SexualSet(View v) {
			
	  }
	  
	  public void btn_OfficeSet(View v) {
		  UIHelper.showInfoEdit(this,"department",departmentView.getText().toString());
	  }
	  
	  public void btn_DutySet(View v) {
			
	  }
	  public void btn_JobtitleSet(View v) {
			
	  }
	  public void btn_HospitalSet(View v) {
		  UIHelper.showInfoEdit(this,"hospital",hospitalView.getText().toString());
	  }
	  
}

