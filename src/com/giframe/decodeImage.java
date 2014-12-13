package com.giframe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import com.giframe.LinkedBag.Node;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.LinearLayout;

 	
public class decodeImage extends MainActivity{
	public LinkedBag<Bitmap> bmpNode;
	public ProgressDialog progressDialog;
	AnimatedGifEncoder encoder;
	public CameraPreview preview;
	public int progressNumber=0;
	public Context context;
	public File file;
	@SuppressWarnings("rawtypes")
	public Node holder;
	public decodeImage(CameraPreview cameraPreview,ArrayList<byte[]> list,Context context){
		bmpNode=new LinkedBag<Bitmap>();
		
		encoder=new AnimatedGifEncoder();
		preview=cameraPreview;
		this.context=context;		
			Log.d("asd", "null");
			if(!list.isEmpty()){
		new byteArrayToBitmap(list).run();
		new byteArrayToGif().run();
			
			}
	}
	
	
	
	
	public class byteArrayToBitmap  implements Runnable{
		public ArrayList<byte[]> data;
		public Bitmap bmp;
		
		public byteArrayToBitmap(ArrayList<byte[]> data) {
			this.data=data;
		}
		public byteArrayToBitmap() {
			this.data=null;
		}
		@Override
		public void run() {
			Matrix matrix=new Matrix();
			if(preview
					.getmCameraId()==1)
			matrix.postRotate(-90);
			else {
				matrix.preRotate(90);
			}
			android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
			BitmapFactory.Options bitmapFatoryOptions = new BitmapFactory.Options();
            bitmapFatoryOptions.inPreferredConfig = Config.RGB_565;
            progressNumber=0;
            	for (int i=data.size()-1;i>=0;i--) {
            		progressNumber++;
            		 ByteArrayOutputStream out=new ByteArrayOutputStream();
         	    	YuvImage yuv = new YuvImage(data.get(i), ImageFormat.NV21, preview.mPreviewSize.width, preview.mPreviewSize.height, null);
         	    	yuv.compressToJpeg(new Rect(0,0,preview.mPreviewSize.width,preview.mPreviewSize.height), 100, out);
         	    	
         	    	
         	    	
         	    	
         	    	
         	    	bmp=BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.toByteArray().length, bitmapFatoryOptions);
         	    		
         	    	bmpNode.add(bmp);
         	    	
            	}
            	
            }
		
	}
	
	public class byteArrayToGif implements Runnable{
		
		@Override
		public void run() {
			android.os.Process.setThreadPriority(-20);
			
			
			OutputStream os=null;
			file=new File(gifDirectory+"/"+new Random().nextInt()%1000+".gif");
			
			try {
				file.createNewFile();
				os=new FileOutputStream(file);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			encoder.start(os);
			
			encoder.setDelay(100);
			
			 progressDialog = ProgressDialog.show(context, "", "In Progress...");
			 
			 new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(bmpNode.getFirstNode()!=null){
						//do conversion and move
						
						encoder.addFrame((Bitmap)bmpNode.getFirstNode().getData());
						bmpNode.remove();
					}
					if(holder==null){
					progressDialog.dismiss();
					progressDialog=null;
					encoder.finish();
					encoder=null;
					preview=null;
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
							
							arrangeGifGallery ar=new arrangeGifGallery(context, lay);
							
							ar.addToGallery(file.getAbsolutePath());
							
							Log.d("burak", "1"+ arrangeGifGallery.gif1.path+" 2"+arrangeGifGallery.gif2.path+"  3"+arrangeGifGallery.gif3.path );
							file=null;
							
							
							bmpNode=null;
							
							
							
						}
					});
					
					}
					}
				
				
			}).start();
			
			 os=null;
			
		}
		
		
		
		
		
		
		
	}	
	
	
	
	
	
	
	
	
}


