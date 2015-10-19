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
	 * ��ʼ��imagelode���������
	 */
	//ͼƬ���첽���ؿ��,��������ͼƬ������ͼƬ����ʾͼƬ�ľ���ִ����
	public ImageLoader imageLoader = ImageLoader.getInstance();
	//����ָ��ÿһ��Imageloader��������ͼƬ��״̬���հס����ش����������أ���ʾ��Ӧ��ͼƬ��
	//�Ƿ񽫻�����ص������ϣ���������ͼƬ������ô���Ĵ���
	public DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_expand)//ͼƬ�����ڼ���ʾ��ͼƬ
		.showImageForEmptyUri(R.drawable.ic_expand)//ͼƬ����ʱuriΪ�յ�ʱ����ʾ��ͼƬ
		.considerExifParams(true)////�Ƿ���JPEGͼ��EXIF��������ת����ת��
		.bitmapConfig(Bitmap.Config.RGB_565)//16 Rռ5λ Gռ6λ Bռ5λ û��͸���ȣ�A��
		.imageScaleType(ImageScaleType.EXACTLY)//��ͼƬ�߽����ţ�����Ӧ��ͼ�߽�ʱ�Ŀ�ѡ�
		.showImageOnFail(R.drawable.ic_expand)//����ͼƬ���ػ���ת�����ʱ��ʵ��ͼƬ
		.cacheInMemory(true)//���ص�ͼƬ�Ƿ񻺴����ڴ���
		.cacheOnDisc(true)//���ص�ͼƬ�Ƿ񻺴���SD����
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
	
	//��ť���
		private View.OnClickListener btnClickLeft = new OnClickListener() {  
			
			@Override
			public void onClick(View v) {
					leftButtonClick();
			}
		};
		
		 //��ť���
		private View.OnClickListener btnClickRight = new OnClickListener() {  
			
			@Override
			public void onClick(View v) {
					rightButtonClick();
			}
		};
		
		/**
		 * ����Լ��Ĳ���
		 * �����ļ�ID
		 */
		public View setContentView2Base(int layoutID){
			View contentView = mInflater.inflate(layoutID, null);
			mainLayout.addView(contentView);
			return contentView;
		}
		/**
		 * ����Լ��Ĳ��֣�
		 * �˴�ֱ���Ǹ�view
		 * @param contentView
		 */
		public void setContentView2Base(View contentView){
			mainLayout.addView(contentView);
		}
		
		/**
		 * �����Լ����ֵ����ɫ
		 * @param color
		 */
		public void setBackgroundColor(int color){
			mainLayout.setBackgroundColor(color);
		}
		
		/**
		 * ���ñ�������
		 * @param layoutID �����ļ�ID
		 */
		public void setBackGroundDrawable(int drawableID){
			mainLayout.setBackgroundResource(drawableID);
		}
		
		/**
		 * ���ñ�������
		 * @param layoutID �����ļ�
		 */
		public void setBackGroundDrawable(Drawable d){
			mainLayout.setBackgroundDrawable(d);
		}
		
		/*** ���ñ���
		 * @param title 
		 */
		public void setTitle(String title){
			titleText.setText(title);
		}
		
		/***  ���ñ���
		 * @param stringID ��Դid
		 * */
		@Override
		public void setTitle(int stringID){
			titleText.setText(stringID);
		}
		/**
		 * �������Ҳఴť
		 * @param strID
		 */
		//��ͼ�� ������
		public void setToolBarLeftButtonByString(int strID) {
			setToolBarButtonString(strID, 0);
		}
		public void setToolBarRightButtonByString(int strID) {
			setToolBarButtonString(strID, 1);
		}
		//ֻ������
		public void setToolBarLeftButtonByString0(int strID) {
			setToolBarButtonString(strID, 5);
		}
		public void setToolBarRightButtonByString0(int strID) {
			setToolBarButtonString(strID, 6);
		}
		//������ ��ͼ��
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
		//��ͼ��
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
		 * ���õ�һ���ұ߰�ť�ı���
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
					// ����һ����ť
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.VISIBLE);
					tv_left.setVisibility(View.VISIBLE);
					tv_left.setText(strID);
					break;
				case 1:
					// �Ҳ��һ����ť
					l_right.setVisibility(View.VISIBLE);
					b_right.setVisibility(View.VISIBLE);
					tv_right.setVisibility(View.VISIBLE);
					tv_right.setText(strID);
					break;
				case 3:
					// ����һ����ť
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.VISIBLE);
					tv_left.setVisibility(View.VISIBLE);
					tv_left.setText(strID);
					break;
				case 4:
					// �Ҳ��һ����ť
					l_right.setVisibility(View.VISIBLE);
					b_right3.setVisibility(View.VISIBLE);
					tv_right.setVisibility(View.VISIBLE);
					tv_right.setText(strID);
					break;
				case 5:
					// ����һ����ť
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.GONE);
					tv_left.setVisibility(View.VISIBLE);
					tv_left.setText(strID);
					break;
				case 6:
					// �Ҳ��һ����ť
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
					// ����һ����ť
					l_left.setVisibility(View.VISIBLE);
					b_left.setVisibility(View.VISIBLE);
					b_left.setBackgroundResource(strID);
					break;
				case 1:
					// �Ҳ��һ����ť
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
		 * ȥ���ұ߼�ͷ
		 */
		public void setToolBarRightArrowGone(){
			b_right.setVisibility(View.GONE);
		}
		
		
		/**���ذ�ť���,ͨ��Ϊ�رոô���**/
		public void leftButtonClick(){
			this.finish();
		}
		
		/**ȷ����ť���**/
		public void rightButtonClick(){
			//do nothing......
		}
		
		//���ر�����
		protected void hideHeadBar(){
			findViewById(R.id.head_bar).setVisibility(View.GONE);
		}
		//��ʾ������
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
		 * ҳ����ת
		 */
		protected void close(){
			this.finish();
		}
		
}
