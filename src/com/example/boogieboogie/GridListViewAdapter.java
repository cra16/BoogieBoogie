package com.example.boogieboogie;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridListViewAdapter extends BaseAdapter {
	Context context = null;
	ArrayList<Bitmap> bitmap_image = null;
	
	public GridListViewAdapter(Context context, ArrayList<Bitmap> images) {
		this.context = context;
		this.bitmap_image = images;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return (null != bitmap_image) ? bitmap_image.size() : 0;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (null != bitmap_image) ? bitmap_image.get(position) : 0;
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
			// Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
			// imageIDs[position]);
			// Bitmap bmp;
			// bmp = Bitmap.createScaledBitmap(bitmap_image.get(position), 320,
			// 240, false);
			
			imageView = new ImageView(context);
			imageView.setAdjustViewBounds(true);
			imageView.setImageBitmap(bitmap_image.get(position));
			
		}
		return imageView;
	}
}
