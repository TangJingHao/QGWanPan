package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.DataBean.GroupMemberBean;
import com.example.myapplication.R;

import org.w3c.dom.Text;

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
        TextView memberName=(TextView)view.findViewById(R.id.member_information_tv);
        TextView groupName=(TextView)view.findViewById(R.id.member_group_tv);
        TextView permissionName=(TextView)view.findViewById(R.id.member_permission_tv);
        memberName.setText(memberName.getText().toString());
        groupName.setText(groupName.getText().toString());
        permissionName.setText(permissionName.getText().toString());
        return view;
    }
}
