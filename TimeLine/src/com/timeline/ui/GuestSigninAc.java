package com.timeline.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.timeline.adapter.SigninGuestAdapter;
import com.timeline.bean.guest;
import com.timeline.main.R;

public class GuestSigninAc extends BaseActivity{
	
	private ListView guestsView;
	private SigninGuestAdapter guestAdapter;
	private List<guest> guests = new ArrayList<guest>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        InitView();
        InitData();  }
  
  private void InitView() {
	  guestsView = (ListView)findViewById(R.id.signin_listview);
}
  private void InitData() {
	  for (int i = 0; i < 10; i++) {
		  guest info = new guest();
		  info.setName("Shirley Harris");
		  guests.add(info);
	}
	  guestAdapter = new SigninGuestAdapter(GuestSigninAc.this, guests, R.layout.listitem_signin);
	  guestsView.setAdapter(guestAdapter);
}
}
