package com.example.myapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.GroupData;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/08/01/17:03
 * @Description:
 */
public class GroupInfoAdapter extends ArrayAdapter<GroupData> {
    private int resourceId;
    public GroupInfoAdapter(@NonNull Context context, int resource, ArrayList<GroupData> list) {
        super(context, resource, list);
        this.resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GroupData groupData=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView textView=view.findViewById(R.id.group_item_tv);
        textView.setText(groupData.getGroupName());
        TextView textView1=view.findViewById(R.id.group_btn);
        return view;
    }
}
