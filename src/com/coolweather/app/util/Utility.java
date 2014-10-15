package com.coolweather.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

/**
 * 
 * 1.���ڽ������������ص�ʡ��������
 * �磺"����|����,����|����"
 * �����������Ȱ����ŷָ����ٰ������߷ָ�
 * ���Ž����������������õ�ʵ�����У�Ȼ�󱣴浽���ݿ⵱��
 * 
 * 2.���ڽ����ʹ����ص�JSON����
 * 
 */
public class Utility {

	//�����ʹ�����������ص�ʡ�����ݣ������浽���ݿ�
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolweatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0){
				for(String p : allProvinces){
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coolweatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	//�����ʹ�����������ص��м����ݣ������浽���ݿ�
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolweatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
Log.d("Utility", response);
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length > 0){
				for(String p : allCities){
					String[] array = p.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					coolweatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	//�����ʹ�����������ص��ؼ����ݣ������浽���ݿ�
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolweatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCounties = response.split(",");
			if(allCounties != null && allCounties.length > 0){
				for(String p : allCounties){
					String[] array = p.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					coolweatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
	
	//�������������ص�JSON����
	public static void handleWeatherResponse(Context context,String response){
		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
			String cityName = weatherInfo.getString("city");
			String weatherCode = weatherInfo.getString("cityid");
			//�¶�
			String temp1 = weatherInfo.getString("temp");
			//ʪ��
			String temp2 = weatherInfo.getString("SD");
			//����
			String weatherDesp = weatherInfo.getString("WD");
			String publishTime = weatherInfo.getString("time");
System.out.println("�����������" + context+cityName+weatherCode+temp1+temp2+weatherDesp+publishTime);
			saveWeatherInfo(context,cityName,weatherCode,temp1,temp2,weatherDesp,publishTime);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	//����������������Ϣ������Ϣ�洢��SharedPreferences�ļ���
	private static void saveWeatherInfo(Context context, String cityName,
			String weatherCode, String temp1, String temp2, String weatherDesp,
			String publishTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��M��d��",Locale.CHINA);
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("city_selected", true);
		editor.putString("city_name", cityName);
		editor.putString("weather_code", weatherCode);
		editor.putString("temp1", temp1);
		editor.putString("temp2", temp2);
		editor.putString("weather_desp", weatherDesp);
		editor.putString("publish_time", publishTime);
		editor.putString("current_date", sdf.format(new Date()));
		editor.commit();
	}
	
}
