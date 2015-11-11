package com.calm.calm.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.calm.calm.R;
import com.calm.calm.ui.login.LoginActivity;
import com.calm.calm.util.SysConfig;

public class WelcomeActivity extends Activity{

	private View welcomePanel;
	private ViewPager welcomePager;
	private Context context;
	private SysConfig sysConfig;
	//欢迎界面图片
	private static int[] picResIDs = {
		R.drawable.page,
		R.drawable.page2,
		R.drawable.page3
		//此处放需要在滑动页看得见的图片
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		context = this;
		sysConfig = new SysConfig(context);
		init();
	}

	public void init(){
		welcomePanel = findViewById(R.id.wlecomePanel);
		welcomePager = (ViewPager) findViewById(R.id.welcomePager);
		
		FrameLayout.LayoutParams l_menulp = (FrameLayout.LayoutParams) welcomePager.getLayoutParams();
		l_menulp.height = (int) ((sysConfig.getScreenWidth()/1080f) * 1920f);
		welcomePager.setLayoutParams(l_menulp);

		welcomePager.setAdapter(new WelcomePagerAdaptar());
	}

	/**
	 * 开始写viewpage的适配器
	 */
	public class WelcomePagerAdaptar extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return picResIDs.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = productView(position);
			container.addView(view);
			return view;
		}
		//生成图片文字VIEW
		private View productView(int position){
			int imgId = picResIDs[position];
			ImageView picImageView = new ImageView(context);
			picImageView.setImageResource(imgId);
			picImageView.setScaleType(ScaleType.CENTER_INSIDE);
			if(position == picResIDs.length -1 ){
				picImageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//最后一个了跳转了
						startActivity(new Intent(context, LoginActivity.class));
						WelcomeActivity.this.finish();
					}
				});
			}
			return picImageView;
		}
	}
}
