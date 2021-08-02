package com.example.myapplication.Presenter;

import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.Model.FileModel;
import com.example.myapplication.View.FileFragment;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.IFile;

import java.util.List;

/**
 * @Name： FilePresenter
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/27 13:36
 */
public class FilePresenter extends BasePresenter<FileModel, FileFragment, IFile.VP> {

    @Override
    public FileModel getModelInstance() {
        return new FileModel(this);
    }

    @Override
    public IFile.VP getContract() {
        return new IFile.VP() {
            @Override
            public void getFileData(int userid,String jwt) {
                try {
                    mModel.getContract().getFileData(userid,jwt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void getFileDataResult(List<FileDataBean> data) {
                mView.getContract().getFileDataResult(data);
            }

            @Override
            public void uploadFile(String path) {

            }

            @Override
            public void uploadFileResult(int resultCode) {

            }

            @Override
            public void newFile(int userid,int fid,String folderName,String jwt) {
                try {
                    mModel.getContract().newFile(userid,fid,folderName,jwt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

            @Override
            public void renameFile(int userid, String jwt, String FileId, String folderName) throws Exception {
                mModel.getContract().renameFile(userid,jwt,FileId,folderName);
            }

            @Override
            public void renameFileresult() {
                mView.getContract().renameFileresult();
            }
        };
    }
}
