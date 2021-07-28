package com.example.myapplication.Presenter;

import com.example.myapplication.Model.RegisterModel;
import com.example.myapplication.View.RegisterActivity;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.IRegister;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/12:44
 * @Description:
 */
public class RegisterPresenter extends BasePresenter<RegisterModel, RegisterActivity, IRegister.VP> {
    @Override
    public RegisterModel getModelInstance() {
        return new RegisterModel(this);
    }

    @Override
    public IRegister.VP getContract() {
        return new IRegister.VP() {
            @Override
            public void requestRegister(String username, String password,String nickname) {
                try{
                    mModel.getContract().requestRegister(username,password,nickname);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseRegisterResult(int registerStatusResult, String username, String password) {
                mView.getContract().responseRegisterResult(registerStatusResult,username ,password );
            }
        };
    }
}
