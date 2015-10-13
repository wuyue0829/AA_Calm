package com.calm.calm.bll;

import com.calm.calm.db.DataHelper;
import com.calm.calm.db.dao.UsrInfoDao;
import com.calm.calm.entity.UsrInfo;
import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class BllUsrInfo {
	private Context context ;
	private UsrInfoDao usrInfoDao;
	
	public BllUsrInfo(Context context){
		this.context = context;
		Dao<UsrInfo, Integer> dao = DataHelper.getDataHelp(this.context).getUsrInfoDao();
		this.usrInfoDao = new UsrInfoDao(dao);
	}
	
	/**
	 * 取得用户信息
	 * @param userSysID
	 * @return
	 */
	public UsrInfo getUsrInfo(int userSysID)
	{
		return this.usrInfoDao.getUsrInfo(userSysID);
	}
}
