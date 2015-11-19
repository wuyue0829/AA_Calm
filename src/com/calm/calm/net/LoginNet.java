package com.calm.calm.net;

import java.util.List;

import com.calm.calm.entity.SchoolName;
import com.calm.calm.util.SysConfig;
import com.calm.calm.util.constant.ConfigConstant;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class LoginNet {

	/**
	 * 登录时，检测用户名和密码是否匹配
	 * @param name
	 * @param schoolNum
	 */
	public static void isMate(String name,final String schoolNum,Context context,final SysConfig sysConfig){
		BmobQuery<SchoolName> query = new BmobQuery<SchoolName>();
		query.addWhereEqualTo("name", name);
		query.findObjects(context, new FindListener<SchoolName>() {
			
			@Override
			public void onSuccess(List<SchoolName> arg0) {
				// TODO Auto-generated method stub
				if(arg0.get(0).getSchoolNum().equals(schoolNum)){
					sysConfig.setCustomConfig(ConfigConstant.CONFIG_SCHOOL_NAME, "1");
				}
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				sysConfig.setCustomConfig(ConfigConstant.CONFIG_SCHOOL_NAME, "0");
			}
		});
	}
}