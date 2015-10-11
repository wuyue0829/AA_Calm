package com.calm.calm.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * APP�Ĳ�����ز���
 * @author wuyue
 *
 */

public class AppInfoUtil {


	/**
	 * ���App�İ汾����
	 * @param context
	 * @return
	 */
	public static String getAppVersionName(Context context){
		String versionName = null;
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionName = info.versionName;
			if(versionName == null ||versionName.length()<=0){
				return "";
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * ȡ��App�İ汾��
	 * @param context
	 * @return
	 */
	public static int getAppVersionCode(Context context){
		int versionCode = 1;
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionCode;
	}

}
