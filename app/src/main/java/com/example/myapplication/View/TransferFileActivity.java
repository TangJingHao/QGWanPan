package com.example.myapplication.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.Adapter.TransViewPager2Adapter;
import com.example.myapplication.R;
import com.example.myapplication.basic.SuperBaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;

import static com.example.myapplication.basic.BaseApplication.getContext;

public class TransferFileActivity extends SuperBaseActivity {

    private int ID;
    private String jwt;
    private TabLayout myTab;
    private ViewPager2 myPager2;
    private ImageView deleteIv,backIv;
    private int currentPosition = 3;//记录在哪个Fragment  下载在前  上传在后
    List<String> titles=new ArrayList<>();
    List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_file);

        this.ID=getIntent().getIntExtra("ID",-1);//接受用户的id
        this.jwt = getIntent().getStringExtra("jwt");

        initView();//初始化控件
        initFragment();//初始化Fragment
        setBackListener();//设置返回键的监听事件
        setDeleteListener();//设置清空键的监听事件
    }

    private void setDeleteListener() {
        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransferFileActivity.this,TestLoadActivity.class);
                startActivity(intent);

//                if(currentPosition==0){
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(TransferFileActivity.this);
//                    dialog.setTitle("");
//                    dialog.setMessage("确认要删除下载记录？");
//                    dialog.setCancelable(false);
//                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //删除下载记录
//                        }
//                    });
//                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    dialog.show();
//                }else{
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(TransferFileActivity.this);
//                    dialog.setTitle("");
//                    dialog.setMessage("确认要删除上传记录？");
//                    dialog.setCancelable(false);
//                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //删除上传记录
//                        }
//                    });
//                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    dialog.show();
//                }
            }
        });

    }

    private void setBackListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        myTab = findViewById(R.id.transfer_tab_layout);
        myPager2 = findViewById(R.id.transfer_view_pager);
        deleteIv = findViewById(R.id.transfer_delete_iv);
        backIv = findViewById(R.id.transfer_back);
    }

    private void initFragment(){
        //添加标题
        titles.add("        下载列表        ");
        titles.add("        上传列表        ");


        //添加Fragment进去
        fragments.add(new TransDownFragment());
        fragments.add(new TransUpFragment());

        //实例化适配器
        TransViewPager2Adapter myAdapter=new TransViewPager2Adapter(getSupportFragmentManager(),getLifecycle(),fragments);
        //设置适配器
        myPager2.setAdapter(myAdapter);
        //TabLayout和Viewpager2进行关联
        new TabLayoutMediator(myTab, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
                currentPosition = position;
            }
        }).attach();
    }
}