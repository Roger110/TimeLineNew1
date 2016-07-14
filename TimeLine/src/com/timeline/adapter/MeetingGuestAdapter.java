package com.timeline.adapter;

import java.util.List;

import com.timeline.bean.guest;
import com.timeline.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MeetingGuestAdapter  extends BaseAdapter {
	private Context context;// ����������
	private List<guest> listItems;// ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private int itemViewResource;// �Զ�������ͼԴ

	static class ListItemView { // �Զ���ؼ����ϣ���djline_listitem����һ��
		public TextView name;
		public TextView job;
	}

	/**
	 * ʵ����Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public MeetingGuestAdapter(Context context, List<guest> data,
			int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.itemViewResource = resource;
		this.listItems = data;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	/**
	 * ListView Item����
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// �Զ�����ͼ
		ListItemView listItemView = null;

		if (convertView == null) {
			// ��ȡlist_item�����ļ�����ͼ
			convertView = listContainer.inflate(this.itemViewResource, null);

			listItemView = new ListItemView();
			// ��ȡ�ؼ�����

			listItemView.name = (TextView) convertView
					.findViewById(R.id.tabpeople_name);
			listItemView.job = (TextView) convertView
					.findViewById(R.id.tabpeople_job);
			// ���ÿؼ�����convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// �������ֺ�ͼƬ
		guest gu = listItems.get(position);

		listItemView.name.setText(gu.getName());
		listItemView.name.setTag(gu);// �������ز���(ʵ����)
		listItemView.job.setText(gu.getJob());


		return convertView;
	}
}
