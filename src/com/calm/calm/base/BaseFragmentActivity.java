package com.calm.calm.base;

import com.calm.calm.R;
import com.calm.calm.util.SysConfig;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseFragmentActivity extends FragmentActivity{
	
	protected Context mContext;
	protected LayoutInflater mInflater;
	protected FrameLayout mainLayout;
	protected LinearLayout l_left,l_left2;
	protected Button b_left;
	protected TextView tv_left;
	protected ImageButton b_left2;
	protected LinearLayout l_right,l_right2;
	protected Button b_right;
	protected Button b_right3;
	protected TextView tv_right;
	protected ImageButton b_right2;
	
	protected TextView titleText;
	protected SysConfig sysConfig;
	
	/**
	 * 初始化imagelode的相关设置
	 */
	//图片的异步加载框架,具体下载图片，缓存图片，显示图片的具体执行类
	public ImageLoader imageLoader = ImageLoader.getInstance();
	//用于指导每一个Imageloader根据网络图片的状态（空白、下载错误、正在下载）显示对应的图片，
	//是否将缓存加载到磁盘上，下载完后对图片进行怎么样的处理。
	public DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_expand)//图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.ic_expand)//图片下载时uri为空的时候显示的图片
		.considerExifParams(true)////是否考虑JPEG图像EXIF参数（旋转，翻转）
		.bitmapConfig(Bitmap.Config.RGB_565)//16 R占5位 G占6位 B占5位 没有透明度（A）
		.imageScaleType(ImageScaleType.EXACTLY)//将图片边界缩放，以适应视图边界时的可选项。
		.showImageOnFail(R.drawable.ic_expand)//设置图片加载或者转码错误时现实的图片
		.cacheInMemory(true)//下载的图片是否缓存在内存中
		.cacheOnDisc(true)//下载的图片是否缓存在SD卡中
		.build();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity);
		sysConfig = sysConfig.getConfig(this);
		mContext = this;
		mInflater = LayoutInflater.from(mContext);
		mainLayout = (FrameLayout) findViewById(R.id.main_layout);
		l_left = (LinearLayout) findViewById(R.id.l_left);
		l_left2 = (LinearLayout) findViewById(R.id.l_left2);
		b_left = (Button) findViewById(R.id.b_left);
		tv_left = (TextView) findViewById(R.id.tv_left);
		b_left2 = (ImageButton) findViewById(R.id.b_left2);
		titleText = (TextView) findViewById(R.id.tv_head_title);
		l_right = (LinearLayout) findViewById(R.id.l_right);
		l_right2 = (LinearLayout) findViewById(R.id.l_right2);
		b_right = (Button) findViewById(R.id.b_right);
		b_right3 = (Button) findViewById(R.id.b_right3);
		tv_right = (TextView) findViewById(R.id.tv_right);
		b_right2 = (ImageButton) findViewById(R.id.b_right2);
		
		
		l_left.setVisibility(View.GONE);
		l_left2.setVisibility(View.GONE);
		b_left.setVisibility(View.GONE);
		tv_left.setVisibility(View.GONE);
		b_left2.setVisibility(View.GONE);
		l_right.setVisibility(View.GONE);
		l_right2.setVisibility(View.GONE);
		b_right.setVisibility(View.GONE);
		b_right3.setVisibility(View.GONE);
		tv_right.setVisibility(View.GONE);
		b_right2.setVisibility(View.GONE);
		
		l_left.setOnClickListener(btnClickLeft);
		b_left.setOnClickListener(btnClickLeft);
		tv_left.setOnClickListener(btnClickLeft);
		b_left2.setOnClickListener(btnClickLeft);
		l_left2.setOnClickListener(btnClickLeft);
		
		l_right.setOnClickListener(btnClickRight);
		b_right.setOnClickListener(btnClickRight);
		b_right3.setOnClickListener(btnClickRight);
		tv_right.setOnClickListener(btnClickRight);
		b_right2.setOnClickListener(btnClickRight);
		l_right2.setOnClickListener(btnClickRight);
	}
	
	//按钮点击
		private View.OnClickListener btnClickLeft = new OnClickListener() {  
			
			@Override
			public void onClick(View v) {
					leftButtonClick();
			}
		};
		
		 //按钮点击
		private View.OnClickListener btnClickRight = new OnClickListener() {  
			
			@Override
			public void onClick(View v) {
					rightButtonClick();
			}
		};
		
		/**
		 * 添加自己的布局
		 * 布局文件ID
		 */
		public View setContentView2Base(int layoutID){
			View contentView = mInflater.inflate(layoutID, null);
			mainLayout.addView(contentView);
			return contentView;
		}
		/**
		 * 添加自己的布局，
		 * 此处直接是个view
		 * @param contentView
		 */
		public void setContentView2Base(View contentView){
			mainLayout.addView(contentView);
		}
		
		/**
		 * 设置自己布局的填充色
		 * @param color
		 */
		public void setBackgroundColor(int color){
			mainLayout.setBackgroundColor(color);
		}
		
		/**
		 * 设置背景遮罩
		 * @param layoutID 布局文件ID
		 */
		public void setBackGroundDrawable(int drawableID){
			mainLayout.setBackgroundResource(drawableID);
		}
		
		/**
		 * 设置背景遮罩
		 * @param layoutID 布局文件
		 */
		public void setBackGroundDrawable(Drawable d){
			mainLayout.setBackgroundDrawable(d);
		}
		
		/*** 设置标题
		 * @param title 
		 */
		public void setTitle(String title){
			titleText.setText(title);
		}
		
		/***  设置标题
		 * @param stringID 资源id
		 * */
		@Override
		public void setTitle(int stringID){
			titleText.setText(stringID);
		}
		/**
		 * 设置左右侧按钮
		 * @param strID
		 */
		//左图标 右文字
		public void setToolBarLeftButtonByString(int strID) {
			setToolBarButtonString(strID, 0);
		}
		public void setToolBarRightButtonByString(int strID) {
			setToolBarButtonString(strID, 1);
		}
		//只有文字
		public void setToolBarLeftButtonByString0(int strID) {
			setToolBarButtonString(strID, 5);
		}
		public void setToolBarRightButtonByString0(int strID) {
			setToolBarButtonString(strID, 6);
		}
		//左文字 右图标
		public void setToolBarLeftButtonByString3(int strID) {
			setToolBarButtonString(strID, 3);
		}
		public void setToolBarRightButtonByString3(int strID) {
			setToolBarButtonString(strID, 4);
		}
		public void setToolBarLeftButton(int strID) {
			setToolBarButton(strID, 0);
		}
		public void setToolBarRightButton(int strID) {
			setToolBarButton(strID, 1);
		}
		
		
		public void setToolBarRightButton2(int drawableID) {
			l_right2.setVisibility(View.VISIBLE);
			b_right2.setVisibility(View.VISIBLE);
			b_right2.setImageResource(drawableID);
		}
		public void setToolBarLeftButton2(int drawableID) {
			b_left2.setVisibility(View.VISIBLE);
			l_left2.setVisibility(View.VISIBLE);
			b_left2.setImageResource(drawableID);
		}
		//左图标
		public void setToolBarLeftButton2() {
			b_left2.setVisibility(View.VISIBLE);
			l_left2.setVisibility(View.VISIBLE);
		}
		public void setToolBarRightButton1(int drawableID) {
			b_right.setBackgroundResource(drawableID);
		}
		public void setToolBarLeftButton1(int drawableID) {
			b_left.setBackgroundResource(drawableID);
		}
		
		/**
		 * 设置第一个右边按钮的背景
		 * @param color
		 */
		public void setToolBarRightButtonBgColor(int drawableID) {
			b_right2.setVisibility(View.GONE);
			l_right.setVisibility(View.VISIBLE);
			l_right.setBackgroundResource(drawableID);
		}
		
		
		private void setToolBarButtonString(int strID, int index) {
			switch (index) {
				case 0:
					// 左侧第一个按钮
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.VISIBLE);
					tv_left.setVisibility(View.VISIBLE);
					tv_left.setText(strID);
					break;
				case 1:
					// 右侧第一个按钮
					l_right.setVisibility(View.VISIBLE);
					b_right.setVisibility(View.VISIBLE);
					tv_right.setVisibility(View.VISIBLE);
					tv_right.setText(strID);
					break;
				case 3:
					// 左侧第一个按钮
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.VISIBLE);
					tv_left.setVisibility(View.VISIBLE);
					tv_left.setText(strID);
					break;
				case 4:
					// 右侧第一个按钮
					l_right.setVisibility(View.VISIBLE);
					b_right3.setVisibility(View.VISIBLE);
					tv_right.setVisibility(View.VISIBLE);
					tv_right.setText(strID);
					break;
				case 5:
					// 左侧第一个按钮
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.GONE);
					tv_left.setVisibility(View.VISIBLE);
					tv_left.setText(strID);
					break;
				case 6:
					// 右侧第一个按钮
					l_right.setVisibility(View.VISIBLE);
					b_right.setVisibility(View.GONE);
					tv_right.setVisibility(View.VISIBLE);
					tv_right.setText(strID);
					break;
				default:
					break;
			}
		}
		private void setToolBarButton(int strID, int index) {
			switch (index) {
				case 0:
					// 左侧第一个按钮
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.VISIBLE);
					b_left.setBackgroundResource(strID);
					break;
				case 1:
					// 右侧第一个按钮
					l_right.setVisibility(View.VISIBLE);
					b_right.setVisibility(View.VISIBLE);
					b_right.setBackgroundResource(strID);
					break;
				default:
					break;
			}
		}
		public void setToolBarLeftButtonGone() {
			l_left.setVisibility(View.GONE);
			
		}
		public void setToolBarRightButtonGone() {
			l_right.setVisibility(View.GONE);
		}
		/**
		 * 去掉右边箭头
		 */
		public void setToolBarRightArrowGone(){
			b_right.setVisibility(View.GONE);
		}
		
		
		/**返回按钮点击,通常为关闭该窗体**/
		public void leftButtonClick(){
			this.finish();
		}
		
		/**确定按钮点击**/
		public void rightButtonClick(){
			//do nothing......
		}
		
		//隐藏标题栏
		protected void hideHeadBar(){
			findViewById(R.id.head_bar).setVisibility(View.GONE);
		}
		//显示标题栏
		protected void showHeadBar(){
			findViewById(R.id.head_bar).setVisibility(View.VISIBLE);
		}
		
		protected void onResume() {
			super.onResume();
		}
		
		protected void onPause() {
			super.onPause();
		}
		/**
		 * 页面跳转
		 */
		protected void close(){
			this.finish();
		}
		
}
