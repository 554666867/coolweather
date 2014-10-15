package com.coolweather.app.model;
/**
 * 
 * 城市
 *
 */
public class City {

	//城市ID
	private int id;
	//城市名字
	private String cityName;
	//城市代码
	private String cityCode;
	//所属省份的ID
	private int provinceId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	
}
