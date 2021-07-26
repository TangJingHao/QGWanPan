package com.example.myapplication.basic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.View.ActivityCollector;

public abstract class BaseActivity<P extends BasePresenter,CONTRACT> extends AppCompatActivity implements View.OnClickListener {

    public abstract CONTRACT getContract();

    public  P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());
        Log.d("正在运行",getClass().getSimpleName());

        initView();
        initData();
        initListener();

        mPresenter = getPresenterInstance();
        mPresenter.bindView(this);
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    public abstract int getContentViewId();

    public abstract P getPresenterInstance();

    //处理错误信息
    public abstract <ERROR extends Object> void responseError(ERROR error,Throwable throwable );

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
        ActivityCollector.removeActivity(this);
        destroy();
    }

    public abstract void destroy();
}

