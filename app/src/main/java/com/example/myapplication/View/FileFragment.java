package com.example.myapplication.View;

import android.content.Intent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.myapplication.Presenter.FilePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IFile;

import java.util.List;

public class FileFragment extends BaseFragment<FilePresenter, IFile.VP> {

    private int ID;
    private String jwt;
    private TextView newTv;
    private CardView searchCv;
    private Switch fileSwitch;


    public FileFragment(int ID,String jwt) {
        this.ID = ID;
        this.jwt = jwt;
    }

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
    public void initView(View view) {
        newTv = view.findViewById(R.id.file_new_bt);
        searchCv = view.findViewById(R.id.file_search_cv);
        fileSwitch = view.findViewById(R.id.file_switch);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        newTv.setOnClickListener(this);
        searchCv.setOnClickListener(this);
        fileSwitch.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.file_search_cv:
                Intent intent=new Intent(getContext(),SearchActivity.class);
                intent.putExtra("ID",ID);
                intent.putExtra("jwt",jwt);
                startActivity(intent);
        }
    }
}
