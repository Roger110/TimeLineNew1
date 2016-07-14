package com.timeline.controls;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MonthDateView extends View {
	private static final int NUM_COLUMNS = 7;
	private static final int NUM_ROWS = 6;
	private Paint mPaint;
	private int mDayColor = Color.parseColor("#000000");
	private int mSelectDayColor = Color.parseColor("#ffffff");
	private int mSelectBGColor = Color.parseColor("#ff0000");
	private int mCurrentColor = Color.parseColor("#ff0000");
	private int mCurrYear,mCurrMonth,mCurrDay;
	private int mSelYear,mSelMonth,mSelDay;
	private int mColumnSize,mRowSize;
	private DisplayMetrics mDisplayMetrics;
	private int mDaySize = 18;
	private TextView tv_month;
	private int weekRow;
	private int [][] daysString;
	private int mCircleRadius = 6;
	private DateClick dateClick;
	private int mCircleColor = Color.parseColor("#ff0000");
	private List<Integer> daysHasThingList;
	public MonthDateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDisplayMetrics = getResources().getDisplayMetrics();
		Calendar calendar = Calendar.getInstance();
		mPaint = new Paint();
		mCurrYear = calendar.get(Calendar.YEAR);
		mCurrMonth = calendar.get(Calendar.MONTH);
		mCurrDay = calendar.get(Calendar.DATE);
		setSelectYearMonth(mCurrYear,mCurrMonth,mCurrDay);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		initSize();
		daysString = new int[6][7];
		mPaint.setTextSize(mDaySize*mDisplayMetrics.scaledDensity);
		String dayString;
		int mMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth);
		int weekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth);
		Log.d("DateView", "DateView:" + mSelMonth+"月 第" + weekNumber);
		for(int day = 0;day < mMonthDays;day++){
			dayString = (day + 1) + "";
			int column = (day+weekNumber - 1) % 7;
			int row = (day+weekNumber - 1) / 7;
			if(column == 6 || column == 0)
			{
				mPaint.setColor(Color.GRAY);
				canvas.drawLine( 0, mRowSize * (row), mColumnSize* 7,mRowSize * (row) , mPaint);
			}
			
			daysString[row][column]=day + 1;
			int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString))/2);
			int startY = (int) (mRowSize * row + mRowSize/2 - (mPaint.ascent() + mPaint.descent())/2*0.7);
			if(dayString.equals(mSelDay+"")){
				//绘制选中的日期的背景色
				float startCircBgX = mColumnSize * column + mColumnSize/2;
				float startCircBgY = (float)(mRowSize * row + mRowSize/2*0.9);
				
				mPaint.setColor(mSelectBGColor);
				float radius = (float)(mPaint.measureText("22")/2*1.1);
				//float radius = (float) (Math.sqrt(mRowSize*mRowSize)/2*0.6);
				canvas.drawCircle(startCircBgX, startCircBgY, radius, mPaint);
				//canvas.drawRect(startRecX, startRecY, endRecX, endRecY, mPaint);
				
				weekRow = row + 1;
			}

			setmCircleRadius(mRowSize/20);
			drawCircle(row,column,day + 1,canvas);
			if(dayString.equals(mSelDay+"")){
				mPaint.setColor(mSelectDayColor);
			}else if(dayString.equals(mCurrDay+"") && mCurrDay != mSelDay && mCurrMonth == mSelMonth){
				mPaint.setColor(mCurrentColor);
			}else{
				mPaint.setColor(mDayColor);
			}
			canvas.drawText(dayString, startX, startY, mPaint);
			if(tv_month != null){
				tv_month.setText( getmSelMonthText(mSelMonth));
			}
			
