package com.calm.calm.bll;

import java.util.List;

import com.calm.calm.db.DataHelper;
import com.calm.calm.db.dao.UserPhoneDao;
import com.calm.calm.entity.UserPhone;
import com.calm.calm.util.BaseUtil;
import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class BllUsrPhone {
	private Context context ;
	private UserPhoneDao userPhoneDao;
	
	public BllUsrPhone(Context context){
		this.context = context;
		Dao<UserPhone, Integer> dao = DataHelper.getDataHelp(this.context).getUserPhone();
		this.userPhoneDao = new UserPhoneDao(dao);
	}
	
	public void saveUserPhone(List<UserPhone> list){
		for(UserPhone phone : list){
			System.out.println("新的一条数据");
			System.out.println(phone.getName());
			UserPhone phone2 = this.userPhoneDao.getUserPhone(phone.getName());
			if(null == phone2){
				this.userPhoneDao.save(phone);
				System.out.println("保存了一条");
			}else{
				this.userPhoneDao.update(phone);
				System.out.println("升级了一条");
			}
		}
	}
	
	public List<UserPhone> getAll(){
		return this.userPhoneDao.getUserPhoneAll("111");
	}
}
