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

import java.util.List;

import static com.example.myapplication.basic.BaseApplication.getContext;

public class TestLoadActivity extends AppCompatActivity{

    Button mBtn;
    FileDownDetail testData;
    List<FileDownDetail> mData;

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
                    testData = new FileDownDetail();
                    testData.setId(4);
                    testData.setDocname("文件1");
                    downDao.insertDownFile(testData);
                    Log.d("TeatActivity======",testData.getDocname()+testData.getId());
                    testData.setId(5);
                    testData.setDocname("文件2");
                    downDao.insertDownFile(testData);
                    Log.d("TeatActivity======",testData.getDocname()+testData.getId());
                    testData.setId(6);
                    testData.setDocname("文件3");
                    downDao.insertDownFile(testData);
                    Log.d("TeatActivity======",testData.getDocname()+testData.getId());
                    mData = downDao.getALLDownFile();
                }).start();
                runOnUiThread(()->{
                    String string = mData.get(1).getDocname();
                    Toast.makeText(getContext(),string,Toast.LENGTH_SHORT).show();
                });

            }
        });
    }

}