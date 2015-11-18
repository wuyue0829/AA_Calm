package com.calm.calm.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.calm.calm.entity.UsrInfo;
import com.calm.calm.util.BaseUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

public class UsrInfoDao {
	private Dao<UsrInfo, Integer> dao;
	
	public  UsrInfoDao(Dao<UsrInfo, Integer> dao){
		this.dao = dao;
	}
	
	/** 新增用户信息
	 * @param newResult
	 */
	public Boolean save(UsrInfo newTarget)
	{
		try {
			dao.create(newTarget);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据用户id取得单个用户信息
	 * @param userSysID
	 * @return
	 */
	public UsrInfo getUsrInfo(int userID)
	{
		List<UsrInfo> uiList = new ArrayList<UsrInfo>();

		try {
			QueryBuilder<UsrInfo, Integer> queryBuilder = dao.queryBuilder();
			Where<UsrInfo, Integer> where = queryBuilder.where();

			where.eq("userSysID", userID);

			PreparedQuery<UsrInfo> preparedQuery = queryBuilder.prepare();
			uiList = dao.query(preparedQuery);
			if(!BaseUtil.isSpace(uiList)){
				return uiList.get(0);
			}
		} catch (SQLException e) {
			
		}
		return null;
	}}
