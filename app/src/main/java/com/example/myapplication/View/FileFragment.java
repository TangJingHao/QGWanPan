package com.example.myapplication.View;

import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IFile;

import java.util.List;

public class FileFragment extends BaseFragment<FilePresenter, IFile.VP> {


    @Override
    public IFile.VP getContract() {
        return new IFile.VP() {
            @Override
            public void getFileData() {

            }

            @Override
            public void getFileDataResult(List<String> data) {

            }

            @Override
            public void uploadFile(String path) {

            }

            @Override
            public void uploadFileResult(int resultCode) {

            }

            @Override
            public void newFile(String path) {

            }

            @Override
            public void newFileResult(int resultCode) {

            }

            @Override
            public void searchFile(String fileName) {

            }

            @Override
            public void searchFileResult(List<String> searchData) {

            }
        };
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_file;
    }

    @Override
    public FilePresenter getPresenterInstance() {
        return new FilePresenter();
    }

    @Override
    public void destroy() {

    }
}