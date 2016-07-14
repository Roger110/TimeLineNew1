package com.timeline.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.VolleyError;
import com.timeline.bean.ReturnInfo;
import com.timeline.bean.ReturnMsg;
import com.timeline.common.JsonToEntityUtils;
import com.timeline.common.StringUtils;
import com.timeline.common.UIHelper;
import com.timeline.interf.VolleyListenerInterface;
import com.timeline.main.R;
import com.timeline.webapi.HttpFactory;

public class AdviceAc extends BaseActivity {
	private EditText contentText;
	private Button btnOK;
	private ImageButton btnClose;
	private VolleyListenerInterface volleyListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);
		InitView();
		
        volleyListener = new VolleyListenerInterface(AdviceAc.this){			
		@Override
		public void onMySuccess(String result) {
			// TODO Auto-generated method stub
			ReturnMsg info = JsonToEntityUtils.jsontoRemsg(result);
			if (info.getRe_st().equals("success")) {
				finish();
			}

			UIHelper.ToastMessage(AdviceAc.this,info.getRe_msg().toString());
		}
		@Override
		public void onMyError(VolleyError error) {
			// TODO Auto-generated method stub
			
		}
	};
	}

	private void InitView() {
		contentText = (EditText) findViewById(R.id.text_advice);
		btnOK = (Button) findViewById(R.id.advice_btn_ok);
		btnClose = (ImageButton) findViewById(R.id.main_head_logo);
		btnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btnOK.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String content = contentText.getText().toString();
				if (!StringUtils.isEmail(content)) {
					HttpFactory.FeedBack(content, volleyListener);
				}
			}
		});
	}

}
