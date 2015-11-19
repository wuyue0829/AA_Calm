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
							//�ȿ�ʼ��ѯ���ݿ���ѧ�ź�����ƥ�䲻
							isMate(name, schoolNum);
							//							sign(phoneNum, schoolNum);
						}
					}).start();
				}
			}
		});
	}

	public void init(){
		phoneNum = sysConfig.getCustomConfig(ConfigConstant.CONFIG__PHONE, "��ȡʧ��");
		et_name = (EditText) findViewById(R.id.et_name);
		et_schoolNum = (EditText) findViewById(R.id.et_schoolNum);
		bt_sign = (Button) findViewById(R.id.bt_sign);
	}

	public boolean check(){
		schoolNum = et_schoolNum.getText().toString().trim();
		name = et_name.getText().toString().trim();
		if(BaseUtil.isSpace(name)||BaseUtil.isSpace(schoolNum)){
			Toast.makeText(mContext, "����д��Ӧ������", 1).show();
			return false;
		}
		if(schoolNum.length()!=14){
			System.out.println(schoolNum.length());
			Toast.makeText(mContext, "ѧ���Ƕ��ٰ����ű�ҵ���������ˣ�����", 1).show();
			return false;
		}
		if(name.equals("�±���")||name.equals("�»���")||name.equals("������")||
				name.equals("������")||name.equals("������")||name.equals("������")||
				name.equals("������")||name.equals("�ƴ�·")||name.equals("��ƽ��")||
				name.equals("���ı�")||name.equals("��Сǿ")||name.equals("������")||
				name.equals("����")||name.equals("�˺�ΰ")||name.equals("ʷ���")||
				name.equals("����")||name.equals("������")||name.equals("������")||
				name.equals("���齿")||name.equals("��ά��")||name.equals("���ͷ�")||
				name.equals("���ǽ�")||name.equals("����ɭ")||name.equals("����")||
				name.equals("������")||name.equals("�����")||name.equals("Ѧ��")||
				name.equals("�����")||name.equals("�����")||name.equals("��ҵ")||
				name.equals("Ҷ�")||name.equals("Ӧ˳")||name.equals("ղ��ϼ")||
				name.equals("�ű���")||name.equals("�Ź���")||name.equals("�����")||
				name.equals("����")||name.equals("֣��ɭ")){
			pd = ProgressDialog.show(mContext, null, "��ȡ������......");
			return true;
		}
		Toast.makeText(mContext, "����111û��������ˣ�����", 1).show();
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
	 * ���ѧ�ź������Ƿ�ƥ��
	 */
	public void isMate(String name,String shcoolNum){
		LoginNet net = new LoginNet();
		net.isMate(name, shcoolNum, mContext, sysConfig);
		if(sysConfig.getCustomConfig(ConfigConstant.CONFIG_SCHOOL_NAME,"0").equals("0")){
			//���ݿ���û���ҵ�
			Message message = handler.obtainMessage();
			message.what = HAVEUSER;
			handler.sendMessage(message);
		}else{
			//���ݿ����ҵ���
			Message message = handler.obtainMessage();
			message.what = NOTHAVEUSER;
			handler.sendMessage(message);
		}
	}

	/**
	 * �����ѧ��֮��ʹ�õ�hander
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
				Toast.makeText(mContext, "�Լ���ѧ�ţ��Լ�����֪�������������°���", 1).show();
				break;
				
			case GOSMS:
				
				break;
			case NOTGOSMS:
				Toast.makeText(mContext, "С���õķ��������˵����⣬Ҫ����λ�͸�Ǯ����ȥ����õ��£�", 1).show();
				break;
			default:
				break;
			}
		};
	};
}
