package com.example.myapplication.View;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.basic.BasePresenter;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 18:04
 */
public class SearchActivity extends BaseActivity {

    private int ID;


    @Override
    public Object getContract() {
        return null;
    }

    @Override
    public void initView() {
        this.ID=getIntent().getIntExtra("ID",-1);//接受用户的id
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public BasePresenter getPresenterInstance() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {

    }
}
