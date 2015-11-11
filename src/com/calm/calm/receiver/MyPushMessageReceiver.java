package com.calm.calm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import cn.bmob.push.PushConstants;

public class MyPushMessageReceiver extends BroadcastReceiver{
	public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
        	 Log.e("bmob", "客户端收到推送内容："+intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING));
        }
    }
}
