package com.timeline.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.timeline.bean.ReturnInfo;
import com.timeline.common.JsonToEntityUtils;
import com.timeline.common.StringUtils;
import com.timeline.common.UIHelper;
import com.timeline.interf.VolleyListenerInterface;
import com.timeline.main.R;
import com.timeline.webapi.HttpFactory;

public class Register2Ac extends BaseActivity{

	private ImageButton backBtn;
	private TextView headTxt;
	private EditText yanzhengTxt;
	private EditText passwordTxt;
	private EditText agpasswordTxt;
	private Button okBtn;
	
	private String psw;
	private String pswag;
	
	private VolleyListenerInterface volleyListener;
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_send_sms);
	        initView();
	        initData();
	   }
	  private void initData() {
		   volleyListener = new VolleyListenerInterface(Register2Ac.this) {
				
			@Override
			public void onMySuccess(String result) {
				// TODO Auto-generated method stub
				Log.e("", result);
				ReturnInfo info = JsonToEntityUtils.jsontoReinfo(result);
				if (info.getRe_st().equals("success")) {
					UIHelper.showLogin(Register2Ac.this);
					Register2Ac.this.finish();
				}
				else {
					UIHelper.ToastMessage(Register2Ac.this,info.getRe_info().toString());
				}
			}
			@Override
			public void onMyError(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		};
	  }
	   
	   private void initView() {
		   backBtn =(ImageButton)findViewById(R.id.main_head_logo);
		   headTxt = (TextView)findViewById(R.id.main_head_title);
		   yanzhengTxt = (EditText)findViewById(R.id.send_number);
		   passwordTxt = (EditText)findViewById(R.id.register_password);
		   agpasswordTxt = (EditText)findViewById(R.id.register_password_again);
		   okBtn = (Button)findViewById(R.id.sms_btn_ok);
		   okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				psw = passwordTxt.getText().toString();
				pswag = agpasswordTxt.getText().toString();
				String verify= yanzhengTxt.getText().toString();
				if (!StringUtils.isEmpty(psw)&&psw.equals(pswag)) {
					HttpFactory.Register(psw, verify, volleyListener);
				}
				else {
					UIHelper.ToastMessage(Register2Ac.this, "请检查密码是否统一");
				}
			}
		});

	   }
}
