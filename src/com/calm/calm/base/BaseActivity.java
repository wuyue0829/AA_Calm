package com.calm.calm.base;

import com.calm.calm.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
/**
 * �����Ĵ���
 * ��ͷ����
 * �½��Ĵ��嶼���Լ̳д��࣬���������ͷ��
 * ���ѡ��̳����������Ҫ��ʹ��������Զ���ķ��������ò����ļ�
 * @author wuyue
 *
 */
public class BaseActivity extends Activity{
	
	
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
	}
}
