package com.giframe;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Rect;
import android.view.View;


public class MYGIFView extends View  {

public Movie mMovie;
private long mMovieStart;
public Bitmap bmp;
boolean truth=true;
public String path;
public int position;
MYGIFView(Context aContext, Movie aMovie,String path,int position) {
    super(aContext);

    if (aMovie == null)
        return;
    
    this.path=path;
    mMovie = aMovie;
   this.position=position;
    mMovieStart = android.os.SystemClock.uptimeMillis();
}

public MYGIFView(Context context, Movie mMovie) {
	super(context);
	if (mMovie == null)
        return;
	this.mMovie = mMovie;
	
}

protected void onDraw(Canvas aCanvas) {
    super.onDraw(aCanvas);
    
    
    if (mMovie == null || mMovie.duration() == 0)
        return;

    int relTime = (int)((android.os.SystemClock.uptimeMillis() - mMovieStart)
                        % mMovie.duration());
    mMovie.setTime(relTime);

    Bitmap movieBitmap = Bitmap.createBitmap(mMovie.width(), mMovie.height(),
                                             Bitmap.Config.ARGB_8888);
    
    Canvas movieCanvas = new Canvas(movieBitmap);
    
    mMovie.draw(movieCanvas, 0, 0);

    Rect src = new Rect(0, 0, mMovie.width(), mMovie.height());
    Rect dst = new Rect(0, 0, this.getWidth(), this.getHeight());
    aCanvas.drawBitmap(Bitmap.createScaledBitmap(movieBitmap, MainActivity.screenWidth/6, MainActivity.screenHeight*25/200, false), src, dst, null);
    
    if(truth){
    	bmp=movieBitmap;
    	truth=false;
    }
    
    
    this.invalidate();
    
}

public Bitmap getBitmap(){
	
	return bmp;
	
}



}