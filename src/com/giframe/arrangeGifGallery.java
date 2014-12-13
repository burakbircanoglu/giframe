package com.giframe;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Movie;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

@SuppressLint("ClickableViewAccessibility")
public class arrangeGifGallery  extends MainActivity{
	public static int lastPosition=2;
	public Context context;
	public LinearLayout mLayout;
	public static MYGIFView gif1;
	public static MYGIFView gif2;
	public static MYGIFView gif3;
	public static int lastClickedGif;
	public RelativeLayout rel;
	
	public arrangeGifGallery(Context context,LinearLayout mLayout){
		this.context=context;
		this.mLayout=mLayout;
		readDirectory();
		
		
	}
	public arrangeGifGallery(Context context,LinearLayout mLayout,RelativeLayout rel){
		this.context=context;
		this.mLayout=mLayout;
		this.rel=rel;
		readDirectory();
		
		
	}
	
	public void createGallery(){
			Log.d("burak", "create gallery");
		InputStream is=null;
		ByteArrayOutputStream buffer;
		int nRead;
		Movie mMovie=null;
		byte[] data = new byte[16384];
			LinearLayout.LayoutParams p=new LayoutParams(MainActivity.screenWidth/3,MainActivity.screenHeight*25/100);
			if(MainActivity.gifURL.size()>2){
			
				
				try {
					is=new BufferedInputStream(new FileInputStream(MainActivity.gifURL.get(0)),16*1024);
					 
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

				
				
				mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
				
			
				
			gif1=new MYGIFView(context,mMovie,MainActivity.gifURL.get(0),0);
			gif1.path=MainActivity.gifURL.get(0);
			gif1.setLayoutParams(p);
			gif1.setId(5);
			mLayout.addView(gif1, 0);
			
				
				
			try {
				is=new BufferedInputStream(new FileInputStream(MainActivity.gifURL.get(1)),16*1024);
				 
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

			
			
			mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
			
		
			
		gif2=new MYGIFView(context,mMovie,MainActivity.gifURL.get(1),1);
		gif2.path=MainActivity.gifURL.get(1);
		gif2.setLayoutParams(p);
		gif2.setId(10);
		mLayout.addView(gif2, 1);
		
		
		
		
		
		
		try {
			is=new BufferedInputStream(new FileInputStream(MainActivity.gifURL.get(2)),16*1024);
			 
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

		
		
			mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
			
		
			
			gif3=new MYGIFView(context,mMovie,MainActivity.gifURL.get(2),2);
			gif3.path=MainActivity.gifURL.get(2);
			gif3.setLayoutParams(p);
			gif3.setId(15);
			mLayout.addView(gif3, 2);
				
			
			setClickListener();
		
		
		
		
		
		
			}
				
		mMovie=null;
		buffer=null;
		p=null;
		data=null;
		is=null;
		gif1.setOnTouchListener(new OnTouchListener() {
			int x=0;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				readDirectory();
				MainActivity.gifURL=gifURL;
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					x=(int)event.getX();
					
					return false;
				case MotionEvent.ACTION_UP:
					if((int)event.getX()-x<0&&Math.abs((double)((int)event.getX()-x))>100)
						swapRightGallery();
					else if((int)event.getX()-x>0)
						swapLeftGallery();
					return false;
					default:
						break;
					
				}
				return false;
			}
		});
		gif3.setOnTouchListener(new OnTouchListener() {
			int x=0;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				readDirectory();
				MainActivity.gifURL=gifURL;
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					x=(int)event.getX();
					
					return false;
				case MotionEvent.ACTION_UP:
					if((int)event.getX()-x<0)
						swapRightGallery();
					else if((int)event.getX()-x>0&&Math.abs((double)((int)event.getX()-x))>100)
						swapLeftGallery();
					return false;
					default:
						break;
					
				}
				return false;
			}
		});
		gif2.setOnTouchListener(new OnTouchListener() {
			int x=0;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				readDirectory();
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					x=(int)event.getX();
					
					return false;
				case MotionEvent.ACTION_UP:
					if((int)event.getX()-x<0)
						swapRightGallery();
					else if((int)event.getX()-x>0)
						swapLeftGallery();
					return false;
					default:
						break;
					
				}
				return false;
			}
		});
		
	}


	
	public void swapRightGallery(){
		if(MainActivity.gifURL.size()>2&&gif3.position+1<MainActivity.gifURL.size()){
			Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
			gif1.mMovie=gif2.mMovie;
			gif1.position++;
			gif1.path=gif2.path;
			gif2.mMovie=gif3.mMovie;
			gif2.position++;
			gif2.path=gif3.path;
			Log.d("burak", ""+gif3.position);
			gif3.mMovie=getMovieFromPath(gif3.position+1);
			gif3.position++;
			gif3.path=MainActivity.gifURL.get(gif3.position);
			
			
			Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
		}
		
	}
	public void swapLeftGallery(){
		if(gif1.position>0){
			Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
			gif3.mMovie=gif2.mMovie;
			gif3.position--;
			gif3.path=gif2.path;
			gif2.mMovie=gif1.mMovie;
			gif2.position--;
			gif2.path=gif1.path;
			gif1.mMovie=getMovieFromPath(gif1.position-1);
			gif1.position--;
			gif1.path=MainActivity.gifURL.get(gif1.position);
			
			Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
			
			
		}
		
	}
	
	
	
	public void gallery(){
		InputStream is=null;
		ByteArrayOutputStream buffer;
		int nRead;
		Movie mMovie=null;
		byte[] data = new byte[16384];
			LinearLayout.LayoutParams p=new LayoutParams(MainActivity.screenWidth/3,MainActivity.screenHeight*25/100);
			
		if(MainActivity.gifURL.size()==0){
			try {
				is=context.getAssets().open("defaultgif.gif");
				 
			} catch (IOException e1) {
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

			
			
			mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
			
		
			
		gif1=new MYGIFView(context,mMovie,"",0);
		
		gif1.setLayoutParams(p);
		gif1.setId(5);
		mLayout.addView(gif1, 0);
		gif2=new MYGIFView(context,mMovie,"",1);
		
		gif2.setLayoutParams(p);
		gif2.setId(10);
		mLayout.addView(gif2, 1);
		gif3=new MYGIFView(context,mMovie,"",2);
		
		gif3.setLayoutParams(p);
		gif3.setId(15);
		mLayout.addView(gif3, 2);
			
		}else if(MainActivity.gifURL.size()==1){
			try {
				is=new BufferedInputStream(new FileInputStream(MainActivity.gifURL.get(0)),16*1024);
				 
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

			
			
			mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
			
		
			
		gif1=new MYGIFView(context,mMovie,MainActivity.gifURL.get(0),0);
		gif1.path=MainActivity.gifURL.get(0);
		gif1.setLayoutParams(p);
		gif1.setId(5);
		mLayout.addView(gif1, 0);
		try {
			is=context.getAssets().open("defaultgif.gif");
			 
		} catch (IOException e1) {
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

		
		
		mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
		gif2=new MYGIFView(context,mMovie,"",1);
		gif2.setId(10);
		gif2.setLayoutParams(p);
		mLayout.addView(gif2, 1);
		gif3=new MYGIFView(context,mMovie,"",2);
		gif3.setId(15);
		gif3.setLayoutParams(p);
		mLayout.addView(gif3, 2);
		}else if(MainActivity.gifURL.size()==2){
			try {
				is=new BufferedInputStream(new FileInputStream(MainActivity.gifURL.get(0)),16*1024);
				 
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

			
			
			mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
			
		
			
		gif1=new MYGIFView(context,mMovie,MainActivity.gifURL.get(0),0);
		gif1.path=MainActivity.gifURL.get(0);
		gif1.setLayoutParams(p);
		gif1.setId(5);
		mLayout.addView(gif1, 0);
		
		try {
			is=new BufferedInputStream(new FileInputStream(MainActivity.gifURL.get(1)),16*1024);
			 
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

		
		
		mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
		
	
		
	gif2=new MYGIFView(context,mMovie,MainActivity.gifURL.get(1),1);
	gif1.path=MainActivity.gifURL.get(1);
	gif2.setLayoutParams(p);
	gif2.setId(10);
	mLayout.addView(gif2, 1);
	try {
		is=context.getAssets().open("defaultgif.gif");
		 
	} catch (IOException e1) {
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

	
	
	mMovie=Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
	gif3=new MYGIFView(context,mMovie,"",2);
	gif3.setId(15);
	gif3.setLayoutParams(p);
	mLayout.addView(gif3, 2);
	
		}
		
	}
	
	
	
	public Movie getMovieFromPath(int position){
		InputStream is=null;
		ByteArrayOutputStream buffer;
		int nRead;
		byte[] data = new byte[16384];
		try {
			is=new BufferedInputStream(new FileInputStream(MainActivity.gifURL.get(position)),16*1024);
			 
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

		
		
		return Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
	}
	
	
	public void addToGallery(String path){
			
		Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
		
			gif1.position=MainActivity.gifURL.indexOf(path);
			
			gif1.path=path;
			MainActivity.gifURL.remove(gif1.position);
			MainActivity.gifURL.add(0, gif1.path);
			gif1.position=0;
			gif1.mMovie=getMovieFromPath(path);
			gif2.position=gif1.position+1;
			gif2.path=MainActivity.gifURL.get(gif2.position);
			gif2.mMovie=getMovieFromPath(gif2.path);
			gif3.position=gif2.position+1;
			gif3.path=MainActivity.gifURL.get(gif3.position);
			gif3.mMovie=getMovieFromPath(gif3.path);
			
			
			
			
			Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
	}
	
	
	
	
	public Movie getMovieFromPath(String path){
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

		
		
		return Movie.decodeByteArray(buffer.toByteArray(), 0, buffer.toByteArray().length);
	}
	
	
	
	
	
	
	
	
	public void removeGifFromGallery(String pos){
		
		
	}
	
	
	public void setClickListener(){
		gif1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(MODE==GIF_PLAYER_MODE){
					lastClickedGif=1;
					makeEffectApplicable applicable=new makeEffectApplicable(context, arrangeGifGallery.gif1.path,rel,arrangeGifGallery.gif1.position);
					applicable.execute();
					applicable=null;
					Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
					
				}
				
			}
		});
		
		gif2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(MODE==GIF_PLAYER_MODE){
					lastClickedGif=2;
					makeEffectApplicable applicable=new makeEffectApplicable(context, arrangeGifGallery.gif2.path,rel,arrangeGifGallery.gif2.position);
					applicable.execute();
					applicable=null;
					Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
					
				}
				
			}
		});
	
		gif3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(MODE==GIF_PLAYER_MODE){
					lastClickedGif=3;
					makeEffectApplicable applicable=new makeEffectApplicable(context, arrangeGifGallery.gif3.path,rel,arrangeGifGallery.gif3.position);
					applicable.execute();
					applicable=null;
					Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
					
				}
				
			}
		});
	
		
		
		
	}
	
}
