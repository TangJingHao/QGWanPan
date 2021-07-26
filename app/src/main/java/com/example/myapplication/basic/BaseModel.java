package com.example.myapplication.basic;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/26 11:33
 */
public abstract class BaseModel<P extends BasePresenter,CONTRACT> extends SuperBase<CONTRACT> {

    public P mPresenter;

    public BaseModel(P mPresenter) {
        this.mPresenter = mPresenter;
    }
}