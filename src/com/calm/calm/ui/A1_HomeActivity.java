package com.calm.calm.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.calm.calm.R;
import com.calm.calm.base.BaseFragmentActivity;
import com.calm.calm.chat.ChatActivity;
import com.calm.calm.chat.ChatActivityFour;
import com.calm.calm.chat.ChatActivityThree;
import com.calm.calm.chat.ChatActivityTwo;
import com.calm.calm.ui.adapter.SlidingMenu;

public class A1_HomeActivity extends BaseFragmentActivity implements OnClickListener{

	private SlidingMenu mMenu;
	private RelativeLayout ly_cbl;
	private RelativeLayout ly_one;
	private TextView one;
	private TextView two;
	private TextView three;
	private TextView four;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.home_activity);
		this.setToolBarLeftButtonByString(R.string.photo);
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
			break;
		case 2:
			fragment = new ChatActivityTwo();
			break;
		case 3:
			fragment = new ChatActivityThree();
			break;
		case 4:
			fragment = new ChatActivityFour();
			break;
		}

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.main_fragment, fragment);
		ft.commit();
		System.out.println("success");
	}

}
