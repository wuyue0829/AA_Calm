package com.calm.calm.entity;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="usr_info")
public class UsrInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public UsrInfo(){
		
	}

	public UsrInfo(int userSysID, String loginID, String password,
			String nickName, String sex, String home, Date birthday,
			String mail, String photo, String mobile, String userCode,String sinaToken,
			String sinaID, String qqToken, String qqID, Date updateDate) {
		super();
		this.userSysID = userSysID;
		this.loginID = loginID;
		this.password = password;
		this.nickName = nickName;
		this.sex = sex;
		this.home = home;
		this.birthday = birthday;
		this.mail = mail;
		this.photo = photo;
		this.mobile = mobile;
		this.userCode = userCode;
		this.sinaToken = sinaToken;
		this.sinaID = sinaID;
		this.qqToken = qqToken;
		this.qqID = qqID;
		this.updateDate = updateDate;
	}


	@DatabaseField(id=true)
	int userSysID; //�û�ID

	@DatabaseField
	private String loginID; //��¼ID
	
	@DatabaseField
	private String password; //�û�ID
	
	@DatabaseField
	private String nickName; //�ǳ�
	
	@DatabaseField
	private String sex; //�Ա�
	
	@DatabaseField
	private String home; //���ڳ���
	
	@DatabaseField
	private Date birthday; //����
	
	@DatabaseField
	private String mail; //����
	
	@DatabaseField
	private String photo; //ͷ��
	
	@DatabaseField
	private String mobile; //�ֻ�
	
	@DatabaseField
	private String userCode; //���֤��
	@DatabaseField
	private String sinaToken; //����token
	
	@DatabaseField
	private String sinaID; //����ID
	
	@DatabaseField
	private String qqToken; //��Ѷtoken
	
	@DatabaseField
	private String qqID; //��ѶID

	@DatabaseField
	Date updateDate; //��������

	public int getUserSysID() {
		return userSysID;
	}

	public void setUserSysID(int userSysID) {
		this.userSysID = userSysID;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSinaToken() {
		return sinaToken;
	}

	public void setSinaToken(String sinaToken) {
		this.sinaToken = sinaToken;
	}

	public String getSinaID() {
		return sinaID;
	}

	public void setSinaID(String sinaID) {
		this.sinaID = sinaID;
	}

	public String getQqToken() {
		return qqToken;
	}

	public void setQqToken(String qqToken) {
		this.qqToken = qqToken;
	}

	public String getQqID() {
		return qqID;
	}

	public void setQqID(String qqID) {
		this.qqID = qqID;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}