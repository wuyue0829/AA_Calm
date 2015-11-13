package com.calm.calm.util;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ��Ÿ���ϵͳ����
 * @author jerry
 *
 */
public class SysConfig {
	private final static String SYS_PARMS = "calm";
	private SharedPreferences config ;

	private static SysConfig sysConfig;

	public SysConfig(Context context){
		config = context.getSharedPreferences(SYS_PARMS, 0);
	}

	//����ģʽ
	public static SysConfig getConfig(Context context){
		if(sysConfig == null){
			sysConfig = new SysConfig(context);
		}
		return sysConfig;
	}

	/*---------------------�ָ���----------------------------------------*/

	//��ȡԭ�������еİ汾��
	public int getAppVesion(){ 
		return config.getInt("version", 0); 	
	}

	//д�����µİ汾��-
	public void setAppVesion(int version){
		config.edit().putInt("version", version).commit();
	}
	

	//��ȡ�����������µİ汾��
	public int getSerAppVesion(){ 
		return config.getInt("serversion", 1); 	
	}

	//д������������µİ汾��-
	public void setSerAppVesion(int version){
		config.edit().putInt("serversion", version).commit();
	}
	
	//��ȡ�û�token
	public String getToken(){
		return config.getString("token", "");
	}
	//д���û�token
	public void setToken(String token){
		config.edit().putString("token", token).commit();
	}
	//��ȡ�û�ID
	public String getUserID(){
		return config.getString("userID", "0");
	}
	/**
	 * ��ȡint ���û�ID
	 * @return
	 */
	public int getUserID_(){
		return DoNumberUtil.intNullDowith(getUserID());
	}
	//д���û�ID
	public void setUserID(String userID){
		config.edit().putString("userID", userID).commit();
	}
	//��ȡ�û��Ա�
	public String getUserSex(){
		return config.getString("sex", "1");
	}
	//д���û��Ա�
	public void setUserSex(String sex){
		config.edit().putString("sex", sex).commit();
	}
	//��ȡ��Ļ���
	public int getScreenWidth(){
		return config.getInt("screenWidth", 0);
	}
	//д����Ļ���
	public void setScreenWidth(int windth){
		config.edit().putInt("screenWidth", windth).commit();
	}
	//��ȡ�û�����ʱ��
	public String getExpirationDate(){
		return MD5Util.decodeUnburden(config.getString("expirationDate", ""));
	}
	//д���û�����ʱ��
	public void setExpirationDate(String token){
		config.edit().putString("expirationDate", MD5Util.encodeUnburden(token)).commit();
	}
	//��ȡ�û�������
	public String getExpiredDay(){
		return MD5Util.decodeUnburden(config.getString("expiredDay", "0"));
	}
	//д���û�������
	public void setExpiredDay(String token){
		config.edit().putString("expiredDay", MD5Util.encodeUnburden(token)).commit();
	}

	//��ȡ����ȡʱ��
	public String getGuangGaoDate(){
		return config.getString("guangaoDate", "");
	}

	//д�����ȡʱ��
	public void setGuangGaoDate(String date){
		config.edit().putString("guangaoDate", date).commit();
	}

	//��ȡ�����������
	public String getGuangGaoLink(){
		return config.getString("guangaoLink", "");
	}
	//д�붥���������
	public void setGuangGaoLink(String link){
		config.edit().putString("guangaoLink", link).commit();
	}
	//��ȡ�������ͼƬ��ַ
	public String getGuangGaoPath(){
		return config.getString("guangaoPath", "");
	}
	//д�붥�����ͼƬ��ַ
	public void setGuangGaoPath(String path){
		config.edit().putString("guangaoPath", path).commit();
	}	
	//�����������
	public int getStartCount(){
		return DoNumberUtil.intNullDowith(config.getString("startcount", ""));
	}
	//д����������
	public void setStartCount(int count){
		config.edit().putString("startcount", DoNumberUtil.IntToStr(count)).commit();
	}
	//����Ƿ�����΢��
	public String getWeiboSendFlag(){
		return config.getString("issendweibo", "");
	}
	//д���Ƿ�����΢��
	public void setWeiboSendFlag(String flag){
		config.edit().putString("issendweibo", flag).commit();
	}
	//��ý�������ʦ����
	public String getHealthManageName(){
		return config.getString("healthmanagename", "");
	}
	//д�뽡������ʦ����
	public void setHealthManageName(String name){
		config.edit().putString("healthmanagename", name).commit();
	}
	//��ý�������ʦͷ��
	public String getHealthManagePic(){
		return config.getString("healthmanagepic", "");
	}
	//д�뽡������ʦͷ��
	public void setHealthManagePic(String pic){
		config.edit().putString("healthmanagepic", pic).commit();
	}
	//��ý�������ʦ����
	public String getHealthManageNumber(){
		return config.getString("healthmanagenumber", "");
	}
	//д�뽡������ʦ����
	public void setHealthManageNumber(String number){
		config.edit().putString("healthmanagenumber", number).commit();
	}
	/**
	 * �����Ƿ���ʾ���ı��
	 * @return
	 */
	public boolean isTodayShow(String action){
		boolean flag = false;
		String showDate = config.getString(action, "");
		String today = DateUtil.getToday();
		if(today.equals(showDate)){
			flag = true;
		}else {
			config.edit().putString(action, today).commit();
		}
		return flag;
	}

	/**
	 * ֻ��ʾһ�ε���Ŀ
	 * @param onceTpe
	 * @return
	 */
	public boolean isOnceConfig(String onceTpe){
		boolean flag = config.getBoolean(onceTpe, false);
		if(!flag){
			config.edit().putBoolean(onceTpe, true).commit();
		}
		return flag;
	}
	/***
	 * �����Զ���������Ϣ
	 * @param custom_action
	 * @param info  ��������
	 * @return
	 */
	public void setCustomConfig(String custom_action,String info){
		config.edit().putString(custom_action, info).commit();
	}
	/***
	 * ��ȡ�Զ���������Ϣ
	 * @return
	 */
	public String getCustomConfig(String custom_action,String defaultStr){
		return config.getString(custom_action, defaultStr);
	}
	
	/***
	 * ��ȡ�Զ���������Ϣ
	 * @return
	 */
	public String getCustomConfig(String custom_action){
		return config.getString(custom_action, "");
	}
	/***
	 * ��ȡ�Զ���������Ϣ
	 * @return
	 */
	public Date getCustomConfigDate(String custom_action){
		return DateUtil.getDate("yyyy-MM-dd HH:mm:ss", config.getString(custom_action, ""));
	}
}
