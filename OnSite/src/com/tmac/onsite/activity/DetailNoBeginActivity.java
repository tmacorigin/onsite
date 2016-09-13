package com.tmac.onsite.activity;

import com.tmac.onsite.R;
import com.tmac.onsite.view.AudioPopupWindow;

import android.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;

/**
 * @author tmac
 */
public class DetailNoBeginActivity extends Activity {
	
	private Button btn_upload_img;
	private AudioPopupWindow popupWindow;
	private LinearLayout layout;
	private ImageView iv_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_nobegin);
		
		initDatas();
		initEvents();
			
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		btn_upload_img = (Button) findViewById(R.id.upload_pre_image);
		layout = (LinearLayout) findViewById(R.id.nobegin_layout_bottom);
		iv_back = (ImageView) findViewById(R.id.iv_back_detail);
		popupWindow = new AudioPopupWindow(this);
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		
		btn_upload_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(DetailNoBeginActivity.this, UploadImgActivity.class));	
			}
		});
		
		iv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
	}

	private void lightOn() {
		// TODO Auto-generated method stub
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 1.0f;
		getWindow().setAttributes(lp);
	}
	
	
	private void lightOff(){
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.5f;
		getWindow().setAttributes(lp);
	}
	
}
