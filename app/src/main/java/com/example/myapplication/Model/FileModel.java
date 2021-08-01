package com.example.myapplication.Model;

import com.example.myapplication.DataBean.FileBean;
import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IFile;
import com.example.myapplication.util.Constants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
            public void getFileData(int id) throws Exception {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestBody requestBody
                                = new FormBody.Builder()
                                .add("id", String.valueOf(id))
                                .build();
                        Request request = new Request.Builder()
                                .url(Constants.ServerURL + "folder/enterYun")
                                .post(requestBody)
                                .build();

                        OkHttpClient okHttpClient = new OkHttpClient();
                        Response response = null;

                        try{
                            response = okHttpClient.newCall(request).execute();
                            String requestData = response.body().string();
                            JSONObject jsonObject = new JSONObject(requestData);
                            boolean flag = jsonObject.getBoolean("flag");

                            if (flag){
                                Gson gson = new Gson();
                                FileBean fileBean = gson.fromJson(requestData, FileBean.class);
                                List<FileDataBean> list = fileBean.getData();
                                mPresenter.getContract().getFileDataResult(list);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
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
