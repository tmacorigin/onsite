package com.tmac.onsite.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.tmac.onsite.R;

public class SelectImgAdapter extends CommonAdapter<String> {
	
	private static final boolean DBG = true;
	private static final String TAG = "LC-SelectImgAdapter";
	private OnTakePhotoListener mListener;

	public SelectImgAdapter(Context context, List<String> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
		if(DBG) Log.d(TAG, "init SelectImgAdapter construct");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(DBG) Log.d(TAG, "enter getView");
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.select_gridview_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.gridview_img);
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.gridview_checkBox);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(position == 0){
			holder.iv.setImageResource(R.drawable.take_photo);
			holder.checkBox.setVisibility(View.GONE);
			holder.iv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(DBG)Log.i(TAG, "start take photo");
					mListener.onTakePhoto();
				}
			});
		}else {
			
		}
		
		return convertView;
	}

	private class ViewHolder{
		
		private ImageView iv;
		private CheckBox checkBox;
		
	}
	
	public void setOnTakePhotoListener(OnTakePhotoListener listener){
		this.mListener = listener;
	}
	
	public interface OnTakePhotoListener{
		void onTakePhoto();
	}
	
}
