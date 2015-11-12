package com.calm.calm.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.calm.calm.R;
import com.calm.calm.base.BaseFragmentActivity;
import com.calm.calm.ui.adapter.SlidingMenu;
import com.calm.calm.ui.chat.ChatActivity;
import com.calm.calm.ui.chat.ChatActivityFour;
import com.calm.calm.ui.chat.ChatActivityThree;
import com.calm.calm.ui.chat.ChatActivityTwo;

public class A1_HomeActivity extends BaseFragmentActivity implements OnClickListener{

	private SlidingMenu mMenu;
	private RelativeLayout ly_cbl;
	private RelativeLayout ly_one;
	private TextView one;
	private TextView two;
	private TextView three;
	private TextView four;
	private int flag = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.home_activity);
		this.setToolBarLeftButtonByString0(R.string.photo);
		init();
		changeFragment(1);
	}

	public void init(){
		ly_cbl = (RelativeLayout) findViewById(R.id.ly_cbl);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		one = (TextView) findViewById(R.id.tv_one);
		two = (TextView) findViewById(R.id.tv_two);
		three = (TextView) findViewById(R.id.tv_three);
		four = (TextView) findViewById(R.id.tv_four);
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
	}

	@Override
	public void leftButtonClick() {
		// TODO Auto-generated method stub
		if(flag == 1){
			this.setToolBarLeftButtonByString0(R.string.base_return);
			flag = 0;
		}else{
			this.setToolBarLeftButtonByString0(R.string.photo);
			flag = 1;
		}
		mMenu.toggle();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_one:
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
			break;
		default:
			break;
		}
	}
	
	private void changeFragment(int tabId) {
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

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
