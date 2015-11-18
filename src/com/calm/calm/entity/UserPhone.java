package com.calm.calm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import cn.bmob.v3.BmobObject;
@DatabaseTable(tableName="usr_phone")
public class UserPhone extends BmobObject{
	private static final long serialVersionUID = 1L;
	
	public UserPhone(){
		
	}
	public UserPhone(String name,String phone,String tag){
		this.name = name;
		this.phone = phone;
		this.tag = tag;
	}
	
	@DatabaseField(generatedId = true)
	int userSysID; //ÓÃ»§ID
	@DatabaseField
	private String name;
	@DatabaseField
	private String phone;
	@DatabaseField
	private String tag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
