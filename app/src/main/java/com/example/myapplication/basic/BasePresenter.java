package com.example.myapplication.basic;

/**
 * @Name： BasePresenter
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/26 11:33
 */
public abstract class BasePresenter<M extends BaseModel,V,CONTRACT> extends SuperBase<CONTRACT>{

    public V mView;

    public M mModel;

    /**
     * 构造方法
     */

    public BasePresenter(){
        this.mModel = getModelInstance();
    }

    /**
     * 与View绑定
     * @param mView
     */
    public void bindView(V mView){
        this.mView = mView;
    }

    /**
     * 与View解绑
     */
    public void unBindView(){
        this.mView = null;
    }

    /**
     * 实例化Model层
     * return 继承于BaseModel的M层
     */
    public abstract M getModelInstance();
}