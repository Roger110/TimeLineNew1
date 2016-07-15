package com.timeline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.timeline.common.UIHelper;
import com.timeline.main.R;
import com.timeline.widget.MyEditText;

public class InfoEditAc extends BaseActivity{

	private ImageButton btnClose;
	private com.timeline.widget.MyEditText editView;
	private TextView btnSave;
	
	private String type;
	private String name;
	private String changinfo;
	
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_infoedit);
	        Bundle bundle = getIntent().getExtras();;
	        type = bundle.getString("type"); 
	        name =  bundle.getString("name"); 
	        initView();
	    }
	    
	    private void initView(){
	    	btnClose =(ImageButton)findViewById(R.id.main_head_logo);
	    	editView =(MyEditText)findViewById(R.id.info);
	    	btnSave = (TextView)findViewById(R.id.infoedit_head_save);
	    	editView.setText(name);
	    }
	    
	    public void Btn_Save(View v) {
	    	name = editView.getText().toString();
			UIHelper.showMyInfo(InfoEditAc.this,type,name);
		}
}
