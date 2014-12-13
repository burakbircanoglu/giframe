package com.giframe;


import java.io.File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Random;



import com.giframe.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;


import com.todddavies.components.progressbar.ProgressWheel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;

import android.graphics.Movie;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;	 
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import android.widget.Toast;
    
import android.widget.RelativeLayout;



@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends Activity{
	public CameraPreview mPreview;
    private RelativeLayout mLayout;
    public final static int GIF_PLAYER_MODE=1;
    public final static int GIF_MAKER_MODE=2;
    public static ProgressWheel wheel;
    public static int MODE=2;
    public static int camera=1000;
    public ArrayList<Bitmap> bmp=new ArrayList<Bitmap>();
    public  DisplayMetrics displayMetrics;
    public static ArrayList<String> gifURL;
    public boolean isPhotoTaken=false;
    public Movie mMovie;
    public decodeImage decode;
    public RelativeLayout.LayoutParams params;
    public final String gifDirectory=Environment.getExternalStorageDirectory()+"/Pictures/Gif Gallery";
    public static Intent browserIntent;
    public makeEffectApplicable applicable;
    public  long mMovieStart=0;
    public static gifView mGif;
    public static ImageButton cameraSwitch;
    public int cameraId=1;
    public Intent playerIntent=null;
    public static Button modeChanger;
    public LinearLayout gifLinearLayout;
    public String externalGifPath; 
    public static ClipboardManager clipboardManager;
    public static RelativeLayout.LayoutParams paramOfButton;
    public static ClipboardManager.OnPrimaryClipChangedListener clipListener;
    public  static ImageButton addButton;
    public LinearLayout lay;
    public static int count=0;
    public ImageButton help;
	private boolean doubleBackToExitPressedOnce;
    public static SharedPreferences shared;
    public static int screenWidth;
    public static int screenHeight;
    public static ImageButton styleButton;
    public ProgressDialog dialog;
    public static ImageButton saveButton;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);
		//arrange sharedpref
				shared=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				
					count=shared.getInt("counter", 0)+1;
					Editor editor=shared.edit();
					editor.putInt("counter", count);
					editor.commit();
