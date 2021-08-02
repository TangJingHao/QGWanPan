package com.example.myapplication.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.GroupInfoAdapter;
import com.example.myapplication.DataBean.GroupMemberBean;
import com.example.myapplication.Model.GroupData;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;
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
 * @Date: 2021/08/01/17:49
 * @Description:
 */
public class GroupingFragment extends Fragment {
    private int ID;
    private String jwt;
    private ArrayList<String> listName=new ArrayList<>();
    private ArrayList<Integer> listId=new ArrayList<>();
    private ArrayList<GroupMemberBean> list=new ArrayList<>();
    private ArrayList<GroupData> mGroupList = new ArrayList<>();

    public GroupingFragment(int ID, String jwt) {
        this.ID = ID;
        this.jwt = jwt;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grouping,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GroupInfoAdapter groupInfoAdapter =new GroupInfoAdapter(getContext(),R.layout.group_item,mGroupList);
                ListView listView=(ListView)getView().findViewById(R.id.group_name_lv);
                listView.setAdapter(groupInfoAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        GroupData groupData = mGroupList.get(i);
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        View view1=View.inflate(getContext(),R.layout.alter_dialog_group_item,null);
                        EditText userId=(EditText)view1.findViewById(R.id.alter_id_et);
                        EditText groupName=(EditText)view1.findViewById(R.id.alter_group_name_et);
                        Button sureBtn=(Button)view1.findViewById(R.id.alter_sure_btn);
                        Button falseBtn=(Button)view1.findViewById(R.id.alter_false_btn);
                        builder.setView(view1);
                        AlertDialog dialog=builder.create();
                        falseBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        sureBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RequestBody requestBody = new FormBody.Builder().add("groupid", String.valueOf(groupData.getGroupId())).add("userid",userId.getText().toString()).build();
                                        Request request = new Request.Builder().addHeader("Authorization", jwt).addHeader("userid", String.valueOf(ID)).url("http://39.98.41.126:31109/group/saveUserToGroup").post(requestBody).build();
                                        OkHttpClient okHttpClient = new OkHttpClient();
                                        try {
                                            Response execute = okHttpClient.newCall(request).execute();
                                            String res=execute.body().string();
                                            JSONObject jsonObject=new JSONObject(res);
                                            Boolean flag=jsonObject.getBoolean("flag");
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if(flag){
                                                        Toast.makeText(getContext(),"成功添加",Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    }
                                                }
                                            });
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }
                        });
                        dialog.show();
                    }
                });
            }
        });
    }
}
