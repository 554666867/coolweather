package com.coolweather.app.service;

import com.coolweather.app.receiver.AutoUpdateReceiver;
import com.coolweather.app.util.HttpCallbackListener;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.Utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
/**
 * 
 * 用于自动更新的天气信息的服务
 *
 */
public class AutoUpdateService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	//首先开启一次线程更新天气，然后每8小时发送一次广播给广播接受者
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				updateWeather();
			}

		}).start();
		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
		int anhour = 8*60*60*1000;
		long triggerAtTime = SystemClock.elapsedRealtime() + anhour;
		Intent i = new Intent(this,AutoUpdateReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
		return super.onStartCommand(intent, flags, startId);
	}

	//用于更新天气
	private void updateWeather() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String weatherCode = prefs.getString("weather_code", "");
		String address = "http://www.weather.com.cn/data/cityinfo/" + weatherCode + ".html";
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				Utility.handleWeatherResponse(AutoUpdateService.this, response);
			}
			
			@Override
			public void onError(Exception e ) {
				e.printStackTrace();
			}
		});
	}
	
}
