package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.DataBean.FileBean;
import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.DataBean.NewFolderBean;
import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IFile;
import com.example.myapplication.contract.IPost;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            public void getFileData(int userid, String jwt) throws Exception {
                IPost post = BaseCreator.create(IPost.class);
                post.getFileData(jwt,userid).enqueue(new Callback<FileBean>() {
                    @Override
                    public void onResponse(Call<FileBean> call, Response<FileBean> response) {
                        FileBean fileBean = response.body();
                        List<FileDataBean> list = fileBean.getData();
                        mPresenter.getContract().getFileDataResult(list);
                    }

                    @Override
                    public void onFailure(Call<FileBean> call, Throwable t) {
                        Log.d("TAG_ERROR","获取默认文件夹失败");
                    }
                });

            }

            @Override
            public void uploadFile(String path) throws Exception {

            }

            @Override
            public void newFile(int userid,int fid,String folderName,String jwt) throws Exception {
                IPost post = BaseCreator.create(IPost.class);
                post.newFile(jwt,userid,userid,folderName,fid).enqueue(new Callback<NewFolderBean>() {
                    @Override
                    public void onResponse(Call<NewFolderBean> call, Response<NewFolderBean> response) {
                            NewFolderBean newFolderBean = response.body();
                    }

                    @Override
                    public void onFailure(Call<NewFolderBean> call, Throwable t) {
                            Log.d("TAG_ERROR","新建文件夹失败");
                    }
                });
            }

            @Override
            public void searchFile(String fileName) throws Exception {

            }
        };
    }
}
