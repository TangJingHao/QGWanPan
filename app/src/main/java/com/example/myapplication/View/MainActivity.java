package com.example.myapplication.View;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.BottomPagerAdapter;
import com.example.myapplication.Event.SetBottomNavigationEvent;
import com.example.myapplication.R;
import com.example.myapplication.basic.SuperBaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SuperBaseActivity {

    private List<Fragment> mList=new ArrayList<>();
    private BottomPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private BottomNavigationView mBnView;
    private int ID;
    private String jwt;
    private String password;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册通信器
        EventBus.getDefault().register(this);
        ID=getIntent().getIntExtra("ID",-1);//接受用户的id
        jwt=getIntent().getStringExtra("jwt");
        password=getIntent().getStringExtra("password");
        if(ID!=-1){
            createView(ID,jwt,password);
        }
    }

    private void createView(int ID,String jwt,String password) {
        mList.add(new HomeFragment(ID, jwt));
        mList.add(new FileFragment(ID,jwt));
        mList.add(new MyPageFragment(ID,jwt,password));
        mAdapter=new BottomPagerAdapter(getSupportFragmentManager(),mList);
        mViewPager= this.findViewById(R.id.fragment_viewPager);
        mBnView=this.findViewById(R.id.select_bottomNavigationView);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBnView.setSelectedItemId(R.id.HomePage);
                        break;
                    case 1:
                        mBnView.setSelectedItemId(R.id.FilePage);
                        break;
                    case 2:
                        mBnView.setSelectedItemId(R.id.MyPage);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.HomePage:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.FilePage:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.MyPage:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void FileCheckBoxEvent(SetBottomNavigationEvent event){
        if (event.IsCheckable()){
            mBnView.setVisibility(View.GONE);
        }else{
            mBnView.setVisibility(View.VISIBLE);
        }
    }

}

