package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.DataBase.FileDownDao;
import com.example.myapplication.DataBase.FileDownDataBase;
import com.example.myapplication.DataBase.FileDownDetail;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.basic.BaseApplication.getContext;

public class TestLoadActivity extends AppCompatActivity{

    Button mBtn;
    FileDownDetail testData;
    List<FileDownDetail> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_load);
        mBtn = findViewById(R.id.load_button);

        FileDownDataBase mFileDownDataBase = FileDownDataBase.getInstance(this);
        FileDownDao downDao = mFileDownDataBase.getFileDownDao();


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(()->{
                    FileDownDetail testData1 = new FileDownDetail(1,1,"文件名1","2020");
                    FileDownDetail testData2 = new FileDownDetail(2,1,"文件名2","2021");
                    FileDownDetail testData3 = new FileDownDetail(3,1,"文件名3","2022");
                    FileDownDetail testData4 = new FileDownDetail(4,1,"文件名4","2023");
                    downDao.insertDownFile(testData1,testData2,testData3,testData4);
                    mData = downDao.getALLDownFile();
                }).start();
                runOnUiThread(()->{
                    String string = mData.get(2).getDocname()+mData.get(3).getTime();
                    Log.d("TeatActivity======",string);
                    Toast.makeText(TestLoadActivity.this,string,Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

}