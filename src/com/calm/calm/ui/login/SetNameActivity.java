package com.calm.calm.ui.login;

import java.util.List;

import com.calm.calm.R;
import com.calm.calm.base.BaseActivity;
import com.calm.calm.bll.BllUsrPhone;
import com.calm.calm.entity.UserPhone;
import com.calm.calm.entity.Usr;
import com.calm.calm.net.LoginNet;
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
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

public class SetNameActivity extends BaseActivity{


	public static final int HAVEUSER = 1000;
	public static final int NOTHAVEUSER = 2000;
	public static final int GOSMS = 3000;
	public static final int NOTGOSMS = 4000;
	
	private String name;
	private String schoolNum;
	private String phoneNum;
	private EditText et_name;
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
				if(check()){
					new Thread(new  Runnable() {
						public void run() {
							//先开始查询数据库里学号和姓名匹配不
							isMate(name, schoolNum);
							//							sign(phoneNum, schoolNum);
						}
					}).start();
				}
			}
		});
	}

	public void init(){
		phoneNum = sysConfig.getCustomConfig(ConfigConstant.CONFIG__PHONE, "获取失败");
		et_name = (EditText) findViewById(R.id.et_name);
		et_schoolNum = (EditText) findViewById(R.id.et_schoolNum);
		bt_sign = (Button) findViewById(R.id.bt_sign);
	}

	public boolean check(){
		schoolNum = et_schoolNum.getText().toString().trim();
		name = et_name.getText().toString().trim();
		if(BaseUtil.isSpace(name)||BaseUtil.isSpace(schoolNum)){
			Toast.makeText(mContext, "请填写相应的数据", 1).show();
			return false;
		}
		if(schoolNum.length()!=14){
			System.out.println(schoolNum.length());
			Toast.makeText(mContext, "学号是多少啊，才毕业多久你就忘了？？？", 1).show();
			return false;
		}
		if(name.equals("陈宝迪")||name.equals("陈华军")||name.equals("董陶玲")||
				name.equals("杜明芳")||name.equals("范骄龙")||name.equals("方少言")||
				name.equals("冯晓晓")||name.equals("黄大路")||name.equals("焦平云")||
				name.equals("李文斌")||name.equals("李小强")||name.equals("刘春艳")||
				name.equals("苗旺")||name.equals("潘海伟")||name.equals("史淑娟")||
				name.equals("汪茹")||name.equals("王家祥")||name.equals("王稼宇")||
				name.equals("王灵娇")||name.equals("王维康")||name.equals("王贤锋")||
				name.equals("王亚杰")||name.equals("王艺森")||name.equals("吴悦")||
				name.equals("徐向荣")||name.equals("许凤珍")||name.equals("薛鑫")||
				name.equals("杨大鑫")||name.equals("杨鸿儒")||name.equals("杨业")||
				name.equals("叶铿")||name.equals("应顺")||name.equals("詹春霞")||
				name.equals("张宝华")||name.equals("张国斌")||name.equals("张彭飞")||
				name.equals("张潇")||name.equals("郑晶森")){
			pd = ProgressDialog.show(mContext, null, "获取数据中......");
			return true;
		}
		Toast.makeText(mContext, "我们111没有你这个人！滚！", 1).show();
		return false;
	}
	
	public void sign(String phoneNum,String schoolNum){
		BmobUser buUser = new BmobUser();
		buUser.setUsername(name);
		buUser.setPassword(schoolNum);
		buUser.signUp(mContext, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
//				insert();
				Message message = handler.obtainMessage();
				message.what = GOSMS;
				handler.sendMessage(message);
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}
		});
	}

//	public void insert(){
//		UserPhone phone = new UserPhone();
//		phone.setName(name);
//		phone.setPhone(phoneNum);
//		phone.setTag("111");;
//
//		phone.save(mContext, new SaveListener() {
//
//			@Override
//			public void onSuccess() {
//				// TODO Auto-generated method stub
//				initPhone();
//			}
//
//			@Override
//			public void onFailure(int arg0, String arg1) {
//				// TODO Auto-generated method stub
//			}
//		});
//
//	}

//	public void initPhone(){
//		BmobQuery<UserPhone> query	 = new BmobQuery<UserPhone>();
//		query.addWhereEqualTo("tag", "111");
//		query.setLimit(50);
//		query.findObjects(mContext, new FindListener<UserPhone>() {
//
//			@Override
//			public void onSuccess(List<UserPhone> arg0) {
//				// TODO Auto-generated method stub
//				savePhone(arg0);
//			}
//
//			@Override
//			public void onError(int arg0, String arg1) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//	}

	public void savePhone(List<UserPhone> arg0){
		bllUsrPhone.saveUserPhone(arg0);
		Message message = myhandler.obtainMessage();
		message.what = 30000;
		myhandler.sendMessage(message);
	}

	/**
	 * 检测学号和姓名是否匹配
	 */
	public void isMate(String name,String shcoolNum){
		LoginNet net = new LoginNet();
		net.isMate(name, shcoolNum, mContext, sysConfig);
		if(sysConfig.getCustomConfig(ConfigConstant.CONFIG_SCHOOL_NAME,"0").equals("0")){
			//数据库中没有找到
			Message message = handler.obtainMessage();
			message.what = HAVEUSER;
			handler.sendMessage(message);
		}else{
			//数据库中找到了
			Message message = handler.obtainMessage();
			message.what = NOTHAVEUSER;
			handler.sendMessage(message);
		}
	}

	/**
	 * 检测完学号之后使用的hander
	 */
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HAVEUSER:
				new Thread(new Runnable() {
					public void run() {
						sign(name, schoolNum);
					}
				}).start();
				break;

			case NOTHAVEUSER:
				Toast.makeText(mContext, "自己的学号，自己都不知道？还是想找事啊？", 1).show();
				break;
				
			case GOSMS:
				
				break;
			case NOTGOSMS:
				Toast.makeText(mContext, "小悦悦的服务器出了点问题，要不各位赏个钱让他去买个好的呗？", 1).show();
				break;
			default:
				break;
			}
		};
	};
}
