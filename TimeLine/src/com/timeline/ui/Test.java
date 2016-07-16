package com.timeline.ui;

import android.R.integer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.timeline.common.DensityUtil;
import com.timeline.main.R;

public class Test extends BaseActivity{
	
	private EditText input;
	private Button btn; 
	private TextView text;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testcount);
        input = (EditText)findViewById(R.id.login_password);
        btn=(Button)findViewById(R.id.login_btn_login);
        text = (TextView)findViewById(R.id.item_signin_no);
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int in = Integer.valueOf(input.getText().toString());
				text.setText(String.valueOf(DensityUtil.px2dip(Test.this,( float)in)));
			}
		});
	}
}