//			if(tv_week != null){
//				tv_week.setText("第" + weekRow  +"周");
//			}
		}
	}
	
	private CharSequence getmSelMonthText(int mSelectedMonth) {
		switch (mSelectedMonth) {
		case 0:
			return "January" ;

		case 1:
			return "February" ;
		case 2:
			return "March" ;

		case 3:
			return "April" ;

		case 4:
			return "May" ;

		case 5:
			return "June" ;

		case 6:
			return "July" ;

		case 7:
			return "August" ;

		case 8:
			return "September" ;

		case 9:
			return "October" ;

		case 10:
			return "November" ;

		case 11:
			return "December" ;
		}
		return null;
	}

	private void drawCircle(int row,int column,int day,Canvas canvas){
		if(daysHasThingList != null && daysHasThingList.size() >0){
			if(!daysHasThingList.contains(day))return;
			mPaint.setColor(mCircleColor);
			float circleX = (float) (mColumnSize * column +	mColumnSize/2);
			float circley = (float) (mRowSize * row + mRowSize*0.88);
			canvas.drawCircle(circleX, circley, mCircleRadius, mPaint);
		}
	}
	@Override
	public boolean performClick() {
		return super.performClick();
	}

	private int downX = 0,downY = 0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int eventCode=  event.getAction();
		switch(eventCode){
		case MotionEvent.ACTION_DOWN:
			downX = (int) event.getX();
			downY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			int upX = (int) event.getX();
			int upY = (int) event.getY();
			if(Math.abs(upX-downX) < 10 && Math.abs(upY - downY) < 10){
				performClick();
				doClickAction((upX + downX)/2,(upY + downY)/2);
			}
			break;
		}
		return true;
	}

	/**
	 * 初始化行高和列宽
	 */
	private void initSize(){
		mColumnSize = getWidth() / NUM_COLUMNS;
		mRowSize = getHeight() / NUM_ROWS;
	}
	
	/**
	 * 获取当前月的天数
	 * @param year
	 * @param month
	 */
	private void setSelectYearMonth(int year,int month,int day){
		mSelYear = year;
		mSelMonth = month;
		mSelDay = day;
	}
	/**
	 * 执行点击事件
	 * @param x
	 * @param y
	 */
	private void doClickAction(int x,int y){
		int row = y / mRowSize;
		int column = x / mColumnSize;
		setSelectYearMonth(mSelYear,mSelMonth,daysString[row][column]);
		invalidate();

		if(dateClick != null){
			dateClick.onClickOnDate();
		}
	}

	/**
	 * 上一个月 点击事件
	 */
	public void onLeftClick(){
		int year = mSelYear;
		int month = mSelMonth;
		int day = mSelDay;
		if(month == 0){
			year = mSelYear-1;
			month = 11;
		}else if(DateUtils.getMonthDays(year, month) == day){
			month = month-1;
			day = DateUtils.getMonthDays(year, month);
		}else{
			month = month-1;
		}
		setSelectYearMonth(year,month,day);
		invalidate();
	}
	
	/**
	 * 下一个月 点击事件
	 */
	public void onRightClick(){
		int year = mSelYear;
		int month = mSelMonth;
		int day = mSelDay;
		if(month == 11){
			year = mSelYear+1;
			month = 0;
		}else if(DateUtils.getMonthDays(year, month) == day){
			month = month + 1;
			day = DateUtils.getMonthDays(year, month);
		}else{
			month = month + 1;
		}
		setSelectYearMonth(year,month,day);
		invalidate();
	}
	
	/**
	 * 获取当前选中的年份
	 * @return
	 */
	public int getmSelYear() {
		return mSelYear;
	}
	/**
	 * 获取当前选中的月份
	 * @return
	 */
	public int getmSelMonth() {
		return mSelMonth;
	}
	/**
	 * 获取当前选中的天
	 * @param mSelDay
	 */
	public int getmSelDay() {
		return this.mSelDay;
	}
	/**
	 * 设置日的字体颜色
	 * @param mDayColor
	 */
	public void setmDayColor(int mDayColor) {
		this.mDayColor = mDayColor;
	}
	
	/**
	 * 设置当前选中日的字体颜色
	 * @param mSelectDayColor
	 */
	public void setmSelectDayColor(int mSelectDayColor) {
		this.mSelectDayColor = mSelectDayColor;
	}

	/**
	 * 设置当前选中日的背景颜色
	 * @param mSelectBGColor
	 */
	public void setmSelectBGColor(int mSelectBGColor) {
		this.mSelectBGColor = mSelectBGColor;
	}
	/**
	 * 设置当前颜色
	 * @param mCurrentColor
	 */
	public void setmCurrentColor(int mCurrentColor) {
		this.mCurrentColor = mCurrentColor;
	}

	/**
	 *
	 * @param mDaySize
	 */
	public void setmDaySize(int mDaySize) {
		this.mDaySize = mDaySize;
	}
	/**
	 * 设置日期内容
	 * @param tv_month
	 */
	public void setTextView(TextView tv_month){
		this.tv_month = tv_month;
		invalidate();
	}

	/**
	 * 设置当日事项（即日下边的小点）
	 * @param daysHasThingList
	 */
	public void setDaysHasThingList(List<Integer> daysHasThingList) {
		this.daysHasThingList = daysHasThingList;
	}

	/***
	 * 璁设置事项圆点半径
	 * @param mCircleRadius
	 */
	public void setmCircleRadius(int mCircleRadius) {
		this.mCircleRadius = mCircleRadius;
	}
	
	/**
	 * 设置事项圆点颜色
	 * @param mCircleColor
	 */
	public void setmCircleColor(int mCircleColor) {
		this.mCircleColor = mCircleColor;
	}
	
	/**
	 * 日期点击事件
	 * @author
	 *
	 */
	public interface DateClick{
		public void onClickOnDate();
	}

	/**
	 *  设置日期点击事件
	 * @param dateClick
	 */
	public void setDateClick(DateClick dateClick) {
		this.dateClick = dateClick;
	}
	
	/**
	 * 跳转到当前日期
	 */
	public void setTodayToView(){
		setSelectYearMonth(mCurrYear,mCurrMonth,mCurrDay);
		invalidate();
	}
}
