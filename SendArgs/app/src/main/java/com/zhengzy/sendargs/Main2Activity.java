package com.zhengzy.sendargs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv_main2);
        mTextView.setText(getIntent().getStringExtra("data"));
    }
}
