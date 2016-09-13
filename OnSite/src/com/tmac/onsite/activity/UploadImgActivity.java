package com.tmac.onsite.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tmac.onsite.R;

public class UploadImgActivity extends Activity{
	
	private Button btn_add_img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_img);
		btn_add_img = (Button) findViewById(R.id.add_img_btn);
		
		initEvents();
	}
	
	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		btn_add_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(UploadImgActivity.this, SelectImgActivity.class));
			}
		});
	}

}
