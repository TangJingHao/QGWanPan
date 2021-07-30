package com.example.myapplication.Presenter;

import com.example.myapplication.Model.RegisterModel;
import com.example.myapplication.View.RegisterFragment;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.IRegister;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/12:44
 * @Description:
 */
public class RegisterPresenter extends BasePresenter<RegisterModel, RegisterFragment, IRegister.VP> {
    @Override
    public RegisterModel getModelInstance() {
        return new RegisterModel(this);
    }

    @Override
    public IRegister.VP getContract() {
        return new IRegister.VP() {
            @Override
            public void requestRegister(String username,String password,String nickname,String userEmail,String checkCode) {
                try{
                    mModel.getContract().requestRegister(username,password,nickname,userEmail,checkCode);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void requestCheckCode(String userEmail) throws Exception {
                mModel.getContract().requestCheckCode(userEmail);
            }

            @Override
            public void responseRegisterCodeResult(int registerStatusResult) throws Exception {
                mView.getContract().responseRegisterCodeResult(registerStatusResult);
            }

            @Override
            public void responseRegister(String username, String password, int code) throws Exception {
                mView.getContract().responseRegister(username,password,code);
            }
        };
    }
}
