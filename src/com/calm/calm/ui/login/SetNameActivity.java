package com.calm.calm.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.calm.calm.R;
import com.calm.calm.base.BaseActivity;
import com.calm.calm.ui.A1_HomeActivity;
import com.calm.calm.util.LogUtil;

public class SetNameActivity extends BaseActivity{
	
	public String phoneNum;
	public EditText et_password;
	public EditText et_password_argin;
	private String passWord;
	private String passWordArgin;
	private Handler myhandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 30000:
				startActivity(new Intent(mContext, A1_HomeActivity.class));
				close();
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.login_set_password);
		this.setToolBarRightButtonByString(R.string.head_submit);
		this.setTitle(R.string.set_password);
		init();
	}
	
	public void init(){
		Intent intent  = getIntent();
		phoneNum = intent.getStringExtra("phoneNum");
		et_password = (EditText) findViewById(R.id.et_password);
		et_password_argin = (EditText) findViewById(R.id.et_password_argin);
	}
	
	@Override
	public void rightButtonClick() {
		// TODO Auto-generated method stub
		super.rightButtonClick();
		if(check()){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sign(phoneNum,passWord);
				}
			}).start();
		}
	}
	
	public boolean check(){
		passWord = et_password.getText().toString().trim();
		passWordArgin = et_password_argin.getText().toString().trim();
		if(passWord.equals(passWordArgin)){
			LogUtil.e("true");
			return true;
		}else{
			LogUtil.e("false");
			return false;
		}
	}
	
	public void sign(String phoneNum,String password){
		BmobUser buUser = new BmobUser();
		buUser.setUsername(phoneNum);
		buUser.setPassword(password);
		buUser.setMobilePhoneNumber(phoneNum);
		/*buUser.signUp(mContext, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Message message = myhandler.obtainMessage();
				message.what = 30000;
				myhandler.sendMessage(message);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});*/
		Message message = myhandler.obtainMessage();
		message.what = 30000;
		myhandler.sendMessage(message);
	}
}
