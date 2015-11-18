package com.calm.calm.net;

import com.calm.calm.entity.AppVersionCode;
import com.calm.calm.util.SysConfig;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

public class InitNet {
	
	private Context context;
	private SysConfig sysConfig ;
	public InitNet(Context context ){
		this.context = context;
		sysConfig = new SysConfig(context);
	}
	
	public void getVersionCode(){
		BmobQuery<AppVersionCode> query = new BmobQuery<AppVersionCode>();
		query.getObject(context,"s3PjFFF9", new GetListener<AppVersionCode>() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("faile");
			}
			
			@Override
			public void onSuccess(AppVersionCode arg0) {
				// TODO Auto-generated method stub
				sysConfig.setSerAppVesion(arg0.getVersionCode().intValue());
			}
		});
	}
}
