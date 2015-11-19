package com.calm.calm.entity;

import cn.bmob.v3.BmobObject;

public class SchoolName extends BmobObject{
	private String name;
	private String schoolNum;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	
	
}
