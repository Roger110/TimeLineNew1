package com.timeline.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.timeline.app.AppContext;
import com.timeline.bean.ReturnInfo;
import com.timeline.common.JsonToEntityUtils;
import com.timeline.common.StringUtils;
import com.timeline.common.UIHelper;
import com.timeline.interf.VolleyListenerInterface;
import com.timeline.main.R;
import com.timeline.webapi.HttpFactory;

public class Register1Ac  extends BaseActivity{

	private EditText phoneText;
	private Button btnOK;
	private TextView cancelText;
	
	private String phoneNum;
	
	private VolleyListenerInterface volleyListener;
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_register);
	        initView();
	        volleyListener = new VolleyListenerInterface(Register1Ac.this){			
			@Override
			public void onMySuccess(String result) {
				// TODO Auto-generated method stub
				Log.e("", result);
				ReturnInfo info = JsonToEntityUtils.jsontoReinfo(result);
				if (info.getRe_st().equals("success")) {
					UIHelper.showRegister2(Register1Ac.this);
				}

				UIHelper.ToastMessage(Register1Ac.this,info.getRe_info().toString());
			}
			@Override
			public void onMyError(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		};
	   }
	   
	   
	   private void initView() {
		   phoneText = (EditText)findViewById(R.id.register_phone);
		   btnOK = (Button)findViewById(R.id.register_btn_login);
		   cancelText = (TextView)findViewById(R.id.login_head_cancel);
		   btnOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String numString = phoneText.getText().toString();
				if (isMobileNO(numString)) {
					HttpFactory.getSend_SMS(Register1Ac.this,numString,"1",volleyListener);
					AppContext.phoneNum = numString;
				}
				else {
					UIHelper.ToastMessage(Register1Ac.this, "请输入正确的手机号");
				}
			}
		});
	}
	   
	  public boolean isMobileNO(String mobiles){

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	  }
	  public  String UnicodeToString(String str) {
	        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4 }))");    
	        Matcher matcher = pattern.matcher(str);
	        char ch;
	        while (matcher.find()) {
	            ch = (char) Integer.parseInt(matcher.group(2), 16);
	            str = str.replace(matcher.group(1), ch + "");    
	        }
	        return str;
	    }
}
