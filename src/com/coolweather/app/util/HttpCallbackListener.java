package com.coolweather.app.util;
/**
 * 
 * 回调接口
 *
 */
public interface HttpCallbackListener {

	void onFinish(String string);

	void onError(Exception e);

}
