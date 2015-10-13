package com.calm.calm.entity;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "usr_cache")
public class UsrCache implements Serializable{
	private static final long serialVersionUID = 1;
	
	public UsrCache(){
		
	}
	
	public UsrCache(long id, int userSysID, String key, String value,
			Date updateDate){
		super();
		this.id = id;
	}
	
	@DatabaseField(generatedId = true)
	long id; //����
	
	@DatabaseField
	int userSysID; //�û�ID
	
	@DatabaseField
	String key; //�ؼ���
	
	@DatabaseField
	String value; //ֵ

	@DatabaseField
	Date updateDate; //����ʱ��

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserSysID() {
		return userSysID;
	}

	public void setUserSysID(int userSysID) {
		this.userSysID = userSysID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
