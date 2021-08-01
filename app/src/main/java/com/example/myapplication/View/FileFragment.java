package com.example.myapplication.View;

<<<<<<< Updated upstream
import android.content.Intent;
=======
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
>>>>>>> Stashed changes
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

<<<<<<< Updated upstream
=======
import com.example.myapplication.Adapter.FileListAdapter;
import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.Event.SelectItemEvent;
import com.example.myapplication.Event.SetBottomNavigationEvent;
import com.example.myapplication.Event.FileLongClickEvent;
>>>>>>> Stashed changes
import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IFile;
<<<<<<< Updated upstream

import java.util.List;


public class FileFragment extends BaseFragment<FilePresenter, IFile.VP> {

    private int ID;

    public FileFragment(int ID) {
        this.ID = ID;
=======
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
    private FileListAdapter fileListAdapter;

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

       // mPresenter.getContract().getFileData(ID);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
>>>>>>> Stashed changes
    }

    @Override
    public IFile.VP getContract() {
        return new IFile.VP() {
            @Override
            public void getFileData() {

            }

            @Override
<<<<<<< Updated upstream
            public void getFileDataResult(List<String> data) {

=======
            public void getFileDataResult(List<FileDataBean> data) {
                initAdapter(data);
>>>>>>> Stashed changes
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

    }

<<<<<<< Updated upstream
=======
    public void initAdapter(List<FileDataBean> fileData){
        //加载文件列表
        fileListAdapter = new FileListAdapter(fileData);
        file_list.setAdapter(fileListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        file_list.setLayoutManager(linearLayoutManager);

    }

>>>>>>> Stashed changes
    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

        /**
         * 取消键监听
         */
        tv_FileFragment_Title_cancel.setOnClickListener(this::onClick);


        /**
         * 文件操作底部按钮监听
         */
        select_FileOperation_menu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.file_operation_download:{
                        Log.d("TAG","file_operation_download");

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_FileFragment_Title_selectAll:{
                fileListAdapter.notifyDataSetChanged();
            }break;

            case R.id.tv_FileFragment_Title_cancel:{
                ll_FileFragment_Title.setVisibility(View.VISIBLE);
                ll_topTitle_selectFile.setVisibility(View.GONE);
                select_FileOperation_menu.setVisibility(View.GONE);
                EventBus.getDefault().post(new SetBottomNavigationEvent(false));
            }break;

<<<<<<< Updated upstream
        /*switch (v.getId()){
            case R.id.et:
                Intent intent=new Intent(getContext(),SearchActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
        }*/

=======
        }
>>>>>>> Stashed changes
    }

    /**
     * 文件夹长按监听
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onFileLongClickEvent(FileLongClickEvent event){
        String Msg = event.getMsg();
        ll_FileFragment_Title.setVisibility(View.GONE);
        ll_topTitle_selectFile.setVisibility(View.VISIBLE);
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

}
