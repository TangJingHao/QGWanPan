package com.example.myapplication.Presenter;

import android.os.Binder;

import com.example.myapplication.contract.IUpload;

public class MUploadPresenter extends Binder implements IUpload.MUpload {
    @Override
    public void uploadFile(String path) throws Exception {

    }
}