//					if(shared.getInt("counter", 0)==1){
//						copyFromAsset("normal");
//					}
					shared=null;
					
		
		playerIntent=getIntent();
		directBrowser.context=getApplicationContext();
		//Set style button
		styleButton=(ImageButton) findViewById(R.id.styleButton);
		
		styleButton.setId(5000);
		registerForContextMenu(styleButton);
		styleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				openContextMenu(styleButton);
			}
		});
		
		//set save button
		saveButton=(ImageButton) findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
			
			
			
			
			
			
			
			
			
			
			
			
			  dialog = ProgressDialog.show(MainActivity.this, "", "In Progress...");
			 
			 new Thread(new Runnable() {
				
				@Override
				public void run() {
					OutputStream os=null;
					android.os.Process.setThreadPriority(-20);
					externalGifPath=gifDirectory+"/"+new Random().nextInt()%1000+".gif";
					
					try {
						os=new FileOutputStream(new File(externalGifPath));
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					AnimatedGifEncoder encoder=new AnimatedGifEncoder();
					encoder.start(os);
					
					encoder.setDelay(100);
					int i=0;
					while(i<((gifView) getEditableGif()).bitmaps.size()&&((gifView) getEditableGif()).bitmaps.get(i)!=null){
						//do conversion and move
						
						encoder.addFrame(((gifView) getEditableGif()).bitmaps.get(i));
						i++;
						
					}
					dialog.dismiss();
					dialog=null;
					encoder.finish();
					encoder=null;
					if(os!=null)
					try {	
						os.close();
						os=null;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							gifURL.add(0, externalGifPath);
							arrangeGifGallery ap=new arrangeGifGallery(getApplicationContext(), lay);
							ap.addToGallery(externalGifPath);
							ap=null;
							externalGifPath=null;
							
						}
					});
					}
					}
				
				
			).start();
			
			
			
			
			
			
			
			
			
			
			}
		});
		
		//init help button
		help=(ImageButton) findViewById(R.id.help);
		RelativeLayout.LayoutParams paramsi=(RelativeLayout.LayoutParams)help.getLayoutParams();
		
		
		
		//set add button's property
		addButton=(ImageButton) findViewById(R.id.add);
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId()==addButton.getId()){
					browserIntent=new Intent(Intent.ACTION_VIEW);
					browserIntent.setData(Uri.parse("https://www.google.com.tr/imghp?hl=tr&tab=wi&authuser=0&ei=5_FMVOGIN4f7ygOzuYKAAQ&ved=0CAQQqi4oAg"));
					startActivity(browserIntent);
					
					
					
				}
			}
		});
		//mode switcher
		modeChanger=(Button) findViewById(R.id.modeChanger);
		
		modeChanger.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				        
				if(v.getId()==modeChanger.getId()){
					if(MODE==GIF_MAKER_MODE){
						
						MODE=GIF_PLAYER_MODE;
						
						
							
							            
							            
									
						onPause();
					}
					else if(MODE==GIF_PLAYER_MODE){
						MODE=GIF_MAKER_MODE;
						
					}
						onResume();
						
					
				}
				
				
			}
		});
		
		//camera side-switching
		cameraSwitch=(ImageButton) findViewById(R.id.switcher);
		cameraSwitch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v.getId()==cameraSwitch.getId()){
					if(cameraId==0)
					cameraId=1;
					else
						cameraId=0;
					onPause();
					onResume();
					
				}
				
			}
		});
		
		
		//get resolution of screen
		displayMetrics= new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenHeight=displayMetrics.heightPixels;
		screenWidth=displayMetrics.widthPixels;
		displayMetrics=null;
		//create snapButton-loadCycle
		wheel=new ProgressWheel(getApplicationContext(),null);
		
		paramOfButton=new RelativeLayout.LayoutParams((int)(screenWidth*300/1080),(int)(screenWidth*300/1080));
		
		wheel.setRimColor(Color.parseColor("#B8B8B8"));
		wheel.setBarColor(Color.parseColor("#3098B8"));
		wheel.setRimWidth(50);
		wheel.setBarWidth(50);
		
		wheel.setCircleRadius(100);
		
		
		
		
		wheel.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				
				
						
				int x,y,dx=0,dy=0;
				
				if(v.getId()==wheel.getId())
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					isPhotoTaken=true;

					dx=(int) (event.getRawX()-paramOfButton.leftMargin);
					dy=(int)(event.getRawY()-paramOfButton.topMargin-screenHeight*25/100);
					Thread snapThread = new Thread(){
				   		 public void run() {
				   		  while(isPhotoTaken&& mPreview!=null) {
				   			  if(isPhotoTaken==false)
				   				  break;
				   			  mPreview.takeSnapPhoto();
				   		   try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				   		  }
				   		  
				   		  
				   		 }
				   		 
				   		};
				   	
				   		
				   		
				   		Thread buttonThread=new Thread(new Runnable() {
								int i=0;
								@Override
								public void run() {
									// TODO Auto-generated method stub
									while (isPhotoTaken) {
										
										
									
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									i+=(int)360/7;
									if(i<360){
									if(i>350)	
									i=360;
									wheel.setProgress(i);
									}
									if(i==360){
										i=0;
										isPhotoTaken=false;
										break;
									}
									
								}
								if(!isPhotoTaken){
									wheel.setProgress(0);
								}	
								}
				   		});
			    	
						
			    		
						buttonThread.start();
			    		snapThread.start();
					return true;
				case MotionEvent.ACTION_MOVE:	
					 x = (int) event.getRawX();
			         y = (int) event.getRawY();
			         
			         paramOfButton.leftMargin=(int)(x-dx-wheel.getWidth()/2);
			         
			         if((int)(y-dy-screenHeight*25/100-wheel.getHeight()/2)>0)
			         paramOfButton.topMargin=(int)(y-dy-screenHeight*25/100-wheel.getHeight()/2);
			         
			         wheel.requestLayout();
			         wheel.bringToFront();
			         
					return true;
				case MotionEvent.ACTION_UP:
			    	isPhotoTaken=false;
					decode=new decodeImage(mPreview,mPreview.mImage,MainActivity.this);
				    mPreview.mImage=new ArrayList<byte[]>();
			      
			      	return true;
				default:
					break;
				}	
			
				return false;
			}
		});
		
		//create directory for gifs
		File file=new File(gifDirectory);
		
		
		readDirectory();
		
		
				
		
		//arrange gis display units
		
		lay=(LinearLayout) findViewById(R.id.upperLinearLayout);
		android.view.ViewGroup.LayoutParams p=lay.getLayoutParams();
		
		p.height=screenHeight*25/100;
		p.width=screenWidth;
		lay.requestLayout();
		
		//init layouts
		
		
		mLayout=(RelativeLayout) findViewById(R.id.cameraHolder);
		//get gifs from gif's directory
		arrangeGifGallery gifGallery=new arrangeGifGallery(getApplicationContext(), lay,mLayout);
		gifGallery.createGallery();
		gifGallery=null;

		
			
		
		
		//add button to camera holder
		
		mLayout.addView(wheel,paramOfButton);
		paramOfButton.topMargin=(int)(screenHeight*75/300);
		paramOfButton.leftMargin=(screenWidth/2)-150-paramOfButton.leftMargin;
		wheel.requestLayout();
		//set mode of app either player or maker
		modeController(playerIntent);
		
		//set clipboard listener
		clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		
		clipListener=new ClipboardManager.OnPrimaryClipChangedListener() {
			
			@Override
			public void onPrimaryClipChanged() {
				String hold=clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
				if (hold.contains("http")||hold.contains("https")||hold.contains(".co")||hold.contains(".com")||hold.contains(".org")||hold.contains(".net")||hold.contains(".edu")||hold.contains("www.")) {
					downloadGifFromClipBoard downloadGifFromClipBoard=new downloadGifFromClipBoard(MainActivity.this,clipboardManager.getPrimaryClip().getItemAt(0).getText().toString(),gifDirectory,lay,gifURL);
					downloadGifFromClipBoard.execute();
					downloadGifFromClipBoard=null;
					hold=null;
				}
				
			}
		};
		clipboardManager.addPrimaryClipChangedListener(clipListener);
		
		//get default
		//getDefaultGif();
		//get bring the help button
		
		help.bringToFront();
		paramsi.width=(screenWidth/3)/4;
		paramsi.height=(screenHeight*25/100)/4;
		help.setLayoutParams(paramsi);
		paramsi=null;
		help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i=new Intent(MainActivity.this,HelpActivity.class);	
				startActivity(i);
				
			}
		});
		//arrange buttton
		LayoutParams param=(LayoutParams) styleButton.getLayoutParams();
		int wid=17*screenWidth/100;
		
		param.width=wid;
		param.height=wid;
		styleButton.setLayoutParams(param);
		styleButton.requestLayout();
		param=(LayoutParams) modeChanger.getLayoutParams();
		param.width=wid;
		param.height=wid;
		modeChanger.setLayoutParams(param);
		modeChanger.requestLayout();
		
		param=(LayoutParams) cameraSwitch.getLayoutParams();
		param.width=wid;
		param.height=wid;
		cameraSwitch.setLayoutParams(param);
		cameraSwitch.requestLayout();
		
		param=(LayoutParams) saveButton.getLayoutParams();
		param.width=wid;
		param.height=wid;
		saveButton.setLayoutParams(param);
		saveButton.requestLayout();
		param=(LayoutParams) addButton.getLayoutParams();
		param.width=15*screenWidth/100;
		param.height=15*screenWidth/100;
		addButton.setLayoutParams(param);
		addButton.requestLayout();
		
		
		
		//add ad
		
		((MainApplication) getApplication()).getTracker(MainApplication.TrackerName.APP_TRACKER);
		
		
		
		
		
		
		//set gallery
		 
		
	}

	  @Override
	    protected void onResume() {
	        super.onResume();
	        
	        
	      
				
				if(MODE==GIF_MAKER_MODE){
					
	        mPreview = new CameraPreview(this, cameraId, CameraPreview.LayoutMode.FitToParent,screenWidth,screenHeight);
	        mPreview.setId(camera);
	        mLayout.addView(mPreview);
	        wheel.setVisibility(ImageButton.VISIBLE);
	        if(getEditableGif()!=null){
	        	mLayout.removeView(getEditableGif());
	        }
	        applicable=null;
	        saveButton.setVisibility(ImageButton.INVISIBLE);
	        wheel.bringToFront();
	        cameraSwitch.bringToFront();
	        styleButton.setVisibility(ImageButton.INVISIBLE);
	        mLayout.invalidate();
				}
				else if(MODE==GIF_PLAYER_MODE){
					
					setIntentStyle(playerIntent);
					styleButton.bringToFront();
					 wheel.setVisibility(ImageButton.INVISIBLE);
					styleButton.setVisibility(ImageButton.VISIBLE);
					saveButton.setVisibility(ImageButton.VISIBLE);

				}
	       
	        modeChanger.bringToFront();
	        
	        addButton.bringToFront();
	        mLayout.invalidate();
	       
			}
	    

	    

		@Override
	    protected void onPause() {
	        super.onPause();
	        if(mPreview!=null){
	        mPreview.stop();
	        mLayout.removeView(mPreview); // This is necessary.
	        mPreview = null;
	        }
	       
	    }
		
		
		
		public void readDirectory(){
			gifURL=new ArrayList<String>();
			if(!new File(gifDirectory).exists()){	
				try {
					new File(gifDirectory).createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}		
			File[] fileList=null;
			fileList=new File(gifDirectory+"/").listFiles();
			
			if(fileList.length>0){
			for (int i=fileList.length-1;i>=0;i--) {
				gifURL.add(fileList[i].getAbsolutePath());
			}	
			}
			fileList=null;
			
		}
	
		
		
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,
		    ContextMenuInfo menuInfo) {
			if(v instanceof gifView){
			 Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
			
			
			
			MainActivity.mGif=(gifView)v;
			
			menu.setHeaderTitle("File Name: \n"+getNameOfGif(MainActivity.mGif.path));
			
			
			Drawable d = new BitmapDrawable(((gifView)v).getResources(),((gifView)v).bitmaps.get(0));
			
			menu.setHeaderIcon(d);
			
			
			menu.add(Menu.NONE,0,0,"Rename");
			menu.add(Menu.NONE,1,0,"Share");
			menu.add(Menu.NONE,2,0,"Delete");
			
			
			}else if(v.getId()==styleButton.getId()){
				mGif=null;
				menu.setHeaderTitle("Styles");
				menu.add(Menu.NONE,0,0,"Grey");
				menu.add(Menu.NONE,1,0,"Inverted");
				menu.add(Menu.NONE,2,0,"Blue");
				menu.add(Menu.NONE,3,0,"Green");
				menu.add(Menu.NONE,4,0,"Red");
				
			}
		}
		
		@Override
		public boolean onContextItemSelected(MenuItem item) {
			int position=item.getItemId();	
			if(mGif==null){
				switch (position) {
				case 0:
						
						dialog=ProgressDialog.show(this,"", "In Progress..");
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								ArrayList<Bitmap> bit=new ArrayList<Bitmap>();
								gifView view=null;
								for(int i=0;i<mLayout.getChildCount();i++){
									if(mLayout.getChildAt(i) instanceof gifView){
										view=(gifView) mLayout.getChildAt(i);
										
										
										Log.e("burak", "it should be done ");
									}
								}
								
								if(view!=null)
								for(Bitmap bmp:view.bitmapsHolder){
									bit.add(styleManagement.greyScaler(bmp));
									Log.e("burak", "it should be done ");
								}
								view.bitmaps=bit;
								bit=null;
								view.invalidate();
								dialog.dismiss();
								dialog=null;
							}
						}).start();
					
					break;
					
					
					
				case 1:
						dialog=ProgressDialog.show(this,"", "In Progress..");
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								ArrayList<Bitmap> bit=new ArrayList<Bitmap>();
								gifView view=null;
								for(int i=0;i<mLayout.getChildCount();i++){
									if(mLayout.getChildAt(i) instanceof gifView){
										view=(gifView) mLayout.getChildAt(i);
									}
								}
								
								if(view!=null)
								for(Bitmap bmp:view.bitmapsHolder){
									bit.add(styleManagement.doInvert(bmp));
									
								}
								view.bitmaps=bit;
								bit=null;
								view.invalidate();
								dialog.dismiss();
								dialog=null;
							}
						}).start();
					
					break;
					
				case 2:
						dialog=ProgressDialog.show(this,"", "In Progress..");
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								ArrayList<Bitmap> bit=new ArrayList<Bitmap>();
								gifView view=null;
								for(int i=0;i<mLayout.getChildCount();i++){
									if(mLayout.getChildAt(i) instanceof gifView){
										view=(gifView) mLayout.getChildAt(i);
										
										
										Log.e("burak", "it should be done ");
									}
								}
								
								if(view!=null)
								for(Bitmap bmp:view.bitmapsHolder){
									bit.add(styleManagement.doColorFilter(bmp, 0.1, 0.2, 1));
									Log.e("burak", "it should be done ");
								}
								view.bitmaps=bit;
								bit=null;
								view.invalidate();
								dialog.dismiss();
								dialog=null;
							}
						}).start();
					
					break;
					
					
				case 3:
					dialog=ProgressDialog.show(this,"", "In Progress..");
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							ArrayList<Bitmap> bit=new ArrayList<Bitmap>();
							gifView view=null;
							for(int i=0;i<mLayout.getChildCount();i++){
								if(mLayout.getChildAt(i) instanceof gifView){
									view=(gifView) mLayout.getChildAt(i);
									
									
									Log.e("burak", "it should be done ");
								}
							}
							
							if(view!=null)
							for(Bitmap bmp:view.bitmapsHolder){
								bit.add(styleManagement.doColorFilter(bmp, 0.2, 1, 0.1));
								Log.e("burak", "it should be done ");
							}
							view.bitmaps=bit;
							bit=null;
							view.invalidate();
							dialog.dismiss();
							dialog=null;
						}
					}).start();
				
				break;
					
				case 4:
					dialog=ProgressDialog.show(this,"", "In Progress..");
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							ArrayList<Bitmap> bit=new ArrayList<Bitmap>();
							gifView view=null;
							for(int i=0;i<mLayout.getChildCount();i++){
								if(mLayout.getChildAt(i) instanceof gifView){
									view=(gifView) mLayout.getChildAt(i);
									
									
									Log.e("burak", "it should be done ");
								}
							}
							
							if(view!=null)
							for(Bitmap bmp:view.bitmapsHolder){
								bit.add(styleManagement.doColorFilter(bmp, 1, 0.1, 0.2));
								Log.e("burak", "it should be done ");
							}
							view.bitmaps=bit;
							bit=null;
							view.invalidate();
							dialog.dismiss();
							dialog=null;
						}
					}).start();
				break;
					
					
					
					
					
					
					
					
					
					
					
				default:
					break;
				}
			}else{
			 
			switch (position) {
			case 0:			//rename
				AlertDialog.Builder alert=new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.custom));
				
				alert.setTitle("Type a new file name");
				final EditText editText=new EditText(getApplicationContext());
				alert.setView(editText);
				
				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					  String value = editText.getText().toString();
					  if(mGif!=null){
						  	 
						  	
						  	File from=new File(gifURL.get(mGif.position));
							 File to = new File(gifDirectory+"/"+value+".gif");
						        from.renameTo(to);
						        readDirectory();
						        if(arrangeGifGallery.lastClickedGif==1)
						        	arrangeGifGallery.gif1.path=to.getAbsolutePath();
						        else if(arrangeGifGallery.lastClickedGif==2)
						        	arrangeGifGallery.gif2.path=to.getAbsolutePath();
						        else if(arrangeGifGallery.lastClickedGif==3)
						        	arrangeGifGallery.gif3.path=to.getAbsolutePath();
						        from=null;
						        to=null;
						        mGif=null;
						}
					  }
					});
				
				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int whichButton) {
						  mGif=null;
					  }
					});

				alert.show();
					
				break;
			case 2:			//delete
				AlertDialog.Builder sure=new AlertDialog.Builder(this);
				sure.setTitle("Are you sure you want to delete ?");
				sure.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						//Log.d("burak", mGif.path);
					  
					
					
					  arrangeGifGallery ar=new arrangeGifGallery(getApplicationContext(), lay);
					(new File(mGif.path)).delete();
					 gifURL.remove(mGif.path);
					 readDirectory();
					 if(arrangeGifGallery.lastClickedGif==1){
				        	arrangeGifGallery.gif1.path=arrangeGifGallery.gif2.path;
				        	arrangeGifGallery.gif1.position=gifURL.indexOf(arrangeGifGallery.gif1.path);
				        	arrangeGifGallery.gif1.mMovie=ar.getMovieFromPath(arrangeGifGallery.gif1.path);
				        	
				        	arrangeGifGallery.gif2.path=gifURL.get(arrangeGifGallery.gif1.position+1);
				        	arrangeGifGallery.gif2.position=gifURL.indexOf(arrangeGifGallery.gif2.path);
				        	arrangeGifGallery.gif2.mMovie=ar.getMovieFromPath(arrangeGifGallery.gif2.path);
				        	
				        	arrangeGifGallery.gif3.path=gifURL.get(arrangeGifGallery.gif2.position+1);
				        	arrangeGifGallery.gif3.position=gifURL.indexOf(arrangeGifGallery.gif3.path);
				        	arrangeGifGallery.gif3.mMovie=ar.getMovieFromPath(arrangeGifGallery.gif3.path);
				        	
					 }else if(arrangeGifGallery.lastClickedGif==2){
						 arrangeGifGallery.gif2.path=gifURL.get(arrangeGifGallery.gif1.position+1);
				        	arrangeGifGallery.gif2.position=gifURL.indexOf(arrangeGifGallery.gif2.path);
				        	arrangeGifGallery.gif2.mMovie=ar.getMovieFromPath(arrangeGifGallery.gif2.path);
				        	
				        	arrangeGifGallery.gif3.path=gifURL.get(arrangeGifGallery.gif2.position+1);
				        	arrangeGifGallery.gif3.position=gifURL.indexOf(arrangeGifGallery.gif3.path);
				        	arrangeGifGallery.gif3.mMovie=ar.getMovieFromPath(arrangeGifGallery.gif3.path);
					}else if(arrangeGifGallery.lastClickedGif==3){
						arrangeGifGallery.gif3.path=gifURL.get(arrangeGifGallery.gif2.position+1);
			        	arrangeGifGallery.gif3.position=gifURL.indexOf(arrangeGifGallery.gif3.path);
			        	arrangeGifGallery.gif3.mMovie=ar.getMovieFromPath(arrangeGifGallery.gif3.path);
					}
					
					
					makeEffectApplicable ap=new makeEffectApplicable(getApplicationContext(), gifURL.get(0), mLayout, 0);
					ap.execute();
					ar=null;
					 ap=null;
					  mGif=null;
						
					  
					}});
				
				sure.setNegativeButton("No", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int whichButton) {
						  mGif=null;
					  }
					});
				sure.show();
				break;
				
			case 1:                //share
				shareImage(gifURL.get(position));
				mGif=null;
				break;
			default: 
				break;
			}}
			
			return true;
		}
		
		
		 private void shareImage(String imagePath) {
		        Intent share = new Intent(Intent.ACTION_SEND);
		 
		        // If you want to share a png image only, you can do:
		        // setType("image/png"); OR for jpeg: setType("image/jpeg");
		        share.setType("image/gif");
		        
		        
		 
		        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imagePath)));
		  
		        startActivity(Intent.createChooser(share, "Share Image!"));
		    }
		 
		
		public void setIntentStyle(Intent intent){
			if(intent.getAction()=="android.intent.action.VIEW"){
				if(intent.getData()!=null){
				Uri uriOfGifFile=intent.getData();
				if(uriOfGifFile.getPath()!=null){
				
				makeEffectApplicable ap=new makeEffectApplicable(getApplicationContext(), getRealPathFromURI(uriOfGifFile), mLayout,0);
				ap.execute();
				ap=null;
				}
				uriOfGifFile=null;
				}}
			else {
				
				if(gifURL.size()<1){
					
					arrangeGifGallery ar=new arrangeGifGallery(getApplicationContext(), lay);	
					ar=null;
				}else{
					Log.d("burak", "olmasi gereken");
					applicable=new makeEffectApplicable(getApplicationContext(), gifURL.get(0), mLayout,0);
					applicable.execute();	
					applicable=null;
				}
				
			}
				
			
			
			
			
		}
		
		public void  modeController(Intent intent){
			if(intent.getAction()=="android.intent.action.VIEW"){
				MainActivity.MODE=1;
				
			}else {
				MainActivity.MODE=2;
				
			}
			
			
			
		}
		
		
		
		private String getRealPathFromURI(Uri contentURI) {
		    String result;
		    Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
		    if (cursor == null) { // Source is Dropbox or other similar local file path
		        result = contentURI.getPath();
		    } else { 
		        cursor.moveToFirst(); 
		        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
		        result = cursor.getString(idx);
		        cursor.close();
		    }
		    cursor=null;
		    
		    return result;
		}
		
		
		
		
		public void getDefaultGif(){
			if(gifURL.size()<2){
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
					
				InputStream inputStream=null;
				try {
					inputStream = getAssets().open("defaultgif.gif");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			
			//registerForContextMenu((MYGIFView)gifFileToMovie.assetToGif(getApplicationContext(), inputStream));
			
					}}).start();
				}
		}
		
		
		
		public static void bringButtons(){
			addButton.bringToFront();
			modeChanger.bringToFront();
			
			}
			
			
			
		@Override
		public void onBackPressed() {
		    if (doubleBackToExitPressedOnce) {
		    	android.os.Process.killProcess(android.os.Process.myPid());
		        super.onBackPressed();
		        return;
		    }

		    this.doubleBackToExitPressedOnce = true;
		    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

		    new Handler().postDelayed(new Runnable() {

		        @Override
		        public void run() {
		            doubleBackToExitPressedOnce=false;                       
		        }
		    }, 2000);
		} 
		
		
		
		public String getNameOfGif(String path){
			String name=null;
			if(path!=null){
			int start=path.lastIndexOf('/');
			int end=path.lastIndexOf(".gif");
			Log.d("burak", ""+start+"  "+end);
			if(start!=-1&&end!=-1){
				name=path.substring(start+1, end);
				return name;
				
			}
			}
			
			return "defaultgif";
		}
		public View getEditableGif(){
			for(int i=0;i<mLayout.getChildCount();i++)
			if(mLayout.getChildAt(i) instanceof gifView)
				return mLayout.getChildAt(i);
		
		return null;
}
		
		
		
		
		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			GoogleAnalytics.getInstance(this).reportActivityStart(this);
			super.onStart();
		}
		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			GoogleAnalytics.getInstance(this).reportActivityStop(this);
			super.onStop();
		}
		
		
	
		
		
		
		
}