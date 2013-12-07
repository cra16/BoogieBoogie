package com.example.boogieboogie;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridImageAdapter extends ArrayAdapter<Bitmap>{

	Context context = null;
	ArrayList<Bitmap> images = new ArrayList<Bitmap>();
	ImageView vImage;
	
	public GridImageAdapter(Context context, int gridViewResourceId,
			ArrayList items) {
		// TODO Auto-generated constructor stub
		super(context, gridViewResourceId, items);
		this.context = context;
		this.images = images;
	}

	/*
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = null;
		Log.i("getview","1");
		
		if(convertView!= null )
			imageView = (ImageView)convertView;
		else {
			
			//아래 세 줄은 메모리 부족을 막기 위함임
//			 Bitmap bmp
//             = BitmapFactory.decodeResource(context.getResources(), images.get(position));
//	         bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);

	         imageView = new ImageView(context);
	         imageView.setAdjustViewBounds(true); 
	         imageView.setImageBitmap(images.get(position));
	         Log.i("getview method", imageView.toString());

	         ImageClickListener imageViewClickListener
	         = new ImageClickListener(context, images.get(position));
	         
	         imageView.setOnClickListener(imageViewClickListener);
		}
		
	        return imageView;
	    } 
		*/
	}


class ImageClickListener implements android.view.View.OnClickListener {
	Context context;
	
	int imageID;
	
	public ImageClickListener(Context context, Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.imageID = imageID;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}