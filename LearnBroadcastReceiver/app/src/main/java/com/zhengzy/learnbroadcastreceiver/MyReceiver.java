package com.zhengzy.learnbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.zhengzy.learnbroadcastreceiver.intent.action.MyReceiver";//包名.intent.action.类名

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");

        Log.v("Log", "接收到消息,消息内容是:"+intent.getStringExtra("data"));
        abortBroadcast();
    }
}
