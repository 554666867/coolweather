package com.coolweather.app.model;
/**
 * 
 * 省份
 *
 */
public class Province {

	//省份的ID
	private int id;
	//省份的名字
	private String provinceName;
	//省份的代号
	private String provinceCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
}
