package com.timeline.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.timeline.app.AppContext;
import com.timeline.bean.ReturnInfo;
import com.timeline.bean.User;
import com.timeline.common.JsonToEntityUtils;
import com.timeline.common.StringUtils;
import com.timeline.common.UIHelper;
import com.timeline.interf.VolleyListenerInterface;
import com.timeline.main.R;
import com.timeline.webapi.HttpFactory;

public class LoginInAc extends BaseActivity {

	private EditText accountText;
	private EditText passwordText;
	private Button loginBtn;
	private TextView forgetText;
	private TextView cancelText;
	private TextView registerText;

	private String account;
	private String psw;

	private VolleyListenerInterface volleyListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		volleyListener = new VolleyListenerInterface(LoginInAc.this) {

			@Override
			public void onMySuccess(String result) {
				// TODO Auto-generated method stub
				ReturnInfo info = JsonToEntityUtils.jsontoReinfo(result);
				if (info.getRe_st().equals("consummate")) {
//					User us = JsonToEntityUtils.jsontoUser(info.getRe_info());
//					AppContext.setUser(us);
					AppContext.getInstance().remenberPsw(account, psw);
					UIHelper.showMain(LoginInAc.this);
				}else {
					UIHelper.ToastMessage(LoginInAc.this,info.getRe_info() );
				}
				
			}

			@Override
			public void onMyError(VolleyError error) {
				// TODO Auto-generated method stub

			}

		};

	}

	public void Btn_Register(View v) {
		UIHelper.showRegister1(this);
	}

	public void Btn_Cancel(View v) {
		this.finish();
	}

	private void initView() {
		accountText = (EditText) findViewById(R.id.login_account);
		passwordText = (EditText) findViewById(R.id.login_password);
		loginBtn = (Button) findViewById(R.id.login_btn_login);
		forgetText = (TextView) findViewById(R.id.login_forget);
		cancelText = (TextView) findViewById(R.id.login_head_cancel);
		registerText = (TextView) findViewById(R.id.login_head_cancel);

		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				account = accountText.getText().toString();
				psw = passwordText.getText().toString();
				if (!StringUtils.isEmpty(account)&&!StringUtils.isEmail(psw)) {
					HttpFactory.Login(account, psw, volleyListener);
				}else {
					UIHelper.ToastMessage(LoginInAc.this, "请输入账号密码");
				}
				
				
			}
		});
	}
}
