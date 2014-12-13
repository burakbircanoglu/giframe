package com.giframe;

import java.util.List;

import com.google.android.gms.internal.co;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class directBrowser extends MainActivity{
	public static Context context;
	public directBrowser(){
		
		
	
	}
	
	
	
	protected void moveToFront() {
		if (Build.VERSION.SDK_INT >= 11) { // honeycomb
		final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		final List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

		for (int i = 0; i < recentTasks.size(); i++) {


		if (recentTasks.get(i).baseActivity.toShortString().indexOf("com.giframe") > -1) {
		activityManager.moveTaskToFront(recentTasks.get(i).id, ActivityManager.MOVE_TASK_WITH_HOME);
		}
		}
		}
		context=null;
		}
		
}
