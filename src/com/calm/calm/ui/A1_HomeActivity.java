package com.calm.calm.ui;

import java.util.List;

import com.calm.calm.R;
import com.calm.calm.base.BaseFragmentActivity;
import com.calm.calm.bll.BllUsrPhone;
import com.calm.calm.entity.UserPhone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class A1_HomeActivity extends BaseFragmentActivity implements OnClickListener{

	/*private SlidingMenu mMenu;
	private RelativeLayout ly_cbl;
	private RelativeLayout ly_one;
	private TextView one;
	private TextView two;
	private TextView three;
	private TextView four;
	private int flag = 1;
	private BllUsrInfo bllUsrInfo;*/
	
	private ListView ly_phoneNum;
	private BllUsrPhone bllUserPhone = null;
	private int resoirce;
	private List<UserPhone> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.home_activity_new);
		this.setTitle("Forever 111");
//		this.setToolBarLeftButtonByString0(R.string.photo);
		init();
//		changeFragment(1);
	}

	public void init(){
		bllUserPhone = new BllUsrPhone(mContext);
		list = bllUserPhone.getAll();
		ly_phoneNum = (ListView) findViewById(R.id.lv_phoneNum);
		ly_phoneNum.setAdapter(new MyAdapter(mContext, R.layout.list_item, list));
		ly_phoneNum.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				UserPhone phone = list.get(position);
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone.getPhone()));  
                startActivity(intent); 
			}
		});
		
		/*ly_cbl = (RelativeLayout) findViewById(R.id.ly_cbl);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		one = (TextView) findViewById(R.id.tv_one);
		two = (TextView) findViewById(R.id.tv_two);
		three = (TextView) findViewById(R.id.tv_three);
		four = (TextView) findViewById(R.id.tv_four);
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		bllUsrInfo = new BllUsrInfo(mContext);
		UsrInfo info = new UsrInfo();
		info.setSex("nan");
		info.setMobile("18516761618");
		bllUsrInfo.saveUsrInfo(info);*/
		
		ly_phoneNum = (ListView) findViewById(R.id.lv_phoneNum);
	}

	
	class MyAdapter extends ArrayAdapter<UserPhone>{

		public MyAdapter(Context context, int resource, List<UserPhone> objects) {
			super(context, resource, objects);
			// TODO Auto-generated constructor stub
			resoirce = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			UserPhone userPhone = getItem(position);
			View view ;
			viewHolded holded ;
			if(convertView == null){
				view = LayoutInflater.from(getApplicationContext()).inflate(resoirce,null);
				holded = new viewHolded();
				holded.name = (TextView) view.findViewById(R.id.tv_name);
				view.setTag(holded);
			}else{
				view = convertView;
				holded = (viewHolded) view.getTag();
			}
			holded.name.setText(userPhone.getName());
			return view;
		}
		
	}
	
	class viewHolded{
		TextView name;
	}
	
	
	@Override
	public void leftButtonClick() {
		// TODO Auto-generated method stub
		/*if(flag == 1){
			this.setToolBarLeftButtonByString0(R.string.base_return);
			flag = 0;
		}else{
			this.setToolBarLeftButtonByString0(R.string.photo);
			flag = 1;
		}
		mMenu.toggle();*/
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		/*case R.id.tv_one:
			Toast.makeText(mContext, "1", 1).show();
			changeFragment(1);
			break;
		case R.id.tv_two:
			Toast.makeText(mContext, "2", 1).show();
			changeFragment(2);
			break;
		case R.id.tv_three:
			Toast.makeText(mContext, "3", 1).show();
			changeFragment(3);
			break;
		case R.id.tv_four:
			Toast.makeText(mContext, "4", 1).show();
			changeFragment(4);
			break;*/
		default:
			break;
		}
	}
	
	/*private void changeFragment(int tabId) {
		Fragment fragment = null;
		switch (tabId) {
		case 1:
			fragment = new ChatActivity();
			this.setTitle("聊天");
			break;
		case 2:
			fragment = new ChatActivityTwo();
			this.setTitle("联系人");
			break;
		case 3:
			fragment = new ChatActivityThree();
			this.setTitle("朋友圈");
			break;
		case 4:
			fragment = new ChatActivityFour();
			this.setTitle("ok");
			break;
		}

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.main_fragment, fragment);
		ft.commit();
		System.out.println("success");
	}
*/
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
