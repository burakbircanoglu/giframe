package com.giframe;


import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

import android.graphics.Paint;
import android.util.Log;

public class styleManagement {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Bitmap doInvert(Bitmap src) {
	    // create new bitmap with the same settings as source bitmap
	    Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
	    // color info
	    int A, R, G, B;
	    int pixelColor;
	    // image size
	    int height = src.getHeight();
	    int width = src.getWidth();
	 
	    // scan through every pixel
	    for (int y = 0; y < height; y++)
	    {
	        for (int x = 0; x < width; x++)
	        {
	            // get one pixel
	            pixelColor = src.getPixel(x, y);
	            // saving alpha channel
	            A = Color.alpha(pixelColor);
	            // inverting byte for each R/G/B channel
	            R = 255 - Color.red(pixelColor);
	            G = 255 - Color.green(pixelColor);
	            B = 255 - Color.blue(pixelColor);
	            // set newly-inverted pixel to output image
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	 
	    // return final bitmap
	    return bmOut;
	}
	
	
	
	 public ArrayList<Bitmap> doFilterTo(ArrayList<Bitmap> bmps){
		  ArrayList<Bitmap> images=new ArrayList<Bitmap>();
		  Bitmap bmp=null;
		  for (Bitmap bitmap : bmps) {
			

			Log.d("burak", "filtreledi");
			//bmp=greyScaler(bitmap);255;246;143
			images.add(doColorFilter(bitmap, 0.139, 0.34, 0.82));
		}
		  
		return images;  
	  }
	
	
	 public static Bitmap doColorFilter(Bitmap src, double red, double green, double blue) {
	        // image size
	        int width = src.getWidth();
	        int height = src.getHeight();
	        // create output bitmap
	        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
	        // color information
	        int A, R, G, B;
	        int pixel;
	 
	        // scan through all pixels
	        for(int x = 0; x < width; ++x) {
	            for(int y = 0; y < height; ++y) {
	                // get pixel color
	                pixel = src.getPixel(x, y);
	                // apply filtering on each channel R, G, B
	                A = Color.alpha(pixel);
	                R = (int)(Color.red(pixel) * red);
	                G = (int)(Color.green(pixel) * green);
	                B = (int)(Color.blue(pixel) * blue);
	                // set new color pixel to output bitmap
	                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	            }
	        }
	 
	        // return final image
	        return bmOut;
	    }
	
	
	 public static Bitmap greyScaler(Bitmap b) {    
	     Bitmap grayscaleBitmap = Bitmap.createBitmap(b.getWidth(),
	            b.getHeight(), Bitmap.Config.RGB_565);
	     Canvas c = new Canvas(grayscaleBitmap);
	     Paint p = new Paint();
	     ColorMatrix cm = new ColorMatrix();
	     cm.setSaturation(0);
	     ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
	     p.setColorFilter(filter);
	     c.drawBitmap(b, 0, 0, p);
	     return grayscaleBitmap;
	}	
	
	
	
}
