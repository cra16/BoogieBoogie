package com.example.boogieboogie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageGridAdapterOnCalendar extends BaseAdapter {
	Context context = null;
	int[] imageIDs = null;
	
	public ImageGridAdapterOnCalendar(Context context, int[] imageIDs) {
		this.context = context;
		this.imageIDs = imageIDs;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return (null != imageIDs) ? imageIDs.length : 0;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (null != imageIDs) ? imageIDs[position] : 0;
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// ImageView imageView = null;
		ImageView imageView = null;
		
		if (null != convertView)
			imageView = (ImageView) convertView;
		else {
			Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
					imageIDs[position]);
			bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);
			
			imageView = new ImageView(context);
			imageView.setAdjustViewBounds(true);
			imageView.setImageBitmap(bmp);
			
		}
		return imageView;
	}
}
