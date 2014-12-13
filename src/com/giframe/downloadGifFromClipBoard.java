package com.giframe;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;





import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class downloadGifFromClipBoard extends AsyncTask<Void, Void, Void> {
	public String path;
	public String directory;
	public String name;
	public Context context;
	public ProgressDialog dialog;
	protected PowerManager.WakeLock mWakeLock;
	public File newFile;
	public directBrowser direct;
	public ArrayList<String> urls;
	public LinearLayout mLayout;
	public downloadGifFromClipBoard(Context c,String url,String directory,LinearLayout mLayout,ArrayList<String> urls){
		path=url;
		this.urls=urls;
		this.directory=directory;
		context=c;
		this.mLayout=mLayout;
		}
	
	
	
	@Override
	protected void onPreExecute() {
		
		dialog=new ProgressDialog(context);
		dialog.setMessage("Downloading...");
		dialog.show();
		
		direct=new directBrowser();
		directBrowser.context=context;
		direct.moveToFront();
		
		super.onPreExecute();
	}



	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
        BufferedOutputStream output = null;
        InputStream inputStream=null;
        URL url;
        name=getNameOfGif(path);
        newFile=new File(directory+"/"+name+".gif");
        if(!newFile.exists())
			try {
				newFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
       
		try {
			url = new URL(path);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			output=new BufferedOutputStream(new FileOutputStream(newFile)); 
			inputStream=connection.getInputStream();
			int byt;
			while((byt=inputStream.read())!=-1)
				output.write(byt);
			if(output!=null)
			output.close();
			if(inputStream!=null)
				inputStream=null;
			if(connection!=null)
				connection=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
		
		url=null;
		
		return null;
	}

	
	
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		dialog.dismiss();
		
		
		
		arrangeGifGallery ar=new arrangeGifGallery(context, mLayout);
		ar.addToGallery(newFile.getAbsolutePath());
		
		context=null;
		mLayout=null;
		newFile=null;
		System.gc();
		super.onPostExecute(result);
	}



	public String getNameOfGif(String path){
		String name;
		int start=path.lastIndexOf('/');
		int end=path.lastIndexOf(".gif");
		if(start!=-1&&end!=-1){
			name=path.substring(start, end);
			
		}else{
			name=""+new Random().nextInt()%100;
			
		}
		return name;
	}
	


}
