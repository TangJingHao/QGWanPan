package com.example.myapplication.basic;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 23:55
 */
public abstract class BaseFragmentPresenter<M extends BaseModel,V extends BaseFragment,CONTRACT> extends SuperBase<CONTRACT>{

    public V mView;

    public M mModel;

    /**
     * 构造方法
     */

    public BaseFragmentPresenter(){
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
