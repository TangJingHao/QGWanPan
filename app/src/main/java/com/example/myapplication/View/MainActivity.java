package com.example.myapplication.View;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.basic.BasePresenter;

public class MainActivity extends BaseActivity {

    @Override
    public Object getContract() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    public BasePresenter getPresenterInstance() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void responseError(Object o, Throwable throwable) {

    }

    @Override
    public void onClick(View v) {

    }
}