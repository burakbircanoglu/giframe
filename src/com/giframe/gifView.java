package com.giframe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class gifView extends View{
	public ArrayList<Bitmap> bitmaps;
	public int duration=0;
	public ArrayList<Bitmap> bitmapsHolder;
	public int start=0;
	public int width;
	public int height;
	public String path;
	public int position;
	public  static Rect rect;
	public gifView(Context context,ArrayList<Bitmap> bit,int width,int height) {
		super(context);
		bitmaps=bit;
		bitmapsHolder=bit;
		
		duration=getDuration(bitmaps);
		Log.d("burak", "duration"+duration);
		start=(int) android.os.SystemClock.uptimeMillis();
		this.width=width;
		this.height=height;
		rect=new Rect(0,0,width,height);
		
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int relTime=0;
		if(duration!=0){
			relTime = (int)((android.os.SystemClock.uptimeMillis() - start)% duration);
		
			relTime=getRealTime(relTime);
		if(relTime==bitmaps.size())
			relTime=0;
		
		canvas.drawBitmap(bitmaps.get(relTime),rect,rect,null);
		
		this.invalidate();
		}
	}
	
	
	public static int getDuration(ArrayList<Bitmap> bit){
		int duration=0;
		if(bit.size()!=0){
			for (int i = 0; i < bit.size(); i++) {
				duration+=100;
			}
			return duration;
		}
		else{
			return 0;
		}
		
	}
	
	public int getRealTime(int time){
		int realTime=0;
		realTime=(time-time%100)/100;
		
		
		return realTime;
	}
}
