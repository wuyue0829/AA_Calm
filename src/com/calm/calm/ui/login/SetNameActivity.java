package com.calm.calm.ui.login;

import java.util.List;

import com.calm.calm.R;
import com.calm.calm.base.BaseActivity;
import com.calm.calm.bll.BllUsrPhone;
import com.calm.calm.entity.UserPhone;
import com.calm.calm.ui.A1_HomeActivity;
import com.calm.calm.util.BaseUtil;
import com.calm.calm.util.constant.ConfigConstant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class SetNameActivity extends BaseActivity{

	private String name;
	private String password;
	private String schoolNum;
	private String phoneNum;
	private EditText et_name;
	private EditText et_password;
	private EditText et_schoolNum;
	private Button bt_sign;

	private ProgressDialog pd;
	private BllUsrPhone bllUsrPhone = null;

	private Handler myhandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 30000:
				if(pd.isShowing()){
					pd.dismiss();
				}
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
		this.setTitle(R.string.set_password);
		init();
		bt_sign.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pd = ProgressDialog.show(mContext, null, "获取数据中......");
				if(check()){
					new Thread(new  Runnable() {
						public void run() {
							sign(phoneNum, password);

						}
					}).start();
				}
			}
		});
	}

	public void init(){
		phoneNum = sysConfig.getCustomConfig(ConfigConstant.CONFIG__PHONE, "获取失败");
		System.out.println(phoneNum);
		et_name = (EditText) findViewById(R.id.et_name);
		et_password = (EditText) findViewById(R.id.et_password);
		et_schoolNum = (EditText) findViewById(R.id.et_schoolNum);
		bt_sign = (Button) findViewById(R.id.bt_sign);
		bllUsrPhone = new BllUsrPhone(mContext);
	}

	public boolean check(){
		schoolNum = et_schoolNum.getText().toString().trim();
		name = et_name.getText().toString().trim();
		password = et_password.getText().toString().trim();
		if(BaseUtil.isSpace(name)||BaseUtil.isSpace(password)||BaseUtil.isSpace(schoolNum)){
			Toast.makeText(mContext, "请填写相应的数据", 1).show();
			return false;
		}
		if(schoolNum.length()!=14){
			System.out.println(schoolNum.length());
			Toast.makeText(mContext, "学号是14位啊，才毕业多久你就忘了？？？", 1).show();
			return false;
		}
		if(!name.equals("陈宝迪")){
			Toast.makeText(mContext, "我们111没有你这个人！滚！", 1).show();
			return false;
		}
		return true;
	}
	public void sign(String phoneNum,String password){
		BmobUser buUser = new BmobUser();
		buUser.setUsername(name);
		buUser.setPassword(password);
		buUser.setMobilePhoneNumber(phoneNum);
		//		buUser.setEmail(schoolNum);
		buUser.signUp(mContext, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				insert();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void insert(){
		UserPhone phone = new UserPhone();
		phone.setName(name);
		phone.setPhone(phoneNum);
		phone.setTag("111");;

		phone.save(mContext, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				initPhone();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}
		});

	}

	public void initPhone(){
		BmobQuery<UserPhone> query	 = new BmobQuery<UserPhone>();
		query.addWhereEqualTo("tag", "111");
		query.setLimit(50);
		query.findObjects(mContext, new FindListener<UserPhone>() {
			
			@Override
			public void onSuccess(List<UserPhone> arg0) {
				// TODO Auto-generated method stub
				savePhone(arg0);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void savePhone(List<UserPhone> arg0){
		bllUsrPhone.saveUserPhone(arg0);
		Message message = myhandler.obtainMessage();
		message.what = 30000;
		myhandler.sendMessage(message);
	}
}
