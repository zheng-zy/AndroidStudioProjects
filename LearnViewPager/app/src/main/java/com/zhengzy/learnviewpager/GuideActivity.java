package com.zhengzy.learnviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengzy on 2015/11/30.
 */
public class GuideActivity extends Activity {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initViews();
    }

    private void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(layoutInflater.inflate(R.layout.one, null));
        views.add(layoutInflater.inflate(R.layout.two, null));
        views.add(layoutInflater.inflate(R.layout.three, null));

        viewPagerAdapter = new ViewPagerAdapter(this,views);
        viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);
    }
}
