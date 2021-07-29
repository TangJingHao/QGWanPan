package com.example.myapplication.basic;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 23:57
 */
public abstract class BaseFragmentModel<P extends BaseFragmentPresenter,CONTRACT> extends SuperBase<CONTRACT> {

    public P mPresenter;

    /**
     * 根据传入的P层与P层绑定
     * @param mPresenter
     */
    public void BaseFragmentPresenter(P mPresenter) {
        this.mPresenter = mPresenter;
    }
}