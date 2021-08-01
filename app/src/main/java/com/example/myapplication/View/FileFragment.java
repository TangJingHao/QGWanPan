package com.example.myapplication.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.FileListAdapter;
import com.example.myapplication.DataBean.FileBean;
import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.DataBean.UserDataBean;
import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IFile;
import com.example.myapplication.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FileFragment extends BaseFragment<FilePresenter, IFile.VP> {

    private int ID;
    private String jwt;
    private ConstraintLayout ll_topTitle_selectFile;
    private TextView tv_FileFragment_Title_cancel;
    private TextView tv_FileFragment_Title_selectAll;
    private TextView tv_FileFragment_Title_Selected;
    private EditText et_FileFragment_search;
    private RecyclerView file_list;
    private ConstraintLayout ll_FileFragment_Title;
    private TextView tv_FileFragment_Title_sort;
    private Button btn_FileFragment_Title_transmission;
    private Button btn_FileFragment_Title_add;
    private TextView tv_FileFragment_Title;
    private BottomNavigationView select_FileOperation_menu;


    public FileFragment(int ID,String jwt) {
        this.ID = ID;
        this.jwt = jwt;
    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //绑定p层
        mPresenter = getPresenterInstance();
        View view = inflater.inflate(getContentViewId(),container,false);
        mPresenter.bindView(this);


        //初始化控件 数据 监听器
        initView(view);
        initData();
        initListener();
        mPresenter.getContract().getFileData(ID);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IFile.VP getContract() {
        return new IFile.VP() {
            @Override
            public void getFileData(int id) {
                mPresenter.getContract().getFileData(id);
            }
            @Override
            public void getFileDataResult(List<FileDataBean> data) {

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
    public void initView(View view) {
        ll_topTitle_selectFile = view.findViewById(R.id.ll_topTitle_selectFile);
        tv_FileFragment_Title_cancel = view.findViewById(R.id.tv_FileFragment_Title_cancel);
        tv_FileFragment_Title_selectAll = view.findViewById(R.id.tv_FileFragment_Title_selectAll);
        tv_FileFragment_Title_Selected = view.findViewById(R.id.tv_FileFragment_Title_Selected);
        file_list = view.findViewById(R.id.file_list);
        et_FileFragment_search = view.findViewById(R.id.et_FileFragment_search);
        ll_FileFragment_Title = view.findViewById(R.id.ll_FileFragment_Title);
        tv_FileFragment_Title_sort = view.findViewById(R.id.tv_FileFragment_Title_sort);
        btn_FileFragment_Title_transmission = view.findViewById(R.id.btn_FileFragment_Title_transmission);
        btn_FileFragment_Title_add = view.findViewById(R.id.btn_FileFragment_Title_add);
        tv_FileFragment_Title = view.findViewById(R.id.tv_FileFragment_Title);
        select_FileOperation_menu = view.findViewById(R.id.select_FileOperation_menu);


    }

    public void initAdapter(List<FileDataBean> fileData){
        //加载文件列表
        FileListAdapter fileListAdapter = new FileListAdapter(fileData);
        file_list.setAdapter(fileListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        file_list.setLayoutManager(linearLayoutManager);
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

    @Override
    public void onClick(View v) {

    }
}
