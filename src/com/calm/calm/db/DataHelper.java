package com.calm.calm.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.calm.calm.entity.UserPhone;
import com.calm.calm.entity.UsrCache;
import com.calm.calm.entity.UsrInfo;
import com.calm.calm.util.LogUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataHelper extends OrmLiteSqliteOpenHelper{

	public static final String DATABASE_NAME = "YY.db";
	public static final int DATABASE_VERSION = 1;
	
	public static DataHelper staticDB;
	public Context context;
	
	@SuppressWarnings("rawtypes")
	Map<String, Dao> daoMaps = null; //所有dao集合
	
	public DataHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		initDaoMaps();	
	}
	
	public static DataHelper getDataHelp(Context context){
		if(staticDB == null){
			staticDB = new DataHelper(context);
			System.out.println("datahelp是空的");
		}
		return staticDB;
		
	}
	
	public static SQLiteDatabase getDB(){
		
		return staticDB.getWritableDatabase();
	}

	@SuppressWarnings("rawtypes")
	private void initDaoMaps() {
		// TODO Auto-generated method stub
		daoMaps = new HashMap<String, Dao>();
		daoMaps.put("usrCache", null);  //用户缓存表
		daoMaps.put("usrInfo", null);  //用户信息表
		daoMaps.put("userphone", null);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try {
			System.out.println("见表success");
			TableUtils.createTable(arg1, UsrInfo.class);
			TableUtils.createTable(arg1, UserPhone.class);
			initData(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LogUtil.e("见表", "失败");
			System.out.println("见表faile");
			e.printStackTrace();
		}
	}

	private void initData(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public Dao<UsrInfo, Integer> getUsrInfoDao(){
		Dao<UsrInfo, Integer> accountDao = daoMaps.get("usr_info");
		if (accountDao == null) {
			try {
				accountDao = getDao(UsrInfo.class);
			} catch (SQLException e) {
			}
		}
		return accountDao;
		
	}
	
	@SuppressWarnings("unchecked")
	public Dao<UsrCache, Integer> getUsrCache(){
		Dao<UsrCache, Integer> accountDao = daoMaps.get("usr_cache");
		if (accountDao == null) {
			try {
				accountDao = getDao(UsrCache.class);
			} catch (SQLException e) {
			}
		}
		return accountDao;
	}
	
	@SuppressWarnings("unchecked")
	public Dao<UserPhone, Integer> getUserPhone(){
		Dao<UserPhone, Integer> accountDao = daoMaps.get("usr_phone");
		if (accountDao == null) {
			try {
				accountDao = getDao(UserPhone.class);
			} catch (SQLException e) {
			}
		}
		return accountDao;
	}

}
