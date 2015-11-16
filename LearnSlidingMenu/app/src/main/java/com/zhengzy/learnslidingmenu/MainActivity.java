package com.zhengzy.learnslidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        slidingMenu = new SlidingMenu(this);
        //设置模式:左边
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置偏移尺寸
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        //设置成全屏都是可以触摸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //附加在当前的activity
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置ui资源
        slidingMenu.setMenu(R.layout.slidingmenu);

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 实现物理按键监听
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_MENU:
                slidingMenu.toggle(true);
                break;
            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}
