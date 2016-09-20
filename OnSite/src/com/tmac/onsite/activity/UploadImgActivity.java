package com.tmac.onsite.activity;

import java.util.ArrayList;
import java.util.List;

import me.tmac.photopicker.PhotoPicker;
import me.tmac.photopicker.PhotoPreview;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.tmac.onsite.R;
import com.tmac.onsite.adapter.RecyclerViewAdapter;
import com.tmac.onsite.inter_face.RecyclerItemClickListener;
import com.tmac.onsite.inter_face.RecyclerItemClickListener.OnItemClickListener;

public class UploadImgActivity extends Activity{
	
	private Button btn_add_img;
	private ListView mListView;
	private ArrayList<String> selectedPhotos;
	private RecyclerView recyclerView;
	private RecyclerViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_img);
		btn_add_img = (Button) findViewById(R.id.add_img_btn);
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		selectedPhotos = new ArrayList<String>();
		adapter = new RecyclerViewAdapter(this, selectedPhotos);
		
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);
		
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
				//startActivity(new Intent(UploadImgActivity.this, SelectImgActivity.class));
				PhotoPicker.builder()
					.setPhotoCount(4)
					.setShowCamera(true)
					.setSelected(selectedPhotos)
					.start(UploadImgActivity.this);
			}
		});
		
		
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(UploadImgActivity.this, 
				new OnItemClickListener() {
					
					@Override
					public void onItemClick(View view, int position) {
						// TODO Auto-generated method stub
						PhotoPreview.builder()
								.setPhotos(selectedPhotos)
								.setCurrentItem(position)
								.start(UploadImgActivity.this);
					}
				}));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK
				&& (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

			List<String> photos = null;
			if (data != null) {
				photos = data
						.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
			}
			selectedPhotos.clear();

			if (photos != null) {

				selectedPhotos.addAll(photos);
			}
			adapter.notifyDataSetChanged();
		}
	}

}
