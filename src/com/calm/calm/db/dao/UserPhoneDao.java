package com.calm.calm.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.calm.calm.entity.UserPhone;
import com.calm.calm.util.BaseUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

public class UserPhoneDao {
	private Dao<UserPhone, Integer> dao;


	public UserPhoneDao(){

	}
	public  UserPhoneDao(Dao<UserPhone, Integer> dao){
		this.dao = dao;
	}

	/** 新增用户信息
	 * @param newResult
	 */
	public Boolean save(UserPhone newTarget)
	{
		try {
			dao.create(newTarget);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(UserPhone newTarget)
	{
		try {
			dao.update(newTarget);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public UserPhone getUserPhone(String name)
	{	
		List<UserPhone> usrCredit = new ArrayList<UserPhone>();
		try {
			QueryBuilder<UserPhone, Integer> queryBuilder = dao.queryBuilder();
			Where<UserPhone, Integer> where = queryBuilder.where();

			where.eq("name", name);
			
			PreparedQuery<UserPhone> preparedQuery = queryBuilder.prepare();
			usrCredit = dao.query(preparedQuery);
			if(!BaseUtil.isSpace(usrCredit)){
				return usrCredit.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public List<UserPhone> getUserPhoneAll(String tag)
	{	
		List<UserPhone> usrCredit = new ArrayList<UserPhone>();
		try {
			QueryBuilder<UserPhone, Integer> queryBuilder = dao.queryBuilder();
			Where<UserPhone, Integer> where = queryBuilder.where();

			where.eq("tag", tag);
			
			PreparedQuery<UserPhone> preparedQuery = queryBuilder.prepare();
			usrCredit = dao.query(preparedQuery);
			if(!BaseUtil.isSpace(usrCredit)){
				return usrCredit;
			}
		} catch (Exception e) {
		}
		return null;
	}

}
