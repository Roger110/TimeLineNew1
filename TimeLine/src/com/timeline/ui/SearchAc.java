package com.timeline.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.timeline.main.R;

public class SearchAc extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		 //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	}
}
