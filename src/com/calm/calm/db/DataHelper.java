package com.calm.calm.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.bocommlife.healthywalk.entity.SysAlarm;
import com.calm.calm.entity.AppVersionCode;
import com.calm.calm.entity.UsrInfo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataHelper extends OrmLiteSqliteOpenHelper{

	public static final String DATABASE_NAME = "AA_base";
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
		daoMaps.put("", null);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try {
			TableUtils.createTable(arg1, UsrInfo.class);
			initData(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return null;
		
	}

}
