package com.example.myapplication.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.InitPageAdapter;
import com.example.myapplication.Model.GroupData;
import com.example.myapplication.Model.MemberData;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<GroupData> mGroupList = new ArrayList<>();
    private ArrayList<MemberData> mMemberList = new ArrayList<>();
    private ArrayList<Integer> listId = new ArrayList<>();
    private int ID;
    private String jwt;
    private Button button;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        mTabLayout = this.findViewById(R.id.group_tab_layout);
        mViewPager = this.findViewById(R.id.group_view_pager);
        button=this.findViewById(R.id.group_act_my_back_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ID = getIntent().getIntExtra("ID", -1);//接受用户的id
        jwt = getIntent().getStringExtra("jwt");
        initView();
//        skip1();
    }

    private void skip1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = new FormBody.Builder().add("userid", String.valueOf(ID)).build();
                Request request = new Request.Builder().addHeader("Authorization", jwt).addHeader("userid", String.valueOf(ID)).url("http://39.98.41.126:31109/group/findGroupByUid").post(requestBody).build();
                OkHttpClient okHttpClient = new OkHttpClient();
                try {
                    Response execute = okHttpClient.newCall(request).execute();
                    String res = execute.body().string();
                    JSONObject jsonObject = new JSONObject(res);
                    Boolean flag = jsonObject.getBoolean("flag");
                    if (flag) {
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String groupName = jsonObject1.getString("groupname");
                            Log.d("==============",groupName);
                            int groupId=jsonObject1.getInt("id");
                            GroupData groupData=new GroupData(groupId,groupName);
                            mGroupList.add(groupData);
                        }
                        skip();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void skip() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mGroupList.size(); i++) {
                    String groupName=mGroupList.get(i).getGroupName();
                    RequestBody requestBody = new FormBody.Builder().add("userid", String.valueOf(ID)).add("groupid", String.valueOf(mGroupList.get(i).getGroupId())).build();
                    Request request = new Request.Builder().addHeader("Authorization", jwt).addHeader("userid", String.valueOf(ID)).url("http://39.98.41.126:31109/group/findAllByGid").post(requestBody).build();
                    OkHttpClient okHttpClient = new OkHttpClient();
                    try {
                        Response execute = okHttpClient.newCall(request).execute();
                        String res=execute.body().string();
                        JSONObject jsonObject=new JSONObject(res);
                        Boolean flag=jsonObject.getBoolean("flag");
                        if(flag){
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i1 = 0; i1 < jsonArray.length(); i1++) {
                                JSONObject jsonObject1=jsonArray.getJSONObject(i1);
                                String nickname=jsonObject1.getString("nickname");
                                MemberData memberData=new MemberData(groupName,nickname);
                                mMemberList.add(memberData);
                            }
                            initView();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTabLayout.addTab(mTabLayout.newTab().setText("我的分组"));
                mTabLayout.addTab(mTabLayout.newTab().setText("成员列表"));
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
        });

    }
}

