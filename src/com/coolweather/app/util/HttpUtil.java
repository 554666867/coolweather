package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;

import android.util.Log;

/**
 * 
 * �������н�������
 * ����һ�����̻߳�ȡ����������
 *
 */
public class HttpUtil {

	//����HTTP�������ж����˻ص��ӿ��з����ĽӴ�ʱ��
	public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
		new Thread(new Runnable(){
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
Log.d("����ĵ�ַ-address", address);
					URL url = new URL(address);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
Log.d("���ص�����-response", response + "");
					if(listener != null){
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					if(listener != null)
						listener.onError(e);
				}finally{
					if(connection != null)
						connection.disconnect();
				}
			}
			
		}).start();
	}
	
}
