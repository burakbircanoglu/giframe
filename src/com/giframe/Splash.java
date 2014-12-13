package com.giframe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_splash);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				File newFile=new File(Environment.getExternalStorageDirectory()+"/Pictures/Gif Gallery/");
				if(!newFile.exists())
					newFile.mkdirs();
				copyFromAsset("normal");
				copyFromAsset("inverted");
				copyFromAsset("grey");
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Intent i=new Intent(Splash.this,MainActivity.class);
						startActivity(i);
						finish();
					}
				});
			}
		}).start();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	
	
	
	
	
	
	
	private void copyFromAsset(String name){
		String path=Environment.getExternalStorageDirectory()+"/Pictures/Gif Gallery/"+name+".gif";
		File newFile=new File(path);
		
		try {
			if(!newFile.exists())
				newFile.createNewFile();
			OutputStream os=new BufferedOutputStream(new FileOutputStream(newFile));
			InputStream is=new BufferedInputStream(getAssets().open(name+".gif"));
			int i=-1;
			while((i=is.read())!=-1){
				os.write(i);
			}
			
			if(os!=null){
				os.flush();
				os.close();
				os=null;
			}
			if(is!=null){
				is.close();
				is=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		newFile=null;
	}
}
