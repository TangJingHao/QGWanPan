package com.example.myapplication.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class DownDoingRvAdapter extends RecyclerView.Adapter<DownDoingRvAdapter.DownDoingViewHolder> {

    List<FileDownData> mData;
    private Boolean isDowning = false;

    public DownDoingRvAdapter(List<FileDownData> doingData) {
        this.mData = doingData;
    }

    @NonNull
    @NotNull
    @Override
    public DownDoingViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trans_doing_item,parent,false);
        DownDoingViewHolder holder = new DownDoingViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DownDoingViewHolder holder, int position) {
        FileDownData data = mData.get(position);
        holder.fileNameTv.setText(data.getDocname());
        holder.fileIv.setImageResource(R.drawable.file);
        if(isDowning){
            holder.statusTv.setText("正在下载");
            holder.statusIv.setBackgroundResource(R.mipmap.trans_pause_ic);
        }else {
            holder.statusTv.setText("已暂停");
            holder.statusIv.setBackgroundResource(R.mipmap.trans_start_ic);
        }
        holder.statusIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDowning){
                    holder.statusIv.setBackgroundResource(R.mipmap.trans_start_ic);
                }else {
                    holder.statusIv.setBackgroundResource(R.mipmap.trans_pause_ic);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class DownDoingViewHolder extends RecyclerView.ViewHolder{

        ImageView fileIv,statusIv;
        ProgressBar progressBar;
        TextView fileNameTv,statusTv;
        public DownDoingViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            fileIv = itemView.findViewById(R.id.doing_file_iv);
            progressBar = itemView.findViewById(R.id.doing_progressBar);
            fileNameTv = itemView.findViewById(R.id.doing_file_name_tv);
            statusTv = itemView.findViewById(R.id.doing_status_tv);
            statusIv = itemView.findViewById(R.id.doing_status_iv);
        }
    }
}
