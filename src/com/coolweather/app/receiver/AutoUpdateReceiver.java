package com.coolweather.app.receiver;

import com.coolweather.app.service.AutoUpdateService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 
 * ÿ8Сʱ���ܵ�һ���㲥��Ȼ����һ�η���
 *
 */
public class AutoUpdateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context,AutoUpdateService.class);
		context.startService(i);
	}


}
