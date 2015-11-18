package com.calm.calm.entity;

import cn.bmob.v3.BmobObject;

public class AppVersionCode extends BmobObject{
	
	private String versionName;
	private Integer versionCode;
	
	
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public Integer getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}
	
}
