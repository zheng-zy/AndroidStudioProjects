package com.zhengzy.learncustomviewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //透明动画效果
//                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//                alphaAnimation.setDuration(1000);
//                view.startAnimation(alphaAnimation);
                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_xml));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //旋转动画效果
//                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                rotateAnimation.setDuration(1000);
//                v.startAnimation(rotateAnimation);
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_xml));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //混合动画效果
//                AnimationSet animationSet = new AnimationSet(true);
//                animationSet.setDuration(1000);
//                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//                alphaAnimation.setDuration(1000);
//                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                rotateAnimation.setDuration(1000);
//                animationSet.addAnimation(alphaAnimation);
//                animationSet.addAnimation(rotateAnimation);
//                v.startAnimation(animationSet);
                //View动画-动画效果监听
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.set_xml);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(MainActivity.this, "Animation end", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                v.startAnimation(animation);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAnim customAnim = new CustomAnim();
                customAnim.setDuration(1000);
                v.startAnimation(customAnim);
            }
        });
    }
}
