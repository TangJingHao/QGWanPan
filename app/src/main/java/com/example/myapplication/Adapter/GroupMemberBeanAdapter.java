package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.DataBean.GroupMemberBean;

import java.util.ArrayList;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/08/01/19:23
 * @Description:
 */
public class GroupMemberBeanAdapter extends ArrayAdapter<GroupMemberBean> {
    private int resourceId;
    public GroupMemberBeanAdapter(Context context, int textViewResourceId,ArrayList<GroupMemberBean> list){
        super(context,textViewResourceId,list);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GroupMemberBean groupMemberBean=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        return super.getView(position, convertView, parent);
    }
}
