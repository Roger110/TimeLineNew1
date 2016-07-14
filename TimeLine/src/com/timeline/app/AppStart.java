package com.timeline.app;

import java.io.File;
import java.util.List;

import com.timeline.common.FileUtils;
import com.timeline.common.StringUtils;
import com.timeline.main.R;
import com.timeline.ui.Main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;


public class AppStart extends Activity {
		    
			private static final String TAG  = "AppStart";
			 final AppContext ac = (AppContext) getApplication();
			
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		       
		        final View view = View.inflate(this, R.layout.start, null);
				LinearLayout wellcome = (LinearLayout) view.findViewById(R.id.app_start_view);
				//check(wellcome);
				setContentView(view);
		        
				//����չʾ������
				AlphaAnimation aa = new AlphaAnimation(0.5f,1.0f);
				aa.setDuration(1000);
				view.startAnimation(aa);
				aa.setAnimationListener(new AnimationListener()
				{
					@Override
					public void onAnimationEnd(Animation arg0) {
						redirectTo();
					}
					@Override
					public void onAnimationRepeat(Animation animation) {}
					@Override
					public void onAnimationStart(Animation animation) {}
					
				});
				
				//���ݵͰ汾cookie��1.5�汾���£�����1.5.0,1.5.1��
				AppContext appContext = (AppContext)getApplication();
				String cookie = appContext.getProperty("cookie");
				if(StringUtils.isEmpty(cookie)) {
					String cookie_name = appContext.getProperty("cookie_name");
					String cookie_value = appContext.getProperty("cookie_value");
					if(!StringUtils.isEmpty(cookie_name) && !StringUtils.isEmpty(cookie_value)) {
						cookie = cookie_name + "=" + cookie_value;
						appContext.setProperty("cookie", cookie);
						appContext.removeProperty("cookie_domain","cookie_name","cookie_value","cookie_version","cookie_path");
					}
				}
		    }
		    
		    /**
		     * ����Ƿ���Ҫ��ͼƬ
		     * @param view
		     */
		    private void check(LinearLayout view) {
		    	String path = FileUtils.getAppCache(this, "welcomeback");
		    	List<File> files = FileUtils.listPathFiles(path);
		    	if (!files.isEmpty()) {
		    		File f = files.get(0);
		    		long time[] = getTime(f.getName());
		    		long today = StringUtils.getToday();
		    		if (today >= time[0] && today <= time[1]) {
		    			view.setBackgroundDrawable(Drawable.createFromPath(f.getAbsolutePath()));
		    		}
		    	}
		    }
		    
		    /**
		     * ������ʾ��ʱ��
		     * @param time
		     * @return
		     */
		    private long[] getTime(String time) {
		    	long res[] = new long[2];
		    	try {
		    		time = time.substring(0, time.indexOf("."));
		        	String t[] = time.split("-");
		        	res[0] = Long.parseLong(t[0]);
		        	if (t.length >= 2) {
		        		res[1] = Long.parseLong(t[1]);
		        	} else {
		        		res[1] = Long.parseLong(t[0]);
		        	}
				} catch (Exception e) {
				}
		    	return res;
		    }
		    
		    /**
		     * ��ת��...
		     */
		    private void redirectTo(){        
		        Intent intent = new Intent(this, Main.class);
		        startActivity(intent);
		        finish();
		    }
}
