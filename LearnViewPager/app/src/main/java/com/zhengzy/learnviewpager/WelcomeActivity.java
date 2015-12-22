package com.zhengzy.learnviewpager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class WelcomeActivity extends AppCompatActivity {

    private boolean isFirstIn = true;
    private static final int DELAYED_TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init(){
        SharedPreferences sharedPreferences = getSharedPreferences("jike", MODE_PRIVATE);
        isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
        if (isFirstIn){
            handler.sendEmptyMessageDelayed(GO_GUIDE, DELAYED_TIME);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }else {
            handler.sendEmptyMessageDelayed(GO_HOME,DELAYED_TIME);
        }

    }

    private void goHome(){
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void goGuide(){
        Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

}
