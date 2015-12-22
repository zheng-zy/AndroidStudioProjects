package com.zhengzy.learnviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengzy on 2015/11/30.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> views;

    private ImageView[] imageViews;
    private int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3};

    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initViews();
        initDots();
    }

    private void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(layoutInflater.inflate(R.layout.one, null));
        views.add(layoutInflater.inflate(R.layout.two, null));
        views.add(layoutInflater.inflate(R.layout.three, null));

        viewPagerAdapter = new ViewPagerAdapter(this, views);
        viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);

        startBtn = (Button) views.get(2).findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initDots() {
        imageViews = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            imageViews[i] = (ImageView) findViewById(ids[i]);
        }
    }

    /**
     * 当页面被滑动的时候进行调用
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 当前新的页面被选中的时候调用
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                imageViews[i].setImageResource(R.drawable.login_point_selected);
            } else {
                imageViews[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    /**
     * 当滑动状态改变的时候进行调用
     *
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
