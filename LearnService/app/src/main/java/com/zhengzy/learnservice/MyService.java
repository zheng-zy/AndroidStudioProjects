package com.zhengzy.learnservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private boolean serviceRunning = false;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }

    /**
     * 只要执行startService方法，就会执行onStartCommand
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("Log", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.v("Log", "onCreate");
        super.onCreate();
        serviceRunning = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (serviceRunning){
                    Log.v("Log", "服务正在运行...");
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
}
