package com.calm.calm;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;
import cn.bmob.v3.Bmob;

import com.calm.calm.bll.BllUsrInfo;
import com.calm.calm.net.InitNet;
import com.calm.calm.ui.A1_HomeActivity;
import com.calm.calm.ui.dialog.CustomDialog;
import com.calm.calm.util.AppInfoUtil;
import com.calm.calm.util.SysConfig;
import com.calm.calm.util.constant.BaseConstant;

/**
 * Calm to do the first 
 * @author wuyue
 *
 */

public class MainActivity extends Activity {

	private static final int DONE =10000;
	private static final int WELCOME =20000;
	private static final int HAVENEW =30000;
	private static final int SLEEP = 3000;
	private static final int WEBVIEW = 4000;
	private CustomDialog dialog;
	private Context mContext ;
	private Handler handler = null;
	private SysConfig sysConfig;
	private BllUsrInfo bllUsrInfo = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, BaseConstant.BMOB_KEY);
		setContentView(R.layout.activity_main);
		mContext = this;
		sysConfig = new SysConfig(mContext);

		/*******************************************************************
		 * 消息机制
		 *******************************************************************/

		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case DONE:
					Toast.makeText(mContext, "进入界面", 1).show();
					Intent intent = new Intent(mContext, A1_HomeActivity.class);
					startActivity(intent);
					goNext();
					break;
				case WELCOME:
					Toast.makeText(mContext, "欢迎界面", 1).show();
					
					goNext();
					break;
				case HAVENEW:
					showDialog();
					break;
				case WEBVIEW:
					Uri uri = Uri.parse("http://www.baidu.com");
					Intent it = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(it);
					goNext();
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};

		new Thread(myRunnable).start();
	}

	//线程.......
	private Runnable myRunnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg  = handler.obtainMessage();
			try {
				//记录开始执行的时间
				long now1 = SystemClock.elapsedRealtime();  //当前时间1
				//从服务器获取最新版本的版本号，保存到shareperferce
				InitNet initNet = new InitNet(mContext);
				initNet.getVersionCode();
				//获取到的版本号与软件当前版本的版本号比较作出相应的发送信息
				int servion = sysConfig.getSerAppVesion();
				System.out.println(servion);
				if(servion > AppInfoUtil.getAppVersionCode(mContext)){
					msg.what = HAVENEW;
				}else{
					//作是不是第一次登陆的判断
					String isfirstlogin = sysConfig.getCustomConfig("isfirstlogin", "0");
					if(isfirstlogin.equals("0")){
						sysConfig.setCustomConfig("isfirstlogin", "1");
						msg.what = WELCOME;
					}else{
						msg.what = DONE;	
					}
				}
				//记录结束的时间
				long now2 = SystemClock.elapsedRealtime();  //当前时间2
				
				//将线程睡够需要在第一个界面显示的时间
				if(now2-now1 < SLEEP){
					Thread.sleep(SLEEP-(now2-now1));
				}
				
				//发送消息
				handler.sendMessage(msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	public void showDialog(){
		
		CustomDialog.Builder customBuilder = new
                CustomDialog.Builder(MainActivity.this);
            customBuilder.setTitle("提示")
                .setMessage("软件有新的版本哦~~~~快点来更新吧！")
                .setNegativeButton("取消", 
                		new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	dialog.dismiss();
                    	Message message = handler.obtainMessage();
                    	message.what = DONE;
                    }
                })
                .setPositiveButton("确定", 
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	dialog.dismiss();
                    	Message message = handler.obtainMessage();
                    	message.what = WEBVIEW;
                    	handler.sendMessage(message);
                    }
                });
            dialog = customBuilder.create();
            dialog.show();
	}
	private void goNext() {
		this.finish();
	}
}
