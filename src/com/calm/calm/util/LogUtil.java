package com.calm.calm.util;

import android.util.Log;
/**
 * 统一显示log的方法
 * @author jijun
 *
 */
public class LogUtil {
	public static final boolean DEBUG = false;//开启debug
	
	public static void e(String tag,String msg) {
		if(DEBUG){
			Log.e(tag, msg);
		}
	}
	public static void d(String tag,String msg) {
		if(DEBUG){
			Log.d(tag, msg);
		}
	}
	public static void i(String tag,String msg) {
		if(DEBUG){
			Log.i(tag, msg);
		}
	}
	public static void w(String tag,String msg) {
		if(DEBUG){
			Log.w(tag, msg);
		}
	}
	public static void v(String tag,String msg) {
		if(DEBUG){
			Log.v(tag, msg);
		}
	}
	
	public static void e(String msg) {
		if(DEBUG){
			Log.e("LOG打印","="+msg);
		}
	}
	
	/**
	 * 没有必要请勿用
	 * @param tag
	 * @param msg
	 */
	public static void destroy(String tag,String msg) {
		if(DEBUG){
			Log.wtf(tag, msg);
		}
	}
	
}
