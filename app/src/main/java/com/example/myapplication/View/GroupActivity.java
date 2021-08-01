package com.example.myapplication.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.InitPageAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/31/15:01
 * @Description: 分组的活动
 */
public class GroupActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments=new ArrayList<>();
    private int ID;
    private String jwt;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ID=getIntent().getIntExtra("ID",-1);//接受用户的id
        jwt=getIntent().getStringExtra("jwt");
        mTabLayout = this.findViewById(R.id.group_tab_layout);
        mViewPager = this.findViewById(R.id.group_view_pager);
        mTabLayout.addTab(mTabLayout.newTab().setText("已分组"));
        mTabLayout.addTab(mTabLayout.newTab().setText("未分组"));
        mFragments.add(new GroupingFragment(ID,jwt));
        mFragments.add(new NoGroupFragment(ID,jwt));
        InitPageAdapter initPageAdapter = new InitPageAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(initPageAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
