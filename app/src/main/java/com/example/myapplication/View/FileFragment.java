package com.example.myapplication.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.FileListAdapter;
import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.Event.FileCreate;
import com.example.myapplication.Event.FileLongClickEvent;
import com.example.myapplication.Event.SelectItemEvent;
import com.example.myapplication.Event.SetBottomNavigationEvent;
import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IFile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class FileFragment extends BaseFragment<FilePresenter, IFile.VP> {

    private int ID;
    private String jwt;
    private ConstraintLayout CL_topTitle_selectFile;
    private TextView tv_FileFragment_Title_cancel;
    private TextView tv_FileFragment_Title_selectAll;
    private TextView tv_FileFragment_Title_Selected;
    private EditText et_FileFragment_search;
    private RecyclerView file_list;
    private ConstraintLayout CL_FileFragment_Title;
    private TextView tv_FileFragment_Title_sort;
    private Button btn_FileFragment_Title_transmission;
    private Button btn_FileFragment_Title_add;
    private TextView tv_FileFragment_Title;
    private BottomNavigationView select_FileOperation_menu;
    private Button btn_FileFragment_close;
    private FileListAdapter fileListAdapter;
    private ImageButton btn_FileFragment_NewFolder;
    private ImageButton btn_FileFragment_upload;
    private ConstraintLayout CL_FileFragment_topAdd;

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

        //注册通信器
        EventBus.getDefault().register(this);


        //初始化控件 数据 监听器
        initView(view);
        initData();
        initListener();
        mPresenter.getContract().getFileData(ID,jwt);
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
            public void getFileData(int userid,String jwt) {
                mPresenter.getContract().getFileData(userid,jwt);
            }

            @Override
            public void getFileDataResult(List<FileDataBean> data) {
                initAdapter(data);
            }

            @Override
            public void uploadFile(String path) {

            }

            @Override
            public void uploadFileResult(int resultCode) {

            }

            @Override
            public void newFile(int userid,int fid,String folderName,String jwt) {

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
        CL_topTitle_selectFile = view.findViewById(R.id.CL_topTitle_selectFile);
        tv_FileFragment_Title_cancel = view.findViewById(R.id.tv_FileFragment_Title_cancel);
        tv_FileFragment_Title_selectAll = view.findViewById(R.id.tv_FileFragment_Title_selectAll);
        tv_FileFragment_Title_Selected = view.findViewById(R.id.tv_FileFragment_Title_Selected);
        file_list = view.findViewById(R.id.file_list);
        et_FileFragment_search = view.findViewById(R.id.et_FileFragment_search);
        CL_FileFragment_Title = view.findViewById(R.id.CL_FileFragment_Title);
        tv_FileFragment_Title_sort = view.findViewById(R.id.tv_FileFragment_Title_sort);
        btn_FileFragment_Title_transmission = view.findViewById(R.id.btn_FileFragment_Title_transmission);
        btn_FileFragment_Title_add = view.findViewById(R.id.btn_FileFragment_Title_add);
        tv_FileFragment_Title = view.findViewById(R.id.tv_FileFragment_Title);
        select_FileOperation_menu = view.findViewById(R.id.select_FileOperation_menu);
        btn_FileFragment_upload = view.findViewById(R.id.btn_FileFragment_upload);
        btn_FileFragment_close = view.findViewById(R.id.btn_FileFragment_close);
        btn_FileFragment_NewFolder = view.findViewById(R.id.btn_FileFragment_NewFolder);
        CL_FileFragment_topAdd = view.findViewById(R.id.CL_FileFragment_topAdd);
    }

    public void initAdapter(List<FileDataBean> fileData){
        //加载文件列表
        fileListAdapter = new FileListAdapter(fileData);
        file_list.setAdapter(fileListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        file_list.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

        //新建文件夹键监听
        btn_FileFragment_NewFolder.setOnClickListener(this::onClick);

        //顶部取消键监听
        tv_FileFragment_Title_cancel.setOnClickListener(this::onClick);

        //顶部添加键监听
        btn_FileFragment_Title_add.setOnClickListener(this::onClick);

        //顶部关闭键监听
        btn_FileFragment_close.setOnClickListener(this::onClick);

        //顶部传输键监听
        btn_FileFragment_Title_transmission.setOnClickListener(this::onClick);

        //搜索框监听
        et_FileFragment_search.setOnClickListener(this::onClick);
        //底部文件操作按钮监听
        select_FileOperation_menu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.file_operation_download:{
                        CL_FileFragment_Title.setVisibility(View.VISIBLE);
                        CL_topTitle_selectFile.setVisibility(View.GONE);
                        select_FileOperation_menu.setVisibility(View.GONE);
                        EventBus.getDefault().post(new SetBottomNavigationEvent(false));
                        Intent intent=new Intent(getContext(),TransferFileActivity.class);
                        intent.putExtra("jwt",jwt);
                        intent.putExtra("ID",ID);
                        intent.putStringArrayListExtra("FileId",fileListAdapter.GetSelectedList());
                        startActivity(intent);

                    }break;
                    case R.id.file_operation_del:{
                        Log.d("TAG","file_operation_del");

                    }break;
                    case R.id.file_operation_rename:{
                        Log.d("TAG","file_operation_rename");

                    }break;
                    case R.id.file_operation_detail: {
                        Log.d("TAG","file_operation_detail");

                    }break;
                }
                return true;
            }
        });
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
        //解除注册通信器
        EventBus.getDefault().unregister(this);
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_FileFragment_Title_selectAll:{

            }break;

            case R.id.tv_FileFragment_Title_cancel:{
                CL_FileFragment_Title.setVisibility(View.VISIBLE);
                CL_topTitle_selectFile.setVisibility(View.GONE);
                select_FileOperation_menu.setVisibility(View.GONE);
                EventBus.getDefault().post(new SetBottomNavigationEvent(false));
            }break;

            case R.id.btn_FileFragment_Title_add:{
                CL_FileFragment_topAdd.setVisibility(View.VISIBLE);
            }break;

            case R.id.btn_FileFragment_close:{
                CL_FileFragment_topAdd.setVisibility(View.GONE);
            }break;

            case R.id.btn_FileFragment_NewFolder:{
                CL_FileFragment_topAdd.setVisibility(View.GONE);
                View view = View.inflate(getContext(),R.layout.alertdialogview,null);
                AlertDialog.Builder builder
                        = new AlertDialog.Builder(getContext())
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = view.findViewById(R.id.et_AlertDialogView);
                                String folderName = editText.getText().toString();
                                mPresenter.getContract().newFile(ID,0,folderName,jwt);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = builder.create();
                dialog.setView(view);
                dialog.show();
                dialog.getWindow().setLayout(1000,500);
            }break;

            case R.id.btn_FileFragment_Title_transmission:{
                Intent intent=new Intent(getContext(),TransferFileActivity.class);
                intent.putExtra("jwt",jwt);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }break;

            case R.id.et_FileFragment_search:{
                Intent intent=new Intent(getContext(),SearchActivity.class);
                intent.putExtra("jwt",jwt);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }break;
        }
    }

    /**
     * 文件夹长按监听
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onFileLongClickEvent(FileLongClickEvent event){
        String Msg = event.getMsg();
        CL_FileFragment_Title.setVisibility(View.GONE);
        CL_topTitle_selectFile.setVisibility(View.VISIBLE);
        select_FileOperation_menu.setVisibility(View.VISIBLE);

    }

    /**
     * 更新选中文件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSelectItemEvent(SelectItemEvent event){
        String Num = event.GetSelectedNum();
        tv_FileFragment_Title_Selected.setText("已选中" + Num + "个文件");

    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onFileCreateEvent(FileCreate event){
        mPresenter.getContract().getFileData(ID,jwt);
    }
}
