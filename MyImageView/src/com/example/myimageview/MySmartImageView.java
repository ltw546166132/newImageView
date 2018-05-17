package com.example.myimageview;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MySmartImageView extends ImageView{
	protected static final int SET_IMAGE_URL = 0;
	protected static final int DEFAULT_IMAGE = 1;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SET_IMAGE_URL:
				Bitmap bitmap = (Bitmap) msg.obj;
				setImageBitmap(bitmap);
				break;
			case DEFAULT_IMAGE:
				int res_id = msg.arg1;
				setImageResource(res_id);
				break;
			default:
				break;
			}
		};
	};

	public MySmartImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MySmartImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MySmartImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void setimageURL(final String path,final int id) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					URL url = new URL(path);
					HttpURLConnection content = (HttpURLConnection) url.openConnection();
					content.setRequestMethod("GET");
					content.setConnectTimeout(5*1000);
					if(content.getResponseCode() == 200) {
						InputStream inputStream = content.getInputStream();
						Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
						Message msg = Message.obtain();
						msg.what = SET_IMAGE_URL;
						msg.obj = bitmap;
						handler.sendMessage(msg);
					}else{
						Message msg = Message.obtain();
						msg.what = DEFAULT_IMAGE;
						msg.arg1 = id;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Message msg = Message.obtain();
					msg.what = DEFAULT_IMAGE;
					msg.arg1 = id;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}
}
