package com.zhengzy.learnrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        rv = new RecyclerView(this);
        //表格布局
//        rv.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false));
        //线性布局
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        setContentView(rv);

        MyAdapter.initData();

        rv.setAdapter(new MyAdapter());
    }

}
