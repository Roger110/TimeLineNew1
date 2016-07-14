package com.timeline.calendar;

import java.util.List;

import com.timeline.bean.MomentBean;
import com.timeline.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;


public class MomentAdapter extends BaseAdapter {
	List<MomentBean> moments;
	Context context;

	public MomentAdapter(List<MomentBean> moments, Context context) {
		super();
		this.moments = moments;
		this.context = context;
	}

	public void setMoments(List<MomentBean> moments) {
		this.moments = moments;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return moments.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return moments.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHodler mHodler;
		if (null == convertView) {
			mHodler = new ViewHodler();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.moment_item, null);
			mHodler.mMoment = (TextView) convertView.findViewById(R.id.moment);
			mHodler.mMomentPoint = convertView.findViewById(R.id.moment_point);
			mHodler.mPointContainer = (FrameLayout) convertView
					.findViewById(R.id.moment_pointcontainer);
			convertView.setTag(mHodler);

		} else {
			mHodler = (ViewHodler) convertView.getTag();
		}
		mHodler.mMoment.setText(moments.get(position).hour + "");
		mHodler.mMomentPoint.setVisibility(View.GONE);
		if (moments.get(position).minute != -1) {
			mHodler.mMomentPoint.setVisibility(View.VISIBLE);
			LayoutParams params = (LayoutParams) mHodler.mMomentPoint
					.getLayoutParams();

			params.topMargin = dip2px(context, moments.get(position).minute);
			// moments.get(position).minute;
			System.out.println("margn........." + moments.get(position).minute
					+ " ," + dip2px(context, moments.get(position).minute)
					+ "," + mHodler.mPointContainer.getMeasuredHeight());
		}
		mHodler.mPointContainer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		return convertView;
	}

	class ViewHodler {
		TextView mMoment;
		View mMomentPoint;
		FrameLayout mPointContainer;
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
}
