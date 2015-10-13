package com.calm.calm.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.calm.calm.entity.UsrCache;
import com.calm.calm.entity.UsrInfo;
import com.calm.calm.util.LogUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataHelper extends OrmLiteSqliteOpenHelper{

	public static final String DATABASE_NAME = "yy";
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
	
	public DataHelper(Context context, String databaseName,
			CursorFactory factory, int databaseVersion) {
		super(context, databaseName, factory, databaseVersion);
		// TODO Auto-generated constructor stub
	}
	
	public static DataHelper getDataHelp(Context context){
		if(staticDB == null){
			staticDB = new DataHelper(context);
		}
		return staticDB;
		
	}
	
	public static SQLiteDatabase getDB(){
		
		return staticDB.getWritableDatabase();
	}

	
	private void initDaoMaps() {
		// TODO Auto-generated method stub
		daoMaps = new HashMap<String, Dao>();
		daoMaps.put("usrCache", null);  //用户缓存表
		daoMaps.put("usrInfo", null);  //用户信息表
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try {
			LogUtil.e("见表", "success");
			TableUtils.createTable(arg1, UsrInfo.class);
			TableUtils.clearTable(arg1, UsrCache.class);
			initData(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LogUtil.e("见表", "失败");
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

}
