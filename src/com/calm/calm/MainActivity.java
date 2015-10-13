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
		 * ��Ϣ����
		 *******************************************************************/

		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case DONE:
					Toast.makeText(mContext, "�������", 1).show();
					Intent intent = new Intent(mContext, A1_HomeActivity.class);
					startActivity(intent);
					goNext();
					break;
				case WELCOME:
					Toast.makeText(mContext, "��ӭ����", 1).show();
					
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

	//�߳�.......
	private Runnable myRunnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg  = handler.obtainMessage();
			try {
				//��¼��ʼִ�е�ʱ��
				long now1 = SystemClock.elapsedRealtime();  //��ǰʱ��1
				//�ӷ�������ȡ���°汾�İ汾�ţ����浽shareperferce
				InitNet initNet = new InitNet(mContext);
				initNet.getVersionCode();
				//��ȡ���İ汾���������ǰ�汾�İ汾�űȽ�������Ӧ�ķ�����Ϣ
				int servion = sysConfig.getSerAppVesion();
				System.out.println(servion);
				if(servion > AppInfoUtil.getAppVersionCode(mContext)){
					msg.what = HAVENEW;
				}else{
					//���ǲ��ǵ�һ�ε�½���ж�
					String isfirstlogin = sysConfig.getCustomConfig("isfirstlogin", "0");
					if(isfirstlogin.equals("0")){
						sysConfig.setCustomConfig("isfirstlogin", "1");
						msg.what = WELCOME;
					}else{
						msg.what = DONE;	
					}
				}
				//��¼������ʱ��
				long now2 = SystemClock.elapsedRealtime();  //��ǰʱ��2
				
				//���߳�˯����Ҫ�ڵ�һ��������ʾ��ʱ��
				if(now2-now1 < SLEEP){
					Thread.sleep(SLEEP-(now2-now1));
				}
				
				//������Ϣ
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
            customBuilder.setTitle("��ʾ")
                .setMessage("������µİ汾Ŷ~~~~��������°ɣ�")
                .setNegativeButton("ȡ��", 
                		new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	dialog.dismiss();
                    	Message message = handler.obtainMessage();
                    	message.what = DONE;
                    }
                })
                .setPositiveButton("ȷ��", 
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
