package com.tmac.onsite.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.tmac.onsite.R;
import com.tmac.onsite.adapter.SelectImgAdapter;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;

public class SelectImgActivity extends Activity {
	
	private static final boolean DBG = true;
	private static final String TAG = "LC-SelectImgActivity";
	private static final int REQUEST_CODE = 1;
	private String fileName;

	private GridView mGridView;
	private ImageView camView;
	private SelectImgAdapter mAdapter;
	List<String> mList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_img);
		
		mGridView = (GridView) findViewById(R.id.select_gridview);
		camView = (ImageView) findViewById(R.id.camera_iv);
		mList = new ArrayList<String>();
		mList.add("s");
		mAdapter = new SelectImgAdapter(this, mList);
		mAdapter.setOnTakePhotoListener(onTakePhotoListener);
		mGridView.setAdapter(mAdapter);
		
		initEvents();
		
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		
	}
	
	private SelectImgAdapter.OnTakePhotoListener onTakePhotoListener = new SelectImgAdapter.OnTakePhotoListener() {
		
		@Override
		public void onTakePhoto() {
			// TODO Auto-generated method stub
			if(DBG) Log.d(TAG, "onTakePhoto");
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			fileName = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
			File file = createFileIfNeed(fileName);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
			startActivityForResult(intent, REQUEST_CODE);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
			if(DBG) Log.d(TAG, "camera return data");
			
			if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				return;
			// 显示原图到ImageView
			camView.setImageBitmap(readPic());
			
		}
		
	};
	
	/**
	 * 从保存原图的地址中读取原图
	 * @return
	 */
	private Bitmap readPic(){
		String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/hanwei/" + fileName;
		Bitmap bitmap = BitmapFactory.decodeFile(filePath);
		return bitmap;
	}
	
	/**
	 * 创建拍照图片存储的路径
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private File createFileIfNeed(String fileName){
		
		String dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/hanwei";
		File fileDir = new File(dirPath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		File file = new File(fileDir, fileName);
		
		try {
			if(!file.exists()){
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
}
