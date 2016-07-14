package com.timeline.controls;

import java.text.AttributedCharacterIterator.Attribute;

import com.timeline.main.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ChangeColorIconWithText extends View {

	private int mColor = 0xFF45C01A;
	private Bitmap mIconBitmap;
	private String mText="日历";
	private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
	
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;
	
	private float mAlpha;
	
	private Rect mIconRect;
	private Rect mTextBound;
	
	private Paint mTextPaint;
	
	public ChangeColorIconWithText(Context context) {
		this(context,null);
	}

	/**
	 * 初始化
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public ChangeColorIconWithText(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText);
		
		int n = a.getIndexCount();
		for(int i=0; i< n; i++)
		{
			int attr = a.getIndex(i);
			switch(attr)
			{
				case R.styleable.ChangeColorIconWithText_icon:
					BitmapDrawable drawable = (BitmapDrawable)a.getDrawable(attr);
					mIconBitmap = drawable.getBitmap();
					break;
				case R.styleable.ChangeColorIconWithText_color:
					mColor = a.getColor(attr, 0xFF45C01A);
					break;
				case R.styleable.ChangeColorIconWithText_text:
					mText = a.getString(attr);
					break;
				case R.styleable.ChangeColorIconWithText_text_size:
					//mTextSize = (int)a.getDimension(TypedValue.COMPLEX_UNIT_SP, 12);
					mTextSize = 30;
					break;
			}
		}
		
		a.recycle();
		
		mTextBound = new Rect();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0xff555555);
		
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int iconWidth = Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(), getMeasuredHeight()-getPaddingTop()-getPaddingBottom());
		int left= getMeasuredWidth()/2 - iconWidth/2;
		int top = (getMeasuredHeight() - mTextBound.height())/2 - iconWidth/2;
		
		mIconRect = new Rect(left+10,top+10,left+iconWidth-10,top+iconWidth-10);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
		
		int alpha = (int) Math.ceil(255*mAlpha);
		setupTargetBitmap(alpha);
		
		drawSourceText(canvas, 255-alpha);
		drawTargetText(canvas, alpha);
		
		canvas.drawBitmap(mBitmap, 0, 0,null);
	}

	private void drawTargetText(Canvas canvas, int alpha) {
		// TODO Auto-generated method stub
		String familyName = "normal"; 
		Typeface font = Typeface.create("", Typeface.BOLD);
		mTextPaint.setTypeface(font); 
		mTextPaint.setColor(mColor);
		mTextPaint.setAlpha(alpha);
		int x = mIconRect.left + mIconRect.width()/2 - mTextBound.width()/2;
		int y = mIconRect.bottom + mTextBound.height()-5;
		canvas.drawText(mText, x, y+10, mTextPaint);
	}

	private void drawSourceText(Canvas canvas, int alpha) {
		// TODO Auto-generated method stub
		String familyName = "normal"; 
		Typeface font = Typeface.create("", Typeface.BOLD);
		mTextPaint.setTypeface(font);
		mTextPaint.setColor(Color.GRAY);
		int x = mIconRect.left + mIconRect.width()/2 - mTextBound.width()/2;
		int y = mIconRect.bottom + mTextBound.height()-5;
		canvas.drawText(mText, x, y+10, mTextPaint);
	}

	private void setupTargetBitmap(int alpha) {
		// TODO Auto-generated method stub
		mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mPaint = new Paint();
		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setAlpha(alpha);
		mCanvas.drawRect(mIconRect, mPaint);
		
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		mPaint.setAlpha(255);
		
		mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
	}

	public ChangeColorIconWithText(Context context, AttributeSet attrs) {
		this(context,attrs,0);
	}
	
	public void SetIconAlpha(float alpha){
		this.mAlpha = alpha;
		invalidateView();
	}

	private void invalidateView() {
		// TODO Auto-generated method stub
		if(Looper.getMainLooper() == Looper.myLooper()){
			invalidate();
		}
		else{
			postInvalidate();
		}
	}
	

}
