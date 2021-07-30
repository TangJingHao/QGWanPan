package com.example.myapplication.basic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.View.ActivityCollector;

public abstract class BaseActivity<P extends BasePresenter,CONTRACT> extends SuperBaseActivity implements View.OnClickListener {

    /**
     *实现接口
     * @return 接口
     */
    public abstract CONTRACT getContract();

    public  P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());

        //初始化控件 数据 监听器
        initView();
        initData();
        initListener();

        //绑定p层
        mPresenter = getPresenterInstance();
        mPresenter.bindView(this);

    }


    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    /**
     * 获取id给Activity进行加载视图
     * @return  layout的id
     */
    public abstract int getContentViewId();

    public abstract P getPresenterInstance();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
        destroy();
    }

    public abstract void destroy();
}

