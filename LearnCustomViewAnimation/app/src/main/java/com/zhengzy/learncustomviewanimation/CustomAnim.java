package com.zhengzy.learncustomviewanimation;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zhengzy on 2015/11/27.
 */
public class CustomAnim extends Animation {

    /**
     * 在初始化的时候被执行，可以获取目标控件的宽高
     *
     * @param width  目标控件宽
     * @param height 目标控件高
     * @param parentWidth  父控件宽
     * @param parentHeight 父控件高
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        Log.i("Tag", "获取目标对象的宽高");
        Log.i("Tag", String.valueOf(width) + "||" + String.valueOf(height) + "||" + String.valueOf(parentWidth) + "||" + String.valueOf(parentHeight));
    }

    /**
     * interpolatedTime：动画补间 从0到1一直执行
     *
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        // 相当于透明动画效果
//        t.setAlpha(interpolatedTime);

        // 相当于位移动画效果
//        t.getMatrix().setTranslate(200*interpolatedTime, 200*interpolatedTime);

        // 左右摇摆
        t.getMatrix().setTranslate((float)(Math.sin(interpolatedTime*20)*50), 0);


        Log.i("Tag", String.valueOf(interpolatedTime));

    }
}
