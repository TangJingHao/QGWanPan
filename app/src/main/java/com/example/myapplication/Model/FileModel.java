package com.example.myapplication.Model;

import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IFile;

/**
 * @Name： FileModel
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/27 13:37
 */
public class FileModel extends BaseModel<FilePresenter, IFile.M> {
    /**
     * 根据传入的P层与P层绑定
     * @param mPresenter
     */
    public FileModel(FilePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public IFile.M getContract() {
        return new IFile.M() {
            @Override
            public void getFileData() throws Exception {

            }

            @Override
            public void uploadFile(String path) throws Exception {

            }

            @Override
            public void newFile(String path) throws Exception {

            }

            @Override
            public void searchFile(String fileName) throws Exception {

            }
        };
    }
}
