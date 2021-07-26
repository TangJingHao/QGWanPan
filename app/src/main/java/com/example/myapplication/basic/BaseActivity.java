package com.example.myapplication.basic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.View.ActivityCollector;

public abstract class BaseActivity<P extends BasePresenter,CONTRACT> extends AppCompatActivity implements View.OnClickListener {

    /**
     *实现接口
     * @return 接口
     */
    public abstract CONTRACT getContract();

    public  P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加载布局 加入活动管理器
        setContentView(getContentViewId());
        ActivityCollector.addActivity(this);
        Log.d("正在运行",getClass().getSimpleName());

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

    /**
     * 获取p进行实例化
     * @return p
     */
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

