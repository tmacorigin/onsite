package com.tmac.onsite.activity;

import com.tmac.onsite.R;
import com.tmac.onsite.view.AudioPopupWindow;

import android.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	
	private Button btn_finish;
	private Button btn_nofinish;
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
		btn_finish = (Button) findViewById(R.id.task_finish);
		btn_nofinish = (Button) findViewById(R.id.task_no_finish);
		layout = (LinearLayout) findViewById(R.id.nobegin_layout_bottom);
		iv_back = (ImageView) findViewById(R.id.iv_back_detail);
		popupWindow = new AudioPopupWindow(this);
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		btn_finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(DetailNoBeginActivity.this);
				builder.setMessage(R.string.finish_task_dialog_message)
				.setPositiveButton(R.string.ensure_finish, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						popupWindow = new AudioPopupWindow(DetailNoBeginActivity.this);
						popupWindow.setAnimationStyle(R.style.dir_popupwindow_anim);
						popupWindow.showAsDropDown(layout, 0, -layout.getMeasuredHeight());
						lightOff();
						popupWindow.setOnDismissListener(new OnDismissListener() {
							
							@Override
							public void onDismiss() {
								// TODO Auto-generated method stub
								lightOn();
							}
						});
					}
				})
				.setNegativeButton(R.string.waiting, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
				.create()
				.show();
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
