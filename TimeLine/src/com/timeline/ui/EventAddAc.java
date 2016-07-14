package com.timeline.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timeline.main.R;
import com.timeline.slidedatetimepicker.SlideDateTimeListener;
import com.timeline.slidedatetimepicker.SlideDateTimePicker;

public class EventAddAc extends BaseActivity{

	private SimpleDateFormat mFormatter = new SimpleDateFormat("MM月dd号 hh:mm aa");
	private TextView beginText;
	private TextView endText;
	
	private TextView repeatText;
	private RadioOnClick OnClick = new RadioOnClick(1);
	 private ListView areaListView;
	//重复选项
	private String[] repites = new String[]{"不重复","每天", 
			"每周", "每月", "每年" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        initView();
    }
    
    private void initView(){
    	beginText =(TextView)findViewById(R.id.event_begintime);
    	endText =(TextView)findViewById(R.id.event_endtime);
    	repeatText = (TextView)findViewById(R.id.event_repeat_modeval);
    }
    
    public void Begintime_Click(View v){
        new SlideDateTimePicker.Builder(getSupportFragmentManager())
        .setListener(listener)
        .setInitialDate(new Date())
        .setIs24HourTime(true)
        //.setTheme(SlideDateTimePicker.HOLO_DARK)
        //.setIndicatorColor(Color.parseColor("#990000"))
        .build()
        .show();
    }
    public void Endtime_Click(View v){
        new SlideDateTimePicker.Builder(getSupportFragmentManager())
        .setListener(enlistener)
        .setInitialDate(new Date())
        .setIs24HourTime(true)
        //.setTheme(SlideDateTimePicker.HOLO_DARK)
        //.setIndicatorColor(Color.parseColor("#990000"))
        .build()
        .show();
    }
    
    public void btn_Alert(View v){
    }
    
    public void Repeat_Click(View v){
    	 AlertDialog ad =new AlertDialog.Builder(EventAddAc.this).setTitle("选择重复项")
    			    .setSingleChoiceItems(repites,OnClick.getIndex(),OnClick).create();
    			    areaListView=ad.getListView();
    			    ad.show();
    }
    
    class RadioOnClick implements DialogInterface.OnClickListener{
    	   private int index;
    	 
    	   public RadioOnClick(int index){
    	    this.index = index;
    	   }
    	   public void setIndex(int index){
    	    this.index=index;
    	    repeatText.setText(repites[index]);
    	   }
    	   public int getIndex(){
    	    return index;
    	   }
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
		     setIndex(which);
		     Toast.makeText(EventAddAc.this, "您已经选择了 " +  ":" + repites[index], Toast.LENGTH_SHORT).show();
		     dialog.dismiss();
		}
    }
    
    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
        	beginText.setText(mFormatter.format(date));
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {

        }
    };
    private SlideDateTimeListener enlistener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
        	endText.setText(mFormatter.format(date));
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {

        }
    };
}
