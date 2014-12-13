package com.giframe;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class makeEffectApplicable extends AsyncTask<Void, Void, Void>{
	public String path;
	public Context context;
	public RelativeLayout mLayout;
	public Movie movie;
	ArrayList<Bitmap> bmpArrayList;
	public int position;
	public makeEffectApplicable(Context context,String path,RelativeLayout mLayout,int pos){
	this.path=path;
	this.context=context;
	this.mLayout=mLayout;
	position=pos;
}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		android.os.Process.setThreadPriority(-20);
		Toast.makeText(context, "Working..", Toast.LENGTH_SHORT).show();
		movie=pathToMovie(path);
	}
	@Override
	protected Void doInBackground(Void... params) {
		android.os.Process.setThreadPriority(-20);
		bmpArrayList=movieToBitmapArray(movie);
		movie=null;
		
		
		
		
		
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		android.os.Process.setThreadPriority(-20);
		gifView view=null;
		boolean truth=false;
		if(mLayout!=null){
		for(int i=0;i<mLayout.getChildCount();i++){
			if(mLayout.getChildAt(i) instanceof gifView){
				view=(gifView) mLayout.getChildAt(i);
				view.bitmapsHolder=bmpArrayList;
				view.bitmaps=bmpArrayList;
				view.path=path;
				view.position=position;
				view.start=(int) android.os.SystemClock.uptimeMillis();
				view.duration=gifView.getDuration(view.bitmaps);
				view.invalidate();
				truth=true;
			}
			
		}
		if(truth==false){
		view=new gifView(context, bmpArrayList,MainActivity.screenWidth,MainActivity.screenHeight*75/100);
		new menuRegisteration(view);
		view.path=path;
		view.position=position;
		mLayout.addView(view);
		}	
		view=null;
		context=null;
		mLayout=null;
		bmpArrayList=null;
		movie=null;
		MainActivity.modeChanger.bringToFront();
		MainActivity.styleButton.bringToFront();
		MainActivity.saveButton.bringToFront();
		MainActivity.addButton.bringToFront();
		}
	}
	
	 public Movie pathToMovie(String path){
		  InputStream is=null;
			ByteArrayOutputStream buffer;
			int nRead;
			byte[] data = new byte[16384];
			
				try {
					is=new BufferedInputStream(new FileInputStream(path),16*1024);
					 
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				buffer = new ByteArrayOutputStream();

				
				
				try {
					while ((nRead = is.read(data, 0, data.length)) != -1) {
					  buffer.write(data, 0, nRead);
					}
					buffer.flush();
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				

				buffer.toByteArray();
				return Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
		  
	  }
	
	  
	  
	  public ArrayList<Bitmap> movieToBitmapArray(Movie movie){
		  ArrayList<Bitmap> bitmaps=new ArrayList<Bitmap>();
		  
		  int relTime=0;
		  while(movie.duration()>relTime){
			  
			
			Log.d("burak", ""+relTime);
			
			movie.setTime(relTime);
			Bitmap movieBitmap = Bitmap.createBitmap(movie.width(), movie.height(),
                  Bitmap.Config.ARGB_8888);

			Canvas movieCanvas = new Canvas(movieBitmap);
			movie.draw(movieCanvas, 0, 0);

			Rect src = new Rect(0, 0, movie.width(), movie.height());
			Rect dst = new Rect(0, 0, movie.width(), movie.height());
			movieCanvas.drawBitmap(movieBitmap, src, dst, null);
			Log.d("burak", "width :"+movie.width()+" bitmap height:"+movieBitmap.getHeight()+" bitmap height:"+movieBitmap.getWidth());
			if(movie.width()>480)
			bitmaps.add(Bitmap.createScaledBitmap(movieBitmap,480,320 , false));
			else
				bitmaps.add(movieBitmap);
			
			relTime +=100 ;
		  }
				
		  
		  
		  
		return bitmaps;
	  }

}
