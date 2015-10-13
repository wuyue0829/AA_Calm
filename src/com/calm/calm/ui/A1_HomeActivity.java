package com.calm.calm.ui;

import android.os.Bundle;
import android.view.View;

import com.calm.calm.R;
import com.calm.calm.base.BaseActivity;
import com.calm.calm.ui.adapter.SlidingMenu;

public class A1_HomeActivity extends BaseActivity{
	private SlidingMenu mMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.home_activity);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
	}
	
	public void toggleMenu(View view)
	{
		mMenu.toggle();
	}
}
