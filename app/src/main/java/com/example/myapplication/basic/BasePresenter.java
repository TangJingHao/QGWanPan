package com.example.myapplication.basic;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/26 11:33
 */
public abstract class BasePresenter<M extends BaseModel,V extends BaseActivity,CONTRACT> extends SuperBase<CONTRACT>{

    public V mView;

    public M mModel;

    public BasePresenter(){
        this.mModel = getModelInstance();
    }

    public void bindView(V mView){
        this.mView = mView;
    }

    public void unBindView(){
        this.mView = null;
    }

    public abstract M getModelInstance();
}