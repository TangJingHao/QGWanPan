package com.example.myapplication.basic;

import android.view.View;

import androidx.fragment.app.Fragment;

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


    @Override
    public void onClick(View v) {

    }
}
