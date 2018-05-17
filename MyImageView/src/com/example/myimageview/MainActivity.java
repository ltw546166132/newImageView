package com.example.myimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MySmartImageView mysmartimage = (MySmartImageView) findViewById(R.id.mysmartimage);
		mysmartimage.setimageURL("http://192.168.1.3:8080/image/c.png",R.drawable.ic_launcher);
	}

}
