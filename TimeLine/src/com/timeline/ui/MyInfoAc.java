package com.timeline.ui;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.timeline.common.UIHelper;
import com.timeline.main.R;
import com.timeline.widget.CircleImageView;

public class MyInfoAc extends BaseActivity {

	private static final int RESULT = 1;
	private CircleImageView headView;
	private String path;
	private TextView nameTextView;
	private TextView sexualView;
	private TextView hospitalView;
	private TextView departmentView;
	private TextView dutyView;
	private TextView jobtitleView;

	private String type;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_info);
		InitView();
	}

	private void InitView() {
		nameTextView = (TextView) findViewById(R.id.myinfo_name_val);
		sexualView = (TextView) findViewById(R.id.myinfo_sexual_val);
		departmentView = (TextView) findViewById(R.id.myinfo_department_val);
		dutyView = (TextView) findViewById(R.id.myinfo_duty_val);
		jobtitleView = (TextView) findViewById(R.id.myinfo_jobtitle_val);
		hospitalView = (TextView) findViewById(R.id.myinfo_hospital_val);
		headView = (CircleImageView) findViewById(R.id.my_head_ima);
	}

	@Override
	protected void onNewIntent(Intent intent) {

		super.onNewIntent(intent);
		setIntent(intent);// must store the new intent unless getIntent() will
							// return the old one
		Bundle bundle = getIntent().getExtras();
		;
		type = bundle.getString("type");
		name = bundle.getString("name");
		if (type.equals("name")) {
			nameTextView.setText(name);
		} else if (type.equals("sexual")) {
			sexualView.setText(name);
		} else if (type.equals("department")) {
			departmentView.setText(name);
		} else if (type.equals("duty")) {
			dutyView.setText(name);
		} else if (type.equals("jobtitle")) {
			jobtitleView.setText(name);
		} else if (type.equals("hospital")) {
			hospitalView.setText(name);
		}

	}

	public void btn_HeadSet(View v) {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, RESULT);

	}

	public void btn_NameSet(View v) {
		UIHelper.showInfoEdit(this, "name", nameTextView.getText().toString());
	}

	public void btn_SexualSet(View v) {

	}

	public void btn_OfficeSet(View v) {
		UIHelper.showInfoEdit(this, "department", departmentView.getText()
				.toString());
	}

	public void btn_DutySet(View v) {

	}

	public void btn_JobtitleSet(View v) {

	}

	public void btn_HospitalSet(View v) {
		UIHelper.showInfoEdit(this, "hospital", hospitalView.getText()
				.toString());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT && resultCode == RESULT_OK && data != null) {

			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			path = picturePath;

			showPhoto(headView);
		}
	}

	private void showPhoto(ImageView photo) {
		String picturePath =path;// 图片的uri
		if (picturePath.equals(""))
			return;
		// 缩放图片, width, height 按相同比例缩放图片
		BitmapFactory.Options options = new BitmapFactory.Options();
		// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);
		int scale = (int) (options.outWidth / (float) 300);
		if (scale <= 0)
			scale = 1;
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(picturePath, options);

		photo.setImageBitmap(bitmap);
		photo.setMaxHeight(350);
	}
}
