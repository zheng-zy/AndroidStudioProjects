package com.zhengzy.learnfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhengzy on 2015/11/13.
 */
public class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //以下跳转到另一个fragment没有回退功能，会直接退出当前程序
                //getFragmentManager().beginTransaction().relace(R.id.container, new AnotherFragment()).commit();
                //添加addToBackStack实现后退
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new AnotherFragment()).commit();
            }
        });
        rootView.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), SliderActivity.class));
            }
        });
        return rootView;
    }
}
