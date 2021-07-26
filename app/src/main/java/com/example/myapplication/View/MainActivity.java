package com.example.myapplication.View;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.BottomPagerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.basic.BasePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<Fragment> mList=new ArrayList<>();
    private BottomPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private BottomNavigationView mBnView;

    @Override
    public Object getContract() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createView();
    }

    private void createView() {
        mList.add(new FileFragment());
        mList.add(new HomeFragment());
        mList.add(new GroupFragment());
        mList.add(new MyPageFragment());
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
                        mBnView.setSelectedItemId(R.id.FilePage);
                        break;
                    case 1:
                        mBnView.setSelectedItemId(R.id.HomePage);
                        break;
                    case 2:
                        mBnView.setSelectedItemId(R.id.GroupPage);
                        break;
                    case 3:
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
                    case R.id.FilePage:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.HomePage:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.GroupPage:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.MyPage:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    public BasePresenter getPresenterInstance() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void responseError(Object o, Throwable throwable) {

    }

    @Override
    public void onClick(View v) {

    }
}