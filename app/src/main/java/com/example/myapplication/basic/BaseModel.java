package com.example.myapplication.basic;

/**
 * @Name： BaseModel
 * @Description： M层的基类
 * @Author： Suzy.Mo
 * @Date： 2021/7/26 11:33
 */
public abstract class BaseModel<P extends BasePresenter,CONTRACT> extends SuperBase<CONTRACT> {

    public P mPresenter;

    /**
     * 根据传入的P层与P层绑定
     * @param mPresenter
     */
    public BaseModel(P mPresenter) {
        this.mPresenter = mPresenter;
    }
}