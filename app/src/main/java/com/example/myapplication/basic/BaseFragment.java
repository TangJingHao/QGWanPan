package com.example.myapplication.basic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 8:51
 */
public abstract class BaseFragment<P extends BasePresenter,CONTRACT> extends Fragment implements View.OnClickListener{
    /**
     *实现接口
     * @return 接口
     */
    public abstract CONTRACT getContract();

    public  P mPresenter;

    //要传入view把初始化的view给fragment
    public abstract void initView(View view);

    public abstract void initData();

    public abstract void initListener();

    /**
     * 获取id给Activity进行加载视图
     * @return  layout的id
     */
    public abstract int getContentViewId();

    public abstract P getPresenterInstance();

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        //绑定p层
        mPresenter = getPresenterInstance();
        View view = inflater.inflate(getContentViewId(),container,false);
        mPresenter.bindView(this);

        //初始化控件 数据 监听器
        initView(view);
        initData();
        initListener();
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
        destroy();
    }

    public abstract void destroy();
}
