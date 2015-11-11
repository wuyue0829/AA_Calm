package com.calm.calm.ui.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.calm.calm.R;
import com.calm.calm.base.BaseActivity;
import com.calm.calm.ui.A1_HomeActivity;
import com.calm.calm.util.BaseUtil;
import com.calm.calm.util.constant.ConfigConstant;

public class LoginActivity extends BaseActivity implements OnClickListener{

	private TextView et_phonenum;
	private TextView et_phonecode;
	private Button bt_sendcode;
	private Button bt_gonext;
	private String phoneNum;
	private String phoneCode;
	private String phone;
	int timeNum = 60;

	private ProgressDialog dialog; 
	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.login);
		init();
	}

	public void init(){
		et_phonenum = (TextView) findViewById(R.id.et_phonenum);
		et_phonecode = (TextView) findViewById(R.id.et_phonecode);
		bt_sendcode = (Button) findViewById(R.id.bt_sendcode);
		bt_gonext = (Button) findViewById(R.id.bt_gonext);

		bt_sendcode.setOnClickListener(this);
		bt_gonext.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_sendcode:
			checkPhone();
			break;
		case R.id.bt_gonext:
			if(isOkCode()){
				pd = ProgressDialog.show(mContext, null, "登录中。。。");
//				startActivity(new Intent(mContext, A1_HomeActivity.class));
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						bmobCode();
						
					}
				}).start();
			}
			break;
		default:
			break;
		}
	}

	public boolean checkPhone(){
		phoneNum = et_phonenum.getText().toString().trim();

		if(BaseUtil.isSpace(phoneNum)){
			Toast.makeText(mContext, "电话号码不能为空~~", 1).show();
			return false;
		}
		if(!isMobileNO(phoneNum)){
			Toast.makeText(mContext, "请输入有效的电话号码~~", 1).show();
			return false;
		}
		dialog = ProgressDialog.show(mContext, null, "获取验证码。。。");
		bt_sendcode.setEnabled(false);
		setTextSend();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				bmobSmsSend(phoneNum);
			}
		}){}.start();

		return true;
	}

	/**
	 * 设置发送验证码按键的倒计时
	 */
	private void setTextSend(){
		timeNum = timeNum - 1;
		if(timeNum==0){
			timeNum = 60;
			bt_sendcode.setEnabled(true);
			bt_sendcode.setText("获取验证码");
		}else{
			bt_sendcode.setEnabled(false);
			bt_sendcode.setText(timeNum+"秒重发");
			handler.sendEmptyMessageDelayed(20000, 1000);
		}
	}

	//正则表达式验证是否为手机号码
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	//使用bmob获取验证码
	public void bmobSmsSend(String phoneNum){

		BmobSMS.requestSMSCode(mContext, phoneNum, "AA助手",new RequestSMSCodeListener() {
			@Override
			public void done(Integer smsId,BmobException ex) {
				// TODO Auto-generated method stub
				if(ex==null){//验证码发送成功
					Message message =handler.obtainMessage();
					message.what = 10000;
					handler.sendMessage(message);
				}else{
					
				}
			}
		});
	}

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 10000:
				isSuc();
				Toast.makeText(mContext, "验证码已经发出，请注意查收~", 1).show();
				break;
			case 20000:
				setTextSend();
				break;
			default:
				break;
			}
		};
	};

	//关闭dialog
	public void isSuc(){
		if(dialog.isShowing()){
			dialog.dismiss(); 
		} 
	}

	//关闭dialog
	public void isSuc1(){
		if(pd.isShowing()){
			pd.dismiss(); 
		} 
	}

	//验证验证码是否正确
	public boolean isOkCode(){
		String phone = et_phonenum.getText().toString().trim();
		String phoneCode = et_phonecode.getText().toString().trim();
		if(BaseUtil.isSpace(phone)){
			Toast.makeText(mContext, "手机号码不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(BaseUtil.isSpace(phoneCode)){
			Toast.makeText(mContext, "验证码不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(phoneCode.length()!= 6){
			Toast.makeText(mContext, "验证码必须为6位", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	//连接bmob进行验证码确定
	public void bmobCode(){
		final String phone = et_phonenum.getText().toString().trim();
		String phoneCode = et_phonecode.getText().toString().trim();
		/*BmobSMS.verifySmsCode(this,phone, phoneCode, new VerifySMSCodeListener() {

			@Override
			public void done(BmobException ex) {
				// TODO Auto-generated method stub
				if(ex==null){//短信验证码已验证成功
					Message message = myhandler.obtainMessage();
					message.what = 30000;
					myhandler.sendMessage(message);
				}else{
					Message message = myhandler.obtainMessage();
					message.what = 40000;
					myhandler.sendMessage(message);
				}
			}
		});*/
		Message message = myhandler.obtainMessage();
		message.what = 30000;
		myhandler.sendMessage(message);
	}

	private Handler myhandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 30000:
				isSuc1();
				Toast.makeText(mContext, "登录完成", 1).show();
				Intent intent = new Intent(mContext, SetNameActivity.class);
				intent.putExtra("phoneNum", phone);
				sysConfig.setCustomConfig(ConfigConstant.CONFIG__LOGIN, "1");
				startActivity(intent);
				LoginActivity.this.finish();				
				break;
			case 40000:
				isSuc1();
				Toast.makeText(mContext, "您输入的验证码有误", 1).show();
				break;
			case 50000:
				
			default:
				break;
			}
		};
	};
}
