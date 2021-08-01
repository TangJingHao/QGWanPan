package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.R;
import com.example.myapplication.DataBean.FileBean;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.MyViewHolder> {

    private List<FileDataBean> files;
    private String string;
    public FileListAdapter(String string){
        this.string = string;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fileitem,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        /*holder.filename.setText(files.get(position).getId());
        holder.date.setText(files.get(position).getDate());*/
        holder.filename.setText(string);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView filename;
        TextView date;
        CheckBox select;

        MyViewHolder(View view){
            super(view);
            filename = view .findViewById(R.id.fileItem_tv_filename);
            date = view.findViewById(R.id.fileItem_tv_date);
            select = view.findViewById(R.id.fileitem_cb_select);
        }
    }
}
