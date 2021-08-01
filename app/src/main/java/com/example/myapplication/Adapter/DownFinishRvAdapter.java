package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBean.FileDownData;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/8/1 16:36
 */
public class DownFinishRvAdapter extends RecyclerView.Adapter<DownFinishRvAdapter.DownFinishViewHolder> {

    List<FileDownData> mData;

    public DownFinishRvAdapter(List<FileDownData> finishData) {
        this.mData = finishData;
    }

    @NonNull
    @NotNull
    @Override
    public DownFinishViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trans_finish_item,parent,false);
        DownFinishViewHolder holder = new DownFinishViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DownFinishViewHolder holder, int position) {
        FileDownData data = mData.get(position);
        holder.fileNameTv.setText(data.getDocname());
        holder.fileIv.setImageResource(R.drawable.file);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class DownFinishViewHolder extends RecyclerView.ViewHolder{

        ImageView fileIv;
        TextView fileNameTv,timeTv,sizeTv;

        public DownFinishViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            fileIv = itemView.findViewById(R.id.finish_file_iv);
            fileNameTv = itemView.findViewById(R.id.finish_file_namr_iv);
            timeTv = itemView.findViewById(R.id.finfish_time_tv);
            sizeTv = itemView.findViewById(R.id.finish_size_tv);
        }
    }
}
