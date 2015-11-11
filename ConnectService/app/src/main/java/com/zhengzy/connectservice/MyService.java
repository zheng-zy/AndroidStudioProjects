package com.zhengzy.connectservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private String data = "这是默认信息";
    private int i = 0;//每执行一次，i++
    private boolean serviceRunning = false;
    private Callback callback = null;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }

    public class Binder extends android.os.Binder {
        public void setData(String data) {
            MyService.this.data = data;
        }
        public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("Log", "onStartCommand");
//        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.v("Log", "onCreate");
        super.onCreate();
        serviceRunning = true;
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (serviceRunning) {

                    Log.v("Log", i + data);
                    String msg = i + data;
                    if (callback != null) {
                        callback.onDataChange(msg);
                    }
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        Log.v("Log", "onDestroy");
        super.onDestroy();
        serviceRunning = false;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }

    public static interface Callback {
        void onDataChange(String data);
    }

}
