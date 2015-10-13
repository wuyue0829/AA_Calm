package com.calm.calm.base;

import com.calm.calm.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
/**
 * 基础的窗体
 * 带头部栏
 * 新建的窗体都可以继承此类，获得这个类的头部
 * 如果选择继承这个类则需要，使用这个类自定义的方法，设置布局文件
 * @author wuyue
 *
 */
public class BaseActivity extends Activity{
	
	
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
	}
}
