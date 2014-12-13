package com.giframe;

import java.util.ArrayList;

import com.giframe.R;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class HelpActivity extends ActionBarActivity {
	public ListView list;
	public ArrayList<RelativeLayout> layouts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_help);
		layouts=new ArrayList<RelativeLayout>();
		list=(ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		init();
		
		
		
		
	}

	public BaseAdapter adapter=new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return layouts.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return layouts.size();
		}
	};
	
	public void init(){
		DisplayMetrics metrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		RelativeLayout rel=new RelativeLayout(getApplicationContext());
		ImageView image=new ImageView(getApplicationContext());
		
		//add button
		TextView textView=new TextView(getApplicationContext());
		textView.setText("When you press,it leads to a browser to download a gif.In menu pops up when long press to gif, there is \"Copy Picture\" segmet.Hit that segment and it brings the gif to the app");
		RelativeLayout.LayoutParams param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels/4;
		param.height=metrics.heightPixels/6;
		image.setImageDrawable(getResources().getDrawable(R.drawable.addbutton));
		
		param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		rel.addView(image, param);
		
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels*5/8;
		param.height=metrics.heightPixels/6;
		textView.setTextSize(15);
		textView.setTextColor(Color.BLACK);
		param.addRule(RelativeLayout.CENTER_VERTICAL);
		param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param.addRule(RelativeLayout.LEFT_OF,image.getId());
		rel.addView(textView, param);
		layouts.add(rel);
		
		
		
		
		
		//add camera turner
		rel=new RelativeLayout(getApplicationContext());
		image=new ImageView(getApplicationContext());
		textView=new TextView(getApplicationContext());
		textView.setText("Changes camera mode between front camera and back camera");
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels/4;
		param.height=metrics.heightPixels/6;
		image.setImageDrawable(getResources().getDrawable(R.drawable.switchcamera));
		
		param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		rel.addView(image, param);
		textView.setTextSize(15);
		textView.setTextColor(Color.BLACK);
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels*5/8;
		param.height=metrics.heightPixels/6;
		param.addRule(RelativeLayout.CENTER_VERTICAL);
		param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param.addRule(RelativeLayout.LEFT_OF,image.getId());
		rel.addView(textView, param);
		layouts.add(rel);
		
		
		
		//add mode changer 
		rel=new RelativeLayout(getApplicationContext());
		image=new ImageView(getApplicationContext());
		
		
		textView=new TextView(getApplicationContext());
		textView.setText("You can easly turn the app to a gif file player by using this button ");
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels/4;
		param.height=metrics.heightPixels/6;
		image.setImageDrawable(getResources().getDrawable(R.drawable.cameraswitch));
		
		param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		rel.addView(image, param);
		textView.setTextSize(15);
		textView.setTextColor(Color.BLACK);
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels*5/8;
		param.height=metrics.heightPixels/6;
		param.addRule(RelativeLayout.CENTER_VERTICAL);
		param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param.addRule(RelativeLayout.LEFT_OF,image.getId());
		rel.addView(textView, param);
		layouts.add(rel);
		
		
		
		rel=new RelativeLayout(getApplicationContext());
		image=new ImageView(getApplicationContext());
		
		
		textView=new TextView(getApplicationContext());
		textView.setText("It makes you to make gifs.when you touch this button,app will start to record.");
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels/4;
		param.height=metrics.heightPixels/6;
		param.addRule(RelativeLayout.CENTER_VERTICAL);
		image.setImageDrawable(getResources().getDrawable(R.drawable.wheel));
		
		param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		rel.addView(image, param);
		textView.setTextSize(15);
		textView.setTextColor(Color.BLACK);
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels*5/8;
		param.height=metrics.heightPixels/6;
		param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param.addRule(RelativeLayout.LEFT_OF,image.getId());
		rel.addView(textView, param);
		layouts.add(rel);
		
		
		
		
		rel=new RelativeLayout(getApplicationContext());
		image=new ImageView(getApplicationContext());
		
		
		textView=new TextView(getApplicationContext());
		textView.setText("Default gif.After you make enough gif on your own, it will be removed.");
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels/4;
		param.height=metrics.heightPixels/6;
		image.setImageDrawable(getResources().getDrawable(R.drawable.d));
		
		param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		rel.addView(image, param);
		textView.setTextSize(15);
		textView.setTextColor(Color.BLACK);
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.width=metrics.widthPixels*5/8;
		param.height=metrics.heightPixels/6;
		param.addRule(RelativeLayout.CENTER_VERTICAL);
		
		param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param.addRule(RelativeLayout.LEFT_OF,image.getId());
		rel.addView(textView, param);
		layouts.add(rel);
		
		
		rel=new RelativeLayout(getApplicationContext());
		textView=new TextView(getApplicationContext());
		textView.setText("      \u2605    Doing long press to recorded gifs,cause to pop a menu up.It makes you easier to organize your gifs.There will be \" Rename\",\" Share\",\" Delete\" buttons for your needs.");
		param=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		param.addRule(RelativeLayout.CENTER_HORIZONTAL);
		param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		param.rightMargin=10;
		param.leftMargin=10;
		
		textView.setTextSize(20);
		textView.setTextColor(Color.BLACK);
		rel.addView(textView, param);
		layouts.add(rel);
		
		rel=new RelativeLayout(getApplicationContext());
		textView=new TextView(getApplicationContext());
		textView.setText("      \u2605    You can find the recorded gifs at directory : \"My Files/All Files/Pictures/Gif Gallery/\"");
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.addRule(RelativeLayout.CENTER_HORIZONTAL);
		param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		textView.setTextSize(20);
		textView.setTextColor(Color.BLACK);
		rel.addView(textView, param);
		layouts.add(rel);
		
		
		
		rel=new RelativeLayout(getApplicationContext());
		textView=new TextView(getApplicationContext());
		textView.setText("  Warning : Sharing file with Whatsapp is meaningless.Whatsapp does not support gif types!");
		param=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param.addRule(RelativeLayout.CENTER_HORIZONTAL);
		param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		textView.setTextSize(20);
		textView.setTextColor(Color.BLACK);
		rel.addView(textView, param);
		layouts.add(rel);
		
	}
	
}
